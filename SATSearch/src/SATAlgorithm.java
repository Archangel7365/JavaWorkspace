import java.util.ArrayList;

public class SATAlgorithm {
	protected SATParser parser;
	
	public SATAlgorithm(String filepath) {
		this.parser = new SATParser(filepath);
		try {
			this.parser.parseInput();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Integer> search() {
		return null;
	}
}
