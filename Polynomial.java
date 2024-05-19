public class Polynomial {
	//i Field
	double [] coefficients;
	
	//ii
	public Polynomial() {
		//this.coefficients = new double[]{0};
		this.coefficients = new double[1];
		this.coefficients[0] = 0;
	}

	//iii
	public Polynomial(double [] arr) {
		int len = arr.length;
		this.coefficients = new double[len];
		for(int i = 0; i < len; i++) {
			this.coefficients[i] = arr[i];
		}
	}

	//iv
	public Polynomial add(Polynomial poly) {
		int maxLen = Math.max(this.coefficients.length, poly.coefficients.length);
        double[] resultCoefficients = new double[maxLen];

		for (int i = 0; i < maxLen; i++){
			double thisCoeff = 0;
			double otherCoeff = 0;

			if (i < this.coefficients.length) {
				thisCoeff = this.coefficients[i];
			}

			if (i < poly.coefficients.length) {
				otherCoeff = poly.coefficients[i];
			}

			resultCoefficients[i] = thisCoeff + otherCoeff;
		}

		Polynomial result = new Polynomial(resultCoefficients);
		return result;
	}

	//v
	public double evaluate(double x) {
		double ans = 0;
		double pow = 1;

		for(int i = 0; i < coefficients.length; i++) {
			ans += (coefficients[i]*pow);
			pow *= x;
		}

		return ans;
	}

	//vi
	public Boolean hasRoot(double root) {
		return evaluate(root) == 0;
	}
}