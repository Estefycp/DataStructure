import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DLinkedList<T> implements LinearList<T>{
	int size;
	DNode<T> first;
	DNode<T> last;
	
	public DLinkedList(){
		this.first=null;
		this.last=null;
		this.size=0;
	}
	
	public ListIterator<T> getIterator(){
		return new LstItr();
	}
	
	public ListIterator<T> getIterator(int index){
		if(index<0||index>=size){
			throw new IndexOutOfBoundsException();
		}
		return new LstItr(index);
	}
	
	private DNode<T> getNode(int index){
		DNode<T> temp= this.first;
		for(int i=0;i<index;i++){
			temp = temp.next;
		}
		return temp;
		
	}
	
	public void add(int index, T item) {
		if(index<0||index>size){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		else{
				DNode<T> nextNode=getNode(index);
				DNode<T> newNode =new DNode<T>(nextNode.prev,item,nextNode);
				nextNode.prev.next=newNode;
				getNode(index).prev=newNode;
			
			size++;
		}
		
	}
	
	private void addFirst(T value){
		DNode<T> newNode =new DNode<T>(null,value,this.first);
		if(!isEmpty()){
			this.first.prev=newNode;
		}
		else{
			this.last=newNode;
		}
		this.first=newNode;
		this.size++;
	}
	
	private void addLast(T value){
		DNode<T> newNode =new DNode<T>(this.last,value,null);
		if(isEmpty()){
			this.first=newNode;
			
		}
		else{
			this.last.next= newNode;
		}
	
		this.last=newNode;
	}
	
	public boolean isEmpty() {
		
		return this.size==0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T get(int index) {
		if(index<0||index>= this.size){
			throw new IndexOutOfBoundsException();
		}
		return getNode(index).value;
	}

	@Override
	public int indexOf(T item) {
		 ListIterator<T> iterator = this.getIterator(); 
		  while (iterator.hasNext()) { 
			  T current = iterator.next(); 
			  if (current == item) { 
				  return iterator.previousIndex(); 
			  } 
		  } 
	 return -1; 

		
	}

	public T remove(int index) {
		 if (index < 0 || index > size) { 
			 throw new IndexOutOfBoundsException(); 
		 } 
		 else{
			 DNode<T> removed = getNode(index);
			 removed.prev.next= removed.next;
			 removed.next.prev=removed.prev;
			 size--;
			 
			 return (T) removed.value;
		 }

	
	}
	public String output() {
		StringBuilder sb = new StringBuilder("[");
		DNode<T> node=this.first;
		while(node!= null){
			sb.append(" [");
			sb.append(node.value);
			sb.append("] ");
			node=node.next;
			
		} 
		sb.append("]");
		return sb.toString();
	}
	
	private static class DNode<T>{
		T value;
		DNode<T> next;
		DNode <T> prev;
		
		public DNode(DNode<T> prev,T value,DNode<T> next){
			this.value=value;
			this.next=next;
			this.prev=prev;
			
		}
	}
	
	class LstItr implements ListIterator<T>{
		
		DNode<T> next;
		DNode<T> ultimoVisitado=null;
		int nextIndex;
	
		public LstItr(){
			this.next=first;
			this.nextIndex=0;
		}

		public LstItr(int index){
			if(index == size){
				this.next=null;
			}else{
				this.next=getNode(index);
			}
			this.nextIndex=index;
		}
		
		public int nextIndex(){
			return this.nextIndex;
			
		}
		
		public boolean hasNext() {
			return this.next.next != null;
		}

		public boolean hasPrevious() {
			return this.nextIndex>0;
		}

		public T next() {
			if(this.hasNext()){
				ultimoVisitado=this.next;
				this.nextIndex++;
				this.next=this.next.next;
				return this.ultimoVisitado.value;
			}else throw new NoSuchElementException();
			
		}

		public T previous() {
			if(!this.hasPrevious()){
				throw new NoSuchElementException();
			}
			if(this.next==null){
				this.next=last;
			}
			else{
				this.next=this.next.prev;
				
			}
			this.ultimoVisitado=this.next;
			this.nextIndex--;
			return this.ultimoVisitado.value;
		}

		
		public int previousIndex() {

			return this.nextIndex-1;
		}

		
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		
		public void set(T arg0) {
			// TODO Auto-generated method stub
			
		}
		public void add(T arg0) {
			
		}

		
	}
	
	
	public static void main(String[] args){
		DLinkedList<Integer> dl = new DLinkedList<Integer>();
		dl.addFirst(0);
		dl.addLast(1);
		dl.addLast(3);
		
		ListIterator<Integer> iterator= dl.getIterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		while(iterator.hasPrevious()){
			System.out.println(iterator.previous());
		}
		System.out.println(dl);
	}
}
