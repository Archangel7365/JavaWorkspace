import java.util.ArrayList;
import java.util.Set;

public class DPLL extends SATAlgorithm {
	private DPLLNode startNode;
	private Set<Integer> solution;
	
	
	public DPLL(String filename) {
		super(filename);
		System.out.println(filename);
		startNode = new DPLLNode();
		try {
			startNode.startFormula = new Formula(this.parser.nbvars, (ArrayList<ArrayList<Integer>>)this.parser.parseInput());
			startNode.pureSymbols = new ArrayList<int[]>();
			startNode.unitClauses = new ArrayList<int[]>();
			startNode.calculatePureSymbols(startNode.startFormula);
			startNode.calculateUnitClauses(startNode.startFormula);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public DPLLNode getStartNode() {
		return this.startNode;
	}
	
	public boolean DPLLSearch(DPLLNode nextNode) {
		nextNode.calculatePureSymbols(nextNode.startFormula);
		nextNode.calculateUnitClauses(nextNode.startFormula);
		if (nextNode.startFormula.clauses.size() == 0) {
			System.out.println("Solution Found!");
			this.solution = nextNode.tracePath();
			return true;
		}
		if (nextNode.startFormula.hasEmptyClause()) {
			return false;
		}
		if (nextNode.unitClauses.size() > 0) {
			//System.out.println("NODE");
			//nextNode.printNode();
			//nextNode.printUnitClauses();
			//System.out.println("************************************");
			ArrayList<Integer> newNodeID = new ArrayList<Integer>();
			DPLLNode newNode = nextNode.copy();
			for (int[] arr : nextNode.unitClauses) {
				newNodeID.add(arr[1]);
				//System.out.println("ADDING " + arr[1] + " TO UNIT CLAUSES");
				newNode = newNode.simplify(newNode, arr[1]);
			}
			newNode.parent = nextNode;
			newNode.nodeID = newNodeID;
			//newNode.printNode();
			return DPLLSearch(newNode);
		}
		int mostFrequentElement = nextNode.startFormula.freqElem();
		DPLLNode newNode = nextNode.copy();
		newNode.parent = nextNode;
		if (DPLLSearch(newNode.simplify(newNode, mostFrequentElement))) {
			return true;
		}
		else {
			//System.out.println("Trying anothing value");
			return DPLLSearch(newNode.simplify(newNode, -mostFrequentElement));
		}
	}
	
	public void printSolution() {
		if (this.solution != null) {
			System.out.println(solution.toString());
		}
		else {
			System.out.println("Unsatisfiable!");
		}
	}
	
}
