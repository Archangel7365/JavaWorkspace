//This class serves to hold all of the individual Tiles of the Megaminx
//and also forms the building blocks for the puzzle as a whole.

//Class Name: Face
//Class Properties: Tile[] tiles, Face[]adjacentFaces, 
//					Color faceColor, int faceNo
//Class Methods: 
// - getters and setters for fields 
// - fillFace() -> loops through the array of Tiles in a Face and initializes
//					the tiles to be the face color
// - printTiles() -> loops through the array of tiles to print() each tile
// - printAdj() -> prints the face numbers of the arrays adjacent to the current 
//					one in the order in which they would be affected by a rotate
//					function

import java.awt.Color;

public class Face {
	public Tile[] tiles;
	public Face[] adjacentFaces;
	private Color faceColor;
	private int faceNo;

	public Face() {
		tiles = new Tile[11];
		adjacentFaces = new Face[5];
		faceColor = Color.WHITE;
		faceNo = -1;
	}

	public Face(Color c1, int faceN) {
		tiles = new Tile[11];
		adjacentFaces = new Face[5];
		faceColor = c1;
		faceNo = faceN;
		fillFace();
	}

	public void setColor(Color c1) {
		faceColor = c1;
	}

	public Color getColor() {
		return this.faceColor;
	}

	public void setNum(int facenum) {
		this.faceNo = facenum;
	}

	public int getNum() {
		return this.faceNo;
	}

	public void setAdj(Face[] adj) {
		this.adjacentFaces = adj;
	}

	public void fillFace() {
		for (int i = 0; i < 11; i++) {
			boolean temp = false;
			if (i == 0) {
				temp = true;
			}
			int tempi = faceNo * 11 + 1;
			tiles[i] = new Tile(faceColor, tempi + i, temp, faceNo);
			if (i % 2 == 1) {
				tiles[i].setIsCorner(true);
			}
		}
	}

	public void printTiles() {
		System.out.println("---Face Number: " + faceNo + "---");
		for (Tile t : tiles) {
			t.print();
		}
		System.out.println("--------------------");
	}

	public void printAdj() {
		System.out.println("---Face Number: " + faceNo + "---");
		for (Face f : adjacentFaces) {
			System.out.println(f.faceNo);
		}
	}

}
