//Name: Austin J Williams
//Class: CS315
//Professor: Dr. Jaromczyk
//Date: 02/07/2017

import java.util.*;
import java.io.*;
import java.lang.*;

public class mystery_seq_memoized {

	public static void main(String[] args) throws IOException {
		//boolean print_timing = (args.length == 1 && args[0].equals("-t"));;
		//reading input number
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long n = Integer.parseInt(in.readLine());
		in.close();
		long output = 0;
		
		//look up table for memoization
		long arr[] = {1, 1, 3, 17, 171, 3113, 106419, 7035649, 915028347};
		
		
		long start_time, time_elapsed;
		//running recursive function
		start_time = System.currentTimeMillis();
		output = memoSeq(n, arr);
		time_elapsed = System.currentTimeMillis() - start_time;
		output = output % 1000;
		
		System.out.println("Memoized sequence of " + n + " = " + output);
		//if (print_timing) {
			System.out.println("Time of completion: " + time_elapsed);
		//}
	}
	
	public static long memoSeq(long input, long arr[]) {
		long n = input - 1;
		if (n == -1) {
			return 1;
		}
		else if (n < 8) { //table look up for memoization
			return arr[(int)input];
		}
		else {
			long output = 0;
			long i = 0;
			do {
				//recursive step
				output += (long)Math.pow((double)2, (double)i) * memoSeq(i, arr) * memoSeq(n-i, arr);
				i++;
			} while (i <= n); //Summation includes upper limit, therefore "<=" is the while condition
			return output;
		}
	}

}
