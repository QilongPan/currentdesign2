package test5;

import javax.swing.JApplet;

public class DisplayBinaryTree extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DisplayBinaryTree() {
		add(new TreeControl(new BinaryTree<Integer>())) ;
	}

}
