
public class AlgTest {

	public static void main(String[] args) {
		//DPLL test = new DPLL("src/testFiles/CNF Formulas/uf50-01.cnf");
		DPLL test = new DPLL("src/yo.txt");
		
		Formula testFormula = test.getStartFormula();
		testFormula.printClauses();
		System.out.println();
		
		System.out.println("Most frequent element: " + testFormula.freqElem());
		System.out.println();
		
		test.printPureSymbols();
		System.out.println();
		
		test.printUnitClauses();
	}

}
