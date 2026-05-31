import java.util.Arrays;
import java.util.Collections;

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

	float sX, sY, pX, pY;
	float orgX0;
	float orgY0;
	float orgX1;
	float orgY1;
	float a, b,c,d, temX, temY;
	/**
	 * clipPolygon
	 * 
	 * Clip the polygon with vertex count in and vertices inx/iny
	 * against the rectangular clipping region specified by lower-left corner
	 * (x0,y0) and upper-right corner (x1,y1). The resulting vertices are
	 * placed in outx/outy.  
	 * 
	 * The routine should return the with the vertex count of polygon
	 * resulting from the clipping.
	 *
	 * @param in the number of vertices in the polygon to be clipped
	 * @param inx - x coords of vertices of polygon to be clipped.
	 * @param int - y coords of vertices of polygon to be clipped.
	 * @param outx - x coords of vertices of polygon resulting after clipping.
	 * @param outy - y coords of vertices of polygon resulting after clipping.
	 * @param newx0 - x coord of lower left of clipping rectangle.
	 * @param newy0 - y coord of lower left of clipping rectangle.
	 * @param newx1 - x coord of upper right of clipping rectangle.
	 * @param newy1 - y coord of upper right of clipping rectangle.
	 *
	 * @return number of vertices in the polygon resulting after clipping
	 * 
	 */
	public int clipPolygon(int in, float inx[], float iny[], float outx[], 
			float outy[], float x0, float y0, float x1, float y1)
	{
		// clear outx[] and outy[]
		Arrays.fill(outx, 0);
		Arrays.fill(outy, 0);
		orgX0 = x0;
		orgY0 = y0;
		orgX1 = x1;
		orgY1 = y1;



		float orginx[] = new float[50];
		float orginy[] = new float[50];

		float newinx[] = new float[50];
		float newiny[] = new float[50];

		//copy from outx and outy to inx and iny
		for(int j =0;j<inx.length;j++){
			orginx[j]= inx[j];
			orginy[j]= iny[j];
		}

		//==============================left edge=====================================	
		//for left clip
		x0=orgX0;
		y0=orgY0;
		x1=orgX0;
		y1=orgY1;


		int incr=-1;
		// considering left side of the polygon
		//loop till all edges are considered
		
		sX = inx[in-1];
		sY = iny[in-1]; 
		for(int i=0; i< in; i++){
			pX = inx[i];
			pY = iny[i];
			//case 1 & 4, considering the left clipper
			if(pX >= x0){	// P is inside clip plane case 1 and 4
				if(sX < orgX0 && sX < orgX1){ // S is not inside clip plane case 4
					edgeClipper(incr);
					outx[++incr]=(temX);
					outy[incr]=(temY);
				}
				
				outx[++incr]=pX;
				outy[incr]=pY;
				
			} else if(sX >= x0 ) { // case 2 and 3 , if S is inside the plane
				//computer intersection of P, S and clip plane.
				edgeClipper(incr);
				outx[++incr]=(temX);
				outy[incr]=(temY);

			}
			sX = pX;
			sY = pY;
		}

		//==============================right edge=====================================

		//for right edge
		x0=orgX1;
		y0=orgY0;
		x1=orgX1;
		y1=orgY1;

		//copy from outx and outy to inx and iny
		for(int j =0;j<outx.length;j++){
			newinx[j] = outx[j];
			newiny[j] = outy[j];
		}
		Arrays.fill(outx, 0);
		Arrays.fill(outy, 0);

		in =0;
		for(int j =0;j<newinx.length;j++)
			if(newinx[j]!=0)
				in++;

		inx = newinx;
		iny = newiny;

		incr=-1;
		// considering left side of the polygon
		//loop till all edges are considered
		//float sX, sY, pX, pY;
		sX = inx[in-1];
		sY = iny[in-1]; 
		for(int i=0; i< in; i++){
			pX = inx[i];
			pY = iny[i];
			//case 1 & 4, considering the left clipper
			if(pX <=  x0 && pX <=  x1 ){	// P is inside clip plane case 1 and 4
				if(x0 < sX ){ // S is not inside(outside) clip plane case 4
					edgeClipper(incr);
					outx[++incr]=(temX);
					outy[incr]=(temY);

				}
				outx[++incr]=pX;
				outy[incr]=pY;
			} else if(sX <= x0 && sX <= x1) { // case 2 and 3 , if S is inside the plane
				//computer intersection of P, S and clip plane.
				edgeClipper(incr);
				outx[++incr]=(temX);
				outy[incr]=(temY);

			}
			sX = pX;
			sY = pY;
		}
		//====================================== top clipper========================
		//for top edge
/*		x0=orgX1;
		y0=orgY1;
		x1=orgX0;
		y1=orgY1;*/
		
		x0=orgX0;
		y0=orgY1;
		x1=orgX1;
		y1=orgY1;

		Arrays.fill(newinx, 0);
		Arrays.fill(newiny, 0);

		for(int j =0;j<outx.length;j++){
			newinx[j] = outx[j];
			newiny[j] = outy[j];
		}
		Arrays.fill(outx, 0);
		Arrays.fill(outy, 0);

		in =0;
		for(int j =0;j<newinx.length;j++)
			if(newinx[j]!=0)
				in++;

		inx = newinx;
		iny = newiny;

		incr=-1;
		// considering left side of the polygon
		//loop till all edges are considered
		//float sX, sY, pX, pY;
		if(in>0){
			sX = inx[in-1];
			sY = iny[in-1]; 
		}
		for(int i=0; i< in; i++){
			pX = inx[i];
			pY = iny[i];
			//case 1 & 4, considering the left clipper
			if(pY <=y0 && pY <=y1 ){	// P is inside clip plane case 1 and 4
				if(sY > y0 && sY > y1){ // S is not inside clip plane case 4
					edgeClipper(incr);
					outx[++incr]=(temX);
					outy[incr]=(temY);

				}
				outx[++incr]=pX;
				outy[incr]=pY;
			} else if(sY <= y0 && sY <= y1) { // case 2 and 3 , if S is inside the plane
				//computer intersection of P, S and clip plane.
				edgeClipper(incr);
				outx[++incr]=(temX);
				outy[incr]=(temY);

			}
			sX = pX;
			sY = pY;
		}
		//==============================bottom clipper===============================
		//for bottom edge
		x0=orgX0;
		y0=orgY0;
		x1=orgX1;
		y1=orgY0;

		Arrays.fill(newinx, 0);
		Arrays.fill(newiny, 0);

		for(int j =0;j<outx.length;j++){
			newinx[j] = outx[j];
			newiny[j] = outy[j];
		}
		Arrays.fill(outx, 0);
		Arrays.fill(outy, 0);

		in =0;
		for(int j =0;j<newinx.length;j++)
			if(newinx[j]!=0)
				in++;

		inx = newinx;
		iny = newiny;

		incr=-1;
		// considering left side of the polygon
		//loop till all edges are considered
		//float sX, sY, pX, pY;
		if(in>0){
			sX = inx[in-1];
			sY = iny[in-1]; 
		}
		for(int i=0; i< in; i++){
			pX = inx[i];
			pY = iny[i];
			//case 1 & 4, considering the bottom clipper
			if(pY >= y0 && pY >= y1){	// P is inside clip plane case 1 and 4
				if(sY < y0 && sY < y1){ // S is not inside clip plane case 4
					edgeClipper(incr);
					if(c==a){
						temX=d;
						temY = (c*((b-d)/d));
					}
					else{
					temX = ((b-d)/(c-a));
					temY = (c*((b-d)/(c-a))+d);
					
					outx[++incr]=(temX);
					outy[incr]=(temY);
					}
				}
				outx[++incr]=pX;
				outy[incr]=pY;
			} else if(sY >= y0 && sY >= y1) { // case 2 and 3 , if S is inside the plane
				//computer intersection of P, S and clip plane.
				edgeClipper(incr);

				outx[++incr]=(temX);
				outy[incr]=(temY);
			}
			sX = pX;
			sY = pY;
		}
		int size=0;
		for(int i =0;i<outx.length;i++)
			if(outx[i]!= 0.0)
				size++;

		return size; // should return number of vertices in clipped poly.
	}
	private void edgeClipper(int incr) {
		
			float a, b,c,d, temX, temY;
			if(sX==pX)
				a=0;
			else
				a = (sY-pY)/(sX-pX);
			if(orgX1==orgX0)
				c=orgY1-orgY0;
			else
				c = (orgY1-orgY0)/(orgX1-orgX0);
			
			b= sY-(a*sX);
			d = orgY0-(c*orgX0);

			temX = ((b-d)/(c-a));
			temY = (c*((b-d)/(c-a))+d);		
	}
}
