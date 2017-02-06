import java.util.EmptyStackException;

/**
 * Esta clase hace un stack 
 *  
 * @author estefy
 *
 * @param <T> contenido
 * 
 * @version 1.0
 */
import java.util.concurrent.LinkedBlockingQueue;

public class Stack<T> {
	StackNode<T> last;
	
	public StackMio(){
		this.last=new StackNode<T>(null);
		
	}
	
	public StackMio(T value){
		this.last= new StackNode<T>(value);
	}
	
	public T peek(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
		
		return (T) this.last.value;
	}

	public T pop(){
		if(peek()==null){
			throw new EmptyStackException();
		}
		StackNode<T> temp=new StackNode<T>(last.value);
		this.last=last.next;
		
		return temp.value;	
		
	}
	
	public void push(T nodo){
		StackNode<T> nuevo= new StackNode<T>(nodo);
		nuevo.next=last;
		this.last=nuevo;
		
	}
	public boolean isEmpty(){
		return this.last==null;
	}
	
	
	private class StackNode<T>{
		protected T value;
		protected StackNode<T> next;
		
		public StackNode(T value){
			this.value=value;
			this.next=null;
	
		}
		
	}
	
	
	public static void main(String[] args) {
		StackMio<String> stack=new StackMio<String>();
		stack.push("Estefy");
		stack.push("Edgar");
		LinkedBlockingQueue<String> queue=new LinkedBlockingQueue<String>(3);
		queue.add("Estefy");
		queue.peek();
		queue.add("edgar");
		Object [] array= new Object[4];
		array= queue.toArray();
		System.out.println(array[1]);
		//System.out.println(queue.toString());
	

	}
}
