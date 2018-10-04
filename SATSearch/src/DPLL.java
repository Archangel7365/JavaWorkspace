import java.util.ArrayList;

public class DPLL extends SATAlgorithm {
	private ArrayList<int[]> pureSymbols;
	private ArrayList<int[]> unitClauses;
	private Formula startFormula;
	
	public DPLL(String filename) {
		super(filename);
		try {
			this.startFormula = new Formula(this.parser.nbvars, (ArrayList<ArrayList<Integer>>)this.parser.parseInput());
			this.pureSymbols = new ArrayList<int[]>();
			this.unitClauses = new ArrayList<int[]>();
			calculatePureSymbols(this.startFormula);
			calculateUnitClauses(this.startFormula);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public ArrayList<int[]> getPureSymbols() {
		return this.pureSymbols;
	}
	
	public ArrayList<int[]> getUnitClauses() {
		return this.unitClauses;
	}
	
	public void printPureSymbols() {
		if (pureSymbols.size() > 0) {
			for (int[] arr: pureSymbols) {
				System.out.println(arr[0] + " : " + arr[1] + " : " + arr[2]);
			}
		}
		else {
			System.out.println("There are no pure symbols!");
		}
	}
	
	public void calculatePureSymbols(Formula currFormula) {
		ArrayList<ArrayList<Integer>> valueCounts = currFormula.countVals(currFormula.clauses);
		for (int i = 0; i < valueCounts.size(); i++) {
			if (valueCounts.get(i).get(0) == 0 && valueCounts.get(i).get(1) != 0) {
				int[] temp = {i, valueCounts.get(i).get(1), -1};
				pureSymbols.add(temp);
			}
			else if (valueCounts.get(i).get(1) == 0 && valueCounts.get(i).get(0) != 0) {
				int[] temp = {i, valueCounts.get(i).get(0), 1};
				pureSymbols.add(temp);
			}
		}
	}
	
	
	
	public void calculateUnitClauses(Formula currFormula) {
		ArrayList<int[]> result = new ArrayList<int[]>();
		for (int i = 0; i < currFormula.clauses.size(); i++) {
			if (currFormula.clauses.get(i).size() == 1) {
				int[] temp = {i, currFormula.clauses.get(i).get(0)};
				result.add(temp);
			}
		}
		this.unitClauses = (ArrayList<int[]>)result.clone();
	}
}
