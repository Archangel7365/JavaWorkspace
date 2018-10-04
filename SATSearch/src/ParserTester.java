

public class ParserTester {

	public static void main(String[] args) {
		SATParser yoParse = new SATParser("src/testFiles/CNF Formulas/uf50-01.cnf");
		
		try {
			Formula test = new Formula(yoParse.nbvars, yoParse.nbclauses, yoParse.parseInput());
			test.printClauses();
			test = test.removeElement(4);
			System.out.println();
			System.out.println();
		}
		catch (Exception io) {
			io.printStackTrace();
		}

	}

}
