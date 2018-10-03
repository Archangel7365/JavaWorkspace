import java.util.Scanner;

public class quadRoot {

	public static void main(String[] args) {
		
		Scanner quadin = new Scanner(System.in);
		
		System.out.print("Please input a base: ");	//Here is where the user inputs values and
		double a = quadin.nextDouble();				//their exponent, this allows for more robust
		System.out.print("Please input a exp: ");	//input.
		double a2 = quadin.nextDouble();
		
		System.out.print("Please input b base: ");
		double b = quadin.nextDouble();
		System.out.print("Please input b exp: ");
		double b2 = quadin.nextDouble();
		
		System.out.print("Please input c base: ");
		double c = quadin.nextDouble();
		System.out.print("Please input c exp: ");
		double c2 = quadin.nextDouble();
		
		if (a2 != 1) {
			a = Math.pow(a, a2);						//calculating input values from provided base and exponent
		}
		if (b2 != 1) {
			b = Math.pow(b, b2);						//calculating input values from provided base and exponent
		}
		if (c2 != 1) {
			c = Math.pow(c, c2);						//calculating input values from provided base and exponent
		}
		
		
		double root1 = posRoot(a,b,c);				//calculating solutions
		double root2 = negRoot(a,b,c);
		
		System.out.println("Root 1: " + root1 + "\nRoot 2: " + root2);	//printing result
		
	}

	public static double posRoot(double a, double b, double c) { //method to calculate the positive root
		double root = 0;
		if (a == 0) {
			return (-c/b);
		}
		else {
			root = (-b + Math.sqrt(Math.pow(a, 2) - (4*a*c)))/(2*a);
			return root;
		}
	}
	
	public static double negRoot(double a, double b, double c) { //method used to calculate the negative root
		double root = 0;
		if (a == 0) {
			return (-c/b);
		}
		root = (4*a*c)/(2*a*(-b + Math.sqrt(Math.pow(b, 2) - 4*a*c))); //eliminated subtraction in order to
		return root;													//reduce loss of significance
	}
	
}


