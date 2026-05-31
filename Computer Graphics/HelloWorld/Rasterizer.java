//
//  Rasterizer.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * 
 * This is a class that performas rasterization algorithms
 *
 */

import java.util.*;

public class Rasterizer {

	/**
	 * number of scanlines
	 */
	int n_scanlines;
	
	int y[], x[], e;
	ArrayList<Integer>[] globalEdgeTable;
	List<Integer> activeEdgeTable;
	int y_min=-1; 	// setting current y to -1 scan line.
	
	
	/**
	 * Constructor
	 *
	 * @param n - number of scanlines
	 *
	 */
	
	Rasterizer (int n)
	{
		n_scanlines = n;
		activeEdgeTable = new ArrayList<Integer>();
		//forming active edge table
		globalEdgeTable = new ArrayList[n_scanlines];
	}
	

	private void validateGlobalEdgeTable() {
		
		//check global edge table valid or not? and display global edge table contents. For testing only
		System.out.println("\nGlobal Edge Table: ");
		for(int i = 0 ; i < globalEdgeTable.length ; i++) {
			//point y to smallest y in ET which has bucket
			if (globalEdgeTable[i] != null && globalEdgeTable[i].size()>1){
				System.out.println("globalEdgeTable["+i+"]" +globalEdgeTable[i]);
				y_min=i;		// set y to smallest y in the ET which has buckets
				break;
			}
		}
	}
	


	private void addAllVerticesToGlobalEdgeTable(int n) {
		int current =0, next;
		// Add all vertices expect the last ones
		while(current < n-1) { 
			next = current + 1;
			
			if(y[current] >= y[next])
				settingGlobalTable(current, next);
			else 
				settingGlobalTable(next, current);
			current = next;
		}
		// the last vertices is added below here in to global edge table
		if(y[0] >= y[current])
			settingGlobalTable(0, current);
		else 
			settingGlobalTable(current, 0);
		
	}
	
	
	
	void settingGlobalTable( int current, int next){
		
		globalEdgeTable[(y[next])].add(y[current]);
		globalEdgeTable[(y[next])].add(x[next]);
		globalEdgeTable[(y[next])].add((x[current] - x[next]));
		globalEdgeTable[(y[next])].add((y[current] - y[next]));
		globalEdgeTable[(y[next])].add(0);
	}
	
	
	private void compyingEdgeFromGlobalToActiveTable() {
		// copy Global edge table to active edge table
		for(int i = 0 ; i < globalEdgeTable.length ; i++) {
			if (globalEdgeTable[i] != null && i == y_min){
				Iterator<Integer> gEd = globalEdgeTable[i].iterator();
				while(gEd.hasNext())
					activeEdgeTable.add(gEd.next());
				//delete respective global edge table after transferring to active edge table
				globalEdgeTable[i]= null;	
				System.out.println("Active Edge table");
				System.out.println(activeEdgeTable);
				break;
			}
		}
	}
	
	public void removeActiveEdges(){
		//if active edge table has only 1 bucket
		if(activeEdgeTable.size()>3 && activeEdgeTable.size()<6){
			removingActiveEdges(0, 3);
		}
		//if active edge table has only 2 bucket
		if(activeEdgeTable.size()>5 && activeEdgeTable.size()<11){
			removingActiveEdges(5, 8);
			removingActiveEdges(0, 3);
		}
		//if active edge table has 3 bucket
		if(activeEdgeTable.size()>10 && activeEdgeTable.size()<16){
			removingActiveEdges(10, 13);
			removingActiveEdges(5, 8);
			removingActiveEdges(0, 3);
		}
		//if active edge table has 4 bucket
		if(activeEdgeTable.size()>15 && activeEdgeTable.size()<21){
			removingActiveEdges(15, 18);
			removingActiveEdges(10, 13);
			removingActiveEdges(5, 8);
			removingActiveEdges(0, 3);
		}
		// display active edge table after deleting the edge buckets, for testing only
		System.out.println("Active edge table after deleting");
		System.out.println(activeEdgeTable);
	}
	
	public void removingActiveEdges(int current, int next){
		
		if(activeEdgeTable.get(current)== y_min || y_min > activeEdgeTable.get(current) || activeEdgeTable.get(next)==0){
			activeEdgeTable.remove(current);
			activeEdgeTable.remove(current);
			activeEdgeTable.remove(current);
			activeEdgeTable.remove(current);
			activeEdgeTable.remove(current);
		} 
		
	} 
	
	
	public void swappingActiveEdges( int k, int count){
		if ( k == 0) k = count;

		if (activeEdgeTable.get(k) > activeEdgeTable.get(count+5)) {
			Collections.swap(activeEdgeTable, k, count+5);
			Collections.swap(activeEdgeTable, k-1, count+4);
			Collections.swap(activeEdgeTable, k+1, count+6);
			Collections.swap(activeEdgeTable, k+2, count+7);
			Collections.swap(activeEdgeTable, k+3, count+8);
		}	
	}
	
	
	
