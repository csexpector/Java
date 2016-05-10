import java.util.Comparator;
import java.util.LinkedList;


public class TestDoublyLinkedList {

	public static void main(String[] args) {
		Comparator<Integer> comp = null;
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>(comp);
		list.addToFront(10);
		list.addToFront(20);
		list.addToFront(30);
		list.addToFront(40);
		list.addToFront(50);
		list.addToFront(35);
		list.addToFront(60);
		list.addToFront(42);
		list.addToFront(13);
		System.out.println(list.toString());
		System.out.println("-------");
		ListNode p = list.findNode(60);
		System.out.println(p.data);
		list.delete(60);
		System.out.println(list.toString());
		
	}
} // end class