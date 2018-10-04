import java.util.ArrayList;

public class AlgTest {

	public static void main(String[] args) {
		DPLL test = new DPLL("src/testFiles/CNF Formulas/uf20-0156.cnf");
		//DPLL test = new DPLL("src/yo.txt");
		
		boolean found = test.DPLLSearch(test.getStartNode());
		System.out.println("FOUND: " + found);
		
		
		
	}

}
