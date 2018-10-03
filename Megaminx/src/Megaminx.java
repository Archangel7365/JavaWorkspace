//This class represents the entire puzzle, and provides methods to manipulate
//individual faces, as well as Randomize the puzzle by a given number of moves.
//The randomization function utilizes both clockwise and counterclockwise moves
//but ensures that no move is repeated within the last 5 (int NO_REPEATS).

//Class Name: Megaminx
//Class Properties: int NO_REPEATS, Face[] faces, boolean isSolved
//Class Methods: 
// - constructor
// - genMinx() -> initializes the faces of the Megaminx and assigns them colors.
//					also populates each Face's adjacency matrix to set up the
//					over-arching data structure of the puzzle.
// - colorFace(int) -> input an integer to return a Color.  This is used in assigning
//					a different color to each face in genMinx();
// - setAdjacencies(int) -> given an integer input representing a face number, this
//						method populates the adjacentFaces matrix of that face
// - rotateFace(int, boolean) -> this method "rotates" a face of the puzzle
//								either clockwise or counterclockwise based on
//								the boolean input (isCounterClockwise)
// - rotateAdjacentFaces() -> used in the rotateFace method to update the edges
//							of the faces adjacent to the rotating face
// - edgeRotation() -> wrapper used in rotateAdjacentFaces
// - Randomize() -> uses java.util.Random method Random.nextInt() to generate
// 					random moves both clockwise and counter clockwise to
//					mix up the puzzle before solving.
// - printMinx() -> prints all of the Tiles in the Megaminx one face at a time

import java.awt.Color;
import java.util.Random;

public class Megaminx {
	private Color CREAM = new Color(247,253,195);
	private Color PURPLE = new Color(111,0,156);
	private Color DARK_GREEN = new Color(0, 138, 0);
	private Color ORANGE = new Color(255, 128, 0);
	private final int NO_REPEATS = 5;

	public Face[] faces;
	// private boolean isSolved;

	public Megaminx() {
		faces = new Face[12];
		// isSolved = true;
		this.genMinx();
	}

	public void genMinx() {
		for (int i = 0; i < faces.length; i++) {
			faces[i] = new Face(colorFace(i), i);
		}
		for (int j = 0; j < faces.length; j++) {
			this.setAdjacencies(j);
		}
	}

	private Color colorFace(int i) {
		if (i == 0) {
			return Color.BLUE;
		} else if (i == 1) {
			return Color.RED;
		} else if (i == 2) {
			return Color.WHITE;
		} else if (i == 3) {
			return Color.YELLOW;
		} else if (i == 4) {
			return Color.GREEN;
		} else if (i == 5) {
			return Color.MAGENTA;
		} else if (i == 6) {
			return CREAM;
		} else if (i == 7) {
			return Color.DARK_GRAY;
		} else if (i == 8) {
			return ORANGE;
		} else if (i == 9) {
			return PURPLE;
		} else if (i == 10) {
			return DARK_GREEN;
		} else if (i == 11) {
			return Color.CYAN;
		} else {
			return Color.BLACK;
		}
	}

