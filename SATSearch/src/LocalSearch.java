
public class LocalSearch extends SATAlgorithm {
	Formula formula;
	
	public LocalSearch(String filename) {
		super(filename);
		try {
			parser.fileFetch();
			this.formula = new Formula(parser.nbvars, parser.nbclauses, parser.parseInput());
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int[] search(int nbvars) {
		int[] result = new int[nbvars];
		
		
		
		return result;
	}
	
}
