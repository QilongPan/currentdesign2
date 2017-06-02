package test5;
import java.awt.*;

import javax.swing.*;

public class PaintArrayList extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyArrayList<Integer> integer;
	private boolean trimTo=true;
	
	public PaintArrayList(){
		integer=new MyArrayList<Integer>();
	}
	
	public PaintArrayList(MyArrayList<Integer> integer){
		this.integer=integer;
	}
	
	public void change(MyArrayList<Integer> integer){
		this.integer=integer;
		repaint();
	}
	
	public boolean isTrimTo() {
		return trimTo;
	}

	public void setTrimTo(boolean trimTo) {
		this.trimTo = trimTo;
		repaint();
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int startX=10;
		int startY=50;
		int addX=40;
		int addY=30;
		for(int i=0;i<integer.size();i++){
			g.drawLine(startX,startY,startX+addX,startY);
			g.drawLine(startX,startY+addY,startX+addX,startY+addY);
			g.drawLine(startX,startY,startX,startY+addY);
			g.drawLine(startX+addX,startY,startX+addX,startY+addY);
			String valueString=""+integer.get(i);
			g.drawString(valueString, startX+15, startY+20);
			startX=startX+addX;
		}
		if(trimTo){
			for(int i=integer.size();i<integer.getDataSize();i++){
				g.drawLine(startX,startY,startX+addX,startY);
				g.drawLine(startX,startY+addY,startX+addX,startY+addY);
				g.drawLine(startX,startY,startX,startY+addY);
				g.drawLine(startX+addX,startY,startX+addX,startY+addY);
				g.drawLine(startX+addX, startY, startX, startY+addY);
				startX=startX+addX;
			}
		}
		
		
		if(integer.size()==0){
			g.drawString("Array list is empty",startX-9,startY-10);
		}
		else{
			int emptySize=integer.getDataSize()-integer.size();
			g.drawString("size*"+integer.size()+" the empty is "+emptySize,startX-9,startY-10);
		}
		
	}
	

}
