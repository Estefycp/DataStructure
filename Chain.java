
public class Chain<T> implements LinearList<T> {
	
	protected ChainNode<T> firstNode;
	protected int size;

	public Chain(){
		this.size=0;
		this.firstNode=new ChainNode<T>();
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		ChainNode<T> temp=this.firstNode;
		for(int i=0;i<index;i++){
			temp=temp.next;
		}
		return null;
	}

	@Override
	public int indexOf(T item) {
		ChainNode<T> temp=this.firstNode;
		for(int i=0;i<this.size;i++){
			if(item.equals(temp.element)){
				return i;
			}
			temp=temp.next;
		}
		return -1;
	}

	@Override
	public void add(int index, T item) {
		if(index<0||index>=size){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		ChainNode<T> newNode=new ChainNode<T>(item);
		if(index==0){
			newNode.next=this.firstNode;
			this.firstNode=newNode;
		}
		else{
			ChainNode<T> ant= this.firstNode;
			ChainNode<T> sig;
			for(int i=0;i<index-1;i++){
				ant=ant.next;
			}
			sig=ant.next;
			newNode.next=sig;
			ant.next=newNode;
		}
		this.size++;
	}

	@Override
	public String output() {
		String s="";
		ChainNode<T> aux=this.firstNode;
		int i=0;
		while(i<this.size){
			s=s+"[" + aux.element.toString()+"]";
			aux=aux.next;
			i++;
		}
		return s;
	}

	@Override
	public T remove(int index) {
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		T item;
		if(index==0){
			item=this.firstNode.element;
			this.firstNode=this.firstNode.next;
		}
		else{
			ChainNode<T> before=this.firstNode;
			for(int i=0;i<index-1;i++){
				before=before.next;
			}
			item=before.next.element;
			before.next=(before.next).next;
		}
		return item;
	}
	
	private static class ChainNode<T>{
		T element;
		ChainNode<T> next;
		
		public ChainNode(){
			element=null;
			next=null;
		}
		
		public ChainNode(T element){
			element=element;
			next=null;
		}
		public ChainNode(T element,ChainNode next){
			element= element;
			next=next;
		}
	}

}
public class ArrayLinearList <T> implements LinearList <T> {
	protected int size;
	protected T[] list ;
	private final int DEFAULT_INITIAL_CAPACITY = 100;
	
	
	public ArrayLinearList(){
		this.size=0;
		this.list=(T[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	public ArrayLinearList(int initialCapacity){
		this.size=0;
		this.list = (T[]) new Object[initialCapacity];
	}
	
	public boolean isEmpty() {
		if(size==0){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		T element=null;
		for(int i=0;i<=index;i++){
			element=list[i];
		}
		return element;
	}

	@Override
	public int indexOf(T item) {
		for(int i=0;i<size;i++){
			if(list[i].equals(item)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public void add(int index, T item) throws IndexOutOfBoundsException {
		if(isEmpty()&& index==1){
			list[index]=item;
		}
		if(index<=0 || index-1>size){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		if(size==list.length){
			resize();
		}
		if(index>=1 && index<=size+1){
			int newI=index-1;
			int lastI=size-1;
			
			for(int i=lastI;i>=newI;i--){
				list[i+1]=list[i];	
			}
			list[index-1]= item;
			size++;
		}
		
	}

	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		T result = null;
		if(index<=0 || index>size){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		if(index>=1 && index<=size){
			result = list[index-1];
			if(index<size){
				for(int i=index-1;i<size-1;i++){
					list[i]=list[i+1];
				}
			
			}
		size--;
		}
		
		return result;
	}

	@Override
	public String output() {
		String output="";
		for(int i=0;i<size;i++){
			if(list[i]==null){
				output+=",null,";
			}
			output+=list[i].toString();
		}
		return output;
	}
	
	private void resize(){
		T[] larger = (T[]) new Object[list.length*2];
		for(int i=0;i<this.size;i++){
			larger[i]= list[i];
		}
		this.list=larger;
		
	}

	
}
