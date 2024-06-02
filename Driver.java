import java.io.*;

public class Driver {
	public static void main(String [] args) {
	
		// double[] c1 = {1,1};
		// int[] e1 = {0, 1};

		// double[] c2 = {-1,1};
		// int[] e2 = {0, 1};
		// Polynomial p1 = new Polynomial(c1, e1);
		// Polynomial p2 = new Polynomial(c2, e2);
		// Polynomial p3 = new Polynomial(new double[]{5,-3,7}, new int[]{1,2,8});

		//printPoly(p2);

		// printPoly(p1.add(p2));
		// printPoly(p1.multiply(p2));
		

		// // Polynomial p = new Polynomial();
		// // System.out.println(p.evaluate(3));
		// // double [] c1 = {6,0,0,5};
		// // Polynomial p1 = new Polynomial(c1);
		// // double [] c2 = {0,-2,0,0,-9};
		// // Polynomial p2 = new Polynomial(c2);
		// Polynomial s = p1.add(p2);
		// System.out.println("s(0.1) = " + s.evaluate(0.1));
		// if(s.hasRoot(1))
		// 	System.out.println("1 is a root of s");
		// else
		// 	System.out.println("1 is not a root of s");

		// printPoly(p3);

		//Testing File constructor
		try {
            File file = new File("a.txt");
            Polynomial poly = new Polynomial(file);
			if (poly.coefficients == null){
				System.out.println("Null");
			} else {
				printPoly(poly);
			}
            System.out.println("Coefficients: " + java.util.Arrays.toString(poly.coefficients));
            System.out.println("Exponents: " + java.util.Arrays.toString(poly.exponents));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

		// //Testing saveToFile
		// try {
		// 	p3.saveToFile("b");
		// } catch(IOException e){
        //     e.printStackTrace();
		// }
	}

	public static void printPoly(Polynomial poly) {
		double[] coefficients = poly.getCoefficients();
		int[] exponents = poly.getExponents();
		if (coefficients == null && exponents == null ){
			System.out.println("NULL");
		} else{
		for (int i =0; i < coefficients.length - 1; i++){
			System.out.print(coefficients[i] + "x^" + exponents[i] + " + ");
		}
		System.out.println(coefficients[coefficients.length-1] + "x^" + exponents[coefficients.length-1]);
		}
	}
	
}