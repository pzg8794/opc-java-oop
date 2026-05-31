//
//  Rasterizer.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * 
 * This is a class that performs rasterization algorithms
 *
 */

import java.util.*;

public class Rasterizer {
    
    /**
     * number of scanlines
     */
    private int n_scanlines;
    
    /**
     *  y[] and x[] arrays to keep the list of y and x points given.
     *  currentEdge is a variable to keep track of the previous edge iterated.
     */
	private int y[], x[], currentEdge;
	
	/**
     * variable to hold the global edge table
     */
	private ArrayList<Integer>[] globalEdgeTable;
	
	/**
     *  variable to hold the active edge table
     */
	private List<Integer> activeEdgeTable;
	
	/**
     * variable to keep track of minimum y
     */
	private int y_min=-1; 
	
	/**
     * Canvas world, input given
     */
	private simpleCanvas C;
    
	
	
    /**
     * Constructor
     *
     * @param n - number of scanlines
     */
    @SuppressWarnings("unchecked")
	Rasterizer (int n){
        
    	n_scanlines = n;
		activeEdgeTable = new ArrayList<Integer>();
		globalEdgeTable = (ArrayList<Integer>[])new ArrayList[n_scanlines];
    }
    
    
    
    /**
     * Draw a filled polygon in the simpleCanvas C.
     *
     * The polygon has n distinct vertices. The 
     * coordinates of the vertices making up the polygon are stored in the 
     * x and y arrays.  The ith vertex will have coordinate  (x[i], y[i])
     *
     * You are to add the implementation here using only calls
	 * to C.setPixel()
     */
    public void drawPolygon(int n, int x[], int y[], simpleCanvas C){

		 this.y = y; // setting the array of y values 
		 this.x = x; // setting the array of x values 
		 this.C = C; // setting the variable with the Canvas World

		for(int i :y )	// initializing the Global Edge Table
			globalEdgeTable[i] = new ArrayList<Integer>(); 

		addAllVerticesToGlobalEdgeTable(n-1); 
		// adding vertices to the Global Edge Table 
		validateGlobalEdgeTable(); // validating table before using

		// iterating through the Global Edge Table to draw polygons, create 
		// Active Edge Table and fill in polygons
		int globalEdgeTableEmpty =-1; 
		while(globalEdgeTableEmpty==-1 || activeEdgeTable.size()>2) {
			
			for(int i = 0 ; i != globalEdgeTable.length ; i++) {
				// setting to zero unneeded vertices or edges.
				if (globalEdgeTable[i] != null && globalEdgeTable[i].size()>2){
					globalEdgeTableEmpty =1;
					break;
				}
				globalEdgeTableEmpty =0;
			}
			
			// Displaying Current Active Edge Table 
			System.out.println("\n Current Active Edge Table");
			System.out.println(activeEdgeTable);	
			
			if(activeEdgeTable.size()>2)
				removeActiveEdges(); // removing edges not needed
			
			//copy an vertices from the GlobalEdgeTable to the ActiveEdgeTable.
			compyingEdgeFromGlobalToActiveTable(); 
			
			// Sortting edges, once added in the Active
			XActiveEdgeTableSortting(); // Edge Table, according to its x values.
			
			// updating table after removing and sorting edges.
			updateActiveTable(); 
		}
	}

    
    
    /**
     * Evaluate the edges and checks whether the Global Edge Table is valid 
     * or not.
     */
	private void validateGlobalEdgeTable() {
		
		System.out.println("\n << Current Global Edge Table >> \n");
		for(int i = 0 ; i < globalEdgeTable.length ; i++) {
		
			if (globalEdgeTable[i] != null && globalEdgeTable[i].size()>=2){
				System.out.println("globalEdgeTable["+ i +"]" + globalEdgeTable[i]);
				y_min=i;	
				break;
			}
		}
	}
	

	
	/**
     * Add all given vertices to the Global Edge Table in order.
     * if the current edge is greater than the next edge then current edge
     * is set to its position otherwise, the edge is swapped with the next 
     * edge and compared again.
     *
     * @param n, n is the size to the last edge.
     */
	private void addAllVerticesToGlobalEdgeTable(int n) {
		int current =0, next;
		
		while(current != n) { 
			next = current + 1;
			
			if(y[current] >= y[next]) 
				settingGlobalTable(current, next);
			else 
				settingGlobalTable(next, current);
			current = next;
		}
		
		if(y[0] < y[current])
			settingGlobalTable(current, 0);
		else 
			settingGlobalTable(0, current);
	}
	
	
	
