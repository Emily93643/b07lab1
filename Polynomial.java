import java.io.*;
import java.util.*;

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

	public Polynomial(File file) throws FileNotFoundException{
		Scanner scanner = new Scanner(file);
        if (!scanner.hasNextLine()) {
            scanner.close();
            throw new IllegalArgumentException("File is empty");
        }

        String line = scanner.nextLine();
        scanner.close();

        String[] terms = line.split("(?=[+-])"); // Split the line into terms

        double[] tempCoefficients = new double[terms.length];
        int[] tempExponents = new int[terms.length];
        int index = 0;

        for (String term : terms) {
            String[] parts = term.split("x");
            double coefficient;
            int exponent = 0;

			//test this following line
            if (parts[0].isEmpty()) {
                coefficient = 1.0;
            } else if (parts[0].equals("-")) {
                coefficient = -1.0;
            } else {
                coefficient = Double.parseDouble(parts[0]);
            }

            if (parts.length > 1 && !parts[1].isEmpty()) {
                exponent = Integer.parseInt(parts[1]);
            }

			if (coefficient == 0) {
				continue;
			}
			
            tempCoefficients[index] = coefficient;
            tempExponents[index] = exponent;
            index++;
        }

		if (index == 0){
			coefficients = null;
			exponents = null;
			System.out.println("0!");
		} else {
			coefficients = new double[index];
			exponents = new int[index];
			System.arraycopy(tempCoefficients, 0, coefficients, 0, index);
			System.arraycopy(tempExponents, 0, exponents, 0, index);
		}
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
		if (this.coefficients == null && this.exponents == null){
			return poly;
		} else if (poly.coefficients == null && poly.exponents == null){
			Polynomial result = new Polynomial(this.coefficients, this.exponents);
			return result;
		}

    	int maxLen = this.coefficients.length + poly.coefficients.length;
        double[] resultCoefficients = new double[maxLen];
        int[] resultExponents = new int[maxLen];
        int[] addedPoly = new int[poly.exponents.length];
        int[] addedThis = new int[this.exponents.length];

		//Add if the exponent term is same 
		int index = 0;
		for(int i = 0; i < this.coefficients.length; i++){
			for(int j = 0; j < poly.coefficients.length; j++){
				if(this.exponents[i] == poly.exponents[j]){
					double sum = this.coefficients[i] + poly.coefficients[j];
					if (sum != 0) {
						resultCoefficients[index] = sum;
						resultExponents[index] = this.exponents[i];
						index++;
					}
					
					addedPoly[j] = 1;
					addedThis[i] = 1;
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
		if (this.coefficients == null && this.exponents == null){
			return 0;
		}

		double ans = 0;
	
		for (int i = 0; i < coefficients.length; i++) {
			ans += coefficients[i] * Math.pow(x, exponents[i]);
		}
	
		return ans;
	}

	//vi
	public Boolean hasRoot(double root) {
		if (this.coefficients == null && this.exponents == null){
			return true;
		}
		return evaluate(root) == 0;
	}

	public Polynomial multiply(Polynomial poly) {
		if (this.coefficients == null || this.exponents == null || poly.coefficients == null || poly.exponents == null) {
			return new Polynomial(); 
		}

		int maxLen = this.coefficients.length * poly.coefficients.length;
		double[] tempCoefficients = new double[maxLen];
		int[] tempExponents = new int[maxLen];
	
		int index = 0;
	
		for (int i = 0; i < this.coefficients.length; i++) {
			for (int j = 0; j < poly.coefficients.length; j++) {
				double coeffProduct = this.coefficients[i] * poly.coefficients[j];
				int expSum = this.exponents[i] + poly.exponents[j];
				
				// Combine terms with the same exponents, if found same
				boolean found = false;
				for (int k = 0; k < index; k++) {
					if (tempExponents[k] == expSum) {
						tempCoefficients[k] += coeffProduct;
						found = true;
						break;
					}
				}
				// if not found same, add to array
				if (!found) {
					tempCoefficients[index] = coeffProduct;
					tempExponents[index] = expSum;
					index++;
				}
			}
		}

		
		// non-zero terms only
		int j = 0;
		for (int i = 0; i < index; i++) {
			if (tempCoefficients[i] != 0) {
				tempCoefficients[j] = tempCoefficients[i];
				tempExponents[j] = tempExponents[i];
				j++;
			}
		}

		double[] resultCoefficients = Arrays.copyOf(tempCoefficients, j);
		int[] resultExponents = Arrays.copyOf(tempExponents, j);
		return new Polynomial(resultCoefficients, resultExponents);
	}

	public void saveToFile(String fileName) throws IOException {
		FileWriter writer = new FileWriter(fileName);
		StringBuilder polynomialBuilder = new StringBuilder();

		for (int i = 0; i < coefficients.length; i++) {
			// Append the coefficient not zero
			if (coefficients[i] != 0) {
				//positive
				if (coefficients[i] > 0 && i != 0) {
					polynomialBuilder.append("+");
				}
				//negative
				polynomialBuilder.append(coefficients[i]);
			} else {
				continue; // Skip if coefficient is zero
			}

			// Append the exponent
			if (exponents[i] > 0) {
				polynomialBuilder.append("x");
				if (exponents[i] != 1) {
					polynomialBuilder.append(exponents[i]);
				}
			}
		}

		// Write the polynomial to the file
		writer.write(polynomialBuilder.toString());
		writer.close();
	}
}