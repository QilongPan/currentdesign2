package test5;

public interface Tree<E extends Comparable<E>> {
	
	public boolean search(E e);
	
	public boolean insert(E e);
	
	public boolean delete(E e);
	
	public void inorder();
	
	public void preorder();
	
	public void postorder();
	
	public int getSize();
	
	public boolean isEmpty();
	
	@SuppressWarnings("rawtypes")
	public java.util.Iterator iterator();
	
	//public void clear();

}
