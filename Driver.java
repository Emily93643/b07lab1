public class Driver {
	public static void main(String [] args) {
	
		double[] c1 = {6,0,0,5};
		int[] e1 = {0, 1, 2, 3};

		double[] c2 = {0,-2,0,0,-9};
		int[] e2 = {0, 1, 2, 3,4};
		Polynomial p1 = new Polynomial(c1, e1);
		Polynomial p2 = new Polynomial(c2, e2);
		//printPoly(p2);

		printPoly(p1.add(p2));

		
		// Polynomial p = new Polynomial();
		// System.out.println(p.evaluate(3));
		// double [] c1 = {6,0,0,5};
		// Polynomial p1 = new Polynomial(c1);
		// double [] c2 = {0,-2,0,0,-9};
		// Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		// System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
	}

	public static void printPoly(Polynomial poly) {
		double[] coefficients = poly.getCoefficients();
		int[] exponents = poly.getExponents();

		for (int i =0; i < coefficients.length - 1; i++){
			System.out.print(coefficients[i] + "x^" + exponents[i] + " + ");
		}
		System.out.println(coefficients[coefficients.length-1] + "x^" + exponents[coefficients.length-1]);
	}
	
}