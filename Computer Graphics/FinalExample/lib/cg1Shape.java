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
    	makeSphere(0.75f,30,30);
    	
    	//makeCube(20);
    }
    
    public void makeSphere (float radius, int slices, int stacks)
    {
    	double theta = 2*Math.PI / slices;
    	double Theta = theta;
    	double phi = Math.PI / stacks;
    	double Phi;
    	float x0 = radius;
    	float y0 = 0f;
    	float z0 = 0f;
    	float xh1,yh1,zh1,x1,y1,z1,xh2,yh2,zh2;
    	for(int i = 0; i<slices ;i++)
    	{
    		Phi = phi;
    		x0 = (float) (radius*Math.cos(Theta-theta)*Math.sin(Phi-phi));
			y0 = (float) (radius*Math.sin(Theta-theta)*Math.sin(Phi-phi));
			z0 = (float) (radius*Math.cos(Phi-phi));
			x1 = (float) (radius*Math.cos(Theta)*Math.sin(Phi-phi));
			y1 = (float) (radius*Math.sin(Theta)*Math.sin(Phi-phi));
			z1 = (float) (radius*Math.cos(Phi-phi));
    		for(int j=0;j<stacks;j++) 
    		{
    			xh1 = (float) (radius*Math.cos(Theta-theta)*Math.sin(Phi));
    			yh1 = (float) (radius*Math.sin(Theta-theta)*Math.sin(Phi));
    			zh1 = (float) (radius*Math.cos(Phi));
    			xh2 = (float) (radius*Math.cos(Theta)*Math.sin(Phi));
    			yh2 = (float) (radius*Math.sin(Theta)*Math.sin(Phi));
    			zh2 = (float) (radius*Math.cos(Phi));
    			this.addTriangle(x0, y0, z0, xh2, yh2, zh2, xh1, yh1, zh1, zh2, zh2, zh2, zh2, zh2, zh2);
    			this.addTriangle(xh2, yh2, zh2, x0, y0, z0, x1, y1, z1, zh2, zh2, zh2, zh2, zh2, zh2);
    			x0 = xh1; y0 = yh1; z0 = zh1;
    			x1 = xh2; y1 = yh2; z1 = zh2;
    			Phi+=phi;
    		}
    		Theta+=theta;
    	}
    }
    
    public void makeCube (int subdivisions)
	{
		float x0, y0, z0, x1, y1, z1, x2, y2, z2;
		float x10, y10, z10, x11, y11, z11, x12, y12, z12;
		float x20, y20, z20, x21, y21, z21, x22, y22, z22;
		float x30, y30, z30, x31, y31, z31, x32, y32, z32;
		float x40, y40, z40, x41, y41, z41, x42, y42, z42;
		float x50, y50, z50, x51, y51, z51, x52, y52, z52;
		float x60, y60, z60, x61, y61, z61, x62, y62, z62 = 0;

		y10 = -0.5f;
		y20 = -0.5f;
		y30 = -0.5f;
		y40 = -0.5f;
		y50 = -0.5f;
		x60 = -0.5f;
		for(int i = 0; i < subdivisions; i++) {
			x10 = -0.5f;			
			y11 = y10 + 1.0f/subdivisions;

			x20 = -0.5f;			
			y21 = y20 + 1.0f/subdivisions;

			x30 = 0.5f;
			y31 = y30 + 1.0f / subdivisions;

			z40 = 0.5f;
			y41 = y40 + 1.0f / subdivisions;

			z50 = -0.5f;
			y51 = y50 + 1.0f / subdivisions;

			z60 = 0.5f;
			x61 = x60 + 1.0f / subdivisions;

			for(int j = 0; j < subdivisions; j++) {
				x11 = x10 + 1.0f / subdivisions;  
				z10 = 0.5f;
				addTriangle(x10, y10, z10, x11, y10, z10, x11, y11, z10, z62, z62, z62, z62, z62, z62);
				addTriangle(x10, y10, z10, x11, y11, z10, x10, y11, z10, z62, z62, z62, z62, z62, z62);
				x10 = x11;

				x21 = x20 + 1.0f / subdivisions;  
				z20 = 0.5f;
				addTriangle(x20, y20, z20, x21, y20, z20, x21, y21, z20, z62, z62, z62, z62, z62, z62);
				addTriangle(x20, y20, z20, x21, y21, z20, x20, y21, z20, z62, z62, z62, z62, z62, z62);
				x20 = x21;

				x31 = x30 - 1.0f / subdivisions;
				addTriangle(x30, y30, -0.5f, x31, y30, -0.5f, x31, y31, -0.5f, z62, z62, z62, z62, z62, z62);
				addTriangle(x30, y30, -0.5f, x31, y31, -0.5f, x30, y31, -0.5f, z62, z62, z62, z62, z62, z62);
				x30 = x31;

				z41 = z40 - 1.0f / subdivisions;
				addTriangle(0.5f, y40, z40, 0.5f, y40, z41, 0.5f, y41, z41, z62, z62, z62, z62, z62, z62);
				addTriangle(0.5f, y40, z40, 0.5f, y41, z41, 0.5f, y41, z40, z62, z62, z62, z62, z62, z62);
				z40 = z41;

				z51 = z50 + 1.0f / subdivisions;
				addTriangle(-0.5f, y50, z50, -0.5f, y50, z51, -0.5f, y51, z51, z62, z62, z62, z62, z62, z62);
				addTriangle(-0.5f, y50, z50, -0.5f, y51, z51, -0.5f, y51, z50, z62, z62, z62, z62, z62, z62);
				z50 = z51;

				z61 = z60 - 1.0f / subdivisions;
				addTriangle(x60, 0.5f, z60, x61, 0.5f, z60, x61, 0.5f, z61, z62, z62, z62, z62, z62, z62);
				addTriangle(x60, 0.5f, z60, x61, 0.5f, z61, x60, 0.5f, z61, z62, z62, z62, z62, z62, z62);
				z60 = z61;

			}
			y10 = y11;

			y20 = y21;

			y30 = y31;

			y40 = y41;

			y50 = y51;

			x60 = x61;
		}

		x60 = -0.5f;
		for(int i = 0; i < subdivisions; i++){
			z60 = -0.5f;
			x61 = x60 + 1.0f / subdivisions;
			for(int j = 0; j < subdivisions; j++){
				z61 = z60 + 1.0f / subdivisions;
				addTriangle(x60, -0.5f, z60, x61, -0.5f, z60, x61, -0.5f, z61, z62, z62, z62, z62, z62, z62);
				addTriangle(x60, -0.5f, z60, x61, -0.5f, z61, x60, -0.5f, z61, z62, z62, z62, z62, z62, z62);
				z60 = z61;
			}
			x60 = x61;
		}     
	}

	/**
	 * makeCylinder - Create polygons for a cylinder with unit height, centered at
	 * the origin, with separate number of radial subdivisions and height 
	 * subdivisions.
	 *
	 * @param radius - Radius of the base of the cylinder
	 * @param radialDivision - number of subdivisions on the radial base
	 * @param heightDivisions - number of subdivisions along the height
	 *
	 * Can only use calls to addTriangle()
	 */
	public void makeCylinder (float radius, int radialDivisions, int heightDivisions)
	{	
		float x0 = 0;
		float y0 = 0.5f;
		float z0 = 0;
		float x1 = 0;
		float z1 = 0;
		float x = 0;
		float y = 0;
		float z = 0;
		
		float angle = 360 / radialDivisions;
		for (int i = 1; i <= radialDivisions; i++) {
			x = radius * (float) (Math.cos((angle * (Math.PI / 180))));
			z = radius * (float) (Math.sin((angle * (Math.PI / 180))));
			z = -z;
			if (i == 1) {
				if (heightDivisions == 1) {
					addTriangle(radius, 0.5f, 0.0f, x, 0.5f, z, 0.0f, 0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
					addTriangle(radius, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, x, -0.5f, z, angle, angle, angle, angle, angle, angle);
					addTriangle(radius, 0.5f, 0.0f, x, -0.5f, z, x, 0.5f, z, angle, angle, angle, angle, angle, angle);
					addTriangle(radius, 0.5f, 0.0f, radius, -0.5f, 0.0f, x, -0.5f, z, angle, angle, angle, angle, angle, angle);
				} 
				else {
					y0 = 0.5f;
					addTriangle(radius, 0.5f, 0.0f, x, 0.5f, z, 0.0f, 0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
					addTriangle(radius, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, x, -0.5f, z, angle, angle, angle, angle, angle, angle);
					for (int j = 1; j <= heightDivisions; j++) {
						float y1 = y0 - 1.0f / heightDivisions;
						addTriangle(radius, y0, 0.0f, x, y1, z, x, y0, z, y1, y1, y1, y1, y1, y1);
						addTriangle(radius, y0, 0.0f, radius, y1, 0.0f, x, y1, z, y1, y1, y1, y1, y1, y1);
						y0 = y1;
					}
				}
			}
			else if (i == radialDivisions) {
				if (heightDivisions == 1) {
					addTriangle(x1, 0.5f, z1, radius, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
					addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, radius, -0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
					addTriangle(x1, 0.5f, z1, radius, -0.5f, 0.0f, radius, 0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
					addTriangle(x1, 0.5f, z1, x1, -0.5f, z1, radius, -0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
				} 
				else {
					y0 = 0.5f;
					addTriangle(x1, 0.5f, z1, radius, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
					addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, radius, -0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
					for (int j = 1; j <= heightDivisions; j++) {
						float y1 = y0 - 1.0f / heightDivisions;
						addTriangle(x1, y0, z1, radius, y1, 0.0f, radius, y0,0.0f, y1, y1, y1, y1, y1, y1);
						addTriangle(x1, y0, z1, x1, y1, z1, radius, y1,0.0f, y1, y1, y1, y1, y1, y1);
						y0 = y1;
					}
				}
			}
			else if (i < radialDivisions) {				
				if (heightDivisions == 1) {
					addTriangle(x1, 0.5f, z1, x, 0.5f, z, 0.0f, 0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
					addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, x, -0.5f, z, angle, angle, angle, angle, angle, angle);
					addTriangle(x1, 0.5f, z1, x, -0.5f, z, x, 0.5f, z, angle, angle, angle, angle, angle, angle);
					addTriangle(x1, 0.5f, z1, x1, -0.5f, z1, x, -0.5f, z, angle, angle, angle, angle, angle, angle);
				} 
				else {
					y0 = 0.5f;
					addTriangle(x1, 0.5f, z1, x, 0.5f, z, 0.0f, 0.5f, 0.0f, angle, angle, angle, angle, angle, angle);
					addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, x, -0.5f, z, angle, angle, angle, angle, angle, angle);
					for (int j = 1; j <= heightDivisions; j++) {
						float y1 = y0 - 1.0f / heightDivisions;
						addTriangle(x1, y0, z1, x, y1, z, x, y0, z, y1, y1, y1, y1, y1, y1);
						addTriangle(x1, y0, z1, x1, y1, z1, x, y1, z, y1, y1, y1, y1, y1, y1);
						y0 = y1;
					}
				}
			}			
			x1 = x;	z1 = z;
			angle = (float)( angle + (360.0f / radialDivisions));
		}
	}

	/**
	 * makeCone - Create polygons for a cone with unit height, centered at the
	 * origin, with separate number of radial subdivisions and height 
	 * subdivisions.
	 *
	 * @param radius - Radius of the base of the cone
	 * @param radialDivision - number of subdivisions on the radial base
	 * @param heightDivisions - number of subdivisions along the height
	 *
	 * Can only use calls to addTriangle()
	 */
	public void makeCone (float radius, int radialDivisions, int heightDivisions)
	{
		float x1 = 0;
		float z1 = 0;
		float theta = 360.0f/ radialDivisions;
		float x = 0;
		float z = 0;
		for (int i = 1; i <= radialDivisions; i++) {
			x = radius * (float) (Math.cos((theta * (Math.PI / 180))));
			z = radius * (float) (Math.sin((theta * (Math.PI / 180))));
			z = -z;
			float invHD = 1 / (float) heightDivisions;
			float height = 1;
			float x10 = 0, y10 = 0, z10 = 0, x12 = 0, y12 = 0, z12 = 0;
			float x20 = 0, y20 = 0, z20 = 0, x22 = 0, y22 = 0, z22 = 0;
			float x30 = 0, y30 = 0, z30 = 0, x32 = 0, y32 = 0, z32 = 0; 

			if (i == 1){
				addTriangle(radius, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, x, -0.5f, z, z32, z32, z32, z32, z32, z32);
				x10 = radius; y10 = -0.5f; z10 = 0.0f;
				x12 = x; y12 = -0.5f; z12 = z;
			}

			else if (i == radialDivisions){

				addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, radius, -0.5f, 0.0f, z32, z32, z32, z32, z32, z32);
				x20 = x; y20 = -0.5f; z20 = z;
				x22 = x1; y22 = -0.5f; z22 = z1;
			}

			else {
				addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, x, -0.5f, z, z32, z32, z32, z32, z32, z32);
				x30 = x; y30 = -0.5f; z30 = z;
				x32 = x1; y32 = -0.5f; z32 = z1;
			}

			for (int j = 1; j <= heightDivisions; j++) {
				if(i == 1) {
					addTriangle(x12 * height, y12, z12 * height, x10 * (height - invHD), y10 + invHD, z10 * (height - invHD),
							x10 * height, y10, z10 * height, z32, z32, z32, z32, z32, z32);
					addTriangle(x10 * (height - invHD), y10 + invHD, z10* (height - invHD), x12 * height, y12, z12 * height,
							x12 * (height - invHD), y12 + invHD, z12 * (height - invHD), z32, z32, z32, z32, z32, z32);

					height -= invHD; 
					y12 += invHD;
					y10 += invHD;
				}

				else if(i == radialDivisions) {
					addTriangle(x22 * height, y22, z22 * height, x20 * height, y20, z20 * height, x20
							* (height - invHD), y20 + invHD, z20 * (height - invHD), z32, z32, z32, z32, z32, z32);
					addTriangle(x20 * (height - invHD), y20 + invHD, z20 * (height - invHD), x22 * (height - invHD), y22 + invHD, z22
							* (height - invHD), x22 * height, y22, z22 * height, z32, z32, z32, z32, z32, z32);
					height -= invHD; 
					y22 += invHD;
					y20 += invHD;
				}

				else {
					addTriangle(x32 * height, y32, z32 * height, x30 * height, y30, z30 * height, x30
							* (height - invHD), y30 + invHD, z30 * (height - invHD), z32, z32, z32, z32, z32, z32);
					addTriangle(x30 * (height - invHD), y30 + invHD, z30 * (height - invHD), x32 * (height - invHD), y32 + invHD, z32
							* (height - invHD), x32 * height, y32, z32 * height, z32, z32, z32, z32, z32, z32);
					height -= invHD; 
					y32 += invHD;
					y30 += invHD;
				}
			}
			x1 = x;	z1 = z;
			theta += (360f / radialDivisions);
		}
	}
    
    public void makeSphere2 (float radius, int slices, int stacks)
	{
    	float new_theta = 360f / slices;
    	float new_phi = 180f / stacks;    	
    	
    	for(float theta = 0f; theta < 360; theta += new_theta){
    		float theta2 = theta + new_theta;
    		
    		float x = radius * (float)Math.cos(Math.toRadians(theta));
    		float new_x = radius * (float)Math.cos(Math.toRadians(theta2));
    		float z = radius * (float)Math.sin(Math.toRadians(theta));
    		float new_z = radius * (float)Math.sin(Math.toRadians(theta2));
    		    		
    		for(int i=0; i < stacks; i++){
    			float phi = i * new_phi;
    			float phi2 = phi + new_phi;
    			
    			float u0 = theta / 360f;
            	float u1 = (theta2) / 360f; 
            	float v0 = phi / 180f;
            	float v1 = (phi2) / 180f;
            	
    			float x0 = x * (float)Math.sin(Math.toRadians(phi));
    			float x1 = new_x * (float)Math.sin(Math.toRadians(phi));
    			float x2 = x * (float)Math.sin(Math.toRadians(phi2));
    			float x3 = new_x * (float)Math.sin(Math.toRadians(phi2));

    			float y0 = radius * (float)Math.cos(Math.toRadians(phi));
    			float y1 = y0;
    			float y2 = radius * (float)Math.cos(Math.toRadians(phi2));
    			float y3 = y2;
    			
    			float z0 = z * (float)Math.sin(Math.toRadians(phi));
    			float z1 = new_z * (float)Math.sin(Math.toRadians(phi));
    			float z2 = z * (float)Math.sin(Math.toRadians(phi2));
    			float z3 = new_z * (float)Math.sin(Math.toRadians(phi2));
    			
				this.addTriangle(x0,y0,z0,u0,v0,
                        		 x1,y1,z1,u1,v0,
                        		 x2,y2,z2,u0,v1);
				
				this.addTriangle(x3,y3,z3,u1,v1,
                        		 x2,y2,z2,u0,v1,
                        		 x1,y1,z1,u1,v0);
    		}
    	}
	}
    
    public void makeCube2 (int subdivisions)
	{
		float x0, y0, z0, x1, y1, z1, x2, y2, z2;
		float x10, y10, z10, x11, y11, z11, x12, y12, z12;
		float x20, y20, z20, x21, y21, z21, x22, y22, z22;
		float x30, y30, z30, x31, y31, z31, x32, y32, z32;
		float x40, y40, z40, x41, y41, z41, x42, y42, z42;
		float x50, y50, z50, x51, y51, z51, x52, y52, z52;
		float x60, y60, z60, x61, y61, z61, x62, y62, z62;

		y10 = -0.5f;
		y20 = -0.5f;
		y30 = -0.5f;
		y40 = -0.5f;
		y50 = -0.5f;
		x60 = -0.5f;
		for(int i = 0; i < subdivisions; i++) {
			x10 = -0.5f;			
			y11 = y10 + 1.0f/subdivisions;

			x20 = -0.5f;			
			y21 = y20 + 1.0f/subdivisions;

			x30 = 0.5f;
			y31 = y30 + 1.0f / subdivisions;

			z40 = 0.5f;
			y41 = y40 + 1.0f / subdivisions;

			z50 = -0.5f;
			y51 = y50 + 1.0f / subdivisions;

			z60 = 0.5f;
			x61 = x60 + 1.0f / subdivisions;

			for(int j = 0; j < subdivisions; j++) {
				x11 = x10 + 1.0f / subdivisions;  
				z10 = 0.5f;
				addTriangle(x10, y10, z10, x10, y10, x11, y10, z10, x11, y11, x11, y11, z10, x11, y11);
				addTriangle(x10, y10, z10, x10, y10, x11, y11, z10, x11, y11, x10, y11, z10, x10, y11);
				x10 = x11;

				x21 = x20 + 1.0f / subdivisions;  
				z20 = 0.5f;
				addTriangle(x20, y20, z20, x20, y20, x21, y20, z20, x21, y20, x21, y21, z20, x21, y21);
				addTriangle(x20, y20, z20, x20, y20, x21, y21, z20, x21, y21, x20, y21, z20, x20, y21);
				x20 = x21;

				x31 = x30 - 1.0f / subdivisions;
				addTriangle(x30, y30, -0.5f, x30, y30, x31, y30, -0.5f, x31, y30, x31, y31, -0.5f, x31, y31);
				addTriangle(x30, y30, -0.5f, x30, y30, x31, y31, -0.5f, x31, y31, x30, y31, -0.5f, x30, y31);
				x30 = x31;

				z41 = z40 - 1.0f / subdivisions;
				addTriangle(0.5f, y40, z40, 0.5f, y40, 0.5f, y40, z41, 0.5f, y40, 0.5f, y41, z41, 0.5f, y41);
				addTriangle(0.5f, y40, z40, 0.5f, y40, 0.5f, y41, z41, 0.5f, y41, 0.5f, y41, z40, 0.5f, y41);
				z40 = z41;

				z51 = z50 + 1.0f / subdivisions;
				addTriangle(-0.5f, y50, z50, -0.5f, y50, -0.5f, y50, z51, -0.5f, y50, -0.5f, y51, z51, -0.5f, y51);
				addTriangle(-0.5f, y50, z50, -0.5f, y50, -0.5f, y51, z51, -0.5f, y51, -0.5f, y51, z50, -0.5f, y51);
				z50 = z51;

				z61 = z60 - 1.0f / subdivisions;
				addTriangle(x60, 0.5f, z60, x60, 0.5f, x61, 0.5f, z60, x61, 0.5f, x61, 0.5f, z61, x61, 0.5f);
				addTriangle(x60, 0.5f, z60, x60, 0.5f, x61, 0.5f, z61, x61, 0.5f, x60, 0.5f, z61, x60, 0.5f);
				z60 = z61;

			}
			y10 = y11;

			y20 = y21;

			y30 = y31;

			y40 = y41;

			y50 = y51;

			x60 = x61;
		}

		x60 = -0.5f;
		for(int i = 0; i < subdivisions; i++){
			z60 = -0.5f;
			x61 = x60 + 1.0f / subdivisions;
			for(int j = 0; j < subdivisions; j++){
				z61 = z60 + 1.0f / subdivisions;
				addTriangle(x60, -0.5f, z60, x60, -0.5f, x61, -0.5f, z60, x61, -0.5f, x61, -0.5f, z61, x61, -0.5f);
				addTriangle(x60, -0.5f, z60, x60, -0.5f, x61, -0.5f, z61, x61, -0.5f, x60, -0.5f, z61, x61, -0.5f);
				z60 = z61;
			}
			x60 = x61;
		}     
	}
}
