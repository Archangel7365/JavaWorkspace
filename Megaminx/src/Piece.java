
public class Piece {
	public Face[] faces;
	public boolean isCenter;
	public boolean isCorner;
	
	public Piece() {
		faces = new Face[1];
		faces[1] = new Face();
		
		isCenter = true;
		isCorner = false;
	}
	
}
