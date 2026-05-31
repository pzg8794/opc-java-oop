/*  
 *  PQueue.java 
 * 
 * Version:
 *     $Id: PQueue.java,v 1.1 2013/06/19 02:43:14 pzg8794 Exp $
 *
 * Revisions:
 *     $Log: PQueue.java,v $
 *     Revision 1.1  2013/06/19 02:43:14  pzg8794
 *     Lab3!
 *
 */
import java.util.LinkedList;
import java.util.Queue;


/**
 * This program creates a class called PQueue that implements the
 * interface PriorityQueue to add elements, Passengers in this case, to the
 * head of the list according to its priority.
 * 
 * @author Piter Garcia
 */
public class PQueue implements Comparable<Object>{

	public int counter = 0;
	private int id=0;
	private LinkedList<Source> queue = new LinkedList<Source>();
	private LinkedList<Queue<Source>> list = new LinkedList<Queue<Source>>();
	public boolean empty;
	

	public PQueue(){
		
	}
	
	public PQueue( Queue<Source> queue2){
		
		queue.addAll(queue2);
		setSet(new LinkedList<Source>(queue));
	}

	
	
	public PQueue(Queue<Source> s, int id2) {
		queue.addAll(s);
		setSet(new LinkedList<Source>(queue));
		id = id2;
	}

	
	
	
	/**
	 * Sorts the queue list according to each element priority.
	 * Does not alter the queue.
	 * @param <E>
	 * @return copy.reverse, a queue list in a descending order.
	 * @exception UnderflowException if the queue is empty.
	 */ 
	public LinkedList<Source> sort(Queue<Source> t){
		LinkedList<Source>tp = new LinkedList<Source>(t);
		LinkedList<Source> copy = new LinkedList<Source>();
			
		while(!tp.isEmpty()){
			int loc = getMax(tp);
			copy.add(tp.get(loc));
			tp.remove(loc);
		}

		return copy;
	}
	
	
	
	
	public Source first(){
		return queue.peek();
	}

	
	
	public Source poll(){
		return queue.poll();
	}
	
	
	
	/**
	 * Obtaining the smallest element in the array.
	 * @param <E>
	 * @param a, array of elements
	 * @return loc, the location of the smallest element.
	 */
	public int getMax(Queue<Source> t){
		LinkedList<Source> tp = new LinkedList<Source>(t);
		
		int lowest = tp.get(0).getId();
		int loc = 0;

		for( int i = 0; i< t.size(); i++){
			if(lowest > tp.get(i).getId()){
				lowest = tp.get(i).getId();
				loc = i;
			}
		}
		return loc;
	}

	

	
	public LinkedList<Source> getSet() {
		
		
		return sort(queue);
	}
	
	
	public void removeSource(Source s){
		queue.remove(s);
	}
	
	
	
	public void setSet(LinkedList<Source> set) {
		queue = set;
	}


	
	
	public void add(Queue<Source> q) {
		queue.addAll(q);
		setSet(new LinkedList<Source>(queue));
		list.add(queue);
	}
	
	
	
	
	public String toString(){
		 return ""+ this.queue ;
	 }

	
	@Override
	public int compareTo(Object obj) {
		
		PQueue o = (PQueue)obj;
		
		if( this.first().getId() > o.first().getId()){
			return 1;
		}
		else if(this.first().getId() < o.first().getId()){
			return -1;
		}
		
		return 0;
	}

	
	public boolean equals(Object o){
		PQueue obj = (PQueue)o;
		return this.id == obj.id;
	}
	
	
	public int getId() {
		return id;
	}




	public void setId(int i) {
		this.id = i;
	}

	public void sort() {
		
		if(queue.peek() == null)
			queue.poll();
		sort(queue);
	}
}
