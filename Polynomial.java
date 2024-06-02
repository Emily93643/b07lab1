import java.util.Arrays;

public class Polynomial {
	//i Field
	double [] coefficients;
	int [] exponents;
	
	//ii
	public Polynomial() {
		// this.coefficients = new double[1];
		// this.coefficients[0] = 0;
		this.coefficients = null;
		this.exponents = null;
	}

	public Polynomial(double [] co){
		this.coefficients = co;
	}

	//iii
	public Polynomial(double [] coefficients, int [] exponents){
		int co_len = coefficients.length;
		int ex_len = exponents.length;

		if (co_len != ex_len){
			throw new IllegalArgumentException("The lengths of coefficients and exponents arrays must be the same.");
		}
		else{
			this.coefficients = coefficients;
			this.exponents = exponents;
		}
		

	}

	//iv
	//No redundent, all unique in exponent
	public Polynomial add(Polynomial poly) {
       int maxLen = this.coefficients.length + poly.coefficients.length;
        double[] resultCoefficients = new double[maxLen];
        int[] resultExponents = new int[maxLen];
        int[] addedPoly = new int[poly.exponents.length];
        int[] addedThis = new int[this.exponents.length];

		//Add if the exponent term is same STILL NNEED TO DO THE ZERO THING
		int index = 0;
		for(int i = 0; i < this.coefficients.length; i++){
			for(int j = 0; j < poly.coefficients.length; j++){
				if(this.exponents[i] == poly.exponents[j]){
					resultCoefficients[index] = this.coefficients[i] + poly.coefficients[j];
					resultExponents[index] = this.exponents[i];

					addedPoly[j] = 1;
					addedThis[i] = 1;
					index++;
				}
			}
		}

		// Add terms from 'poly' that were not already added
		for (int j = 0; j < poly.coefficients.length; j++) {
			if (addedPoly[j] == 0) {
				resultCoefficients[index] = poly.coefficients[j];
				resultExponents[index] = poly.exponents[j];
				index++;
			}
		}
		
		// Add terms from 'this' that were not already added
		for (int i = 0; i < this.coefficients.length; i++) {
			if (addedThis[i] == 0) {
				resultCoefficients[index] = this.coefficients[i];
				resultExponents[index] = this.exponents[i];
				index++;
			}
		}


        Polynomial result = new Polynomial(Arrays.copyOf(resultCoefficients, index), Arrays.copyOf(resultExponents, index));
        return result;
    } 

	//v
	public double evaluate(double x) {
		double ans = 0;
	
		for (int i = 0; i < coefficients.length; i++) {
			ans += coefficients[i] * Math.pow(x, exponents[i]);
		}
	
		return ans;
	}

	//vi
	public Boolean hasRoot(double root) {
		return evaluate(root) == 0;
	}

























	public double[] getCoefficients() {
		return coefficients;
	}
	
	public int[] getExponents() {
		return exponents;
	}
	
}