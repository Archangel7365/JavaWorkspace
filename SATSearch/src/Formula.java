import java.util.ArrayList;

public class Formula {
	public ArrayList<ArrayList<Integer>> clauses;
	private int nbvars;
	private int nbclauses;

	public Formula(int nbvars, int nbclauses, ArrayList<ArrayList<Integer>> clauses) {
		this.nbvars = nbvars;
		this.nbclauses = nbclauses;
		this.clauses = clauses;
	}

	public Formula(int nbvars, ArrayList<ArrayList<Integer>> clauses) {
		this.nbvars = nbvars;
		this.nbclauses = clauses.size();
		this.clauses = clauses;
	}

	public void printClauses() {
		if (clauses.size() > 0) {
			for (int i = 0; i < clauses.size(); i++) {
				System.out.print("Clause " + i + ": (");
				for (int j = 0; j < clauses.get(i).size(); j++) {
					if (j != clauses.get(i).size() - 1) {
						System.out.print(clauses.get(i).get(j) + " ^ ");
					}
					else {
						System.out.print(clauses.get(i).get(j) + "");
					}
				}
				System.out.println(")");
			}
		}
		else {
			System.out.println("Empty clauses");
		}
	}

	public Formula removeClausesContaining(int element) {
		Formula tempTest = new Formula(this.nbvars, (ArrayList<ArrayList<Integer>>)this.clauses.clone());
		tempTest.clauses.removeIf(c -> contains(c, element) == 1);
		return tempTest;
	}
	
	public Formula removeElement(int element) {
		Formula tempTest = new Formula(this.nbvars, (ArrayList<ArrayList<Integer>>)this.clauses.clone());
		for (int i = 0; i < tempTest.clauses.size(); i++) {
			for (int j = 0; j < tempTest.clauses.get(i).size(); j++) {
				if (tempTest.clauses.get(i).get(j) == element) {
					tempTest.clauses.get(i).set(j, null);
				}
				tempTest.clauses.get(i).removeIf(integer -> integer == null);
			}
		}
		tempTest.clauses.removeIf(c -> c.size() <= 0);
		return tempTest;
	}

	public boolean solvedBy(ArrayList<Integer> solution) {
		boolean result = false;
		Formula tempTest = new Formula(this.nbvars, this.nbclauses, (ArrayList<ArrayList<Integer>>)this.clauses.clone());
		for (Integer i: solution) {
			tempTest = tempTest.removeClausesContaining(i);
		}
		if (tempTest.clauses.size() == 0) {
			result = true;
		}
		return result;
	}

	public int contains(ArrayList<Integer> values, int value) {
		int result = -1;
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) == (value)) {
				result = 1;
			}
			else if (Math.abs(values.get(i)) == Math.abs(value)) {
				result = 0;
			}
		}
		return result;
	}

	public ArrayList<ArrayList<Integer>> countVals(ArrayList<ArrayList<Integer>> input) {
		ArrayList<ArrayList<Integer>> varCounts = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= nbvars; i++) {
			varCounts.add(new ArrayList<Integer>());
			varCounts.get(i).add(0);
			varCounts.get(i).add(0);
		}
		for (int i = 1; i < varCounts.size(); i++) {
			for (int j = 0; j < input.size(); j++) {
				if (contains(input.get(j), i) == 1) {
					varCounts.get(i).set(0, varCounts.get(i).get(0) + 1);
				}
				else if (contains(input.get(j), i) == 0) {
					varCounts.get(i).set(1, varCounts.get(i).get(1) + 1);
				}
			}
		}
		return varCounts;
	}

	public void printCounts(ArrayList<ArrayList<Integer>> counts) {
		for (int i = 1; i <= nbvars; i++) {
			System.out.println(i + ": " + counts.get(i).get(0) + " | " + counts.get(i).get(1));
		}
	}
}
