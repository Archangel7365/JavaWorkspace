

public class ParserTester {

	public static void main(String[] args) {
		SATParser yoParse = new SATParser("src/testFiles/CNF Formulas/uf20-0162.cnf");
		try {
			Formula test = new Formula(yoParse.nbvars, yoParse.nbclauses, yoParse.parseInput());
			test.printClauses();
		}
		catch (Exception io) {
			io.printStackTrace();
		}
	}

}
