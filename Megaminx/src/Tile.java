//This class is designed to represent one of the sides of a Megaminx piece
//There are 132 total "Tiles" on the Megaminx, some coming in groups of 2 or 3
// tiles to a piece, while center tiles are by themselves.  Instead of trying
//to group the tiles into pieces I organized the tiles into faces and was very
//careful in my methods manipulating the arrays of tiles to ensure they stayed
//in the order they would on an actual Megaminx.

//Class Name: Tile
//Class Properties: Color tileColor, int tileNumber, boolean isCenter
//Class Methods: 
// - getters and setters for fields 
// - colorString() -> converts the string value of a color into a String 
//                    containing the name of the color
// - print() -> prints the field names and values

import java.awt.Color;

public class Tile {
	private Color tileColor;
	private int tileNumber;
	private boolean isCenter;
	private boolean isCorner;
	private int homeFace;

	public Tile() {
		tileColor = Color.WHITE;
		tileNumber = -1;
		isCenter = false;
		homeFace = 0;
	}

	public Tile(Color c1, int tileNo, boolean ic, int homeFace) {
		tileColor = c1;
		tileNumber = tileNo;
		isCenter = ic;
		this.homeFace = homeFace;
	}

	public void setColor(Color c1) {
		tileColor = c1;
	}

	public void setTileNo(int tileNo) {
		tileNumber = tileNo;
	}

	public void setCenter(boolean ic) {
		isCenter = ic;
	}

	public Color getColor() {
		return this.tileColor;
	}

	public int getNum() {
		return this.tileNumber;
	}

	public boolean getCenter() {
		return this.isCenter;
	}

	public static String colorString(String s) {
		if (s.equals(Color.BLUE.toString())) {
			return "BLUE";
		} else if (s.equals(Color.RED.toString())) {
			return "RED";
		} else if (s.equals(Color.WHITE.toString())) {
			return "WHITE";
		} else if (s.equals(Color.YELLOW.toString())) {
			return "YELLOW";
		} else if (s.equals(Color.GREEN.toString())) {
			return "GREEN";
		} else if (s.equals(Color.MAGENTA.toString())) {
			return "MAGENTA";
		} else if (s.equals(new Color(247,253,195).toString())) {
			return "CREAM";
		} else if (s.equals(Color.DARK_GRAY.toString())) {
			return "DARK GRAY";
		} else if (s.equals(new Color(255, 128, 0).toString())) {
			return "ORANGE";
		} else if (s.equals(new Color(111,0,156).toString())) {
			return "PURPLE";
		} else if (s.equals(new Color(0, 138, 0).toString())) {
			return "DARK GREEN";
		} else if (s.equals(Color.CYAN.toString())) {
			return "CYAN";
		} else {
			return "Color not found";
		}
	}

	public void setHomeFace(int homeFace) {
		this.homeFace = homeFace;
	}
	
	public void setIsCorner(boolean corner) {
		this.isCorner = corner;
	}
	
	public boolean getCorner() {
		return this.isCorner;
	}

	public int getHomeFace() {
		return this.homeFace;
	}

	public void print() {
		System.out.println("Tile: " + this.tileNumber + " | Color: " + colorString(this.tileColor.toString())
		+ " | isCenter: " + isCenter);
	}

}
