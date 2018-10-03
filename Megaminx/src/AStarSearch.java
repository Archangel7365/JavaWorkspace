import java.util.*;


public class AStarSearch {

	public static final Megaminx SOLVED_MINX = new Megaminx();
	private ArrayList<AStarNode> pQueue;
	private ArrayList<AStarNode> route;
	public int nodesExpanded = 0;

	private int[] node0 = {0,0,0,0,0};
	private int[] node1 = {2,1,2,2,1};
	private int[] node2 = {1,2,1,2,2};
	private int[] node3 = {2,1,2,1,2};
	private int[] node4 = {2,2,1,2,1};
	private int[] node5 = {1,2,2,1,2};
	private int[] node6 = {2,3,4,3,2};
	private int[] node7 = {3,4,3,2,2};
	private int[] node8 = {4,3,2,2,3};
	private int[] node9 = {3,2,2,3,4};
	private int[] node10 = {2,2,3,4,3};
	private int[] node11 = {4,4,4,4,4};

	private int[] cnode0 = {0,0,0,0,0};
	private int[] cnode1 = {1,1,1,2,1};
	private int[] cnode2 = {1,1,1,1,2};
	private int[] cnode3 = {2,1,1,1,1};
	private int[] cnode4 = {1,2,1,1,1};
	private int[] cnode5 = {1,1,2,1,1};
	private int[] cnode6 = {2,2,3,3,2};
	private int[] cnode7 = {2,2,3,3,2};
	private int[] cnode8 = {3,3,2,2,2};
	private int[] cnode9 = {3,2,2,2,3};
	private int[] cnode10 = {2,2,2,3,3};
	private int[] cnode11 = {4,4,4,4,4};

	private int[][] face0costs = {node0,node1,node2,node3,node4,node5,node6,node7,node8,node9,node10,node11};
	private int[][] face1costs = {node1,node0,node5,node6,node10,node2,node3,node9,node11,node7,node4,node8};
	private int[][] face2costs = {node1,node2,node0,node5,node6,node10,node9,node11,node7,node4,node3,node8};
	private int[][] face3costs = {node1,node10,node2,node0,node5,node6,node11,node7,node4,node3,node9,node8};
	private int[][] face4costs = {node1,node6,node10,node2,node0,node5,node7,node4,node3,node9,node11,node8};
	private int[][] face5costs = {node1,node5,node6,node10,node2,node0,node4,node3,node9,node11,node7,node8};
	private int[][] face6costs = {node8,node3,node9,node11,node7,node4,node0,node5,node6,node10,node2,node1};
	private int[][] face7costs = {node8,node9,node11,node7,node4,node3,node2,node0,node5,node6,node10,node1};
	private int[][] face8costs = {node8,node11,node7,node4,node3,node9,node10,node2,node0,node5,node6,node1};
	private int[][] face9costs = {node8,node7,node4,node3,node9,node11,node6,node10,node2,node0,node5,node1};
	private int[][] face10costs = {node8,node4,node3,node9,node11,node7,node5,node6,node10,node2,node0,node1};	
	private int[][] face11costs = {node11,node6,node7,node8,node9,node10,node1,node2,node3,node4,node5,node0};

	private int[][][] edgeCosts = {face0costs,face1costs,face2costs,face3costs,face4costs,face5costs,face6costs,face7costs,
			face8costs,face9costs,face10costs,face11costs};

	private int[][] face0Ccosts = {cnode0,cnode1,cnode2,cnode3,cnode4,cnode5,cnode6,cnode7,cnode8,cnode9,cnode10,cnode11};
	private int[][] face1Ccosts = {cnode1,cnode0,cnode5,cnode6,cnode10,cnode2,cnode3,cnode9,cnode11,cnode7,cnode4,cnode8};
	private int[][] face2Ccosts = {cnode1,cnode2,cnode0,cnode5,cnode6,cnode10,cnode9,cnode11,cnode7,cnode4,cnode3,cnode8};
	private int[][] face3Ccosts = {cnode1,cnode10,cnode2,cnode0,cnode5,cnode6,cnode11,cnode7,cnode4,cnode3,cnode9,cnode8};
	private int[][] face4Ccosts = {cnode1,cnode6,cnode10,cnode2,cnode0,cnode5,cnode7,cnode4,cnode3,cnode9,cnode11,cnode8};
	private int[][] face5Ccosts = {cnode1,cnode5,cnode6,cnode10,cnode2,cnode0,cnode4,cnode3,cnode9,cnode11,cnode7,cnode8};
	private int[][] face6Ccosts = {cnode8,cnode3,cnode9,cnode11,cnode7,cnode4,cnode0,cnode5,cnode6,cnode10,cnode2,cnode1};
	private int[][] face7Ccosts = {cnode8,cnode9,cnode11,cnode7,cnode4,cnode3,cnode2,cnode0,cnode5,cnode6,cnode10,cnode1};
	private int[][] face8Ccosts = {cnode8,cnode11,cnode7,cnode4,cnode3,cnode9,cnode10,cnode2,cnode0,cnode5,cnode6,cnode1};
	private int[][] face9Ccosts = {cnode8,cnode7,cnode4,cnode3,cnode9,cnode11,cnode6,cnode10,cnode2,cnode0,cnode5,cnode1};
	private int[][] face10Ccosts = {cnode8,cnode4,cnode3,cnode9,cnode11,cnode7,cnode5,cnode6,cnode10,cnode2,cnode0,cnode1};	
	private int[][] face11Ccosts = {cnode11,cnode6,cnode7,cnode8,cnode9,cnode10,cnode1,cnode2,cnode3,cnode4,cnode5,cnode0};

