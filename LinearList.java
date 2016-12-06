
public interface LinearList<T> {
	
	public boolean isEmpty();
	public int size();
	public T get(int index);
	public int indexOf(T item);
	public void add(int index, T item);
	public T remove(int index);
	public String output();
	

}
