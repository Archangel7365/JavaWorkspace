
public class GridSquare {
	private boolean isMine;
	private int adjMines;	

	GridSquare(boolean mine, int adj) {
		isMine = mine;
		adjMines = adj;
	}
	
	GridSquare() {
		isMine = false;
		adjMines = 0;
	}
	
	void setMine(boolean isMine) {
		this.isMine = isMine;
	}
	
	void setAdj(int adjMines) {
		this.adjMines = adjMines;
	}
	
	boolean getMine() {
		return this.isMine;
	}
	
	int getAdj() {
		return this.adjMines;
	}
	
}