	private int[][][] cornerCosts = {face0Ccosts,face1Ccosts,face2Ccosts,face3Ccosts,face4Ccosts,face5Ccosts,face6Ccosts,face7Ccosts,
			face8Ccosts,face9Ccosts,face10Ccosts,face11Ccosts};

	public AStarSearch() {
		pQueue = new ArrayList<AStarNode>();
		route = new ArrayList<AStarNode>();
	}

	public ArrayList<AStarNode> getQueue() {
		return pQueue;
	}

	public void setQueue(ArrayList<AStarNode> nodes) {
		this.pQueue = nodes;
	}

	public void expandNode(AStarNode node) {
		for (int i = 0; i < 12; i++) {
			Megaminx tempMinx = new Megaminx();
			tempMinx.copyMinx(node.minxNode);
			boolean isCounterClockwise = false;
			tempMinx.rotateFace(i, isCounterClockwise);
			AStarNode tempNode = new AStarNode(i, node.getGN() + 1, node, tempMinx);
			tempNode.setHN(calcH(tempNode));
			node.children.add(tempNode);
		}
	}

	public ArrayList<AStarNode> search(AStarNode startNode, int randFactor) {
		ArrayList<AStarNode> solution = new ArrayList<AStarNode>();
		boolean found = false;
		pQueue.add(startNode);
		nodesExpanded = 1;
		while (pQueue.size() > 0 && !found) { //loop until either the priority queue is empty of the solution is found
			AStarNode temp = pQueue.get(0);
			pQueue.remove(0);
			expandNode(temp);
			nodesExpanded++;
			for (AStarNode a : temp.children) {
				if (a.minxNode.compareTo(SOLVED_MINX) == 0) {
					found = true;
					route.add(a);
					break;
				}
				else {
					ArrayList<AStarNode> tempAL = new ArrayList<AStarNode>();
					for (AStarNode a1: pQueue) {
						if (a1.getGN() == a.getGN()) {
							tempAL.add(a1);
						}
					}
					boolean lowestCost = true;
					for (AStarNode a2: tempAL) {
						if (a.getCost() > a2.getCost()) {
							lowestCost = false;
						}
					}
					if (lowestCost && a.getGN() <= randFactor) {
						pQueue.add(a);
					}
				}
			}
			if (!found) {
				route.add(temp);
			}
		}
		if (!found) {
			Megaminx newMinx = new Megaminx();
			newMinx.Randomize(randFactor);
			startNode = new AStarNode(newMinx);
			solution = search(startNode, randFactor);
		}
		else {
			solution = tracePath(route.get(route.size()-1));
		}
		return solution;

	}

	public void sortQueue() {
		Collections.sort(pQueue, new Comparator<AStarNode>() {

			public int compare(AStarNode an1, AStarNode an2) {
				return an1.compareCost(an2); 
			}
		});
	}

	public ArrayList<AStarNode> tracePath(AStarNode node) {
		ArrayList<AStarNode> path = new ArrayList<AStarNode>();
		AStarNode temp = node;
		while (temp.getParent() != null) {
			path.add(temp);
			temp = temp.getParent();
		}
		Collections.reverse(path);
		return path;
	}

		public double calcH(AStarNode as1) {
			double result = 0;
			for (Face f: as1.minxNode.faces) {
				result += faceHCost(f);
			}
			result = result/15;
			return result;
		}

		public double faceHCost(Face face1) {
			double result = 0;
			for (int i = 1; i < face1.tiles.length; i++) {
				if (face1.tiles[i].getCorner()) {
					result += cornerCosts[face1.getNum()][face1.tiles[i].getHomeFace()][(i-1)/2];
					//add the pre-calculated distance to solution for each corner tile
				}
				else {
					result += edgeCosts[face1.getNum()][face1.tiles[i].getHomeFace()][(i-2)/2];
					//add the pre-calculated distance to solution for each edge tile
				}
			}
			return result;
		}

		public void pruneQueue() {
			for (int i = 0; i < pQueue.size(); i++) {
				if (pQueue.get(i).getGN() < route.get(route.size() - 1).getGN() - 1) {
					pQueue.remove(i);
				}
			}
			pQueue.trimToSize();
		}

		/*public int findNextNode(AStarNode start) {
	double minH = start.children.get(0).calcH();
	int nextIndex = 0;
	for (int i = 0; i < start.children.size(); i++) {
		if (start.children.get(i).calcH() < minH) {
			minH = start.children.get(i).calcH();
			nextIndex = i;
		}
	}
	return nextIndex;
}*/

		/*double result = 0;
	if (startNode.minxNode.compareTo(SOLVED_MINX) == 0) {
		result = startNode.getGN();
	}
	else {
		expandNode(startNode);
		if (startNode.children.get(0).getGN() > nMoves) {
			return -1;
		}
		int nextIndex = findNextNode(startNode);
		//System.out.println(startNode.children.get(nextIndex).getHN());
		result = search(startNode.children.get(nextIndex), nMoves);
		if (result == -1) {
			if (startNode.children.size() > 1) {
				startNode.children.remove(nextIndex);
				startNode.children.trimToSize();
				nextIndex = findNextNode(startNode);
				result = search(startNode.children.get(nextIndex), nMoves);
			}
			else {
				return -1;
			}
		}
	}
	return result;*/

	}
