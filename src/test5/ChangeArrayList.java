package test5;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
public class ChangeArrayList extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyArrayList<Integer> integer = new MyArrayList<Integer>();
	private JTextField jtfValue = new JTextField();
	private JTextField jtfIndex = new JTextField();
	private JButton jbtSearch = new JButton("Sreach");
	private JButton jbtInsert = new JButton("Insert");
	private JButton jbtDelete = new JButton("Delete");
	private JButton jbtTrimToSize = new JButton("TrimToSize"); 
	private PaintArrayList paintArrayList = new PaintArrayList(integer);
	
	public ChangeArrayList(){
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(1,8));
		p1.add(new JLabel("Enter a value:"));
		p1.add(jtfValue);
		p1.add(new JLabel("Enter an index:"));
		p1.add(jtfIndex);
		p1.add(jbtSearch);
		p1.add(jbtInsert);
		p1.add(jbtDelete);
		p1.add(jbtTrimToSize);
		add(p1,BorderLayout.SOUTH);
		add(paintArrayList,BorderLayout.CENTER);
		jbtSearch.addActionListener(new SearchListener());
		jbtInsert.addActionListener(new InsertListener());
		jbtDelete.addActionListener(new DeleteListener());
		jbtTrimToSize.addActionListener(new TrimToSizeListener());
	}
	
	class SearchListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String valueString=jtfValue.getText();
			try{
				if(valueString.length()==0){
					throw new NumberFormatException(); 
				}
				else{
					Integer value=Integer.parseInt(valueString);
					for (int i = jtfValue.getText().length() ; i != 0; i--)
						jtfValue.setText(jtfValue.getText().substring(0, i - 1));
					if(integer.contains(value))
						JOptionPane.showMessageDialog(null,"该元素在数组线性表里");
					else
						JOptionPane.showMessageDialog(null,"该元素不在数组线性表里");
				}
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "查找失败");
			}
		}
	}
	
	class InsertListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String valueString = jtfValue.getText();
			try{
				if(valueString.length()==0){
					throw new NumberFormatException(); 
				}
				else{
					for (int i = jtfValue.getText().length() ; i != 0; i--)
						jtfValue.setText(jtfValue.getText().substring(0, i - 1));
					Integer value=Integer.parseInt(valueString);
					String indexString=jtfIndex.getText();
					for (int i = jtfIndex.getText().length() ; i != 0; i--)
						jtfIndex.setText(jtfIndex.getText().substring(0, i - 1));
					if(indexString.length()==0){
						integer.add(value);
						paintArrayList.change(integer);
					}else{
						int index = Integer.parseInt(indexString);
						integer.add(index,value);
						paintArrayList.change(integer);
					}
				}
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "插入失败");
			}
		}
	}
	
	class DeleteListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String valueString = jtfValue.getText();
			for (int i = jtfValue.getText().length() ; i != 0; i--)
				jtfValue.setText(jtfValue.getText().substring(0, i - 1));
			try{
				if(valueString.length()==0){
					throw new NumberFormatException();
				}else{
					Integer value = Integer.parseInt(valueString);
					integer.remove(value);
					paintArrayList.change(integer);
				}
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "由于未输入删除元素，删除失败");
			}
		}
	}
	class TrimToSizeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			paintArrayList.setTrimTo(false);
		}
	}
	
	public static void main(String[] args) {
		ChangeArrayList frame = new ChangeArrayList();
		frame.setTitle("DisplayArrayList");
		frame.setSize(800,200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
