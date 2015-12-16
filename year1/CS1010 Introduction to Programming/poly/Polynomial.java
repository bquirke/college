
public class Polynomial {
	private double[] coef;
	private double deg;
	
	public Polynomial (double [] c){
		coef = c; // coefficents..... cloned c into an array that is disected in add and multi methods
		deg = degree(); // degree of polynomial 
	}
	
	private Polynomial(double a, double b){ //for constructing polynomials constructed in this
		coef = new double[(int) (b+1)];     // class alone.
        coef[(int) b] = a;
        deg = degree();
		
	}
	
	public double degree() {
        double d = 0;
        for (int i = 0; i < coef.length; i++)
            if (coef[i] != 0) d = i;
        return d;
     // returns the degree of this polynomial
    }
	 public Polynomial add (Polynomial b) {
	        Polynomial a = this;
	        Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
	        for (int i = 0; i <= a.deg; i++){
	        	c.coef[i] += a.coef[i]; //adding correlated coefficients
	        }
	        for (int i = 0; i <= b.deg; i++){
	        	c.coef[i] += b.coef[i]; //likewise
	        }
	        c.deg = c.degree();
	        return c;
	    }
	 
	 public Polynomial multi(Polynomial b) {
	        Polynomial a = this;
	        Polynomial c = new Polynomial(0, a.deg + b.deg);
	        for (int i = 0; i <= a.deg; i++)
	            for (int j = 0; j <= b.deg; j++)
	                c.coef[i+j] += (a.coef[i] * b.coef[j]);
	        c.deg = c.degree();
	        return c;
	    }
	 
	 public String toString() {
	        if (deg ==  0) return "" + coef[0];
	        if (deg ==  1) return coef[1] + "x + " + coef[0];
	        String s = coef[(int) deg] + "x^" + deg;
	        for (int i = (int) (deg-1); i >= 0; i--) {
	            if      (coef[i] == 0) continue;
	            else if (coef[i]  > 0) s = s + " + " + ( coef[i]);
	            else if (coef[i]  < 0) s = s + " - " + (-coef[i]);
	            if      (i == 1) s = s + "x";
	            else if (i >  1) s = s + "x^" + i;
	        }
	        return s;
	    }

}