	private void setAdjacencies(int faceNo) {
		if (faceNo == 0) {
			faces[0].adjacentFaces[0] = faces[1];
			faces[0].adjacentFaces[1] = faces[2];
			faces[0].adjacentFaces[2] = faces[3];
			faces[0].adjacentFaces[3] = faces[4];
			faces[0].adjacentFaces[4] = faces[5];
		} else if (faceNo == 1) {
			faces[1].adjacentFaces[0] = faces[0];
			faces[1].adjacentFaces[1] = faces[5];
			faces[1].adjacentFaces[2] = faces[6];
			faces[1].adjacentFaces[3] = faces[10];
			faces[1].adjacentFaces[4] = faces[2];
		} else if (faceNo == 2) {
			faces[2].adjacentFaces[0] = faces[0];
			faces[2].adjacentFaces[1] = faces[1];
			faces[2].adjacentFaces[2] = faces[10];
			faces[2].adjacentFaces[3] = faces[9];
			faces[2].adjacentFaces[4] = faces[3];
		} else if (faceNo == 3) {
			faces[3].adjacentFaces[0] = faces[0];
			faces[3].adjacentFaces[1] = faces[2];
			faces[3].adjacentFaces[2] = faces[9];
			faces[3].adjacentFaces[3] = faces[8];
			faces[3].adjacentFaces[4] = faces[4];
		} else if (faceNo == 4) {
			faces[4].adjacentFaces[0] = faces[0];
			faces[4].adjacentFaces[1] = faces[3];
			faces[4].adjacentFaces[2] = faces[8];
			faces[4].adjacentFaces[3] = faces[7];
			faces[4].adjacentFaces[4] = faces[5];
		} else if (faceNo == 5) {
			faces[5].adjacentFaces[0] = faces[0];
			faces[5].adjacentFaces[1] = faces[4];
			faces[5].adjacentFaces[2] = faces[7];
			faces[5].adjacentFaces[3] = faces[6];
			faces[5].adjacentFaces[4] = faces[1];
		} else if (faceNo == 6) {
			faces[6].adjacentFaces[0] = faces[11];
			faces[6].adjacentFaces[1] = faces[10];
			faces[6].adjacentFaces[2] = faces[1];
			faces[6].adjacentFaces[3] = faces[5];
			faces[6].adjacentFaces[4] = faces[7];
		} else if (faceNo == 7) {
			faces[7].adjacentFaces[0] = faces[11];
			faces[7].adjacentFaces[1] = faces[6];
			faces[7].adjacentFaces[2] = faces[5];
			faces[7].adjacentFaces[3] = faces[4];
			faces[7].adjacentFaces[4] = faces[8];
		} else if (faceNo == 8) {
			faces[8].adjacentFaces[0] = faces[11];
			faces[8].adjacentFaces[1] = faces[7];
			faces[8].adjacentFaces[2] = faces[4];
			faces[8].adjacentFaces[3] = faces[3];
			faces[8].adjacentFaces[4] = faces[9];
		} else if (faceNo == 9) {
			faces[9].adjacentFaces[0] = faces[11];
			faces[9].adjacentFaces[1] = faces[8];
			faces[9].adjacentFaces[2] = faces[3];
			faces[9].adjacentFaces[3] = faces[2];
			faces[9].adjacentFaces[4] = faces[10];
		} else if (faceNo == 10) {
			faces[10].adjacentFaces[0] = faces[11];
			faces[10].adjacentFaces[1] = faces[9];
			faces[10].adjacentFaces[2] = faces[2];
			faces[10].adjacentFaces[3] = faces[1];
			faces[10].adjacentFaces[4] = faces[6];
		} else if (faceNo == 11) {
			faces[11].adjacentFaces[0] = faces[6];
			faces[11].adjacentFaces[1] = faces[7];
			faces[11].adjacentFaces[2] = faces[8];
			faces[11].adjacentFaces[3] = faces[9];
			faces[11].adjacentFaces[4] = faces[10];
		}
	}

	public void rotateFace(int faceNo, boolean isCounterClockwise) {
		if (isCounterClockwise) {
			int i = 0;
			for (int j = 0; j < 2; j++) {
				Tile temp = faces[faceNo].tiles[1];
				for (i = 1; i < 10; i++) {
					faces[faceNo].tiles[i] = faces[faceNo].tiles[i + 1];
				}
				faces[faceNo].tiles[i] = temp;
			}
		} else {
			for (int j = 0; j < 4; j++) {
				int i = 0;
				for (int k = 0; k < 2; k++) {
					Tile temp = faces[faceNo].tiles[1];
					for (i = 1; i < 10; i++) {
						faces[faceNo].tiles[i] = faces[faceNo].tiles[i + 1];
					}
					faces[faceNo].tiles[i] = temp;
				}
			}
		}
		this.rotateAdjacentFaces(faceNo, isCounterClockwise);
	}

	private void rotateAdjacentFaces(int faceNo, boolean isCounterClockwise) {
		Face adjFace0 = faces[faceNo].adjacentFaces[0];
		if (faceNo == 0 || faceNo == 11) {
			if (isCounterClockwise) {
				int i = 0;
				Tile temp1 = adjFace0.tiles[1];
				Tile temp2 = adjFace0.tiles[2];
				Tile temp3 = adjFace0.tiles[3];

				for (i = 0; i < 4; i++) {
					for (int j = 1; j < 4; j++) {
						faces[faceNo].adjacentFaces[i].tiles[j] = faces[faceNo].adjacentFaces[i + 1].tiles[j];
					}
				}
				faces[faceNo].adjacentFaces[i].tiles[1] = temp1;
				faces[faceNo].adjacentFaces[i].tiles[2] = temp2;
				faces[faceNo].adjacentFaces[i].tiles[3] = temp3;
			} else {
				for (int j = 0; j < 4; j++) {
					int i = 0;
					Tile temp1 = adjFace0.tiles[1];
					Tile temp2 = adjFace0.tiles[2];
					Tile temp3 = adjFace0.tiles[3];

					for (i = 0; i < 4; i++) {
						for (int l = 1; l < 4; l++) {
							faces[faceNo].adjacentFaces[i].tiles[l] = faces[faceNo].adjacentFaces[i + 1].tiles[l];
						}
					}
					faces[faceNo].adjacentFaces[i].tiles[1] = temp1;
					faces[faceNo].adjacentFaces[i].tiles[2] = temp2;
					faces[faceNo].adjacentFaces[i].tiles[3] = temp3;
				}
			}
		} else if (faceNo % 5 == 1) {
			edgeRotation(faceNo, 1, 2, 3, isCounterClockwise);
		} else if (faceNo % 5 == 2) {
			edgeRotation(faceNo, 3, 4, 5, isCounterClockwise);
		} else if (faceNo % 5 == 3) {
			edgeRotation(faceNo, 5, 6, 7, isCounterClockwise);
		} else if (faceNo % 5 == 4) {
			edgeRotation(faceNo, 7, 8, 9, isCounterClockwise);
		} else if (faceNo % 5 == 0) {
			edgeRotation(faceNo, 9, 10, 1, isCounterClockwise);
		}
	}

