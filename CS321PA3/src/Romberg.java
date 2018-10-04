//Name: Austin Williams
//Course: CS321 Intro to Numerical Methods
//Instructor: Dr. Qiang Ye
//Assignment: Programming Assignment 3
//Due Date: 13 NOV 2017

import java.util.*;

public class Romberg {
	static final double P = 5;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double a, b, h, sum;					//declaring all the variables
		
		System.out.println("Input a: ");		//defining and initializing input variables
		a = input.nextDouble();
		
		System.out.println("Input b: ");
		b = input.nextDouble();
		
		System.out.println("Input n: ");
		int n = input.nextInt();
		
		double romb[][] = new double[n + 1][n + 1];  //initializing a 2-dimensional array to contain Romberg values
		
		//testFill(romb);
		//print(romb);
		
		h = b - a;
		
		romb[0][0] = (h/2)*(func(a, P)+func(b, P));
		
		for (int i = 1; i < romb.length; i++) {
			h = h/2;
			sum = 0;
			for (int j = 1; j < Math.pow(2, 1); j += 2) {
				sum = sum + func(a+j*h, P);
			}
			romb[i][0] = (1/2)*romb[i-1][0] + (sum)*h;
			for (int k = 1; k <= i; k++) {
				romb[i][k] = romb[i][k-1] + (romb[i][k-1] - romb[i-1][k-1])/(Math.pow(4, k) - 1); 
			}
		}
		System.out.println("Approximation: ");
		print(romb);
		System.out.println("\nError: ");
		printError(romb);
	}
	
	public static double func(double x, double p) {
		double result = 0;
		result = Math.pow(x, p + 0.5);
		return result;
	}
	
	public static void print(double[][] arr) { //print the contents of the array
		System.out.print(" |");
		for (int i = 0; i < arr.length; i++) { //print header for each column
			String yo = Integer.toString(i);
			yo = centStr(yo, '_');
			System.out.printf("%-25s|", yo);
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < arr[i].length; j++) {
				String val = "";
				if (arr[i][j] != 0.0) {
					val = Double.toString(arr[i][j]);
				}
				else {
					val = centStr(val, 'X');
				}
				System.out.printf("%-25s|", val);
			}
			System.out.println();
			
		}
	}
	
	public static void printError(double[][] arr) {
		calcErr(arr);
		System.out.print("  |");
		for (int i = 0; i < arr.length; i++) {
			String yo = Integer.toString(i);
			yo = centStr(yo, '_');
			System.out.printf("%-25s|", yo);
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < arr[i].length; j++) {
				String val = "";
				if (arr[i][j] != 0.0) {
					val = Double.toString(arr[i][j]);
				}
				else {
					val = centStr(val, 'X');
				}
				System.out.printf("%-25s|", val);
			}
			System.out.println();	
		}
	}
	
	public static void calcErr(double[][] arr) {
		for (double[] subarr: arr) {
			for (int i = 0; i < subarr.length; i++) {
				subarr[i] = (1/(P + (3/2))) - subarr[i];
			}
		}
	}
	
	public static void testFill(double[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = i + j;
			}
		}
	}
	
	public static String centStr(String str, char paddingChar) { //This function was modified from one I found on StackOverflow
	    if (str == null) {
	        throw new NullPointerException("Can not center a null String!");
	    }

	    int maxPadding = 25; //This is the string length to be filled with padding
	    int length = str.length();
	    int padding = (maxPadding - length) / 2;//decide left and right padding
	    if (padding <= 0) {
	        return str;// return actual String if padding is less than or equal to 0
	    }

	    String empty = "", hash = "#";//hash is used as a place holder

	    // extra character in case of String with even length
	    int extra = (length % 2 == 0) ? 1 : 0;

	    String leftPadding = "%" + padding + "s";
	    String rightPadding = "%" + (padding - extra) + "s";

	    String strFormat = leftPadding + "%s" + rightPadding;
	    String formattedString = String.format(strFormat, empty, hash, empty);

	    //Replace space with * and hash with provided String
	    String centeredString = formattedString.replace(' ', paddingChar).replace(hash, str);
	    return centeredString;
	}
}
