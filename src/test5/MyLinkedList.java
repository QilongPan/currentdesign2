package test5;

public class MyLinkedList<E> extends MyAbstractList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	
	
	public MyLinkedList() {
		
	}
	
	public MyLinkedList(E[] objects) {
		super(objects);
	}
	
	public E getFirst() {
		if(size==0)
			return null;
		else
			return head.element;
	}
	
	public E getLast() {
		if(size==0)
			return null;
		else
			return tail.element;
	}
	
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e);
		//将现在的头部指向原来的头部
		newNode.next=head;
		head=newNode;
		size++;
		if(tail == null)
			tail=head;
	}

	public Node<E> getTail() {
		return tail;
	}
	
	public void addLast(E e) {
		
		Node<E> newNode = new Node<E> (e);
		if(tail==null)
			head = tail = newNode;
		else{
			//原来的尾部指向现在的节点
			tail.next = newNode;
			tail = tail.next;
		}
		size++;
	}
	
	public void add(int index,E e) {
		if(index == 0)
			addFirst(e);
		else if(index >= size)
			addLast(e);
		else{
			Node<E> current = head;
			for(int i = 1 ; i < index ; i++) {
				current = current.next;
			}
			
			Node<E> temp = current.next;
			current.next = new Node<E>(e);
			(current.next).next = temp;
			size++;
		}
	}
	
	public E removeFirst() {
		if(size==0)
			return null;
		else{
			Node<E> temp = head;
			head = head.next;
			size--;
			return temp.element;
		}
	}
	
	public E removeLast() {
		if(size == 0)
			return null;
		else if(size == 1){
			Node<E> temp = head;
			head = tail = null;
			size = 0;
			return temp.element;
		}
		else{
			Node<E> current = head;
			for(int i = 0 ; i < size-2 ; i++){
				current = current.next;
			}
			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}
	
	public E remove(int index) {
		if(index < 0 || index >= size)
			return null;
		else if(index == 0){
			return removeFirst();
		}
		else if(index == size-1){
			return removeLast();
		}
		else{
			Node<E> previous = head;
			for(int i = 1 ; i < index ; i++)
				previous=previous.next;
			Node<E> current=previous.next;
			previous.next=current.next;
			size--;
			return current.element;
		}
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		Node<E> current = head;
		for(int i = 0 ; i < size ; i++) {
			result.append(current.element);
			current = current.next;
			if(current != null) 
				result.append(", ");
			else
				result.append("]");
		}
		return result.toString();
	}
	
	public void clear() {
		head = tail = null;
	}
	
	public boolean contains(E e) {
		Node<E> current = head;
		for(int i = 0 ; i < size ; i++)
			if(current.element.equals(e))
				return true;
			else
				current = current.next;
		return false;
	}
	
	public E get(int index) {
		Node<E> current = head;
		if(index < 0 || index >= size)
			return null;
		else if(index == 0)
			return head.element;
		for(int i = 1 ; i <= index ; i++)
			current = current.next;
		return current.element;
	}
	
	public int indexOf(E e) {
		Node<E> current = head;
		for(int i = 0 ; i < size ; i++){
			if(current.element.equals(e))
				return i;
			else
				current = current.next;
		}
		return -1;
	}
	
	public int lastIndexOf(E e) {
		int count = -1;
		Node<E> current = head;
		for(int i = 0 ; i < size ; i++) {
			if(current.element.equals(e)) {
				count = i ;
				current.element = e;
			}
			current = current.next;
		}
		return count;
	}
	
	public E set(int index,E e) {
		if(size == 0)
			return null;
		else{
			Node<E> current = head;
			for(int i = 1 ; i <= index ; i++)
				current = current.next;
			E oldElement=current.element;
			current.element=e;
			return oldElement;
		}
	}
	
	private static class Node<E> {
		E element ;
		Node<E> next ;
		public Node(E element) {
			this.element = element;
		}
	}
	
}
