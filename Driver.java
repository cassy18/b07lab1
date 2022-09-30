import java.io.*;
public class Driver { 
	public static void main(String [] args) throws Exception{ 
		System.out.println("~~Test for functions of the same size and read from file~~");
		double [] c1 = {4, 1, 8};
		int [] ex1 = {3, 1, 0};
		Polynomial p1 = new Polynomial(c1, ex1);
		File f = new File("/Users/macbookair/Desktop/poly.txt");
		Polynomial po = new Polynomial(f);
		
		System.out.println("Test for add function");
		Polynomial s = po.add(p1);
		for (int i = 0; i < s.exp.length; i++) {
			if (i == 0 || s.coeff[i] < 0)
			System.out.print(s.coeff[i]+"x^"+s.exp[i] + " ");
			else System.out.print("+" + s.coeff[i]+"x^"+s.exp[i] + " ");
		}
		System.out.println("\nTest for evaluate function");
		System.out.print(s.evaluate(2));
		System.out.println("\nTest for hasRoot function");
		System.out.print(s.hasRoot(0));
		System.out.println("\nTest for multiply function");
		Polynomial m1 = p1.multiply(po);
		for (int i = 0; i < m1.exp.length; i++) {
			if (i == 0 || m1.coeff[i] < 0)
			System.out.print(m1.coeff[i]+"x^"+m1.exp[i] + " ");
			else System.out.print("+" + m1.coeff[i]+"x^"+m1.exp[i] + " ");
		}
		System.out.println("");
		
		System.out.println("~~Test for functions of different size and different orders");
		double [] c2 = {5, -1, 2, 7};
		int [] ex2 = {0, 2, 4, 7};
		double [] c3 = {6, 9, -5};
		int [] ex3 = {0, 9, 7};
		Polynomial p2 = new Polynomial(c2, ex2);
		Polynomial p3 = new Polynomial(c3, ex3);
		
		System.out.println("Test for add function");
		Polynomial s1 = p2.add(p3);
		for (int i = 0; i < s1.exp.length; i++) {
			if (i == 0 || s1.coeff[i] < 0)
			System.out.print(s1.coeff[i]+"x^"+s1.exp[i] + " ");
			else System.out.print("+" + s1.coeff[i]+"x^"+s1.exp[i] + " ");
		}
		System.out.println("\nTest for evaluate function");
		System.out.print(s1.evaluate(2));
		System.out.println("\nTest for hasRoot function");
		System.out.print(s1.hasRoot(0));
		System.out.println("\nTest for multiply function");
		Polynomial m2 = p2.multiply(p3);
		for (int i = 0; i < m2.exp.length; i++) {
			if (i == 0 || m2.coeff[i] < 0)
			System.out.print(m2.coeff[i]+"x^"+m2.exp[i] + " ");
			else System.out.print("+" + m2.coeff[i]+"x^"+m2.exp[i] + " ");
		}
		System.out.println("");
		
		s1.saveToFile("/Users/macbookair/Desktop/toFile.txt");
		
	}  
} 