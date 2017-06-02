package test4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class CrossRiverFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CrossRiver cr = new CrossRiver();
	private int[] route = cr.getRoute() ;
	private int[] route2 =cr.getRoute2() ;
	
	CrossRiverFrame(){
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1,2,0,20));
		titlePanel.add(new JLabel("次数",JLabel.LEFT));
		titlePanel.add(new JLabel("状态",JLabel.LEFT));
		Object[][] dataRow = initData();
		Object[][] dataRow2 = initData2();
		String[] columnNames = {" ", "南岸","北岸"};
		JTable table = new JTable(dataRow, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//设置表格单元格的宽度
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(150);
		firsetColumn.setMaxWidth(150);
		TableColumn firsetColumn2 = table.getColumnModel().getColumn(1);
		firsetColumn2.setPreferredWidth(150);
		firsetColumn2.setMaxWidth(150);
		
		TableColumn firsetColumn3 = table.getColumnModel().getColumn(2);
		firsetColumn3.setPreferredWidth(150);
		firsetColumn3.setMaxWidth(150);
		
		JTable table2 = new JTable(dataRow2, columnNames);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn firsetColumn4 = table2.getColumnModel().getColumn(0);
		firsetColumn4.setPreferredWidth(150);
		firsetColumn4.setMaxWidth(150);
		TableColumn firsetColumn5 = table2.getColumnModel().getColumn(1);
		firsetColumn5.setPreferredWidth(150);
		firsetColumn5.setMaxWidth(150);
		TableColumn firsetColumn6 = table2.getColumnModel().getColumn(2);
		firsetColumn6.setPreferredWidth(150);
		firsetColumn6.setMaxWidth(150);
		JPanel tablePanel = new JPanel();
		tablePanel.add(table,BorderLayout.NORTH);
		tablePanel.add(new JLabel("第二种"),BorderLayout.CENTER);
		tablePanel.add(table2,BorderLayout.SOUTH);
		this.add(titlePanel,BorderLayout.NORTH);
		this.add(tablePanel,BorderLayout.CENTER);
	}
	
	private Object[][] initData(){
		for(int i = 0 ; i < route.length ; i++) {
			System.out.print(route[i]+"  ");
		}
		String[] item = { "农夫 ", "狼", "羊", "白菜" };
		Object[][]  dataRow = new Object[8][3];
		int count = 0 ; 
		dataRow[count][0] = "0";		
		dataRow[count][1] = "[农夫,狼,羊,白菜]";		
		dataRow[count++][2] = "";		
		int index = 0;
		while (route[index] != -1) {
			List<String> south = new ArrayList<String>();
			List<String> north = new ArrayList<String>();
			@SuppressWarnings("static-access")
			int[] stateArray = cr.getEachState(route[index]);    //获得状态数组
			int i = 0; 
			for ( ; i < stateArray.length; i++) {
				if (stateArray[i] == 0) {
					south.add(item[i]);
				} else {
					north.add(item[i]);
				}
			}
			//装载数据
			dataRow[count][0] = count;		
			//如果south不为空
			if(!south.toString().equals("[]")){
				dataRow[count][1] = south.toString();	
			}else{
				dataRow[count][1] = "";	
			}
			dataRow[count++][2] = north.toString();
			index = route[index];
		}
		return dataRow;
	}
	
	private Object[][] initData2(){
		String[] item = { "农夫 ", "狼", "羊", "白菜" };
		Object[][]  dataRow = new Object[8][3];
		int count = 0 ; 
		dataRow[count][0] = "0";		
		dataRow[count][1] = "[农夫,狼,羊,白菜]";		
		dataRow[count++][2] = "";		
		int index = 0;
		while (route2[index] != -1) {
			List<String> south = new ArrayList<String>();
			List<String> north = new ArrayList<String>();
			@SuppressWarnings("static-access")
			int[] stateArray = cr.getEachState(route2[index]);    //获得状态数组
			int i = 0; 
			for ( ; i < stateArray.length; i++) {
				if (stateArray[i] == 0) {
					south.add(item[i]);
				} else {
					north.add(item[i]);
				}
			}
			//装载数据
			dataRow[count][0] = count;		
			//如果south不为空
			if(!south.toString().equals("[]")){
				dataRow[count][1] = south.toString();	
			}else{
				dataRow[count][1] = "";	
			}
			dataRow[count++][2] = north.toString();
			index = route2[index];
		}
		return dataRow;
	}
	
	
}
