import java.util.ArrayList;

public class AStarTest {
	//public static final int N_MINXES = 5;
	public static final int N_MOVES = 15;

	public static void main(String[] args) {

		//for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 5; j++) {
				AStarSearch searchBot = new AStarSearch();
				Megaminx minx1 = new Megaminx();
				minx1.Randomize(N_MOVES);

				AStarNode startNode = new AStarNode(minx1);

				ArrayList<AStarNode> solution = searchBot.search(startNode, N_MOVES);
				System.out.println("Solved in: " + solution.size() + " moves");
				System.out.println("Expanded: " + searchBot.nodesExpanded + " nodes");
				System.out.println("________________________");
			}
		//}





	}

}
