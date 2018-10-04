

public class ParserTester {

	public static void main(String[] args) {
		SATParser yoParse = new SATParser("src/yo.txt");
		
		try {
			Formula test = new Formula(yoParse.nbvars, yoParse.nbclauses, yoParse.parseInput());
			test.printClauses();
			test = test.removeElement(4);
			System.out.println();
			test.printClauses();
		}
		catch (Exception io) {
			io.printStackTrace();
		}

	}

}
