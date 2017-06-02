package test4;

public class CrossRiver {

	// ʮ����ת��Ϊ�����ƶ�Ӧ ũ�� �� �� �ײ�
	private final int Total_States = 16;// �ı�״̬ʱ�����Ҫ��ֵ
	private final int Change_Farmer = 8; // ������1000
	private final int Change_Worf = 4; // ������0100
	private final int Change_Sheep = 2; // ������0010
	private final int Change_Food = 1; // ������0001
	private final int[] Change_State_Array = { Change_Farmer, Change_Worf,
			Change_Sheep, Change_Food };

	private Integer[] vertices = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	
//�õ���ȫ��״̬
	public int[][] getEdges() {
		int[][] edges = new int[Total_States][4];
		for(int i = 0 ; i < edges.length ; i++) 
			for(int j = 0 ;j< edges[i].length ;j++) 
				edges[i][j] = -1 ;              //��ʼ������Ϊ-1
		for(int locate = 0 ; locate < 16 ;locate++) {
			int nextState = -1; 
			for (int i = 0; i < 4; i++) {
				if (i == 0) 
					//ũ���Լ���
					nextState = locate ^ Change_State_Array[i];          //   ������Ӧ�Ķ���λ����ʱ�����Ϊ1
				else 
					nextState = locate ^ Change_State_Array[0]         //ũ���߲��������
							^ Change_State_Array[i];
				if (isSafe(nextState))
					edges[locate][i] = nextState ;                 //�����һ��״̬��ȫ����������� 
			}
		}
		return edges ;
	}

	//�õ�״̬ͼ 
	public int[] getRoute2() {
		CrossRiver cr = new CrossRiver();
		int[][] edges = cr.getEdges() ;
		int[][] allEdges = cr.getAllEdges(edges);
		Graph<Integer> graph = new UnweightedGraph<Integer>(allEdges,cr.getVertices()) ;
		AbstractGraph<Integer>.Tree dfs = graph.dfs(0);
		java.util.List<Integer> searchOrders = dfs.getSearchOrders();
		int[] route2 = new int[16];
		for(int i = 0 ;i<16 ; i++) {
			route2[i] = -1;
		}
		int count = 0 ;
		int allCount = 1 ;
		while(allCount <searchOrders.size()-2 ) {
			route2[count]=graph.getVertex(searchOrders.get(allCount));
			count = searchOrders.get(allCount) ;
			allCount++ ;
		}
		return route2;
	}
	
	public int[] getRoute() {
		CrossRiver cr = new CrossRiver();
		int[][] edges = cr.getEdges() ;
		int[][] allEdges = cr.getAllEdges(edges);
		Graph<Integer> graph = new UnweightedGraph<Integer>(allEdges,cr.getVertices()) ;
		AbstractGraph<Integer>.Tree dfs = graph.dfs(15);
		java.util.List<Integer> searchOrders = dfs.getSearchOrders();
		int[] route2 = new int[16];
		for(int i = 0 ;i<16 ; i++)
			route2[i] = -1;
		int count = 0 ;
		int allCount = searchOrders.size()-3 ;
		while(allCount >=0 ) {
			route2[count]=graph.getVertex(searchOrders.get(allCount));
			count = searchOrders.get(allCount) ;
			allCount-- ;
		}
		return route2;
	}
	
	public Integer[] getVertices() {
		return vertices ;
	}
	
	//�õ�ͼ�ı�
	public int[][] getAllEdges(int[][] edges) {
		int count= 0 ;
		int[][] allEdges = new int[40][2] ;
		for(int i = 0 ; i <edges.length ; i++) {
			for(int j = 0 ; j <edges[i].length ;j++) {
				if(edges[i][j] != -1) {
					allEdges[count][0] = i ;
					allEdges[count][1] = edges[i][j] ;
					count++ ;
				}
			}
		}
		
		for(int i = 0 ; i < allEdges.length ;i++) {
			for(int j = 0 ; j < allEdges[i].length ; j++) {
				System.out.print(allEdges[i][j]+"  ");
			}
			System.out.println();
		}
		
		
		return allEdges ;
	}
	// �жϵ�ǰ״̬�Ƿ�ȫ
	private static boolean isSafe(int state) {
		boolean flag = true;
		// �õ�״̬ ũ�� �� �� �ײ�
		int[] stateArray = getEachState(state);
		//�ǵ���������һ���Լ���Ͱײ˵�����һ������
		if ((stateArray[0] != stateArray[1] && stateArray[1] == stateArray[2])
				|| (stateArray[0] != stateArray[2] && stateArray[2] == stateArray[3])) {
			flag = false;
		}
		return flag;
	}

	// �õ�ÿһ����Ʒ��״̬ ����һ�������з���
	public static int[] getEachState(int state) {
		// ÿ����Ʒ��״̬ ����Ϊ ũ�� �� �� �ײ�
		int[] stateArray = new int[4];
		for (int i = 3; i >= 0; i--) {
			stateArray[i] = state & 1;           //   ��ͬλ���������ֶ�Ϊ1����Ϊ1������һ����Ϊ1����Ϊ0
			state = state >> 1;          // ����һλ
		}
		return stateArray;
	}

}

