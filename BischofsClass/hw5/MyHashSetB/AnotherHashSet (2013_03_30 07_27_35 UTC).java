/*
 *  Created:      10/07/2012
 *  Last Changed: 10/07/2012
 *  
 *  AnotherHashSet.java 
 * 
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


/**
 * This program creates an interface called AnotherHashSet.
 * It has methods to add, clear, remove an element from a hash table.
 * It also has methods to check if hash table contains an object, or if it is empty.
 * It has method to calculate the size of the has set.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */
public interface AnotherHashSet {
    public boolean add(Object o);
    public void clear();
    public boolean contains(Object o);
    public boolean isEmpty();
    public boolean remove(Object o);
    public int size();
}