	/**
     * This function set the edges in order, all y and x values are being
     * set according the order obtained from 
     * addAllVerticesToGlobalEdgeTable(int i) method.
     *
     * @param current, next, current is position of the current edge, and
     * next, is the position for the next edge.
     */
	void settingGlobalTable( int current, int next){
		
		globalEdgeTable[y[next]].add(y[current]);
		globalEdgeTable[y[next]].add(x[next]);
		globalEdgeTable[y[next]].add((x[current] - x[next]));
		globalEdgeTable[y[next]].add((y[current] - y[next]));
		globalEdgeTable[y[next]].add(0);
	}
	
	
	
	/**
     *  This function copies each edge from the Global Edge Table to the Active
     *  Edge Table. This is done one edge at the time, and this function is 
     *  called recursively to add all edges from the Global to the Active Table.
     */
	private void compyingEdgeFromGlobalToActiveTable() {
		
		for(int i = 0 ; i != globalEdgeTable.length ; i++) {
			if (globalEdgeTable[i] != null && i == y_min){
				Iterator<Integer> itr = globalEdgeTable[i].iterator();
				while(itr.hasNext())
					activeEdgeTable.add(itr.next());
		
				if( activeEdgeTable.size() != 0){
					System.out.println("Adding Vertices to the Active " +
							"Edge Table");
					System.out.println(activeEdgeTable);
					globalEdgeTable[i]= null;	
				}
				break;
			}
		}
	}
	

	
	/**
     * This function removes unneeded vertices. The edge removed is that 
     * already used from the Active Edge Table. This method is called
     * recursively till no more edges are left.
     */
	public void removeActiveEdges(){
	
		if(activeEdgeTable.size()>= 2 && activeEdgeTable.size()<= 5){ 
			removingActiveEdges(0, 3); // One bucket
		}
	
		if(activeEdgeTable.size() >= 6 && activeEdgeTable.size()<= 10){
			removingActiveEdges(5, 8);
			removingActiveEdges(0, 3); // Two buckets
		}
		
		if(activeEdgeTable.size()>= 11 && activeEdgeTable.size()<= 15){
			removingActiveEdges(10, 13);
			removingActiveEdges(5, 8);
			removingActiveEdges(0, 3); // Three buckets
		}
	
		if(activeEdgeTable.size()>= 16 && activeEdgeTable.size()<= 20){
			removingActiveEdges(15, 18);
			removingActiveEdges(10, 13);
			removingActiveEdges(5, 8);
			removingActiveEdges(0, 3); // four buckets
		}
		if( activeEdgeTable.size() != 0){
			System.out.println("\n Active Edge Table After Removing an Edge");
			System.out.println(activeEdgeTable);
		}
	}
	
	
	
	/**
     * This function deletes all edges no needed from the Active Edge
     * Table. It first check whether the current edge was used or not
     * by comparing with the y_min value which keeps track of the edges
     * used.
     */
	public void removingActiveEdges(int current, int next){
		//Comparing edges from all buckets
		if(activeEdgeTable.get(current)== y_min || y_min > 
		activeEdgeTable.get(current) || activeEdgeTable.get(next)==0){
			
			activeEdgeTable.remove(current);
			activeEdgeTable.remove(current);
			activeEdgeTable.remove(current);
			activeEdgeTable.remove(current);
			activeEdgeTable.remove(current);
		}	
	} 
	
	
	
	
	/**
     * This function swaps the edges in the Active Edge Table according
     * to its value
     *
     * @param counter, counter2, counter and counter2 are the positions of
     * two edges of the polygon that were already placed in the Active Edge
     * Table.
     */
	public void swappingActiveEdges( int counter, int counter2){
		if ( counter == 0) counter = counter2;

		if (activeEdgeTable.get(counter) > activeEdgeTable.get(counter2+5)) {
			Collections.swap(activeEdgeTable, counter, counter2+5);
			Collections.swap(activeEdgeTable, counter-1, counter2+4);
			Collections.swap(activeEdgeTable, counter+1, counter2+6);
			Collections.swap(activeEdgeTable, counter+2, counter2+7);
			Collections.swap(activeEdgeTable, counter+3, counter2+8);
		}	
	}
	
	
	
	
	/**
     * Edges are sorted in the Active Edge Table according to its value
     * every edge position is being determined by threshold used obtained 
     * from the Global Edge Table
     */
	private void sortActiveEdges() {

		if(activeEdgeTable.size()>= 6 && activeEdgeTable.size()<= 10){
			sortingActiveEdges(1,6);
		} 

		if(activeEdgeTable.size()>= 11 && activeEdgeTable.size()<= 15){
			sortingActiveEdges(1,6);
			sortingActiveEdges(6,11);
		}

		if(activeEdgeTable.size()>= 17 && activeEdgeTable.size()<=20){
			sortingActiveEdges(1,6);
			sortingActiveEdges(11,16);
		}
	}
	
	
	
	
	/**
     * this function is just an extension of the function sortingAtiveEdges()
     * and the it works is that after the edges are sorted this function set
     * the pixels to draw the Polygon to fill it in.
     *
     * @param first, last, first is the variable used for the current edge 
     * location in the active edge table, and last is the variable usede for
     * the location of the last edge in the active edge table.
     */
	public void sortingActiveEdges( int first, int last){
		currentEdge =activeEdgeTable.get(first);
		while(currentEdge<activeEdgeTable.get(last)){
			C.setPixel(currentEdge, y_min);
			currentEdge++;
		}
	}
	
	
	
	
	
