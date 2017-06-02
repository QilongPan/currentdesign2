package test5;

public interface MyList<E> {
	
	public void add(E e);
	
	public void add(int index,E e);
	
	public void clear();
	
	public boolean contains(E e);
	
	public E get(int index);
	
	public int indexOf(E e);
	
	public boolean isEmpty();
	
	public int lastIndexOf(E e);
	
	public boolean remove(E e);
	
	public int size();
	
	public E remove(int index);
	
	public E set(int index,E e);

}
