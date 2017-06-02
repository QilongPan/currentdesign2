package test6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TestMaze extends JFrame {
	/**
	 * 
	 */
	public Point start= new Point(0,0,1);   //起点
    public Point end= new Point(ROW-1,COLUMN-1,1);  //终点
    private Stack<Point> path;
    private Stack<Point> savePath = new Stack<Point>() ;
    private Stack<Point> recursionPath = new Stack<Point>();
	private final static char SUCPATH = 'Y';
	private final static char DIDPATH = 'N';
	private static final long serialVersionUID = 1L;
	private final static int ROW = 8;
	private final static int COLUMN = 8;
	private JPanel p= new JPanel(new GridLayout(ROW, COLUMN, 0, 0));
	private char[][] 	numbers = getMaze();
	private JButton[][] jbts = getJBts();
	private JPanel p1 = new JPanel(new BorderLayout());
	private JPanel p2 = new JPanel(new GridLayout(1, 5, 0, 0));
	private JButton newMaze = new JButton("重新生成迷宫");
	private JButton startMaze = new JButton("递归走迷宫");
	private JButton saveData = new JButton("生成数据");
	private JButton takeOutData = new JButton("取出数据");
	private JButton stackMaze = new JButton("栈走迷宫");
	private static boolean con ;
	
	public TestMaze() {
		p2.add(startMaze);
		p2.add(stackMaze);
		p2.add(newMaze);
		p2.add(saveData);
		p2.add(takeOutData);
		p1.add(p2, BorderLayout.NORTH);
		for (int i = 0 ; i < ROW ; i++)
			for (int j = 0 ; j < COLUMN ; j++) {
				p.add(jbts[i][j]);
			}
		p1.add(p, BorderLayout.CENTER);
		add(p1);
		startMaze.addActionListener(new StartMazeListener());
		newMaze.addActionListener(new NewMazeListener());
		stackMaze.addActionListener(new StackMazeListener());
		saveData.addActionListener(new SaveDataListener()) ;
		takeOutData.addActionListener(new TakeOutDataListener());
	}

	class TakeOutDataListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String str = "" ;
			try {
				FileInputStream input = new FileInputStream("test.dat");
				int  value ;
				int count = 0;
				while((value=input.read()) != -1) {
					count++;
					str = str+value +" ";
					if(count == 3) {
						str = str+"\n";
						count = 0;
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 JOptionPane.showMessageDialog(null,str) ;
		}
	}
	
	class SaveDataListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(savePath.size() == 0 && recursionPath.size() == 0) {
				 JOptionPane.showMessageDialog(null,"没有成功走迷宫") ;
			}
			else{
				if(savePath.size() != 0) {
					try {
						FileOutputStream output = new FileOutputStream("test.dat") ;
						 while(!savePath.empty()){     
						    	Point newPoint = savePath.pop();
						    	output.write(newPoint.getX());
						    	output.write(newPoint.getY());
						    	output.write(newPoint.getDirection());
						    }
						 JOptionPane.showMessageDialog(null,"保存成功") ;
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						Stack<Point> recuPath = getNewStack(recursionPath);
						FileOutputStream output = new FileOutputStream("test.dat") ;
						 while(!recuPath.empty()){     
						    	Point newPoint = recuPath.pop();
						    	output.write(newPoint.getX());
						    	output.write(newPoint.getY());
						    	output.write(newPoint.getDirection());
						    }
						 JOptionPane.showMessageDialog(null,"保存成功") ;
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	//栈走迷宫
	class StackMazeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			con=getPath() ;
			if(con) {
				printPath();
				numbers = getMaze();
			}
			else{
				JOptionPane.showMessageDialog(null,"没有通路") ;
			}
		}
	}
	
	//产生新的迷宫
	class NewMazeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			numbers = getMaze();
			 for(int i = 0 ; i <numbers.length ; i++) { 
				  for( int j = 0 ; j <numbers[i].length ; j++) {
					  if (numbers[i][j] == '0')
							jbts[i][j].setBackground(Color.red);
						else
							jbts[i][j].setBackground(Color.white);
				  }
			}
		}
	}
	
	//递归走迷宫
	class StartMazeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			con=traverse(0, 0);
			if(con) {
					printMaze() ;
			}
			else{
				JOptionPane.showMessageDialog(null,"没有通路") ;
			}
		}
	}

	public char[][] getMaze() {
		char[][] lists = new char[ROW][COLUMN] ; 
		  for(int i = 0 ; i < lists.length ; i++) { 
			  for( int j = 0 ; j < lists[i].length ; j++) {
				 int randNumber = (int)(Math.random()*2) ;
				 if(randNumber == 1)
					 lists[i][j] = '1' ;
				 else
					 lists[i][j]='0';
				  }
			  } 
		  lists[0][0] = '1' ;
		  lists[ROW-1][COLUMN-1] = '1' ; 
		  lists[0][1] = '1' ;
		  lists[1][1] = '1' ;
		  lists[1][2] = '1' ;
		  lists[2][2] = '1' ;
		  lists[3][2] = '1' ;
		  lists[3][3] = '1' ;
		  lists[4][3] = '1' ;
		  lists[4][4] = '1' ;
		  
		  return lists ;
	}

	public JButton[][] getJBts() {
		JButton[][] jbts = new JButton[ROW][COLUMN];
		for (int i = 0; i < ROW; i++)
			for (int j = 0; j < COLUMN; j++) {
				if( i == 0 && j == 0) {
					jbts[i][j] = new JButton("入口 ");
				}
				else if( i == ROW-1 && j == COLUMN-1) {
					jbts[i][j] = new JButton("出口 ");
				}
				else
					jbts[i][j] = new JButton();
				if (numbers[i][j] == '0')
					jbts[i][j].setBackground(Color.red);
				else
					jbts[i][j].setBackground(Color.white);
			}
		return jbts;
	}

	//递归 输出走出迷宫的线路
	public void printMaze() {
		for (int i = 0 ; i < ROW ; i++)
			for (int j = 0 ; j < COLUMN ; j++) 
				if (numbers[i][j] == SUCPATH )
					jbts[i][j].setBackground(Color.gray);
		getAllPoint(numbers);
	}

	// 确定坐标能否通过
	public boolean valid(int row, int column) {
		boolean result = false;
		if (row < ROW && column < COLUMN && row >= 0 && column >= 0)
			if (numbers[row][column] == '1')
				result = true;
		return result;
	}

	public static void main(String[] args) {
		TestMaze frame = new TestMaze();
		frame.setSize(600,600);
		frame.setTitle("Hangman Game");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// 用递归走迷宫
	public boolean traverse(int row, int column) {
		boolean contin = false;
		if (valid(row, column)) { // 可以通过
			numbers[row][column] = DIDPATH ;
			if (row == numbers.length - 1 && column == numbers[0].length - 1) {
				contin = true; // 到达出口
			} else {
				if(!contin)
					contin = traverse(row + 1, column); // 向下走
				if (!contin)
					contin = traverse(row, column + 1); // 向右走;
				if (!contin)
					contin = traverse(row - 1, column); // 向上走
				if (!contin)
					contin = traverse(row, column - 1); // 向左走
			}
			if (contin)
				numbers[row][column] = SUCPATH ;
		}
		return contin;
	}
	
	public void printmaze() {
		for(int i = 0 ; i < ROW ; i++) {
			for(int j = 0 ; j < COLUMN ; j++) {
				if(numbers[i][j] == '0') 
					System.out.print('0');
				else if(numbers[i][j] == '5')
					System.out.print("o");
				else
					System.out.print('1');
			}
			System.out.println();
		}
	}
	
	public char returnChar(Point point){
        if(point.getX()>=0&&point.getX()<ROW&&point.getY()>=0&&point.getY()<COLUMN)
          return this.numbers[point.getX()][point.getY()];
        else
          return '#';
    }
	
	public void replacePlace(Point point, char ch){  //更改特定位置处的字符
		 numbers[point.getX()][point.getY()] = ch;
	    }
	
	 //栈求路径
    public boolean getPath() {                
        path = new Stack<Point>();
        start.setDirection(1);
        path.push(start);
        replacePlace(start, DIDPATH );           //防止回退，设置为N
        while(!path.empty()){
            Point nowPoint = path.peek();   
            if(nowPoint.getX() ==end.getX() && nowPoint.getY() == end.getY())
                return true;
            Point temp = new Point();        //存放下一个可走的位置
            int find = 0;                    //标志   是否可向下走
            while(nowPoint.getDirection()<5&&find == 0){
                switch(nowPoint.getDirection()){
                case 1:temp = new Point(nowPoint.getX(),nowPoint.getY()-1,1); break;  //取得当前位置左边的位置  
                case 2:temp = new Point(nowPoint.getX()+1,nowPoint.getY(),1); break;//取得当前位置下边的位置  
                case 3:temp = new Point(nowPoint.getX(),nowPoint.getY()+1,1); break;//取得当前位置右边的位置  
                case 4:temp = new Point(nowPoint.getX()-1,nowPoint.getY(),1); break;//取得当前位置上边的位置 
                }
                nowPoint.addDirection();                    //指向下一个需要验证的点
                if(returnChar(temp)=='1') {
                	find = 1;    //如果能向下走则置为1
                	break ;
                }
            }
                if(find ==1){                                    //如果可走就进栈
                    replacePlace(temp,DIDPATH );           //设置成X 防止回走
                    path.push(temp);
                }else{                                         //如果不可走就退栈
                    replacePlace(nowPoint, '1');     
                    path.pop();
                }
            }  
        return false;
    }
    
    //用栈显示迷宫
	public void printPath() {
		Stack<Point> tempPath = new Stack<Point>();
		while (!path.empty()) {
			Point newPoint = path.pop();
			tempPath.push(newPoint);
			savePath.push(newPoint);
		}
		while (!tempPath.empty()) 
			jbts[tempPath.peek().getX()][tempPath.pop().getY()]
					.setBackground(Color.gray);
	}
	
	//使栈反过来
	public Stack<Point> getNewStack(Stack<Point> stack) {
		Stack<Point> tempPath = new Stack<Point>();
		while (!stack.empty()) {
			Point newPoint = stack.pop();
			tempPath.push(newPoint);
		}
		return tempPath ;
	}
	
	//递归存位置及方向
	public void getAllPoint(char[][] ch) {
		for(int i = 0 ; i < ch.length ; i++ ) {
			for(int j = 0 ; j < ch[i].length ; j++) {
				if(ch[i][j] == SUCPATH ) {
						Point po = new Point(i,j) ;
						if(!recursionPath.isEmpty()) {
							Point lastPo = recursionPath.pop();
							if(lastPo.getX() == i &&lastPo.getY()>j) {  //向上
								addPointToStack(2,lastPo,po);
							}
							else if(lastPo.getX() == i && lastPo.getY() < j) {  //向下
								addPointToStack(4,lastPo,po);
							}
							else if(lastPo.getY() == j && lastPo.getX() > i) {   //向左
								addPointToStack(1,lastPo,po);
							}
							else {    //向右
								addPointToStack(3,lastPo,po);
							}
							
						}
						else{
							recursionPath.push(po);
						}
				}
			}
		}
	}
	
	//将通过的点加入栈
	public void addPointToStack(int direction,Point lastPo,Point po) {
		lastPo.setDirection(direction) ;
    	recursionPath.push(lastPo);
		recursionPath.push(po);
	}
	
}
