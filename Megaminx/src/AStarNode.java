import java.util.ArrayList;

public class AStarNode {

	private int nodeID;
	private double heuristicN;
	private double gN;
	public double totalCost;
	private AStarNode parentNode;
	public ArrayList<AStarNode> children;
	public Megaminx minxNode;
	
	public AStarNode(Megaminx minx1) {
		heuristicN = 0;
		gN = 0;
		parentNode = null;
		minxNode = minx1;
		children = new ArrayList<AStarNode>();
	}
	
	public AStarNode(int nodeID, double gN, AStarNode parent, Megaminx minx1) {
		this.nodeID = nodeID;
		this.gN = gN;
		this.parentNode = parent;
		this.minxNode = minx1;
		this.children = new ArrayList<AStarNode>();
	}
	
	public void setNID(int nodeN) {
		nodeID = nodeN;
	}
	
	public void setHN(double hN) {
		this.heuristicN = hN;
	}
	
	public void setGN(double gN) {
		this.gN = gN;
	}
	
	public void setMinx(Megaminx mx) {
		this.minxNode = mx;
	}
	
	public void setParent(AStarNode parent) {
		this.parentNode = parent;
	}
	
	public double getNID() {
		return this.nodeID;
	}
	
	public double getHN() {
		return this.heuristicN;
	}
	
	public double getGN() {
		return this.gN;
	}
	
	public Megaminx getMinx() {
		return this.minxNode;
	}
	
	public AStarNode getParent() {
		return this.parentNode;
	}
	
	public void setChildren(ArrayList<AStarNode> childArr) {
		children = childArr;
	}
	
	public double getCost() {
		totalCost = this.getGN() + this.getHN();
		return this.totalCost;
	}
	
	public int compareCost(AStarNode other) {
		if (this.getCost() == other.getCost()) {
			return 0;
		}
		else if (this.getCost() > other.getCost()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
}
