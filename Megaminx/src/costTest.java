
public class costTest {

	public static void main(String[] args) {
		Megaminx solvedMinx = new Megaminx();
		AStarSearch searchBot = new AStarSearch();
		
		Megaminx minx1 = new Megaminx();
		for (int i = 0; i < 12; i++) {
			minx1.rotateFace(i, true);
			for (int j = 0; j < 12; j++) {
				minx1.rotateFace(j, true);
				
			}
		}

	}

}
