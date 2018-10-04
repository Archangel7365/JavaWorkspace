
public class LocalSearch {
	private SATParser parser;
	
	public LocalSearch(String filename) {
		parser = new SATParser(filename);
		try {
			parser.fileFetch();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int[] search(int[][] formula, int nbvars) {
		int[] result = new int[nbvars];
		
		
		
		return result;
	}
	
	public boolean solves(int[][] problem, int[] solution) {
		boolean result = false;
		
		
		
		return result;
	}
	
}
