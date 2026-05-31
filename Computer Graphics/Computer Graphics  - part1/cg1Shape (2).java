/**
 * cg1Shape.java
 *
 * Class that includes routines for tessellating a number of basic shapes
 *
 * Students are to supply their implementations for the
 * functions in this file using the function "addTriangle()" to do the 
 * tessellation.
 *
 */

import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.io.*;


public class cg1Shape extends simpleShape
{
    /**
	 * constructor
	 */
	public cg1Shape()
	{
	}
    
     
    /**
     * makeDefaultShape - creates a "unit" shape of your choice using your tesselation routines.
     * 
     *
     */
    public void makeDefaultShape ()
    {
        // tessellate your favorite unit shape here.
    	makeSphere(0.5f, 50, 50);
    }
    /**
     * makeSphere - Create sphere of a given radius, centered at the origin, 
     * using spherical coordinates with separate number of thetha and 
     * phi subdivisions.
     *
     * @param radius - Radius of the sphere
     * @param slides - number of subdivisions in the theta direction
     * @param stacks - Number of subdivisions in the phi direction.
     *
     * Can only use calls to addTriangle
     */
    
    public void makeSphere (float radius, int slices, int stacks) {
    	
    	float thetaInCircle = 360f/slices, phiInCirlce = 180f/stacks;
    	for(float theta=0f; theta < 360; theta += thetaInCircle){
    		float xTheta = radius*(float)Math.cos((Math.toRadians(theta))),xNextTheta=radius*(float)Math.cos((Math.toRadians(theta+thetaInCircle))),
    		zTheta = radius*(float)Math.sin((Math.toRadians(theta))),zNextTheta=radius*(float)Math.sin((Math.toRadians(theta+thetaInCircle)));
    		if(theta + thetaInCircle==360){
    			xNextTheta = radius; zNextTheta=0f;
    		}
    		
    		for(int i=0; i < stacks; i++){
    			float phi=i*phiInCirlce,xPhi=xTheta*(float)Math.sin((Math.toRadians(phi))),xPhiNext=xNextTheta*(float)Math.sin((Math.toRadians(phi))), 
    			xPhiTop=xTheta*(float)Math.sin((Math.toRadians(phi+phiInCirlce))),xPhiTopRight=xNextTheta*(float)Math.sin((Math.toRadians(phi+phiInCirlce))),
    			zPhi=zTheta*(float)Math.sin((Math.toRadians(phi))),zPhiNext=zNextTheta*(float)Math.sin((Math.toRadians(phi))),
    			zPhiTop=zTheta*(float)Math.sin((Math.toRadians(phi+phiInCirlce))),zPhiTopNext=zNextTheta*(float)Math.sin((Math.toRadians(phi+phiInCirlce))),
      			yPhi=radius*(float)Math.cos((Math.toRadians(phi))),yPhiNext=radius*(float)Math.cos((Math.toRadians(phi +phiInCirlce)));
    			addTriangle(xPhi,yPhi,zPhi,(theta/360f),(phi/180f),xPhiNext,yPhi,zPhiNext,((theta+thetaInCircle)/360f),(phi/180f),xPhiTop,yPhiNext,zPhiTop,(theta/360f),((phi+phiInCirlce)/180f));
				addTriangle(xPhiTopRight,yPhiNext,zPhiTopNext,((theta+thetaInCircle)/360f),((phi+phiInCirlce)/180f),xPhiTop,yPhiNext,zPhiTop,(theta/360f),((phi+phiInCirlce)/180f),
                xPhiNext,yPhi,zPhiNext,((theta+thetaInCircle)/360f),(phi/180f));
    		}
    	}
    }

}
