//import java.util.ArrayList;

public class ParserTester {

	public static void main(String[] args) {
		SATParser yoParse = new SATParser("src/yo.txt");
		//yoParse.getFile();
		try {
			int[][] input = yoParse.parseInput();
			int[][] counts = yoParse.countVals(input);
			for (int i = 0; i < input.length; i++) {
				System.out.print("Clause " + i + ": (");
				for (int j = 0; j < input[i].length; j++) {
					String output = "";
					if (j != input[i].length - 1) {
						output = input[i][j] + " ^ ";
					}
					else {
						output = input[i][j] + "";
					}
					System.out.print(output);
				}
				System.out.println(")");
			}
			
			System.out.println("    p  n  ");
			for (int i = 1; i < counts.length; i++) {
				System.out.print(i + ": (");
				for (int j = 0; j < counts[i].length; j++) {
					System.out.print(counts[i][j] + "  ");
				}
				System.out.println(")");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
