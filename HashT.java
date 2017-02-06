import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
/**
 * 
 * @author Estefy Calderon Parra
 *
 * @param <K>
 * @param <V>
 */
public class HashT<K,V> {
	
		public static void main(String[] args) {
			HashT<String, String> test = new HashT<String, String>(15, (float) 0.5);
			System.out.println(test);
			System.out.println("Esta vacio? " + test.isEmpty());
			System.out.println("Tamano = " + test.size());
			test.put("Liz", "Lizbeth - 1234567");
			System.out.println("Funcion hash para Liz: " + test.hash("Lizhehe"));
			test.put("MOROOOOO", "heueueueue");
			System.out.println(test);
			test.remove("MOROOOOO");
			System.out.println(test);
			
		}

		private int m; //tamano tabla
		private int n; // cantidad de datos tiene la tabla hash
		private float loadFactor; //carga
		private int maxCap;
		private Node<K,V> [] tabla;
		private static final int INIT_CAP=13;
		public static final float DEF_LOAD = 0.75f;
		
	public HashT(){
		this(INIT_CAP,DEF_LOAD);
	}
/**
 * Inicializa los parametros
 * @param m
 * @param loadFactor
 */
	public HashT(int m,float loadFactor){
		if (m <= 0) {
			throw new IllegalArgumentException("Tamano no permitido: " + m);
	    }
		this.loadFactor=loadFactor;
		this.maxCap= (int) (loadFactor*m);
		this.m=m;
		this.n=0;
		this.tabla=(Node<K,V>[])new Node[m];
	}
	
	public int hash(K Key){
		return Key.hashCode()&0x7FFFFFF%m;
	}
	
	public int size(){
		return this.n;
	}
	
	public boolean isEmpty(){
		return this.n==0;
	}
	
	public V get(K key){
		if(key==null){
			throw new NullPointerException("no aceptamos llaves nulas");
		}
		int pos=hash(key);
		for(Node<K,V> x = tabla[pos]; x!=null; x=x.next){
			if(x.key.equals(key)){
				return x.value;
			}
		}
		return null;
	}
/**
 * Checa si tiene la key dada
 * @param key
 * @return
 */
	public boolean contains(K key){
		return get(key)!=null;
	}
	
	public void put(K key, V value){
		if(key==null || value==null ){
			throw new NullPointerException("no aceptamos llaves nulas");
		}
		if (n >= maxCap){
			rehash();
		}
		int pos=hash(key);
		for(Node<K,V> x =tabla[pos]; x!=null;x=x.next){
			if(x.key.equals(key)){
				x.value=value;
				return;
			}
		}
		n++;
		tabla[pos]=new Node(key,value,tabla[pos]);
	}
/**
 * aumenta el tamano de la tabla
 */
	private void rehash() {
		Node<K,V>[] oldTable = tabla;
		this.tabla = (Node<K,V>[]) new Node[this.m*2+1];
		this.n=0;
		this.maxCap= (int) (loadFactor*m);
		for (int i=0;i<oldTable.length;i++) {
		      if(oldTable[i] !=null ){
		    	  put(oldTable[i].key,oldTable[i].value);
		    	  	while(oldTable[i].next!=null){
		    	  		oldTable[i]=oldTable[i].next;
		    	  		put(oldTable[i].key,oldTable[i].value);
		    	  	}
		      }
		    }
		
		oldTable=null;
		
	}
/**
 * remueve un elemento
 * @param key
 * @return
 */
	public V remove(K key){
		if (key == null){
			throw new NullPointerException("No aceptamos llaves nulas");
		}
		V value;
		int pos= hash(key);
		if (tabla[pos] != null && tabla[pos].key.equals(key)){
			value = tabla[pos].value;
			tabla[pos] = tabla[pos].next;
			n--;
			return value;
		}
		for(Node<K,V> x = tabla[pos]; x != null; x = x.next){
			if(x.next != null && x.next.key.equals(key)){
				value = x.next.value;
				x.next = x.next.next;
				this.n--;
				return value;
			}
		}
		return null;
	}
	
	 public void clear() {
		 for (Node<K, V> node : tabla) {
			 node = null;
		 }
	}
	 
	 public Iterable <K> keys() {
		 Queue<K> queue = new LinkedList<>();
	        for (int i = 0; i < n; i ++) {
	            for (Node<K, V> x = tabla[i]; x != null; x = x.next) {
	                queue.add((K) x.key);
	            }
	        }
	        return queue;
	 }
	 
	 public String toString() {
		 return "" + Arrays.toString(tabla) + "\n" ;
	 }
	/**
	 * clase para el iterador   
	 * @author estef
	 *
	 * @param <E>
	 */
	public abstract class HashIterator<E> implements Iterator<E>{
		Node<K, V> next;
        int index;

        public HashIterator() {
            this.index = 0;
            if (n > 0){
                while (index < n && tabla[index] == null)
                    this.index++;
            }
            this.next = tabla[index];
        }
        
        public boolean hasNext() {
            return next != null;
        }
        
        public Node<K, V> nextNode() {
            Node<K, V> temp = next;
            
            if (temp == null){
                throw new NoSuchElementException("Se acabo");
            }
            this.next = temp.next;
            if (next == null){
                this.index++;
                while (index < n && tabla[index] == null){
                    this.index++;
                }
                if (index < n){
                    this.next = tabla[index];
                }
            }
            return temp;
        }
    }
   /** regresa los valores del iterador
    * 
    * @author estef
    *
    */
    private class ValueIterator extends HashIterator<V> {
        public V next() {
            return nextNode().value;
        }
    }
    
    private class KeyIterator extends HashIterator<K> {
        public K next(){
            return nextNode().key;
        }
    }
    
    public Iterator<K> getIteratorKey(){
		return new KeyIterator();
	}
	
	public Iterator<V> getIteratorValue(){
		return new ValueIterator();
	}
	/**
	 * nodo que va en la hashtable
	 * @author estef
	 *
	 * @param <K>
	 * @param <V>
	 */
	private static class Node<K, V>{
		K key;
		V value;
		Node<K,V> next;
		
		public Node (K key,V value, Node<K,V> next){
			this.key=key;
			this.value=value;
			this.next=next;
		}
		
		public String toString() {
			return "(" + key.toString() + "/" + value.toString() + ")";
		}
	}
	
	
}
