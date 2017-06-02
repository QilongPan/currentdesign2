package test5;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChangeStack extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyStack<Integer> stack = new MyStack<Integer>(4);
	private JTextField jtfValue = new JTextField();
	private JButton jbtPush = new JButton("Push");
	private JButton jbtPop = new JButton("Pop");
	private PaintMyStack<Integer> paintStack = new PaintMyStack<Integer>(stack);
	
	public ChangeStack() {
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(1,4));
		p1.add(new JLabel("Enter a value:"));
		p1.add(jtfValue);
		p1.add(jbtPush);
		p1.add(jbtPop);
		add(p1,BorderLayout.SOUTH);
		add(paintStack,BorderLayout.CENTER);
		jbtPush.addActionListener(new PushListener());
		jbtPop.addActionListener(new PopListener());
	}
	
	class PushListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String valueString = jtfValue.getText();
			int value = Integer.parseInt(valueString);
			stack.push(value);
			paintStack.change(stack);
		}
	}
	
	class PopListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stack.pop();
			paintStack.change(stack);
		}
	}

	public static void main(String[] args){
		ChangeStack frame=new ChangeStack();
		frame.setTitle("DisplayStack");
		frame.setSize(400,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
