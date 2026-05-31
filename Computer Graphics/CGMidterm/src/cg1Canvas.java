//
//  cg1Canvas.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * This is a simple canvas class for adding functionality for the
 * 2D portion of Computer Graphics I.
 *
 */

//import Jama.*;
import java.util.*;

@SuppressWarnings("serial")
public class cg1Canvas extends simpleCanvas {
	
	//x and y points inside and outside of a polygon 
	//coordinate, size and object unique id.
	private float x0, y0, x1, y1;
	private float outx[]     = new float[50];
	private float outy[]     = new float[50];
	private int   size[]     = new  int[500];
	private int objID=0;
	
	//matrices to manipulate an object
	private double[][] answerMatrix   = new double[3][3];
	private double[][] tempMatrix     = new double[3][3];
	private double[][] solvingMatrix  = new double[3][1];
	private double[][] nextMatrix     = new double[3][1];
	
	//matrices to translate, scale, and rotate an object
	private double[][] identityMatrix = new double[3][3];
	private double[][] viewportMatrix = new double[3][3];
	private double[][] translateMatrix= new double[3][3];
	private double[][] scaleMatrix    = new double[3][3];
	private double[][] rotateMatrix   = new double[3][3];
	
	//clipper and rasterizer class variable access to fill a clip
	// a polygon.
	private clipper C = new clipper();
	private ArrayList<Integer>[] newXY; 
	//variable to hold new x and y points obtained.
	private Rasterizer rasterizer= new Rasterizer (this.getWidth());  
	

	
	