	/**
     * This function set the edges according to the x values obtained from
     * the given set points or y and x values that were already added to the
     * Global Edge Table.
     */
	private void XActiveEdgeTableSortting() {
		int counter2=1;

		if(activeEdgeTable.size()>=2 && activeEdgeTable.size() <= 10){
			for(int counter = 1; counter <(activeEdgeTable.size()/5); counter ++){
				swappingActiveEdges(0, counter2);
				counter2= counter2+5;
			}
		}else if(activeEdgeTable.size()>=11 && activeEdgeTable.size() <= 15){

			int counter=1; 
			counter2 =1;
			
			swappingActiveEdges(counter, counter2);
			swappingActiveEdges(counter, 6);
			swappingActiveEdges(counter+5, 6);
		}

		else if(activeEdgeTable.size() >= 17 && activeEdgeTable.size() <= 20){
			int counter=1; 
			counter2 =1;
			while( counter2 <= 15){
				swappingActiveEdges(counter, counter2);
				swappingActiveEdges(counter2, counter);
				counter2 += 5;
			}	
		}

		if(activeEdgeTable.size()>1){
			sortActiveEdges();
			System.out.println("\n Active Edge Table After Being Sorted \n" 
					 +activeEdgeTable);
		}
		y_min++;	
	}
	
	
	
	
	/**
     * This method updates the Active Edge Table once the unneeded edges were
     * removed from the Active Edge Table.
     *
     * @param f, s, one, two these variables are only location for the points
     * of the edges that are being updated in the active edge table.
     */
	public void updatingActiveTable(int f, int s, int one, int two){
		if((float)activeEdgeTable.get(f)!=0)
			
			if(((float)activeEdgeTable.get(s)/(float)activeEdgeTable.get(f))!= 0){
								activeEdgeTable.set(one, activeEdgeTable.get(one)+
							   ((activeEdgeTable.get(s)+activeEdgeTable.get(two))/
								activeEdgeTable.get(f)));
							   activeEdgeTable.set(two, (activeEdgeTable.get(two)+
								activeEdgeTable.get(s)) % activeEdgeTable.get(f)); 
			} 
	}
	
	
	
	
	/**
     * This method updates the active edge table after edges unneeded were 
     * removed, they that we know edges were already used is by threesholding 
     * the table size with the values 6, 11, and 16 obtained for the global
     * edge table.
     */
	private void updateActiveTable() {
		if(activeEdgeTable.size()>1 && activeEdgeTable.size()<6){
			updatingActiveTable(3, 2, 1, 4); 
			updatingActiveTable(3, 2, 1, 4);
		}

		if(activeEdgeTable.size()>5 && activeEdgeTable.size()<11){
			updatingActiveTable(3, 2, 1, 4); 
			updatingActiveTable(8, 7, 6, 9);// two buckets
		}

		if(activeEdgeTable.size()>10 && activeEdgeTable.size()<16){
			updatingActiveTable(3, 2, 1, 4);
			updatingActiveTable(8, 7, 6, 9);
			updatingActiveTable(13,12,11,14); // three buckets
		}

		if(activeEdgeTable.size()>15 && activeEdgeTable.size()<21){
			updatingActiveTable(3, 2, 1, 4);
			updatingActiveTable(8, 7, 6, 9);
			updatingActiveTable(13,12,11,14);
			updatingActiveTable(18,17,16,19); // four buckets
		}	
	}
}