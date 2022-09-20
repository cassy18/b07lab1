public class Polynomial{
    double[] poly;
    
    public Polynomial() {
        poly = new double[1];
    }
    
    public Polynomial(double[] arr) {
        poly = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            poly[i] = arr[i];
        }
    }
    
    public Polynomial add(Polynomial temp) {
        int minlen = Math.min(temp.poly.length, poly.length);
        int maxlen = Math.max(temp.poly.length, poly.length);

        Polynomial other = new Polynomial();
        other.poly = new double[maxlen];
        
        for (int i = 0; i < minlen; i++) {
            other.poly[i] = poly[i] + temp.poly[i];
        }
        
        for (int i = minlen; i < maxlen; i++) {
            if (poly.length > temp.poly.length) {
                other.poly[i] = poly[i];
            }
            else other.poly[i] = temp.poly[i];
        }
        return other;
    }
    
    public double evaluate(double x) {
        double ret = 0;
        for (int i = 0; i < poly.length; i++) {
            ret = ret + (poly[i]*(Math.pow(x, i)));
        }
        return ret;
    }
    
    public boolean hasRoot(int root) {
        return (evaluate(root) == 0);
    }

}