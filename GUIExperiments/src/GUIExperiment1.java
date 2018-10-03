import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class GUIExperiment1 extends JFrame {

	public GUIExperiment1() {
		setTitle("My Empty Frame");
		setSize(600,600);
		setLocation(10,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton button1 = new JButton("Click on Me!");
		add(button1);
		button1.setSize(100, 15);
		button1.setLocation(300,300);
	}
	
	public static void main(String[] args) {
		Color c = new Color(100,120,150);
		JFrame f = new GUIExperiment1();
		//f.setBackground(Color.BLUE);
		f.setForeground(Color.BLACK);
		
	}

}
