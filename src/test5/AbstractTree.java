package test5;

public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E>{
	
	public void inorder(){
	}
	
	public void postorder(){
		
	}
	
	public void preorder(){
		
	}
	
	public boolean isEmpty(){
		return getSize()==0;
	}
	
	@SuppressWarnings("rawtypes")
	public java.util.Iterator iterator(){
		return null;
	}

}
