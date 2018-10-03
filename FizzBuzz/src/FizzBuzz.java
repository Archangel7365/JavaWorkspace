
public class FizzBuzz {

	public static void main(String[] args) {
		int div1 = 3;
		int div2 = 5;
		int loopLength = 100;
		
		for (int i = 1; i <= loopLength; i++) {
			String output = "";
			boolean mult = false;
			if (i % div1 == 0) {
				output += "Fizz";
				mult = true;
			}
			if (i % div2 == 0) {
				output += "Buzz";
				mult = true;
			}
			if (mult == true) {
				System.out.println(output);
			}
			else { 
				System.out.println(i);
			}
		}
	}
}
