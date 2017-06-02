package test5;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PaintMyStack<E> extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyStack<E> integer ;
	
	public PaintMyStack() {

	}
	
	public PaintMyStack(MyStack<E> integer ) {
		this.integer = integer;
	}
	
	public void change(MyStack<E> integer) {
		this.integer = integer;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		String topString = "top";
		int startWeight = 100;
		int startHeight = 300;
		int endWeight = 150;
		if(integer.getSize()>0){
			for(int i = 0; i < integer.getSize(); i++) {
				g.drawLine(startWeight,startHeight,endWeight,startHeight);
				g.drawLine(startWeight,startHeight-20,endWeight,startHeight-20);
				g.drawLine(startWeight,startHeight,startWeight,startHeight-20);
				g.drawLine(endWeight,startHeight,endWeight,startHeight-20);
				String valueString = ""+integer.getElement(i);
				g.drawString(valueString, startWeight+20, startHeight-10);
				startHeight = startHeight-20;
			}
		}
		g.drawString(topString,startWeight-80,startHeight);
		g.drawLine(startWeight-60, startHeight, startWeight, startHeight);
		g.drawLine(startWeight, startHeight, startWeight-20, startHeight-10);
		g.drawLine(startWeight, startHeight, startWeight-20, startHeight+10);
	}


}
