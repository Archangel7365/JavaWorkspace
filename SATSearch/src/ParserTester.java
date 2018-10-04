import java.util.ArrayList;

public class ParserTester {

	public static void main(String[] args) {
		SATParser yoParse = new SATParser("src/yo.txt");
		//yoParse.getFile();
		try {
			yoParse.parseInput();
			yoParse.printFormula(yoParse.formula);
			ArrayList<ArrayList<Integer>> valCounts = yoParse.countVals(yoParse.formula);
			yoParse.printCounts(valCounts);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
