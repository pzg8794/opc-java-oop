// Comments


import java.util.*;
import java.util.Iterator;


// Comments

public class BinSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> s = new ArrayList<E>();
    BinarySearchTree binSet = new BinarySearchTree();
    

    public BinSet() {
    	
    	binSet = null;
    	s=null;
    }

    // comments
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public BinSet(Collection<? extends E> c){
    	int size = 0;
    	s.addAll(c);
    	
    	Iterator<? extends E> itr = c.iterator();

    	//binSet = new BinarySearchTree();
    	
    	while(size != c.size()){
    		//binSet.add(s.indexOf(s.get(size)), c);
    		binSet.add(Integer.parseInt(itr.next().toString()));
    		//System.out.print(Integer.parseInt(itr.next().toString()));
    		size++;
    	}

    }
    
    // comments
    private int binarySearch(E e){

    	// YOUR CODE GOES HERE	
		return binSet.get(s.indexOf(e));
    }

    // comments
    public boolean add(E e) {
    	s.add(e);
    	boolean test = binSet.add(Integer.parseInt(e.toString())); 
    	// YOUR CODE GOES HERE 
    	System.out.println(" adding " + test);
		return test;
    }

    // comments
    @SuppressWarnings("unchecked")
	public boolean addAll(Collection<? extends E> c) {
      	
    	int size = 0;
    	s.addAll(c);
    	
    	Iterator<? extends E> itr = c.iterator();
    	//c.iterator().next().toString()
    	//System.out.print(c.iterator().next().toString());
 
    	while(size != c.size()){
    		//binSet.add(s.indexOf(s.get(size)), c);
    		binSet.add(Integer.parseInt(itr.next().toString()));
    		//System.out.print(Integer.parseInt(itr.next().toString()));
    		size++;
    		//binSet.insert(s.get(size));	
    	}
		return s.addAll(c);
    }

    // comments\\\
    public void clear() {
    	// YOUR CODE GOES HERE
    	s.clear();
    	binSet.clear();
    }
     
    // comments
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
		s.contains(o);
		System.out.println("contains " + binSet.contains((int) o));
		return binSet.contains((int) o);
 
    }

    // comments
    public boolean containsAll(Collection<?> c) {
		int index = 0;
    	// YOUR CODE GOES HERE
    	@SuppressWarnings("unchecked")
		Iterator<? extends E> itr = (Iterator<? extends E>) c.iterator();
    	//c.iterator().next().toString()
    	//System.out.print(c.iterator().next().toString());

    	
    	while(itr.hasNext()){
    		//binSet.add(s.indexOf(s.get(size)), c);
    		//int temp = Integer.parseInt(itr.next().toString());
    		
    		if (!contains(itr.next())){
    			//System.out.print(itr.next());
 				return false;
 			}else{
 				//System.out.print(itr.next());
 			}
    		index++;
    		//return s.containsAll(c);
    		//binSet.insert(s.get(size));	
    	}
    
    	return true;
     }

    // comments
    public boolean isEmpty() {
    	s.isEmpty();
    	// YOUR CODE GOES HERE
    	
    	if (binSet.size() == 0)
    			return true;
    	else 
    		return false;
    }
          
    // comments
    public java.util.Iterator<E> iterator() {
    	
    	s.iterator();
    	// YOUR CODE GOES HERE	
		return binSet.iterator();
    }
    
    // comments
    public boolean remove(Object o) {
    	s.remove(o);
    	boolean test = binSet.remove((int) o); 
    	System.out.println(" removing a key in binset "+test);
		return test;
	// YOUR CODE GOES HERE
    }
     
    // comments
    public boolean retainAll(Collection<?> c) {
    	
    	BinarySearchTree temp = new BinarySearchTree();   
    	Iterator<?> itr = c.iterator();
    	temp.clear();
    	//System.out.print(c.iterator().next().toString());
 
    	while(itr.hasNext()){
    		//binSet.add(s.indexOf(s.get(size)), c);
    		Integer value = (Integer) itr.next();
    		
    		System.out.println(" adding c value " + value);
    		if(binSet.contains( value )){
    			temp.add(value);
    			System.out.println(" adding intersenting values " + value);
    			System.out.println(" temp size " + temp.size());

    		}
    	}
    	
    	//binSet.clear();
    	System.out.println( " bin size " + binSet.size());
    	binSet = temp;
    	System.out.println(" temp size " + temp.size());
    	//temp.clear();
    	
    	if( binSet.equals(c)){
    		return true;
    	}
    	else
    		return false;
    }
    

    // comments
    public int size() {
    	
		return binSet.size();
    }
     
    // comments
    public Object[] toArray() {
    	
    	Object [] array = new Object[binSet.size()];
    	
    	int index = 0;

	  	Iterator<? extends E> itr = binSet.iterator();
    	//c.iterator().next().toString()
	  	
    	System.out.println("toArray binSet size " + binSet.size());
 
    	while(itr.hasNext()){
    		//binSet.add(s.indexOf(s.get(size)), c);
    		
    		array[index] = Integer.parseInt(itr.next().toString());
    		System.out.print(index);
    		index++;
    		//binSet.insert(s.get(size));	
    	}
    	//s.toArray();
		return array;
    }
     
    
    // comments
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
    	int index = 0;
    	 
    	Iterator<?> itr = binSet.iterator();
    	 if( a.length >= binSet.size()){
    		 
    	 }else{
    		 a = (T[]) new Object[binSet.size()];
    	 }
    		 
    	while(itr.hasNext()){
    		//binSet.add(s.indexOf(s.get(size)), c);
    		Integer value = (Integer) itr.next();
    		a[index] = (T) value;
    		
    		System.out.println(" adding c value " + value);
    		index++;
    	}
    	//binSet.clear();
    	System.out.println( " bin size " + binSet.size());
    	  	
    	return a;
    }

    // comments
    public String toString(){
		return binSet.toString();
    }

    /**
     * Displays a message followed by success or failure indicating
     * whether or not a particular test was successful.
     *
     * @param message The String form of the message.
     *
     * @param       b    A boolean indicating whether the test was successful 
     *			 or not.    
     *
     */

    private static void resultTest(String message, boolean b){
	if (b){
	    System.out.println(message + " success");
	} else {
	    System.out.println(message + " failure");
	}
    }
    
    /**
     * Runs a suite of tests to validate the implemenation of BinSet
     * for Integer elements.
     *
     */

    public static void main(String[] args){
    	
	Set<Integer> set = new BinSet<Integer>(Arrays.asList(1,3));
	
	resultTest("constructor 1", Arrays.equals(set.toArray(), Arrays.asList(1,3).toArray()));
	//java.util.Iterator<Integer> itr = set.iterator();

	//while(itr.next()!= null){
		
		//itr.next().toString();
	//}
	set.add(2);
	resultTest("add 1", Arrays.equals(set.toArray(), Arrays.asList(1,2,3).toArray()));
	
	resultTest("contains 1", set.contains(1));
	resultTest("contains 2", set.contains(2));
	resultTest("contains 3", set.contains(3));
	resultTest("contains 4", !set.contains(4));
	
	resultTest("size 1", set.size() == 3);
	
	set.clear();
	resultTest("clear/size", set.size() == 0);
	resultTest("clear/isEmpty", set.isEmpty());
	
	set.addAll(Arrays.asList(1,2,3));
	resultTest("addAll 1",set.size() == 3);
	

	
	resultTest("containsAll 1", set.containsAll(Arrays.asList(3,2)));
	resultTest("containsAll 2", !set.containsAll(Arrays.asList(4,3)));
	
	set.remove(2);
	resultTest("remove 1", Arrays.equals(set.toArray(), Arrays.asList(1,3).toArray()));
	
	//resultTest("contains 1", set.contains(1));
	//resultTest("contains 2", set.contains(2));
	//resultTest("contains 3", set.contains(3));
	
	Integer[] a = {1,3};
	int j = 0;
	for (Integer i : set){
	    resultTest("iterator " + i, i.equals(a[j]));
	    j++;
	}
	
	set.retainAll(Arrays.asList(3,4));
	resultTest("retainAll 1", Arrays.equals(set.toArray(), Arrays.asList(3).toArray()));
	resultTest("toArray(array) 1", Arrays.equals(set.toArray(new Integer[0]), Arrays.asList(3).toArray()));
    }

    
    
}