	private void sortActiveEdges(simpleCanvas C) {
		//if active table has 2 buckets
		if(activeEdgeTable.size()>5 && activeEdgeTable.size()<11){
			sortingActiveEdges(1,6,C);
		} 
		//if active table has 3 buckets
		if(activeEdgeTable.size()>10 && activeEdgeTable.size()<16){
			sortingActiveEdges(1,6,C);
			sortingActiveEdges(6,11,C);
		}
		//if active table has 4 buckets
		if(activeEdgeTable.size()>16 && activeEdgeTable.size()<21){
			sortingActiveEdges(1,6,C);
			sortingActiveEdges(11,16,C);
		}
	}
	
	public void sortingActiveEdges( int first, int last, simpleCanvas C){
		e =activeEdgeTable.get(first);
		while(e<activeEdgeTable.get(last)){
			C.setPixel(e, y_min);
			e++;
		}
	}
	
	
	private void XActiveEdgeTableSortting(simpleCanvas C) {
		int count=1;
		//if active table has 2 buckets
		if(activeEdgeTable.size()>1 && activeEdgeTable.size() < 11){
			for(int k = 1; k <(activeEdgeTable.size()/5); k ++){
				//compare x on active edge table and swap if needed
				swappingActiveEdges(0, count);
				count= count+5;
			}
			//if active table has 3 buckets
		}else if(activeEdgeTable.size()>10 && activeEdgeTable.size() < 16){
			//compare x on active edge table and swap if needed
			int k=1; 
			count =1;
			
			swappingActiveEdges(k, count);
			swappingActiveEdges(k, 6);
			swappingActiveEdges(k+5, 6);
			
		}
		//if active table has 4 buckets
		else if(activeEdgeTable.size() > 16 && activeEdgeTable.size() < 21){
			//compare x on active edge table and swap if needed
			int k=1; 
			count =1;
			while( count <= 10){
				swappingActiveEdges(k, count);
				swappingActiveEdges(count, k);
				count += 5;
			}	
		}
		// printing sorted active edge table 
		System.out.println("Sorted activeEdgeTable : " +activeEdgeTable);
		
		//Fill pixels on scan line y using pairs of x coords from Active Edge Table
		if(activeEdgeTable.size()>1){
			sortActiveEdges(C);
		}
		y_min++; 	//increment y
		
	}
	
	public void updatingActiveTable(int f, int s, int one, int two){
		if((float)activeEdgeTable.get(f)!=0)
			// curr x = x +(dx+sum)/dy
			//sum = (dx+sum)/dy
			if(((float)activeEdgeTable.get(s)/(float)activeEdgeTable.get(f))!= 0){
				activeEdgeTable.set(one, activeEdgeTable.get(one)+((activeEdgeTable.get(s)+activeEdgeTable.get(two))/activeEdgeTable.get(f)));
				activeEdgeTable.set(two, (activeEdgeTable.get(two)+activeEdgeTable.get(s)) % activeEdgeTable.get(f)); 
			} 
	}
	
	
	
	
	private void updateActiveTable() {
		if(activeEdgeTable.size()>1 && activeEdgeTable.size()<6){
			updatingActiveTable(3, 2, 1, 4);
			updatingActiveTable(3, 2, 1, 4);
		}
		//if active table has 2 buckets
		if(activeEdgeTable.size()>5 && activeEdgeTable.size()<11){
			updatingActiveTable(3, 2, 1, 4);
			updatingActiveTable(8, 7, 6, 9);
		}
		//if active table has 3 buckets
		if(activeEdgeTable.size()>10 && activeEdgeTable.size()<16){
			updatingActiveTable(3, 2, 1, 4);
			updatingActiveTable(8, 7, 6, 9);
			updatingActiveTable(13,12,11,14);
		}
		//if active table has 4 buckets
		if(activeEdgeTable.size()>15 && activeEdgeTable.size()<21){
			updatingActiveTable(3, 2, 1, 4);
			updatingActiveTable(8, 7, 6, 9);
			updatingActiveTable(13,12,11,14);
			updatingActiveTable(18,17,16,19);
		}	
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


	public void drawPolygon(int n, int x[], int y[], simpleCanvas C) {	
		
		//forming global edge tables
		 this.y = y;
		 this.x = x;

		//set the size of the global edge table. 
		for(int i :y )	
			globalEdgeTable[(i)] = new ArrayList<Integer>();

		// Add all vertices to global edge table
		addAllVerticesToGlobalEdgeTable(n);
		validateGlobalEdgeTable();

		// Global edge table is created. 
		// loop until global edge table and active edge table is empty
		int globalEdgeTableEmpty =1;
		while(globalEdgeTableEmpty==1 || activeEdgeTable.size()>1) {
			
			for(int i = 0 ; i < globalEdgeTable.length ; i++) {
				if (globalEdgeTable[i] != null && globalEdgeTable[i].size()>1){
					globalEdgeTableEmpty =1;
					break;
				}
				globalEdgeTableEmpty =0;
			}
			
			//delete active edge table if Ymax = y_min and Ymax > y_min and DY = 0
			System.out.println("Active edge table before deleting");
			System.out.println(activeEdgeTable);	
			
			if(activeEdgeTable.size()>1)
				removeActiveEdges();
			
			compyingEdgeFromGlobalToActiveTable();
			//sort Active Edge table buckets on the  basis of x
			XActiveEdgeTableSortting(C);

			//For each non-vertical edge in AET, update x for new y
			//if active table has 1 bucket
			updateActiveTable();
		}
	}
}
