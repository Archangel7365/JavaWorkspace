//This class is used to represent the puzzle graphically.  I've only built in
//the functionality to see one face at a time, but I hope to add the ability to
//see all twelve at once, or possibly even a 3D model of the whole puzzle.

//Class Name: MinxGUIPanel
//Class Properties: too many to list, mostly coordinate values for the shape drawings
//Class Methods: 
// - Overridden JPanel methods to draw the specific shape of a Megaminx face
//      as well as a setter for the tiles array where the tile colors 
//      are retrieved for the currently displayed face.

import javax.swing.JPanel;
import java.awt.*;

public class MinxGUIPanel extends JPanel {
	
	private int[] outlineX = {238, 132, 99, 185, 271};
	private int[] outlineY = {77,77,178,240,178};
	private Polygon outline = new Polygon(outlineX,outlineY,5);
	
	private int[] tile0X = {209,161,147,185,223};
	private int[] tile0Y = {118,118,162,190,162};
	private Polygon tile0 = new Polygon(tile0X,tile0Y, 5);
	
	private int[] tile1X = {132, 172, 161, 119};
	private int[] tile1Y = {77, 77, 118, 118};
	private Polygon tile1 = new Polygon(tile1X,tile1Y, 4);
	
	private int[] tile2X = {161, 209, 198, 172};
	private int[] tile2Y = {118, 118, 77, 77};
	private Polygon tile2 = new Polygon(tile2X,tile2Y,4);
	
	private int[] tile3X = {209,198,238,251};
	private int[] tile3Y = {118,77,77,118};
	private Polygon tile3 = new Polygon(tile3X,tile3Y,4);
	
	private int[] tile4X = {223,209,251,258};
	private int[] tile4Y = {162,118,118,138};
	private Polygon tile4 = new Polygon(tile4X,tile4Y,4);
	
	private int[] tile5X = {223,258,271,236};
	private int[] tile5Y = {162,138,178,203};
	private Polygon tile5 = new Polygon(tile5X,tile5Y,4);
	
	private int[] tile6X = {185,223,236,218};
	private int[] tile6Y = {190,162,203,216};
	private Polygon tile6 = new Polygon(tile6X,tile6Y,4);
	
	private int[] tile7X = {185,218,185,152};
	private int[] tile7Y = {190,216,240,216};
	private Polygon tile7 = new Polygon(tile7X,tile7Y,4);
	
	private int[] tile8X = {147,185,152,134};
	private int[] tile8Y = {162,190,216,203};
	private Polygon tile8 = new Polygon(tile8X,tile8Y,4);
	
	private int[] tile9X = {147,134,99,112};
	private int[] tile9Y = {162,203,178,138};
	private Polygon tile9 = new Polygon(tile9X,tile9Y,4);
	
	private int[] tile10X = {119,161,147,112};
	private int[] tile10Y = {118,118,162,138};
	private Polygon tile10 = new Polygon(tile10X,tile10Y,4);
	
	private Polygon[] tileShapes = {tile0, tile1, tile2, tile3, 
			tile4, tile5, tile6, tile7, tile8, tile9, tile10};
	
	private Tile[] tiles;
	
	@Override
	public void paintComponent(Graphics g) {
		for (int i = 0; i < tiles.length; i++) {
			g.setColor(tiles[i].getColor());
			g.fillPolygon(tileShapes[i]);
		}
		g.setColor(Color.BLACK);
		g.drawPolygon(outline);
		for (Polygon p: tileShapes) {
			g.drawPolygon(p);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400,400);
	}
	
	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}
	
}
