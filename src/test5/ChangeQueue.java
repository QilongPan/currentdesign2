package test5;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangeQueue extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5176648391004353362L;
	private MyQueue<Integer> integer = new MyQueue<Integer> () ;
	private JTextField jtfValue = new JTextField();
	private JButton jbtEnqueue = new JButton("Enqueue");
	private JButton jbtDequeue = new JButton("Dequeue");
	private PaintMyQueue<Integer> paintqueue = new PaintMyQueue<Integer>(integer);
	
	public ChangeQueue() {
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(1,4));
		p1.add(new JLabel("Enter a value:"));
		p1.add(jtfValue);
		p1.add(jbtEnqueue);
		p1.add(jbtDequeue);
		add(p1,BorderLayout.SOUTH);
		add(paintqueue,BorderLayout.CENTER);
		jbtEnqueue.addActionListener(new EnqueueListener());
		jbtDequeue.addActionListener(new DequeueListener());
	}
	
	class EnqueueListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String indexString = jtfValue.getText();
			Integer index = Integer.parseInt(indexString);
			integer.enqueue(index);
			paintqueue.change(integer);
		}
	}
	
	class DequeueListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			integer.dequeue();
			paintqueue.change(integer);
		}
	}
	
	public static void main(String[] args){
		ChangeQueue frame = new ChangeQueue();
		frame.setTitle("DisplayArrayList");
		frame.setSize(800,400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
