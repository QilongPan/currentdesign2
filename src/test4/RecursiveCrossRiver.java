package test4;

import java.util.ArrayList;
import java.util.List;

public class RecursiveCrossRiver {
	/*
	 * 递归解决农夫过河
	 */
	private int[] state={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15} ;
	private int[] otherState={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15} ;
	boolean[] isVisited = new boolean[16];
	private final int Change_Farmer = 8; // 二进制1000
	private final int Change_Worf = 4; // 二进制0100
	private final int Change_Sheep = 2; // 二进制0010
	private final int Change_Food = 1; // 二进制0001
	private final int[] Change_State_Array = { Change_Farmer, Change_Worf,
			Change_Sheep, Change_Food };
	private int startValue = 100 ;
	private int planCount = 0;
	
	public void recursionGetPath(int locate) {
		int stateValue = state[locate];
		isVisited[locate] = true;
		state[locate] = startValue ;          //表示走过
		startValue++;
		if(locate == 15) {
			printPath();
		}
		if(isSafe(locate ^ Change_State_Array[0]) &&!isVisited[locate ^ Change_State_Array[0]])  {
			recursionGetPath(locate ^ Change_State_Array[0]);
		}
		if(isSafe(locate ^ Change_State_Array[0]^ Change_State_Array[1])
				&&!isVisited[locate ^ Change_State_Array[0]^ Change_State_Array[1]])
			recursionGetPath(locate ^ Change_State_Array[0]^ Change_State_Array[1]);
		if(isSafe(locate ^ Change_State_Array[0]^ Change_State_Array[2])
				&&!isVisited[locate ^ Change_State_Array[0]^ Change_State_Array[2]])
			recursionGetPath(locate ^ Change_State_Array[0]^ Change_State_Array[2]);
		if(isSafe(locate ^ Change_State_Array[0]^ Change_State_Array[3])
				&&!isVisited[locate ^ Change_State_Array[0]^ Change_State_Array[3]])
			recursionGetPath(locate ^ Change_State_Array[0]^ Change_State_Array[3]);
		state[locate] =stateValue ;
		isVisited[locate] = false;
	}
	
	private  boolean isSafe(int state) {
		boolean flag = true;
		// 得到状态 农夫 狼 羊 白菜
		int[] stateArray = getEachState(state);
		//狼单独和羊在一起以及羊和白菜单独在一起的情况
		if ((stateArray[0] != stateArray[1] && stateArray[1] == stateArray[2])
				|| (stateArray[0] != stateArray[2] && stateArray[2] == stateArray[3])) {
			flag = false;
		}
		return flag;
	}
	
	public  int[] getEachState(int state) {
		// 每种物品的状态 依次为 农夫 狼 羊 白菜
		int[] stateArray = new int[4];
		for (int i = 3; i >= 0; i--) {
			stateArray[i] = state & 1;           //   相同位的两个数字都为1，则为1；若有一个不为1，则为0
			state = state >> 1;          // 右移一位
		}
		return stateArray;
	}

	public static void main(String[] args) {
		RecursiveCrossRiver rc = new RecursiveCrossRiver();
		rc.recursionGetPath(0);
	}
	
	public void printPath() {
		planCount++;
		System.out.println("--------------这是第"+planCount+" 种方案---------------");
		System.out.println("南岸 ：[农夫, 狼, 羊, 白菜]");
		int minValue = 100;
		int maxValue = getMaxValue();
		while(minValue <= maxValue){
			for(int i = 0 ; i < state.length ; i++) {
				if(state[i] == minValue)
					printAllState(otherState[i]);
			}
			minValue++;
		}
		System.out.println();
	}
	
	
	public void printAllState(int state) {
		String[] item = { "农夫", "狼", "羊", "白菜" };
		List<String> south = new ArrayList<String>();
		List<String> north = new ArrayList<String>();
		int[] stateArray = getEachState(state);
		for (int i = 0; i < stateArray.length; i++) {
			if (stateArray[i] == 0) {
				south.add(item[i]);
			} else {
				north.add(item[i]);
			}
		}
		System.out.print("南岸 ：" + south + "-----  北岸: " + north);
		System.out.println();
	}
	
	public int getMaxValue() {
		int maxValue = state[0] ;
		for(int i = 0 ; i < state.length ; i++) {
			if(state[i] > maxValue) {
				maxValue = state[i];
			}
		}
		return maxValue ;
	}
	
	
	
	
	
	
}