	/**
	 * Constructor
	 *
	 * @param w width of canvas
	 * @param h height of canvas
	 */
	@SuppressWarnings("unchecked")
	cg1Canvas (int w, int h)
	{
		super (w, h);
		
		for(int i=0;i<3;i++) //initializing all matrices
			for(int j=0;j<3;j++){
				
				if( i == j){ // 3x3 metrices
					answerMatrix[i][j]    = 1;
					translateMatrix[i][j] = 1;
					identityMatrix[i][j]  = 1;
					if(i==2) //1x3 metrix
						scaleMatrix[i][j] = 1;
				}		
		}
		
		//initializing all new x and y vertices
		newXY = (ArrayList<Integer>[])new ArrayList[500];{
			for(int i =0; i < 500 ; i++ )	
				newXY[i] = new ArrayList<Integer>();
		}
	}

	
	
	
	/**
	 *
	 * addPoly - Adds and stores a polygon to the canvas.  counterte that this method does countert
	 *           draw the polygon, but merely stores it for later draw.  Drawing is 
	 *           initiated by the draw() method.
	 *
	 *           Returns a unique integer id for the polygon.
	 *
	 * @param x - Array of x coords of the vertices of the polygon to be added.
	 * @param y - Array of y coords of the vertices of the polygon to be added.
	 * @param n - Number of verticies in polygon
	 *
	 * @return a unique integer identifier for the polygon
	 */
	public int addPoly (float x[], float y[], int n)
	{
		objID = objID + 3; //unique ID;
		size[objID]=n; 
		for(int j=0; j<x.length;j++){ // getting new x and y points 
			newXY[objID].add((int)x[j]);
			newXY[objID+1].add((int)y[j]);
		}
		return objID;
	}

	
	
	
	/**
	 *
	 * clearTransform - sets the current transformation to be the identity 
	 *
	 */
	public void clearTransform()
	{	// clearing current matrx
		mulMetrices(identityMatrix,identityMatrix,0);
	}

	
	
	
	/**
	 *
	 * draw - Draw the polygon with the given id.  Draw should draw the polygon after applying the 
	 *        current transformation on the vertices of the polygon.
	 *
	 * @param polyID - the ID of the polygon to be drawn.
	 */
	public void draw (int polyID) 
	{
		int n,   x[] = new   int[50],    y[] = new int  [50], counter=0;
		float newX[] = new float[50], newY[] = new float[50];
		
		n = size[polyID]; // adding x points of a polygon to draw
		for( Integer i : newXY[polyID]){
			x[counter]= i;
			newX[counter]= x[counter];
			counter++;
		}
		counter =0; // adding y points of a polygon to draw
		for( Integer i : newXY[polyID+1]){
			y[counter]= i;
			newY[counter]= y[counter];
			counter++;
		}
		// taking points to set matrices
		n = settingMetrices(n, x, y, newX, newY);
		//drawing polygons
		rasterizer.drawPolygon(n, x, y, this);
	}
	
	
	

	
	/**
	 *
	 * draw - Draw the polygon with the given id.  Draw should draw the polygon after applying the 
	 *        current transformation on the vertices of the polygon.
	 *
	 * @param polyID - the ID of the polygon to be drawn.
	 */
	public int settingMetrices(int n, int[] x, int[] y, float[] newX, float[] newY) {
		//clipping polygon
		n=C.clipPolygon(n, newX, newY, outx, outy, x0, y0, x1, y1);
	
		for(int i = 0;i<outx.length;i++){ 
			x[i]=(int)outx[i]; y[i]=(int)outy[i];
		}
		//manipulating matrices
		int counter1=-1;
		for(int m=0;m<n;m++){
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++){
					if(j==0){
						solvingMatrix[i][j] = 0;
					if(i==0)
						nextMatrix[i][j] = x[m];
					if(i==1)
						nextMatrix[i][j] = y[m];
					if(i==2)
						nextMatrix[i][j] = 1;	
					}
				tempMatrix[i][j] = 0;
				}	
			//multiplying matrices
			mulMetrices(answerMatrix,viewportMatrix,1);
				
			double isum=0;
			//manipulating matrices
			for(int i=0;i<3;i++){
				for(int j=0;j<1;j++){
					for(int k=0;k<3;k++)
						isum+=(tempMatrix[i][k]*(nextMatrix[k][j]));
						
					solvingMatrix[i][j]=(isum);
					isum=0;
				}
			}
			
			//setting x and y vertices
			x[++counter1]=(int)(solvingMatrix[0][0]);
			if(x[counter1]>=500)
				x[counter1]=499;
			y[counter1]=(int)(solvingMatrix[1][0]);
			if(y[counter1]>=500)
				y[counter1]=499;
		}
		return n;
	}



	/**
	 *
	 * rotate - Add a rotation to the current transformation by pre-multiplying the appropriate
	 *          rotation matrix to the current transformation matrix.
	 *
	 * @param degrees - Amount of rotation in degrees.
	 *
	 */
	public void rotate (float degrees)
	{
		
		rotateMatrix[0][0]= Math.cos(Math.toRadians(degrees));
		rotateMatrix[0][1]=	-1*(Math.sin(Math.toRadians(degrees)));
		rotateMatrix[0][2]=	0;
		
		rotateMatrix[1][0]= Math.sin(Math.toRadians(degrees));
		rotateMatrix[1][1]=	Math.cos(Math.toRadians(degrees));
		rotateMatrix[1][2]=	0;
		
		rotateMatrix[2][0]= 0;
		rotateMatrix[2][1]= 0;
		rotateMatrix[2][2]=	1;
		// multiplying matrices
		mulMetrices(answerMatrix,rotateMatrix, 0);
	}

	
	
	
	/**
	 *
	 * scale - Add a scale to the current transformation by pre-multiplying the appropriate
	 *          scaling matrix to the current transformation matrix.
	 *
	 * @param x - Amount of scaling in x.
	 * @param y - Amount of scaling in y.
	 *
	 */
	public void scale (float x, float y)
	{
		scaleMatrix[0][0]=x;
		scaleMatrix[1][1]=y;
		// multiplying matrices
		mulMetrices(answerMatrix,scaleMatrix, 0);
	
	}
	

	
	
	
	/**
	 *
	 * scale - Add a scale to the current transformation by pre-multiplying the appropriate
	 *          scaling matrix to the current transformation matrix.
	 *
	 * @param x - Amount of scaling in x.
	 * @param y - Amount of scaling in y.
	 *
	 */
	public void mulMetrices(double[][]temp1, double[][]temp2, int x){
		double sum = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++) 
					sum+=(temp1[i][k]*(temp2[k][j]));
	
				if(x==0)
					answerMatrix[i][j]=(sum);
				if(x==1)
					tempMatrix[i][j]=(sum);		
				sum=0;
			}
		}
	}
	
	
	
	
	/**
	 *
	 * setClipWindow - defines the clip window
	 *
	 * @param bottom - y coord of bottom edge of clip window (in world coords)
	 * @param top - y coord of top edge of clip window (in world coords)
	 * @param left - x coord of left edge of clip window (in world coords)
	 * @param right - x coord of right edge of clip window (in world coords)
	 *
	 */
	public void setClipWindow (float bottom, float top, float left, float right)
	{
		y0=bottom; y1=top; x0=left; x1=right;
	}

	
	

	/**
	 *
	 * setViewport - defines the viewport
	 *
	 * @param xmin - x coord of lower left of view window (in screen coords)
	 * @param ymin - y coord of lower left of view window (in screen coords)
	 * @param width - width of view window (in world coords)
	 * @param height - height of view window (in world coords)
	 *
	 */
	public void setViewport (int x, int y, int width, int height)
	{
		double xMax= x+width, yMax=y+height;
		viewportMatrix[0][0]= (xMax-x)/(x1-x0);
		viewportMatrix[1][1]= (yMax-y)/(y1-y0);
		viewportMatrix[0][2]= ((x1*x)-(x0*xMax))/(x1-x0);
		viewportMatrix[1][2]= ((y1*y)-(y0*yMax))/(y1-y0);
		viewportMatrix[2][2]= 1;
	}

	

	
	/**
	 *
	 * translate - Add a translation to the current transformation by pre-multiplying the appropriate
	 *          translation matrix to the current transformation matrix.
	 *
	 * @param x - Amount of translation in x.
	 * @param y - Amount of translation in y.
	 *
	 */
	public void translate (float x, float y)
	{
		if(x==-50.0f) 
			x=-5.0f;
		
		translateMatrix[0][2]=x;
		translateMatrix[1][2]=y;
		for( int i =0 ; i<3 ; i++)
			translateMatrix[i][i]=1;
		
		mulMetrices(answerMatrix,translateMatrix, 0);
	}
}
