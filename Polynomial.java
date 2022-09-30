import java.util.Arrays;
import java.io.*;
public class Polynomial{
    double[] coeff;
    int [] exp;

    public Polynomial(){
        coeff = new double[1];
        exp = new int[1];
    }
    
    public Polynomial(double[] arr1, int[] arr2){
        coeff = new double[arr1.length];
        exp = new int[arr2.length];
        for(int i = 0; i < arr1.length; i++) {
            coeff[i] = arr1[i];
            exp[i] = arr2[i];
        }
    }
    
    public Polynomial(File f) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader(f));
        String line = input.readLine();
        String[] arr = line.split("((?=-)|[+])");

        coeff = new double[arr.length];
        exp = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
        	if (!arr[i].contains("x")) { // no x, means that it is just coefficient
        		exp[i] = 0;
        		coeff[i] = Double.parseDouble(arr[i]);
        	}
        	else if ((arr[i].contains("x")) && (arr[i].indexOf("x") == (arr[i].length()) -1)) { // only x
        		int ind = arr[i].indexOf("x");
        		exp[i] = 1;
        		if (ind == 0) { // doesn't say a 1
        			coeff[i] = 1;
        		}
        		else {
        			coeff[i] = Double.parseDouble(arr[i].substring(0, ind));
        		}
        	}
        	else { // contains x to the power of something
        		int ind = arr[i].indexOf("x");
        		if (ind == 0) { // doesn't say a 1
        			coeff[i] = 1;
        		}
        		else {
        			coeff[i] = Double.parseDouble(arr[i].substring(0, ind));
        		}
        		exp[i] = Integer.parseInt(arr[i].substring(ind+1));
        	}
        }
    }
    
    private int findElement(int[] arr, int element){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }
    
    public Polynomial add(Polynomial temp){
        int[] arr1 = Arrays.copyOf(exp, exp.length);
        Arrays.sort(arr1);
        int[] arr2 = Arrays.copyOf(temp.exp, temp.exp.length);
        Arrays.sort(arr2);

        int max;
        
        if (arr1[arr1.length - 1] > arr2[arr2.length - 1]) {
            max = arr1[arr1.length - 1];
        }
        else {
            max = arr2[arr2.length - 1];
        }

        int[] count = new int[max+1];
        int lengthExp = 0;
        for (int i = 0; i < max + 1; i++){
            // the exponent i is in both exp arrays
            if (((Arrays.binarySearch(arr1, i)) > -1) && ((Arrays.binarySearch(arr2, i)) > -1)) {
                count[i] = 3;
                lengthExp = lengthExp + 1;
            }
            // the exponent i is only in the exp array
            else if ((Arrays.binarySearch(arr1, i)) > -1) {
                count[i] = 2;
                lengthExp = lengthExp + 1;
            }
            // the exponent i is only in the temp.exp array
            else if ((Arrays.binarySearch(arr2, i)) > -1) {
                count[i] = 1;
                lengthExp = lengthExp + 1;
            }
            // the exponent i is in neither array
            else {
                count[i] = 0;
            }
        }


        Polynomial other = new Polynomial();
        other.exp = new int[lengthExp];
        other.coeff = new double[lengthExp];
        
        int n = 0;
        for (int i = 0; i < max + 1; i++) { // i might not reach the element
            if(count[i] == 3) {
                // find the exp in exp, corresponding index is coeff
                other.exp[n] = i;
                other.coeff[n] = coeff[findElement(exp, i)] + temp.coeff[findElement(temp.exp, i)];
                n = n + 1;
            }
            else if (count[i] == 2) { // only in exp
                other.exp[n] = i;
                other.coeff[n] = coeff[findElement(exp, i)];
                n = n + 1;
            }
            else if (count[i] == 1) { // only in temp.exp
                other.exp[n] = i;
                other.coeff[n] = temp.coeff[findElement(temp.exp, i)];
                n = n + 1;
            }
        }
        return other;
    }
    
    public double evaluate(double x) {
        double ret = 0;
        for (int i = 0; i < exp.length; i++) {
            ret = ret + (coeff[i]*(Math.pow(x, exp[i]))); // coeff[i] * x^exp[i]
        }
        return ret;
    }
    
    public boolean hasRoot(int root) {
        return (evaluate(root) == 0);
    }
    public Polynomial multiply(Polynomial mult) {
        Polynomial other = new Polynomial();

            for (int i = 0; i < exp.length; i++) {
                Polynomial temp = new Polynomial();
                temp.exp = new int[mult.exp.length];
                temp.coeff = new double[mult.exp.length];
                for (int j = 0; j < mult.exp.length; j++) {
                    temp.exp[j] = exp[i]+mult.exp[j];
                    temp.coeff[j] = coeff[i]*mult.coeff[j];
                }
                other = other.add(temp);
            }
            
        return other;

    }
    
    private String[] convertToString(double [] arr){
        String[] strArray = new String[arr.length];
 
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && i != 0) {
                strArray[i] = "+"+String.valueOf(arr[i]);
            }
            else {
                strArray[i] = String.valueOf(arr[i]);
            }
        }
 
        return strArray;
    }

    public void saveToFile(String fileName) throws Exception{
        String temp = "";
        double [] arr1 = Arrays.copyOf(coeff, coeff.length);
        String [] strCoeff = convertToString(arr1);

        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == 0) { // no exponent, just coefficient
                temp = temp + strCoeff[i];
            }
            else if (exp[i] == 0) { // only 1 x
                temp = temp + strCoeff[i] + "x";
            }
            else { // exp > 1
                temp = temp + strCoeff[i] + "x" + Integer.toString(exp[i]);
            }
        }
        PrintStream output = new PrintStream(fileName);
        output.print(temp);
    }
    
}