import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;


public class Manager implements Comparable<Manager> {
	
	private int id;
	private PQueue pq;
	private static int size;
	private TreeSet<Manager> queue = new TreeSet<Manager>();
	private static TreeSet<PQueue> managers = new TreeSet<PQueue>();
	private LinkedList<Source> qManager = new LinkedList<Source>();
	public static int counter;
	
	
	
	public void sort(){
		TreeSet<PQueue> temp = new TreeSet<PQueue>();
		
		for( PQueue pq: managers){
			pq.sort();
			temp.add(pq);
		}
		
		managers = temp;
		
		System.out.println("Sorting1");
		//	managers.first().sort();
	}
	
	/**
	 * Sorts the queue list according to each element priority.
	 * Does not alter the queue.
	 * @param <E>
	 * @return copy.reverse, a queue list in a descending order.
	 * @exception UnderflowException if the queue is empty.
	 */ 
	public TreeSet<PQueue> sort(TreeSet<PQueue> managers2){
		System.out.println("Sorting2");
		TreeSet<PQueue> tp = new TreeSet<PQueue>();
		ArrayList<PQueue> temp = new ArrayList<PQueue>(managers2);
			
		while(!temp.isEmpty()){
			int loc = getMax(temp);
			tp.add(temp.get(loc));
			temp.remove(loc);
		}

		return tp;
	}

	
	
	/**
	 * Obtaining the smallest element in the array.
	 * @param <E>
	 * @param a, array of elements
	 * @return loc, the location of the smallest element.
	 */
	public int getMax(ArrayList<PQueue> tp){

		int lowest = tp.get(0).first().getId();
		int loc = 0;

		for( int i = 1; i< tp.size(); i++){
			
			if(lowest > tp.get(i).first().getId()){
				lowest = tp.get(i).first().getId();
				loc = i;
			}
		}
		return loc;
	}
	
	public PQueue getPq() {
		return pq;
	}
	
	
	public TreeSet<PQueue> getAllQueues(){
		return managers;
	}
	
	
	public void removeQueue( PQueue pq){
		managers.remove(pq);
	}
	
	public Source removeQFS(){
		
		Source temp = managers.first().poll();
//		managers.wait(timeout)
		return temp;
	}
	
	public Source firstS(){
		return managers.first().first();
	}
	
	
	public PQueue firstQ(){
		return managers.first();
	}
	
	
	public PQueue pop(){
		return managers.pollFirst();
	}
	
	public void enqueue(PQueue q, int id) {

		System.out.println("Creating a Manager # "+ id);
		this.queue.add(new Manager(q.getSet(), id, q));
		managers.add(q);
		size++;
	}



	public Manager(int sizes) {
		
		size = sizes;
	}



	public Manager(Queue<Source> q, int id2, PQueue q2) {
		this.id = id2;
		pq = q2;
		qManager.addAll(q);
		System.out.println("Adding a queue" + q) ;
	}



	public static void main( String[] args){
		
	}
	
	
	
	
	public void remove(Manager Manager){
		
		if( size != 0)
			size--;
		
		queue.remove(Manager);
	}
	
	
	
	
	public LinkedList<Source>getManagerN() {
		return qManager;
	}
	
	
	
	
	public  void setManagerN(PQueue pQueue) {
		
		System.out.println("ReCreating a Manager # "+ this.id);
		this.queue.add(new Manager(pQueue.getSet(), this.id, pQueue));
		managers.add(pQueue);
		qManager.addAll(pQueue.getSet());
		size++;
	}



	
	public boolean equals(Object obj){
		Manager tp = (Manager)obj;
		
		return this.id == tp.id;
	}
	
	
	
	
	public LinkedList<Manager> getQueue() {
		return new LinkedList<Manager>(queue);
	}

	
	
	
	public String toString(){
		String temp = "";
		for( Manager p : queue)
			temp += "Queue # "+ p.id + "\n" + p.pq + "\n";
		
		return temp ;
	}



	
	public void add(Queue<Source> q, int i) {
		this.id = i;
		qManager.addAll(q);
		System.out.println("Adding a queue");
		
	}



	
	@Override
	public int compareTo(Manager o) {
		if(this.pq.compareTo(o.pq) == 1)
			return 1;
		else
			return -1;
	}



	
	public int getSize() {

		return size;
	}



	
	public void setSize(int sizes) {
		size = sizes;
	}

	
	
	
	public int getId() {
		return this.id;
	}




	public boolean isEmpty() {
		return managers.isEmpty();
	}
}
