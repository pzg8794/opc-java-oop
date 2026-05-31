

//
//  Clipper.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  CopY1right 2010 __MyCompanyName__. All rights reserved.
//

/**
 * Object for performing clipping
 *
 */

public class clipper {
	
	private static float[] outxx = null;
	private static float[] outyy = null;
	private float[] currinx = new float[50];
	private float[] curriny = new float[50];
	private float[] inX     = new float[50];
	private float[] inY     = new float[50];
	private float[] inx;
	private float[] iny;
	private float pX10, pY10, pX1, pY1;
	private float x0, y0, x1, y1;
	private float dpX, dpYXX,dpY,dpYYX, deltaX, deltaY;
	private float currX0, currY0, currX1, currY1;

	
	private int in, size;
	
	
	
	/**
	 * clipPolygon
	 * 
	 * Clip the polygon with vertex count in and vertices inx/iny
	 * against the rectangular clipping region specified by lower-left corner
	 * (x0,y0) and upper-right corner (x1,y1). The resulting vertices are
	 * placed in outxx/outyy.  
	 * 
	 * The routine should return the with the vertex count of polygon
	 * resulting from the clipping.
	 *
	 * @param in the number of vertices in the polygon to be clipped
	 * @param inx - x coords of vertices of polygon to be clipped.
	 * @param int - y coords of vertices of polygon to be clipped.
	 * @param outxx - x coords of vertices of polygon resulting after clipping.
	 * @param outyy - y coords of vertices of polygon resulting after clipping.
	 * @param newx0 - x coord of lower left of clipping rectangle.
	 * @param newy0 - y coord of lower left of clipping rectangle.
	 * @param newx1 - x coord of upper right of clipping rectangle.
	 * @param newy1 - y coord of upper right of clipping rectangle.
	 *
	 * @return number of vertices in the polygon resulting after clipping
	 * 
	 */
	@SuppressWarnings("unused")
	public int clipPolygon(int inn, float pX10[], float pY10[], float outx[], 
			float outy[], float xx0, float yy0, float xx1, float yy1)
	{
		
		in = inn; 
		x0 = xx0; x1 = xx1; y0 = yy0; y1 = yy1;
		
		inx = pX10;
		iny = pY10;
		
		int x = 0;
		// clearing containers to find vertices out of the polygon
		for ( float i: outy){
			outx[x] = 0; 
			outy[x] = 0;
			x++;
		}
		outxx = outx; outyy = outy;
		currX0 = x0; currY0 = y0; currX1 = x1; currY1 = y1;
		
		x = 0;
		// getting current x and y vertices.
		for(float s: inx){
			currinx[x] = inx[x];
			curriny[x] = iny[x];
			x++;
		}

		settingXY(currX0, currX0, currX0, currY1);
		settingleftEdge();// clipping left edges

		settingXY(currX1, currY0, currX1, currY1);
		settingNew();
		settingRightEdge(); // clipping right edges
		
		settingXY(currX0, currY1, currX1, currY1);
		settingNew();
		settingTopEdge(); // clipping top edges

		settingXY(currX0, currY0, currX1, currY0);
		settingNew();
		settingBottomEdge(); // clipping bottom edges.
		
		return size; 
		// should return number of vertices in clipped poly.
	}
	
	
	
	
	/**
     * it looks for bottom edges inside of the polygon and any of this is 
     * outside this is checked and then a vertex is created to then be 
     * added to the list of vertices, or points, outside of the polygon.
     */
	private void settingBottomEdge() {
		int incr=-1;
		//setting vertices values;
		if(in>0){ 
			pX10 = inx[in-1];
			pY10 = iny[in-1]; 
		}
		for(int i=0; i< in; i++){
			pX1 = inx[i];
			pY1 = iny[i];

			//looking for vertices out of the polygon
			if(pY1 >= y0 && pY1 >= y1){	
				if(pY10 < y0 && pY10 < y1){ 
					
					if(pX10==pX1)
						dpX = pY10-pY1;
					else
						dpX = ((pY10-pY1)/(pX10-pX1));
					if(x1==x0)
						dpY=y1-y0;
					else
						dpY = ((y1-y0)/(x1-x0));
					dpYXX= (pY10-(dpX*pX10));
					dpYYX = y0-(dpY*x0);
					
					if(dpY==dpX){
						deltaX=dpYYX;
						deltaY = (dpY*((dpYXX-dpYYX)/dpYYX));
					}
					else{
					deltaX = ((dpYXX-dpYYX)/(dpY-dpX));
					deltaY = (dpY*((dpYXX-dpYYX)/(dpY-dpX))+dpYYX);
					
					outxx[++incr]=(deltaX);
					outyy[incr]=(deltaY);
					}
				}
				outxx[++incr]=pX1;
				outyy[incr]  =pY1;
			} else if(pY10 >= y0 && pY10 >= y1) { //setting vertices 
				// out of the polygon.
				settingEdge(x0,y0,x1,y1);
				outxx[++incr]=(deltaX);
				outyy[incr]=(deltaY);
			}
			pX10 = pX1;
			pY10 = pY1;
		}
		size = 0;
		
		// getting the size of the vertices out of the polygons
		for(int i =0;i<outxx.length;i++)
			if(outxx[i]!= 0.0)
				size = size + 1;
	}


	
	
