package means;

import java.util.Iterator;

public class Stack<T> implements Iterable<T>{
	
	private Node<T> top;
	
	public Stack() {
		super();
	}

	public void push(T data) {
		Node aux = new Node(data);
		aux.setRight(top);
		top = aux;
	}
	
	public T pop() {
		Node<T> aux = top;
		top = aux.getRight();
		return aux.getData();
	}
	
	public T peek() {
		return top.getData();
		
	}
	
	public boolean isEmpty() {
		return (top==null);
		
	}
	
	public boolean exist(T data) {
		Node<T> aux = top;
		while(aux.getRight()!=null) {
			if(aux.getData() == data) {
				return true;
			}
			aux = aux.getRight();
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		Iterator<T> myIterator = new Iterator<T>() {
			Node<T> actual = top;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return actual != null;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				T data = actual.getData();
				actual = actual.getRight();
				return data;
			}
			
		};
		return myIterator;
	}
	
}
