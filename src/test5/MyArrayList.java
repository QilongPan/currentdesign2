package test5;

public class MyArrayList<E> extends MyAbstractList<E> {
	
	public static final int INITIAL_CAPACITY = 4;
	@SuppressWarnings("unchecked")
	private E[] data = (E[])new Object[INITIAL_CAPACITY];
	
	public MyArrayList(){
	
	}
	
	public MyArrayList(E[] objects){
		for(int i = 0 ; i < objects.length ; i++) 
			add(objects[i]);
	}
	
	public void add(int index,E e) {
		ensureCapacity();
		for(int i = size-1 ; i >= index ; i--)
			data[i+1] = data[i];
		data[index] = e;
		size++;
		
	}
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity() {
		if(size >= data.length) {
			E[] newdata = ((E[])new Object[size*2+1]);
			System.arraycopy(data, 0, newdata, 0, size);
			data = newdata;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void clear() {
		data = ((E[])new Object[INITIAL_CAPACITY]) ;
		size = 0 ;
	}
	
	public boolean contains(E e) {
		for(int i = 0 ; i < size ; i++) {
			if(e.equals(data[i]))
				return true;
		}
		return false;
	}
	
	public E get(int index) {
		return data[index];
	}
	
	public int indexOf(E e) {
		for(int i = 0 ; i < size ; i++) 
			if(e.equals(data[i]))
				return i;
		return -1;
	}
	
	public int getDataSize() {
		return data.length;
	}
	
	public int lastIndexOf(E e) {
		for(int i = size-1 ; i >= 0 ; i--)
			if(e.equals(data[i]))
				return i;
		return -1;
	}
	
	public E remove(int index) {
		E e=data[index];
		for(int j = index ; j < size-1 ; j++) 
			data[j] = data[j+1];
		data[size-1] = null;
		size--;
		return e;
	}
	
	public E set(int index,E e) {
		E old = data[index];
		data[index] = e;
		return old;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		for(int i = 0; i < size ; i++) {
			result.append(data[i]);
			if(i < size-1)
				result.append(", ");
		}
		return result.toString()+"]";
	}
	
	public void trimTosize(){
		if(size != data.length){
			@SuppressWarnings("unchecked")
			E[] newdata = (E[])(new Object[size]);
			System.arraycopy(data, 0, newdata, 0, size);
			data = newdata;
		}
	}

}
