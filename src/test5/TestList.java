package test5;

public class TestList {
	
	public static void main(String[] args){
		
		MyList<String> list=new MyArrayList<String>();
		
		list.add("America");
		list.add(0,"Canada");
		list.add("Russia");
		list.add("France");
		list.add(2,"Germay");
		list.add(5,"Norway");
		list.remove("Canada");
		list.remove(2);
		System.out.println(list);
	}

}
