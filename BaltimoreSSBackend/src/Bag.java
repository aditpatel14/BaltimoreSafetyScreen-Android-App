import java.util.Iterator;

/******************************************************************************
 * This Bag class was used from
 *  <a href="http://algs4.cs.princeton.edu/13stacks/Bag.java.html">Bag.java</a></a>
 *  by Robert Sedgewick and Kevin Wayne
 ******************************************************************************/

public class Bag<Item> implements Iterable<Item> {
	private Node first;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void add(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		
		public boolean hasNext(){
			return current != null;
		}
		
		public Item next(){
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {

		}
	}
}
