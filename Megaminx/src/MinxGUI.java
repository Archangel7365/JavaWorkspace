//This class is the entire GUI for the Megaminx.  I ran into a strange bug where
// every time I would change the current face or scramble the puzzle, the picture
// wouldn't update until I manually resized the window.  This was fixed with the
// refreshGUI method, which is a wrapper for the JFrame.repaint() method.

//Class Name: MinxGUI
//Class Properties: too many to list, mostly swing components
//Class Methods: 
// - constructor -> sets window size, title, close operation, location on
// 					screen, and set the window size to not be adjustable
// - createView() -> initializes, places, and adds functionality to the swing
// 						components defined in the class 
// - refreshGUI() -> wrapper to fix weird MinxGUIPanel repaint bug.  Repaints
//					entire GUI.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MinxGUI extends JFrame {
	private Megaminx Minx1;
	private AStarSearch searchBot;
	private AStarNode startNode;
	private ArrayList<AStarNode> solution;

	private JButton buttonMove, buttonView, buttonReset;
	private JLabel currentFace, viewLabel, moveLabel, moveResult;
	private JTextField movesIn, viewField;
	private int nCurrentFace;
	private int nMoves = 0;

	private int framewidth = 600;
	private int frameheight = 400;

	public MinxGUI() {
		Minx1 = new Megaminx();
		searchBot = new AStarSearch();
		startNode = new AStarNode(Minx1);
		solution = new ArrayList<AStarNode>();
		createView();
		setTitle("Megaminx GUI by Austin Williams");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(framewidth, frameheight));
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void createView() {
		JPanel background = new JPanel();
		background.setLayout(new BorderLayout());
		background.setBackground(Color.BLACK);
		getContentPane().add(background);

		MinxGUIPanel minx = new MinxGUIPanel();
		minx.setTiles(Minx1.faces[nCurrentFace].tiles);
		background.add(minx, BorderLayout.CENTER);

		JPanel movePanel = new JPanel();
		moveLabel = new JLabel("Number of Moves: ");
		movesIn = new JTextField();
		movesIn.setPreferredSize(new Dimension(30, 20));
		movePanel.add(moveLabel);
		movePanel.add(movesIn);
		buttonMove = new JButton("Randomize");
		moveResult = new JLabel();
		moveResult.setText("--");
		movePanel.add(moveResult);
		buttonMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rando = movesIn.getText();
				nMoves += Integer.parseInt(rando);
				Minx1.Randomize(nMoves);
				startNode = new AStarNode(Minx1);
				moveResult.setText("" + nMoves);
				refreshGUI();
			}
		});

		movePanel.add(buttonMove);
		background.add(movePanel, BorderLayout.NORTH);

		JPanel viewPanel = new JPanel();
		viewLabel = new JLabel("Face: ");
		viewPanel.add(viewLabel);

		viewField = new JTextField();
		viewField.setPreferredSize(new Dimension(30,20));
		viewPanel.add(viewField);

		currentFace = new JLabel();
		currentFace.setText("Current Face: " + nCurrentFace);

		buttonView = new JButton("View");
		buttonView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newFace = viewField.getText();
				int newFaceInt = Integer.parseInt(newFace);
				if (newFaceInt < 12 && newFaceInt >= 0) {
					nCurrentFace = newFaceInt;
					currentFace.setText("Current Face: " + nCurrentFace);
					minx.setTiles(Minx1.faces[nCurrentFace].tiles);
					refreshGUI();
				}
				else {
					currentFace.setText("Invalid Face Number");
					refreshGUI();
				}

			}
		});
		viewPanel.add(buttonView);
		viewPanel.add(currentFace);

		JPanel solvePanel = new JPanel();
		solvePanel.setLayout(new BoxLayout(solvePanel, BoxLayout.PAGE_AXIS));

		JLabel solvedLabel = new JLabel("--");
		solvedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		solvedLabel.setAlignmentY(CENTER_ALIGNMENT);
		solvePanel.add(solvedLabel);

		JLabel nodeLabel = new JLabel("Nodes expanded: --");
		nodeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nodeLabel.setAlignmentY(CENTER_ALIGNMENT);
		
		JButton buttonSolve = new JButton("Solve");
		buttonSolve.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonSolve.setAlignmentY(CENTER_ALIGNMENT);
		buttonSolve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nMoves > 0) {
					solution = searchBot.search(startNode, nMoves);
					solvedLabel.setText("Solved in: " + solution.size() + " moves!");
					nodeLabel.setText("Nodes expanded: " + searchBot.nodesExpanded);
					Minx1 = solution.get(solution.size()-1).minxNode;
					minx.setTiles(Minx1.faces[nCurrentFace].tiles);
					refreshGUI();
				}
				else {
					solvedLabel.setText("Please randomize cube first.");
				}
			}
		});
		
		solvePanel.setPreferredSize(new Dimension(200,framewidth));
		solvePanel.add(buttonSolve);
		solvePanel.add(nodeLabel);
		background.add(solvePanel, BorderLayout.EAST);
		
		
		
		
		buttonReset = new JButton("Reset");
		buttonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewField.setText("");
				movesIn.setText("");
				Minx1 = new Megaminx();
				startNode = new AStarNode(Minx1);
				solution = new ArrayList<AStarNode>();
				searchBot.nodesExpanded = 0;
				nMoves = 0;
				currentFace.setText("Current Face: " + nCurrentFace);
				moveResult.setText("--");
				nodeLabel.setText("Nodes expanded: --");
				solvedLabel.setText("--");
				minx.setTiles(Minx1.faces[nCurrentFace].tiles);
				refreshGUI();
			}
		});
		viewPanel.add(buttonReset);
		background.add(viewPanel, BorderLayout.SOUTH);



		




	}

	public void refreshGUI() {
		this.repaint();
	}

}
