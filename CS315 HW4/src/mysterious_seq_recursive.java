//Name: Austin J Williams
//Class: CS315
//Professor: Dr. Jaromczyk
//Date: 02/07/2017

import java.util.*;
import java.io.*;
import java.lang.*;

public class mysterious_seq_recursive {

	private static final long MODULO = 1000;
	
	public static void main(String[] args) throws IOException {
		//Check command line arguments to print timing info
		//boolean print_timing = (args.length == 1 && args[0].equals("-t"));
		
		//Read input number
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long n = Integer.parseInt(in.readLine());
		long start_time, time_elapsed;
		in.close();
		
		//running recursive function
		start_time = System.currentTimeMillis();
		long output = sequence(n);
		time_elapsed = System.currentTimeMillis() - start_time;
		output = output % 1000;
		
		//printing results
		System.out.println("mystery sequence of " + n + " % " + MODULO + " = " + output);
		//if (print_timing) {
			System.out.println("Time of completion: " + time_elapsed);
		//}
	}
	
	public static long sequence(long input) {
		long n = input - 1;
		if (n == -1) {
			return 1;
		}
		else {
			long output = 0;
			long i = 0;
			do {
				//recursive step
				output += (int)Math.pow((double)2, (double)i) * sequence(i) * sequence(n-i);
				i++;
			} while (i <= n); //Summation includes upper limit, therefore "<=" is the while condition
			return output;
		}
	}

}
