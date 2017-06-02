package test4;

import java.util.ArrayList;
import java.util.List;

public class RecursiveCrossRiver {
	/*
	 * �ݹ���ũ�����
	 */
	private int[] state={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15} ;
	private int[] otherState={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15} ;
	boolean[] isVisited = new boolean[16];
	private final int Change_Farmer = 8; // ������1000
	private final int Change_Worf = 4; // ������0100
	private final int Change_Sheep = 2; // ������0010
	private final int Change_Food = 1; // ������0001
	private final int[] Change_State_Array = { Change_Farmer, Change_Worf,
			Change_Sheep, Change_Food };
	private int startValue = 100 ;
	private int planCount = 0;
	
	public void recursionGetPath(int locate) {
		int stateValue = state[locate];
		isVisited[locate] = true;
		state[locate] = startValue ;          //��ʾ�߹�
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
		// �õ�״̬ ũ�� �� �� �ײ�
		int[] stateArray = getEachState(state);
		//�ǵ���������һ���Լ���Ͱײ˵�����һ������
		if ((stateArray[0] != stateArray[1] && stateArray[1] == stateArray[2])
				|| (stateArray[0] != stateArray[2] && stateArray[2] == stateArray[3])) {
			flag = false;
		}
		return flag;
	}
	
	public  int[] getEachState(int state) {
		// ÿ����Ʒ��״̬ ����Ϊ ũ�� �� �� �ײ�
		int[] stateArray = new int[4];
		for (int i = 3; i >= 0; i--) {
			stateArray[i] = state & 1;           //   ��ͬλ���������ֶ�Ϊ1����Ϊ1������һ����Ϊ1����Ϊ0
			state = state >> 1;          // ����һλ
		}
		return stateArray;
	}

	public static void main(String[] args) {
		RecursiveCrossRiver rc = new RecursiveCrossRiver();
		rc.recursionGetPath(0);
	}
	
	public void printPath() {
		planCount++;
		System.out.println("--------------���ǵ�"+planCount+" �ַ���---------------");
		System.out.println("�ϰ� ��[ũ��, ��, ��, �ײ�]");
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
		String[] item = { "ũ��", "��", "��", "�ײ�" };
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
		System.out.print("�ϰ� ��" + south + "-----  ����: " + north);
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
