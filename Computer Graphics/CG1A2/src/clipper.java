import java.util.ArrayList;
import java.util.Arrays;

//
//  Clipper.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * Object for performing clipping
 *
 */

public class clipper {
	
    /**
     * variable that contains a new edge.
     */
	Points e;
	
    /**
     * A list of the points remaining inside a polygon.
     */
	ArrayList<Points> inXY;
	
    /**
     * A list of the points that are said to be outside of a polygon.
     */
	ArrayList<Points> outXY;
	
    /**
     * vertices on a polygon
     */
	ArrayList<Points> polygonVerx;
	
    /**
     * delta values of the edges 
     */
	float dx1, dy1, dx0, dy0, dxy, dyx, ddxy;
	
    /**
     * new points and delta values
     */
	float px0, py0, pxy, py1, pyx, px1, dpxy, deltaX, deltaY, deltaXY ;
   
	/**
     * variable containing the horizontal and vertical value intersection.
     */
	float horEdgeIntersect, verEdgeIntersect;
	
	
	
	/**
	 * Object containing vertices of an edge
	 *
	 */
	public class Points{
		
	    /**
	     * number of scanlines
	     */
		private float x;
	    /**
	     * number of scanlines
	     */
		private float y;
		Points edge;


		
	    /**
	     * Constructor
	     *
	     * @param x, a vertex reference.
	     * @param y, a vertex reference.
	     */
		Points( float x, float y){
			this.x = x;
			this.y = y;
		}

		
		
	    /**
	     * gets the vertex with reference to the horizontal line.
	     * 
	     * @return x, vertex with reference to the horizontal line.
	     */
		public float getX() {
			return x;
		}

		
		
		
		 /**
	     * gets the vertex with reference the vertical line.
	     * 
	     * @return y, vertex with reference to the vertical line.
	     */
		public float getY() {
			return y;
		}
		
		
		
		
		/**
	     * it looks for right edges inside of the polygon and any of this is 
	     * outside this is checked and then a vertex is created to then be 
	     * added to the list of vertices, or points, outside of the polygon.
	     *
	     * @param x0, first point laying on the vertical axes.
	     * @param x1, second point laying on the vertical axes.
	     * @param y0, first point laying on the horizontal axes.
	     * @param y1, second point laying on the horizontal axes.
	     */
		public void setEdge1( float x0, float y0, float y1, float x1){

			for(Points edge : inXY) {
				if(edge.getX() > x0) { 
					if(!(e.getX() > x0)) { // points outside
						outXY.add(intersect(edge, x0, y0, x1, y1));
					}
					outXY.add(edge); // adding point
				}
				else if(e.getX() > x0) { // points outside
					outXY.add(intersect(edge, x0, y0, x1, y1));
				}
				e = edge; // new edge of intersecting points 
		   }
			
			if(outXY.size() != 0) {
				outXY.add(outXY.get(0));
			}
		}
		
		
		
		
		/**
	     * checks vertices, or points, already found and outside of a polygon
	     * to set its delta value and edge.
	     *
		 * @param x0, first point laying on the vertical axes.
	     * @param x1, second point laying on the vertical axes.
	     * @param y0, first point laying on the horizontal axes.
	     * @param y1, second point laying on the horizontal axes.
	     * 
	     * @return setDeltaXY(1,edge), an edge outside the polygon whose delta
	     * values have been set up according to its axes.
	     */
		Points intersect(Points edge, float x0, float y0, float x1, float y1) {
			
			edge = setDeltaXY(0, edge); // setting edge points.
			dx0 = x0 - x1;
			dy0 = y0-y1;
			dxy = x0*y1;
			dyx = y0*x1;
			ddxy = dxy - dyx;
		
			return setDeltaXY(1, edge); //setting intersection points edge.
		}




