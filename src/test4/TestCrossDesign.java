package test4;

import javax.swing.JFrame;

public class TestCrossDesign {
	public static void main(String[] args) {
		CrossRiverFrame frame =  new CrossRiverFrame();
		frame.setTitle("农夫过河问题求解");
		frame.setSize(453,400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}

