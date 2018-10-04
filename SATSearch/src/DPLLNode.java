import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class DPLLNode {
	public ArrayList<int[]> pureSymbols;
	public ArrayList<int[]> unitClauses;
	public Formula startFormula;
	public DPLLNode parent;
	public ArrayList<Integer> nodeID;
	public ArrayList<DPLLNode> children;
	public boolean isCandidate;
	public boolean isSolution;

	public DPLLNode() {
		this.parent = null;
		pureSymbols = new ArrayList<int[]>();
		unitClauses = new ArrayList<int[]>();
		startFormula = null;
		nodeID = new ArrayList<Integer>();
		isCandidate = true;
		children = new ArrayList<DPLLNode>();
	}

	public DPLLNode(DPLLNode parent, ArrayList<Integer> nodeID) {
		this.parent = parent;
		this.nodeID = nodeID;
	}

	public ArrayList<int[]> getPureSymbols() {
		return this.pureSymbols;
	}

	public ArrayList<int[]> getUnitClauses() {
		return this.unitClauses;
	}

	public void printPureSymbols() {
		if (pureSymbols.size() > 0) {
			System.out.println("elm | count | sign");
			System.out.println("------------");
			for (int[] arr: pureSymbols) {
				System.out.println(arr[0] + "   |   " + arr[1] + "   |   " + arr[2]);
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

	public Formula getStartFormula() {
		return this.startFormula;
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

	public void printUnitClauses() {
		if (unitClauses.size() > 0) {
			System.out.println("cl#| val");
			System.out.println("------");
			for (int[] arr: unitClauses) {
				System.out.println(arr[0] + "  |  " + arr[1]);
			}
		}
		else {
			System.out.println("There are no unit clauses!");
		}
		System.out.println();
	}

	public DPLLNode simplify(DPLLNode startNode, int element) {
		DPLLNode newNode = startNode.copy();
		newNode.parent = startNode;
		newNode.startFormula = newNode.startFormula.removeClausesContaining(element);
		newNode.startFormula = newNode.startFormula.removeElement(-element);
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(element);
		newNode.nodeID = temp;
		return newNode;
	}

	public Set<Integer> tracePath() {
		Set<Integer> solution = new HashSet<Integer>();
		DPLLNode temp = this.copy();
		solution.addAll(temp.nodeID);
		while (temp.parent != null) {
			solution.addAll(temp.nodeID);
			temp = temp.parent;	
		}
		return solution;
	}
	
	public void printNode() {
		System.out.println("Node ID: " + nodeID.toString());
		System.out.println("Node Formula: ");
		startFormula.printClauses();
		System.out.println();
	}

	public DPLLNode copy() {
		DPLLNode newNode = new DPLLNode();
		newNode.isCandidate = this.isCandidate;
		newNode.isSolution = this.isSolution;
		newNode.parent = this.parent;
		newNode.nodeID = this.nodeID;
		newNode.startFormula = this.startFormula.copy();
		newNode.children = new ArrayList<DPLLNode>();
		return newNode;
	}
	
}
