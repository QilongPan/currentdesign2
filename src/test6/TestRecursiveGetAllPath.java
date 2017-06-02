package test6;

public class TestRecursiveGetAllPath {
	private static int count = 0 ;
	private final static int ROW = 8;
	private final static int COLUMN = 8;
	private char[][] 	numbers = getMaze();
	private char[][] newCh = changeList(numbers) ;
	
	public static void main(String[] args) {
			TestRecursiveGetAllPath t = new TestRecursiveGetAllPath();
			System.out.println("�Թ�����ͼ��ʾ��1��ʾͨ��0��ʾ��ͨ,S��ʾ�߹���·): ");
			t.printmaze();
			t.recursionGetPath(1,1) ;
			if(count == 0) {
				System.out.println("û��·��") ;
			}
		}
	
	//����Թ�
	public void printmaze() {
		for(int i = 0 ; i < ROW ; i++) {
			for(int j = 0 ; j < COLUMN ; j++) {
				if(numbers[i][j] == '0') 
					System.out.print('0');
				else if(numbers[i][j] == '5')
					System.out.print("S");
				else
					System.out.print('1');
			}
			System.out.println();
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
			 
		/*	char[][] lists =   {{'1','0','0','0','0','0','0','0'},
	                {'0','1','0','0','0','0','0','0'},
	                {'0','1','1','0','0','0','0','0'},
	                {'0','0','1','1','1','1','1','0'},
	                {'0','1','1','1','0','0','1','0'},
	                {'0','0','1','1','0','0','1','0'},
	                {'0','1','1','1','1','1','1','1'},
	                {'0','0','0','0','0','0','0','1'}};
			return lists;*/
		}

	public void recursionGetPath(int x , int y) {

		newCh[x][y] = '5';
		numbers[x-1][y-1] = '5';
		if(x == ROW && y == COLUMN) {
			count++ ;
			System.out.println("��"+count+"���߷�");
			printmaze();
		}
		if(newCh[x][y+1] == '1')
			recursionGetPath(x,y+1);
		if(newCh[x+1][y] == '1')
			recursionGetPath(x+1,y);
		if(newCh[x-1][y] == '1')
			recursionGetPath(x-1,y);
		if(newCh[x][y-1] == '1')
			recursionGetPath(x,y-1);
		newCh[x][y] = '1';
		numbers[x-1][y-1] = '1' ;
	}
	
	//������װ���飬��ֹԽ��
		public char[][] changeList(char[][] numbers) {
			char[][] newCh = new char[numbers.length+2][numbers.length+2] ;
			//�ڸտ�ʼ��������������к�����
			for(int m = 0 ;m <newCh.length; m++){
				newCh[m][0]='0';
				newCh[0][m] = '0' ;
				newCh[numbers.length-1][m] = '0' ;
				newCh[m][numbers[0].length-1] = '0' ;
			}
				
			for(int i = 1 ; i < newCh.length-1 ; i++) {
				for(int j = 1 ; j < newCh[i].length-1 ; j++ ) {
					newCh[i][j] = numbers[i-1][j-1] ;
				}
			}
			return newCh ;
		}
		
}
