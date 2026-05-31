//
//  Rasterizer.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * 
 * A simple class for performing rasterization algorithms.
 *
 */
import java.util.*;
public class Rasterizer {
    
    /**
     * number of scanlines
     */
    int n_scanlines;
    
    /**
     * Constructor
     *
     * @param n - number of scanlines
     *
     */
    Rasterizer (int n)
    {
        n_scanlines = n;
    }
    
    
    


    
    
    
    /**
     * Draw a line from (x0,y0) to (x1, y1) on the simpleCanvas C.
     *
     * Implementation should be using the Midpoint Method
     *
	 * You are to add the implementation here using only calls
	 * to C.setPixel()
     *
     * @param x0 - x coord of first endpoint
     * @param y0 - y coord of first endpoint
     * @param x1 - x coord of second endpoint
     * @param y1 - y coord of second endpoint
     * @param C - The canvas on which to apply the draw command.
	 */
	public void drawLine (int x0, int y0, int x1, int y1, simpleCanvas C){

		int dE, dNE, x, y, d, m = 0;
		int dy = y1 - y0; 
		int dx = x1 - x0;
			
		if((x0 - x1) > 0){ //if delta x is greater than 0.
			drawLine(x1, y1, x0, y0, C); // Mirror the lines this way,
			return;
		}
		
		if (Math.abs(y1 - y0) > Math.abs(x1 - x0)) { // if delta y is greater
			// than delta x then mirror then swap the end and start points
			
		       // line and y axis angle is less then 45 degrees 
		       // that is why go on the next procedure 
			  drawLine(y0, x0, y1, x1, C); 
		       return;  
		} 

		if(dy < 0) { // if delta y is negative then change the slope direction
			m  = -1;
			dy = -dy;
		}
		else { 
			m = 1;
		}

		
		dE  = 2 * dy;
		dNE = 2 * ( dy - dx );
		d = dE - dx; /* 2*dy – dx */
		y = y0;
		
		for( x = x0; x <= x1; ++x ) { // loop to set lines repeatedly
			// from all given x and y.
			
			C.setPixel( x, y );// setting pixels
			if( d <= 0 ) { // if below the middle point
				d += dE;  /* choose E */
			} else { // if above the middel point
				d += dNE; /* choose NE */
				  y += m;  
			} 
			C.setPixel( y, x );// setting pixels in the opposite direction.
		} 
	}
}