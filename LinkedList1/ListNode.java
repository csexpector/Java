
public class ListNode<T> {
	T data;
	ListNode<T> next;
	ListNode<T> prev;
	
	public ListNode(T data, ListNode<T> next, ListNode<T> prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	} // end constructor
	
	public String toString() {
		return data + " " ;
	} // end toString()
} // end class