	private void edgeRotation(int faceNo, int temp1, int temp2, int temp3, boolean isCounterClockwise) {
		int bound = 1;
		if (!isCounterClockwise) {
			bound = 4;
		}
		for (int i = 0; i < bound; i++) {
			Face adjFace0 = faces[faceNo].adjacentFaces[0];
			Face adjFace1 = faces[faceNo].adjacentFaces[1];
			Face adjFace2 = faces[faceNo].adjacentFaces[2];
			Face adjFace3 = faces[faceNo].adjacentFaces[3];
			Face adjFace4 = faces[faceNo].adjacentFaces[4];

			Tile tempt1 = adjFace0.tiles[temp1];
			Tile tempt2 = adjFace0.tiles[temp2];
			Tile tempt3 = adjFace0.tiles[temp3];

			adjFace0.tiles[temp1] = adjFace1.tiles[9];
			adjFace0.tiles[temp2] = adjFace1.tiles[10];
			adjFace0.tiles[temp3] = adjFace1.tiles[1];

			adjFace1.tiles[9] = adjFace2.tiles[5];
			adjFace1.tiles[10] = adjFace2.tiles[6];
			adjFace1.tiles[1] = adjFace2.tiles[7];

			adjFace2.tiles[5] = adjFace3.tiles[7];
			adjFace2.tiles[6] = adjFace3.tiles[8];
			adjFace2.tiles[7] = adjFace3.tiles[9];

			adjFace3.tiles[7] = adjFace4.tiles[3];
			adjFace3.tiles[8] = adjFace4.tiles[4];
			adjFace3.tiles[9] = adjFace4.tiles[5];

			adjFace4.tiles[3] = tempt1;
			adjFace4.tiles[4] = tempt2;
			adjFace4.tiles[5] = tempt3;
		}
	}

	public int[] Randomize(int moves) { // Randomize the megaminx
		Random rng = new Random();
		int[] lastMoves = new int[NO_REPEATS];
		int[] mixMatrix = new int[moves];
		int mixCounter = 0;
		for (int i = 0; i < lastMoves.length; i++) { //initializing lastMoves with a marker
			lastMoves[i] = -1;							// to test if it is full
		}
		for (int i = 0; i < moves; i++) {
			boolean validMove = false;
			int newMove = 0;
			while (!validMove) {
				newMove = rng.nextInt(12); // pseudo-random number generator between 0 (inclusive) and 12 (exclusive)
				boolean found = false;
				if (contains(lastMoves, newMove)) {
					found = true;
				}
				else if (contains(lastMoves, -1)) {
					validMove = true;
					for (int x = 0; x < lastMoves.length; x++) {
						if (lastMoves[x] == newMove) {
							validMove = false;
							break;
						}
						else if (lastMoves[x] == -1) {
							lastMoves[x] = newMove;
							break;
						}
					}
				}
				if (!found) {
					validMove = true;
					if (lastMoves.length > 0) {
						for (int x = 0; x < lastMoves.length - 1; x++) {
							lastMoves[x] = lastMoves[x+1];
						}

						lastMoves[lastMoves.length - 1] = newMove;
					}
				}

			}
			boolean isCounterClockwise = true;
			mixMatrix[mixCounter] = newMove;
			mixCounter++;
			this.rotateFace(newMove, isCounterClockwise);	
		}
		return mixMatrix;
	}

	public void printMinx() {
		for (Face f : faces) {
			f.printTiles();
		}
	}

	public void copyMinx(Megaminx origMinx) {
		for (int i = 0; i < this.faces.length; i++) {
			for (int j = 0; j < this.faces[i].tiles.length; j++) {
				this.faces[i].tiles[j] = origMinx.faces[i].tiles[j];
			}
		}
	}

	public boolean contains(int[] arr, int test) {
		for (int i : arr) {
			if (i == test) {
				return true;
			}
		}
		return false;
	}

	public int compareTo(Megaminx otherMinx) {
		int result = 132;
		for (int i = 0; i < this.faces.length; i++) {
			for (int j = 0; j < this.faces[i].tiles.length; j++) {
				if (this.faces[i].tiles[j].getNum() == otherMinx.faces[i].tiles[j].getNum()) {
					result--;
				}
			}
		}
		return result;
	}

}