		/**
	     * sets up the delta values of two points or a vertex according to 
	     * the line that the vertex is laying on obtained by the vertex found
	     * outside of the polygon, the it creates an edge according to its 
	     * reference on the axes.
	     *
	     * @param i, to set an edge delta values according to its order.
	     * @param edge, edge found whose reference is outside of the polygon
	     * @return PointOnVertices, edges whose vertices or points reside 
	     * outside of the polygon.
	     */
		private Points setDeltaXY(int i, Points edge) {
			if( i == 0){
				px1 = edge.getX(); //edge to the horizontal line
				py1 = edge.getY(); // edge to the vertical line
				dy1 = e.getY() - edge.getY(); // delta of lines
				dx1 = e.getX() - edge.getX(); // delta of lines
			return edge;
			}else{
				px0  = e.getX() ;
				py0  = e.getY() ; 
				pxy  = px0 * py1;
				pyx  = py0 * px1;
				dpxy = pxy - pyx;
				deltaX  = dx1 * ddxy - dx0 * dpxy;
				deltaY  = dy1 * ddxy - dy0 * dpxy;
				deltaXY = dx0 * dy1 - dy0 * dx1  ;
				horEdgeIntersect = deltaX/deltaXY; // new point,horizontal line
				verEdgeIntersect = deltaY/deltaXY; // new point, vertical line
			return new Points(horEdgeIntersect, verEdgeIntersect); // edge 
			}
		}
	}
    
	
	
	
	
