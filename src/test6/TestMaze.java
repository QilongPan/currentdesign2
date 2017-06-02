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
	public Point start= new Point(0,0,1);   //���
    public Point end= new Point(ROW-1,COLUMN-1,1);  //�յ�
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
	private JButton newMaze = new JButton("���������Թ�");
	private JButton startMaze = new JButton("�ݹ����Թ�");
	private JButton saveData = new JButton("��������");
	private JButton takeOutData = new JButton("ȡ������");
	private JButton stackMaze = new JButton("ջ���Թ�");
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
				 JOptionPane.showMessageDialog(null,"û�гɹ����Թ�") ;
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
						 JOptionPane.showMessageDialog(null,"����ɹ�") ;
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
						 JOptionPane.showMessageDialog(null,"����ɹ�") ;
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
	
	//ջ���Թ�
	class StackMazeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			con=getPath() ;
			if(con) {
				printPath();
				numbers = getMaze();
			}
			else{
				JOptionPane.showMessageDialog(null,"û��ͨ·") ;
			}
		}
	}
	
	//�����µ��Թ�
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
	
	//�ݹ����Թ�
	class StartMazeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			con=traverse(0, 0);
			if(con) {
					printMaze() ;
			}
			else{
				JOptionPane.showMessageDialog(null,"û��ͨ·") ;
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
					jbts[i][j] = new JButton("��� ");
				}
				else if( i == ROW-1 && j == COLUMN-1) {
					jbts[i][j] = new JButton("���� ");
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

	//�ݹ� ����߳��Թ�����·
	public void printMaze() {
		for (int i = 0 ; i < ROW ; i++)
			for (int j = 0 ; j < COLUMN ; j++) 
				if (numbers[i][j] == SUCPATH )
					jbts[i][j].setBackground(Color.gray);
		getAllPoint(numbers);
	}

	// ȷ�������ܷ�ͨ��
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

	// �õݹ����Թ�
	public boolean traverse(int row, int column) {
		boolean contin = false;
		if (valid(row, column)) { // ����ͨ��
			numbers[row][column] = DIDPATH ;
			if (row == numbers.length - 1 && column == numbers[0].length - 1) {
				contin = true; // �������
			} else {
				if(!contin)
					contin = traverse(row + 1, column); // ������
				if (!contin)
					contin = traverse(row, column + 1); // ������;
				if (!contin)
					contin = traverse(row - 1, column); // ������
				if (!contin)
					contin = traverse(row, column - 1); // ������
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
	
	public void replacePlace(Point point, char ch){  //�����ض�λ�ô����ַ�
		 numbers[point.getX()][point.getY()] = ch;
	    }
	
	 //ջ��·��
    public boolean getPath() {                
        path = new Stack<Point>();
        start.setDirection(1);
        path.push(start);
        replacePlace(start, DIDPATH );           //��ֹ���ˣ�����ΪN
        while(!path.empty()){
            Point nowPoint = path.peek();   
            if(nowPoint.getX() ==end.getX() && nowPoint.getY() == end.getY())
                return true;
            Point temp = new Point();        //�����һ�����ߵ�λ��
            int find = 0;                    //��־   �Ƿ��������
            while(nowPoint.getDirection()<5&&find == 0){
                switch(nowPoint.getDirection()){
                case 1:temp = new Point(nowPoint.getX(),nowPoint.getY()-1,1); break;  //ȡ�õ�ǰλ����ߵ�λ��  
                case 2:temp = new Point(nowPoint.getX()+1,nowPoint.getY(),1); break;//ȡ�õ�ǰλ���±ߵ�λ��  
                case 3:temp = new Point(nowPoint.getX(),nowPoint.getY()+1,1); break;//ȡ�õ�ǰλ���ұߵ�λ��  
                case 4:temp = new Point(nowPoint.getX()-1,nowPoint.getY(),1); break;//ȡ�õ�ǰλ���ϱߵ�λ�� 
                }
                nowPoint.addDirection();                    //ָ����һ����Ҫ��֤�ĵ�
                if(returnChar(temp)=='1') {
                	find = 1;    //���������������Ϊ1
                	break ;
                }
            }
                if(find ==1){                                    //������߾ͽ�ջ
                    replacePlace(temp,DIDPATH );           //���ó�X ��ֹ����
                    path.push(temp);
                }else{                                         //��������߾���ջ
                    replacePlace(nowPoint, '1');     
                    path.pop();
                }
            }  
        return false;
    }
    
    //��ջ��ʾ�Թ�
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
	
	//ʹջ������
	public Stack<Point> getNewStack(Stack<Point> stack) {
		Stack<Point> tempPath = new Stack<Point>();
		while (!stack.empty()) {
			Point newPoint = stack.pop();
			tempPath.push(newPoint);
		}
		return tempPath ;
	}
	
	//�ݹ��λ�ü�����
	public void getAllPoint(char[][] ch) {
		for(int i = 0 ; i < ch.length ; i++ ) {
			for(int j = 0 ; j < ch[i].length ; j++) {
				if(ch[i][j] == SUCPATH ) {
						Point po = new Point(i,j) ;
						if(!recursionPath.isEmpty()) {
							Point lastPo = recursionPath.pop();
							if(lastPo.getX() == i &&lastPo.getY()>j) {  //����
								addPointToStack(2,lastPo,po);
							}
							else if(lastPo.getX() == i && lastPo.getY() < j) {  //����
								addPointToStack(4,lastPo,po);
							}
							else if(lastPo.getY() == j && lastPo.getX() > i) {   //����
								addPointToStack(1,lastPo,po);
							}
							else {    //����
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
	
	//��ͨ���ĵ����ջ
	public void addPointToStack(int direction,Point lastPo,Point po) {
		lastPo.setDirection(direction) ;
    	recursionPath.push(lastPo);
		recursionPath.push(po);
	}
	
}
