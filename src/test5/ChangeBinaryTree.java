package test5;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class ChangeBinaryTree extends JFrame {
	private static final long serialVersionUID = 1L;
	private BinaryTree<Integer> integer = new  BinaryTree<Integer> () ;
	private JTextField jtfValue = new JTextField();
	private JButton jbtSearch = new JButton("Sreach");
	private JButton jbtInsert = new JButton("Insert");
	private JButton jbtRemove = new JButton("Remove");
//	private PaintLinkedList<Integer> paintLinkedList = new PaintLinkedList<Integer>(integer);
	public ChangeBinaryTree(){
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(1,5));
		p1.add(new JLabel("Enter a value:"));
		p1.add(jtfValue);
		p1.add(jbtSearch);
		p1.add(jbtInsert);
		p1.add(jbtRemove);
		add(p1,BorderLayout.SOUTH);
//		add(paintLinkedList,BorderLayout.CENTER);
/*		jbtSearch.addActionListener(new SearchListener());
		jbtInsert.addActionListener(new InsertListener());
		jbtRemove.addActionListener(new jbtRemoveListener());*/
	}
	
/*	class SearchListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String indexString=jtfIndex.getText();
			for (int i =jtfIndex.getText().length() ; i > 0; i--)
				jtfIndex.setText(jtfIndex.getText().substring(0, i - 1));
			try{
				if(indexString.length()==0){
					throw new NumberFormatException(); 
				}
				else{
					Integer index=Integer.parseInt(indexString);
					for (int i = jtfIndex.getText().length() ; i != 0; i--)
						jtfIndex.setText(jtfIndex.getText().substring(0, i - 1));
					if(integer.contains(index)){
						JOptionPane.showMessageDialog(null,"该元素在链表里");
					}
					else{
						JOptionPane.showMessageDialog(null,"该元素不在链表里");
					}
				}
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "查找失败");
			}
		}
	}
	
	class InsertListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String valueString = jtfValue.getText();
			for (int i =jtfValue.getText().length() ; i > 0; i--)
				jtfValue.setText(jtfValue.getText().substring(0, i - 1));
			try{
				int value = Integer.parseInt(valueString);
				integer.add(value);
				paintLinkedList.change(integer);
			}
			catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "请您输入数字");
			}
			
		}
	}
	
	class jbtRemoveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String indexString = jtfIndex.getText();
			for (int i =jtfIndex.getText().length() ; i > 0; i--)
				jtfIndex.setText(jtfIndex.getText().substring(0, i - 1));
			try{
				int index = Integer.parseInt(indexString);
				integer.remove(index);
				paintLinkedList.change(integer);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "请您输入数字");
			}
		}
	}*/
	
	public static void main(String[] args){
		ChangeBinaryTree frame = new ChangeBinaryTree();
		frame.setTitle("DisplayBinaryTree");
		frame.setSize(800,400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