	/**
     * it looks for top edges inside of the polygon and any of this is 
     * outside this is checked and then a vertex is created to then be 
     * added to the list of vertices, or points, outside of the polygon.
     */
	private void settingTopEdge() {
		int incr=-1;
		if(in>0){ //setting vertices values;
			pX10 = inx[in-1];
			pY10 = iny[in-1]; 
		}
		for(int i=0; i< in; i++){
			pX1 = inx[i];
			pY1 = iny[i];

			//looking for vertices out of the polygon
			if(pY1 <=y0 && pY1 <=y1 ){	
				if(pY10 > y0 && pY10 > y1){ 
				
					settingEdge(x0,y0,x1,y1);
					outxx[++incr]=(deltaX);
					outyy[incr]=(deltaY);

				}
				outxx[++incr]=pX1;
				outyy[incr]=pY1;
			} else if(pY10 <= y0 && pY10 <= y1) { 
				settingEdge(x0,y0,x1,y1);

				outxx[++incr]=(deltaX);
				outyy[incr]=(deltaY);

			}
			pX10 = pX1;
			pY10 = pY1;
		}
	}


	
	
	/**
     * it looks for right edges inside of the polygon and any of this is 
     * outside this is checked and then a vertex is created to then be 
     * added to the list of vertices, or points, outside of the polygon.
     */
	private void settingRightEdge() {
		int incr=-1;
		
		pX10 = inx[in-1];
		pY10 = iny[in-1]; 
		for(int i=0; i< in; i++){ //setting vertices values;
			pX1 = inx[i];
			pY1 = iny[i];
			
			//looking for vertices out of the polygon
			if(pX1 <=  x0 && pX1 <=  x1 ){	
				if(x0 < pX10 ){ 
					
					settingEdge(x0,y0,x1,y1);
					outxx[++incr]=(deltaX);
					outyy[incr]=(deltaY);

				}
				outxx[++incr]=pX1;
				outyy[incr]=pY1;
			} else if(pX10 <= x0 && pX10 <= x1) { 
				settingEdge(x0,y0,x1,y1);
				outxx[++incr]=(deltaX);
				outyy[incr]=(deltaY);

			}
			pX10 = pX1;
			pY10 = pY1;
		}
	}


	
	
	
	/**
     * it looks for left ages inside of the polygon and any of this is 
     * outside this is checked and then a vertex is created to then be 
     * added to the list of vertices, or points, outside of the polygon.
     */
	private void settingleftEdge() {
		
		int incr=-1;
		
		pX10 = inx[in-1];
		pY10 = iny[in-1]; 
		for(int i=0; i< in; i++){ //setting vertices values;
			pX1 = inx[i];
			pY1 = iny[i];
			
			//looking for vertices out of the polygon
			if(pX1 >= x0){	
				if(pX10 < x0 && pX10 < x1){ 
					
					settingEdge(x0,y0,x1,y1);
					outxx[++incr]=(deltaX);
					outyy[incr]=(deltaY);

				}
				outxx[++incr]=pX1;
				outyy[incr]=pY1;
			} else if(pX10 >= x0 ) { 
				settingEdge(x0,y0,x1,y1);
				outxx[++incr]=(deltaX);
				outyy[incr]=(deltaY);

			}
			pX10 = pX1;
			pY10 = pY1;
		}

	}


	
	/**
     * sets the x and y vertices in the polygon and initializes the x and y
     * values, outside of the polygon, to be found. 
     */
	private void settingNew() {
		in = 0;
		for(int j =0;j<inX.length;j++)
			if(inX[j]!=0)
				in = in + 1;

		inx = inX;
		iny = inY;	
		
	}
	
	
	
	/**
     * sets the x and y vertices in the polygon and initializes the x and y
     * values, outside of the polygon, to be found. 
     *
	 * @param currx0, first point laying on the vertical axes.
     * @param currx1, second point laying on the vertical axes.
     * @param curry0, first point laying on the horizontal axes.
     * @param curry1, second point laying on the horizontal axes.
     */
	@SuppressWarnings("unused")
	private void settingXY(float currX0, float currY0, float currX1, float currY1) {
		x0=currX0;
		y0=currY0;
		x1=currX0;
		y1=currY1;
			
		//sets the x and y new values
		for(int j =0;j<outxx.length;j++){
			inX[j] = outxx[j];
			inY[j] = outyy[j];
		}
		
		//sets the x and y new values
		int x = 0;
		for ( float i: outyy){
			outxx[x] = 0; 
			outyy[x] = 0;
			x++;
		}
	}
	
	
	
	
	/**
     * Add all given vertices to the Global Edge Table in order.
     * if the current edge is greater than the next edge then current edge
     * is set to its position otherwise, the edge is swapped with the next 
     * edge and compared again.
     *
     * @param x0, vertices on a horizontal line that are outside of a polygon.
     * @param x1,
     * @param y1,
     * @param y1, vertices on a vertical line that are outside of a polygon.
     */
	private void settingEdge(float x0, float y0, float x1, float y1) {
		if(pX10==pX1) // setting delta x to 0 for vertices out of the polygon.
			dpX=0;
		else
			dpX = (pY10-pY1)/(pX10-pX1);  
		//setting delta y for vertices out of the polygon
		
		if(x1==x0)
			dpY=y1-y0; // setting delta x for the vertices in the polygon.
		else
			dpY = (y1-y0)/(x1-x0);
		// setting delta y for the vertices in the polygon.
		
		dpYXX= pY10-(dpX*pX10);
		dpYYX = y0-(dpY*x0);

		deltaX = ((dpYXX-dpYYX)/(dpY-dpX));
		deltaY = (dpY*((dpYXX-dpYYX)/(dpY-dpX))+dpYYX);
	}
}
