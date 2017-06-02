package test5;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TreeControl extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BinaryTree<Integer> tree;
	private JTextField jtfKey = new JTextField(5);
	private TreeView view = new TreeView() ;
	private JButton jbtInsert = new JButton("Insert");
	private JButton jbtDelete = new JButton("Delete");
	
	public TreeControl(BinaryTree<Integer> tree) {
		this.tree = tree;
		setUI();
	}

	private void setUI() {
		this.setLayout(new BorderLayout());
		add(view,BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter a key: "));
		panel.add(jtfKey);
		panel.add(jbtInsert);
		panel.add(jbtDelete);
		add(panel,BorderLayout.SOUTH);
		
		jbtInsert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int key = Integer.parseInt(jtfKey.getText());
				if(tree.search(key) ) {
					JOptionPane.showMessageDialog(null,key+" is already in the tree");
				}
				else
				{
					tree.insert(key);
					view.repaint();
				}
			}
		});
		
		jbtDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int key =Integer.parseInt(jtfKey.getText());
				if(!tree.search(key)) {
					JOptionPane.showMessageDialog(null,key+" is not in the tree");
				}
				else{
					tree.delete(key);
					view.repaint();
				}
			}
		});
	}
	
	class TreeView extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int radius = 20;
		private int vGap = 50;
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(tree.getRoot()!=null){
				displayTree(g,tree.getRoot(),getWidth()/2,30,getWidth()/4);
			}
		}
		
		private void displayTree(Graphics g, BinaryTree.TreeNode root, int x ,int y , int hGap) {
			g.drawOval(x-radius,y-radius,2*radius,2*radius);
			g.drawString(root.element+"", x-6, y+4);
			if(root.left != null) {
				
				connectLeftChild(g,x-hGap,y+vGap,x,y);
				displayTree(g,root.left,x-hGap,y+vGap,hGap/2);
			}
			
			
			if(root.right != null) {
				connectRightChild(g,x+hGap,y+vGap,x,y);
				displayTree(g,root.right,x+hGap,y+vGap,hGap/2);
			}
		}
		
		private void connectLeftChild(Graphics g,int x1,int y1,int x2,int y2) {
			double d = Math.sqrt(vGap*vGap+(x2-x1)*(x2-x1));
			int x11= (int)(x1+radius*(x2-x1)/d);
			int y11= (int )(y1-radius*vGap/d);
			int x21=(int )(x2-radius*(x2-x1)/d);
			int y21= (int )(y2+radius*vGap/d);
			g.drawLine(x11, y11, x21, y21);
		}
		
		private void connectRightChild(Graphics g,int x1,int y1,int x2,int y2) {
			double d = Math.sqrt(vGap*vGap+(x2-x1)*(x2-x1));
			int x11= (int)(x1-radius*(x1-x2)/d);
			int y11= (int )(y1-radius*vGap/d);
			int x21=(int )(x2+radius*(x1-x2)/d);
			int y21= (int )(y2+radius*vGap/d);
			g.drawLine(x11, y11, x21, y21);
		}
		
	}
	
	
}
