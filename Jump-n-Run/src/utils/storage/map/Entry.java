package utils.storage.map;

public class Entry<K,V> {
	
	private K key;
	private V value;
	
	public Entry() {
	
	}
	
	public Entry(K key,V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		
		return key;
	}
	
	public V getValue() {
		
		return value;
	}
	
	public void setValue(V value) {
		
		this.value = value;
	}
	
	public void setKey(K key) {
		
		this.key = key;
	}
	
}
