package test5;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PaintMyQueue<E> extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MyQueue<E> integer ;
	
	public PaintMyQueue(MyQueue<E> integer) {
		this.integer = integer;
	}
	
	public void change(MyQueue<E> integer) {
		this.integer = integer;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		String headString = "head";
		String tailString =  "tail";
		int startWeight1 = 15;
		int startHeight1 = 70;
		int addHeight = 20;
		int addWeight = 50;
		int headStartWeight = 10;
		int headStartHeight = 50;
		int arrowsStartWeight1 = 12;
		int arrowsStartWeight2 = 17;
		int arrowsStratHeight = 65;
		int startWeight2 = 13;
		int startHeight2 = 50;
		g.drawString(headString,headStartWeight,headStartHeight);
		g.drawLine(startWeight2, startHeight2, startWeight1, startHeight1);
		g.drawLine(arrowsStartWeight1,arrowsStratHeight,startWeight1,startHeight1);
		g.drawLine(arrowsStartWeight2,arrowsStratHeight, startWeight1, startHeight1);
		for(int i=0;i<integer.getSize();i++) {
			g.drawLine(startWeight1, startHeight1, startWeight1, startHeight1+addHeight);
			g.drawLine(startWeight1, startHeight1, startWeight1+addWeight, startHeight1);
			g.drawLine(startWeight1+addWeight, startHeight1, startWeight1+addWeight, startHeight1+addHeight);
			g.drawLine(startWeight1, startHeight1+addHeight, startWeight1+addWeight, startHeight1+addHeight);
			String integerString=""+integer.getElements(i);
			g.drawString(integerString,startWeight1+10, startHeight1+13);
			startWeight1 = startWeight1+addWeight;
		}
		if(integer.getSize() != 0){
			g.drawString(tailString,startWeight1,headStartHeight);
			g.drawLine(startWeight1, headStartHeight, startWeight1-addWeight+5, startHeight1);
			g.drawLine(startWeight1-addWeight+5, startHeight1,startWeight1-addWeight+6,startHeight1-10);
			g.drawLine(startWeight1-addWeight+5, startHeight1,startWeight1-addWeight+12,startHeight1+3);
		}
		
	}

}