    /**
     * clipPolygon
     * 
     * Clip the polygon with vertex count in and vertices inx/iny
     * against the rectangular clipping region specified by lower-left corner
     * (x0,y0) and upper-right corner (x1,y1). The resulting vertices are
     * placed in outx/outy.  
     * 
     * The routine should return the with the vertex count of polygon
     * resultinhg from the clipping.
     *
     * @param in the number of vertices in the polygon to be clipped
     * @param inx - x coords of vertices of polygon to be clipped.
     * @param int - y coords of vertices of polygon to be clipped.
     * @param outx - x coords of vertices of polygon resulting after clipping.
     * @param outy - y coords of vertices of polygon resulting after clipping.
     * @param x0 - x coord of lower left of clipping rectangle.
     * @param y0 - y coord of lower left of clipping rectangle.
     * @param x1 - x coord of upper right of clipping rectangle.
     * @param y1 - y coord of upper right of clipping rectangle.
     *
     * @return number of vertices in the polygon resulting after clipping
     * 
     */
    public int clipPolygon(int in, float inx[], float iny[], float outx[], 
                    float outy[], float x0, float y0, float x1, float y1)
    {
        // Your implementation goes here
		polygonVerx = new ArrayList<Points>();	
		for( int x = 0; x != outy.length; x++){
			outx[x] = 0; outy[x] = 0;
			if( x < in)
				polygonVerx.add(x,new Points(inx[x], iny[x]));
		}
		outXY = new ArrayList<Points>(polygonVerx);
		inXY = new ArrayList<Points>(outXY);
		int i = 0;
		while (i != 4){  // four conditions to find edges outside a polygon
			
			clear();
			if( i < 3) resetPoints();
			
			if( i == 0) 					// right edges ...
				e.setEdge1(x0, y0, y1, x0);
			
			if( i == 1 && inXY.size() != 0)	// bottom edges ...
				setEdge2(x0, y0, x1, y0);

			if( i == 2 && inXY.size() != 0) // top edges ...	
				setEdge3(x0, y1, x1, y1);
			
			if(i == 3 && inXY.size() != 0)  // left  edges ...	
				setEdge4(x1, y0, x1, y1);
			
			i++;
		}	
		
		return settingOutPoints(outx, outy);
		// returns the amount of vertices to be clipped in a polygon.
    }

    
    
    
	/**
     * Add all given vertices to the Global Edge Table in order.
     * if the current edge is greater than the next edge then current edge
     * is set to its position otherwise, the edge is swapped with the next 
     * edge and compared again.
     *
     * @param outx,vertices on a horizontal line that are outside of a polygon.
     * @param outy, vertices on a vertical line that are outside of a polygon.
     * 
     * @return size, returns the size of the area that is said not to be part
     * of the polygon.
     */
	private int settingOutPoints(float[] outx, float[] outy) {
		int x = 0;
		while( x != outXY.size() &&  outXY.size() != 0 ){
			
			if( x == outXY.size()  ){ // clipping points in the polygon
				outx[x] = outXY.get(0).getX();
				outy[x] = outXY.get(0).getY();
			}else{	// clipping points outside the polygon
				outx[x] = outXY.get(x).getX();
				outy[x] = outXY.get(x).getY();
			}x++;
		}
		// returning the size of the area to clip.
		return (outXY.size() == 0) ? 0 : outx.length; 
	}

	
	
	
	/**
     * it looks for left ages inside of the polygon and any of this is 
     * outside this is checked and then a vertex is created to then be 
     * added to the list of vertices, or points, outside of the polygon.
     *
     * @param x0, first point laying on the vertical axes.
     * @param x1, second point laying on the vertical axes.
     * @param y0, first point laying on the horizontal axes.
     * @param y1, second point laying on the horizontal axes.
     */
	private void setEdge4(float x1, float y0, float x12, float y1) {
		resetPoints();	
		for(Points edge : inXY) {
			if(edge.getX() < x1) {
				if(!(e.getX() < x1)) // points outside
					outXY.add(e.intersect(edge, x1, y0, x1, y1));
				
				outXY.add(edge); // adding point.
			}
			else if(e.getX() < x1)  // points outside
				outXY.add(e.intersect(edge, x1, y0, x1, y1));
						
			e = edge; // new intersecting points edge
		}	
		if(outXY.size() != 0) 
			outXY.add(outXY.get(0));
	}


	

	
	/**
     * it looks for top edges inside of the polygon and any of this is 
     * outside this is checked and then a vertex is created to then be 
     * added to the list of vertices, or points, outside of the polygon.
     *
     * @param x0, first point laying on the vertical axes.
     * @param x1, second point laying on the vertical axes.
     * @param y0, first point laying on the horizontal axes.
     * @param y1, second point laying on the horizontal axes.
     */
	private void setEdge3(float x0, float y1, float x1, float y12) {
		for(Points edge : inXY) {
			if(edge.getY() < y1) {
				if(!(e.getY() < y1)) // points outside
					outXY.add(e.intersect(edge, x0, y1, x1, y1));
				outXY.add(edge); // adding point.
			}
			else if(e.getY() < y1) // points outside
				outXY.add(e.intersect(edge, x0, y1, x1, y1));
			e = edge; // new intersecting points edge
		}
		
		if(outXY.size() != 0) 
			outXY.add(outXY.get(0));
	}

	
	
	
	/**
     * it looks for bottom edges inside of the polygon and any of this is 
     * outside this is checked and then a vertex is created to then be 
     * added to the list of vertices, or points, outside of the polygon.
     *
     * @param x0, first point laying on the vertical axes.
     * @param x1, second point laying on the vertical axes.
     * @param y0, first point laying on the horizontal axes.
     * @param y1, second point laying on the horizontal axes.
     */
	private void setEdge2(float x0, float y0, float x1, float y02) {
		e = new Points(px1, py1);
		
		for(Points edge : inXY) {
			if(edge.getY() > y0) {
				if(!(e.getY() > y0)) // points outside
					outXY.add(e.intersect(edge, x0, y0, x1, y0));
					
				outXY.add(edge); // adding point.
			}
			else if(e.getY() > y0) // points outside
				outXY.add(e.intersect(edge, x0, y0, x1, y0));
			e = edge; // new intersecting points edge
		}	
		if(outXY.size() != 0) 
			outXY.add(outXY.get(0));
	}

	
	
	
	/**
     * Clear outside vertices found in a polygon in order to find other points,
     * in other polygons, laying outside of such polygons and must be clipped.
     */
	private void resetPoints() {
		outXY.clear();
		px1 =inXY.get(inXY.size()-1).getX();
		py1 = inXY.get(inXY.size()-1).getY();
		e = new Points(px1, py1);
	}

	
	
	
	/**
     * Clear inside vertices found in a polygon in order to find other points,
     * in other polygons, laying inside of such polygons and must be clipped.
     */
	private void clear() {
		inXY.clear();
		for(int i1 = 0; i1 < outXY.size(); i1++) {
			inXY.add(outXY.get(i1));
		}
	}
}
