import java.util.ArrayList;
import java.util.Random;

public class Grid {
	private int size;
	private int difficulty;
	private GridSquare[][] Grid;
	
	Grid() {
		size = 10;
		difficulty = 1;
	}
	
	Grid(int size, int difficulty) {
		this.size = size;
		this.difficulty = difficulty;
	}
	
	void genGrid() {
		Grid = new GridSquare[size][size];	
		int mineCount = 0;
		
		Random rng = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Grid[i][j] = new GridSquare();
				double chance = rng.nextDouble();
				if (chance < calcDiff()) {
					Grid[i][j].setMine(true);
					mineCount += 1;
				}
				
			}
		}
		System.out.println("Mine Count: " + mineCount);
		
	}
	
	void setSize(int size) {
		this.size = size;
	}
	
	void setDiff(int difficulty) {
		this.difficulty = difficulty;
	}
	
	int getSize() {
		return size;
	}
	
	int getDiff() {
		return difficulty;
	}
	
	double calcDiff() {
		return 0.1 * difficulty;
	}
	
	public void printGrid() {
		System.out.println("    0  1  2  3  4  5  6  7  8  9 ");
		//System.out.println("---------------------------------");
		for (int i = 0; i < size; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < size; j++) {
				if (Grid[i][j].getMine() == true) {
					System.out.print("[*]");
				}
				else {
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}
	}
	
}
