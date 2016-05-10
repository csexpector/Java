import java.util.Comparator;
import java.util.Iterator;


public class DoublyLinkedList<DataType> {
	
	ListNode<DataType> head = null;
	ListNode<DataType> tail = null;
	Comparator<DataType> comp;
	
	public DoublyLinkedList(Comparator<DataType> c) {
		comp = c;
	} // end constructor

	public void addToFront(DataType value) {
		ListNode<DataType> node = new ListNode<DataType>(value, head, null);
		if(head != null) {
			head.prev = node;
		} // end if
		head = node;
		if(tail == null) {
			tail = node;
		} // end if
	} // end addToFront()
	
	public void addToEnd(DataType value) {
		ListNode<DataType> node = new ListNode<DataType>(value, null, tail);
		if(head == null) {
			head = node;
		} // end if
		ListNode<DataType> temp = head;
		while(temp.next != null) {
			temp = temp.next;
		} // end while
		temp.next = node;
	} // end addToEnd()
	
	public String toString() {
		String result = "";
		for(ListNode<DataType> curr = head; curr != null; curr = curr.next) {
			result += curr.data + " ";
		} // end for
		return result;
	} // end toStirng()

	public void SelectionSort(ListNode curr) {
		ListNode<DataType> minSoFar = null;
		while(curr.next != null) {
			minSoFar = findMin(curr);
			swap((DataType)curr.data, minSoFar.data);
			SelectionSort(curr.next);
		} // end while
	} // end SelectionSort()
	
	public ListNode<DataType> findMin(ListNode begin) {
		ListNode<DataType> minSoFar = null;
		ListNode<DataType> curr = null;
		for(curr = begin; curr != null; curr = curr.next) {
			if(comp.compare(minSoFar.data, curr.data) > 0) {
				minSoFar = curr;
			} // end if
		} // end for
		return minSoFar;
	} // end findMin()
	
	public void swap(DataType data1, DataType data2) {
		ListNode<DataType> temp = null;
		temp.data = data1;
		data1 = data2;
		data2 = temp.data;
	} // end swap()
	
	public void QuickSort(ListNode<DataType> start, ListNode<DataType> end) {
		if(comp.compare(start.data, end.data) < 0) {
			ListNode<DataType> p = Partition(start, end);
			ListNode<DataType> curr = null;
			curr.next = p;
			QuickSort(start, curr);
			QuickSort(p.next, end);
		} // end if
	} // end QuickSort()
	
	public ListNode<DataType> Partition(ListNode start, ListNode end) {
		ListNode<DataType> lHS = null;
		lHS.next = start;
		ListNode<DataType> unknown = end;
		for(ListNode<DataType> curr = start; curr != end; curr = curr.next) {
			if(comp.compare(curr.data, unknown.data) < 0 || 
					comp.compare(curr.data, unknown.data) == 0) {
				swap(curr.data, lHS.next.data);
				lHS = lHS.next;
			} // end if
		} // end for
		swap((DataType)end.data, lHS.next.data);
		return (ListNode<DataType>) lHS.next.data; 
	} // end Partition()
	
	public ListNode<DataType> findNode(DataType key) {
		ListNode<DataType> curr = null;
		for(curr=head; curr != null && !curr.data.equals(key); curr = curr.next) {
		} // end for
		return curr;
	} // end findNode()
	
	public void delete(DataType valueToRemove) {
		ListNode<DataType> prev = null;
		ListNode<DataType> curr = null;
		for(curr = head; curr != null && curr.data.equals(valueToRemove); 
				prev = curr, curr = curr.next) {
		} // end for
		
		if (prev == null)
		{
			head = curr.next;

		}
		else if (curr.next == null)
		{
			tail = curr.prev;
		}
		else
		{
			prev.next = curr.next;
			curr.prev = prev;
		}
	} // end remove()
	
	Iterator<DataType> iterator() {
		return new DoublyLinkedListIterator();
	} // end iterator()
	
	private class DoublyLinkedListIterator implements Iterator<DataType> {
		
		ListNode<DataType> curr = head;
		
		@Override
		public boolean hasNext() {
			return curr != null;
		} // end hasNext()

		public boolean hasPrevious() {
			return curr.prev != null;
		} // end hasPrevious()
		
		@Override
		public DataType next() {
			DataType result = null;
			result = curr.data;
			curr = curr.next;
			return result;
		} // end next()

		public DataType prev() {
			DataType result = curr.data;
			curr = curr.prev;
			return result;
		} // end prev()
		
		@Override
		public void remove() {
		} // end remove()
	} // end inner class
	
	public void reverse() {
		ListNode<DataType> temp = head;
		head = tail;
		tail = temp;
		ListNode<DataType> pre = head;
		while(pre != null) {
			temp = pre.next;
			pre.prev = temp;
			pre = pre.next;
		} // end while
	} // end reverse()
	
} // end class
