/**
 *
 * viewParams.java
 *
 * Simple class for setting up the viewing and projection transforms for the Transformation
 * Assignment.
 *
 * Students are to complete this class.
 *
 */

import javax.media.opengl.*;


public class viewParams {
	private int viewMatrixLoc;
	
	private float leftView = -1.5f, rightView  =  1.0f;
	private float topView  = 1.5f,  bottomView = -1.5f;
	private float nearView = 1.0f,  farView    =  8.5f;
	private float x, z, z1, x1, y, z2, t, t1, x2, z3, y1;
	
	
	
	
	/**
	 * constructor
	 */
	public viewParams()
	{
		x  = (float)(2.0*nearView)/(rightView-leftView);
		y = -((farView+nearView)  /(farView-nearView ));
		z  = (float)(2.0*nearView)/(topView-bottomView);
		
		x1 = (topView+bottomView) /(topView-bottomView);
		y1 = (float)(-2.0/(farView  -nearView  ));
		z1 = (rightView+leftView) /(rightView-leftView);
		
		x2 = (float)(2.0 /(rightView-leftView  ));
		z2 = (float)(-2.0*farView*nearView)/(farView-nearView);
    	z3 = (float)(2.0 /(topView  -bottomView)); 
    	
    	t  =  0.0f;
		t1 =  -1.0f;	
	}
    
    
    /**
     * This functions sets up the view and projection parameter for a frustrum
     * projection of the scene. See the assignment description for the values
     * for the projection parameters.
     *
     * You will need to write this function, and maintain all of the values needed
     * to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) program to which parameter values
     *    are to be sent
     *
     * @param gl2 - GL2 object on which all OpenGL calls are to be made
     *
     */
    public void setUpFrustrum (int program, GL2 gl2) {
    	viewTransformation(program, gl2);
	
    	float viewMatrix[]={x , t, t, t, t, z, t, t, z1, x1, y, t1, t, t, z2, -t1};
		viewMatrixLoc = gl2.glGetUniformLocation(program, "viewMatrix");
		gl2.glUniformMatrix4fv(viewMatrixLoc,1,false,viewMatrix,0);
	}
    
    
    
    
    /**
     * This functions sets up the view and projection parameter for an orthographic
     * projection of the scene. See the assignment description for the values
     * for the projection parameters.
     *
     * You will need to write this function, and maintain all of the values needed
     * to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) program to which parameter values
     *    are to be sent
     *
     * @param gl2 - GL2 object on which all OpenGL calls are to be made
     *
     */
    public void setUpOrtho (int program, GL2 gl2) {
    	viewTransformation(program, gl2);
    
		float viewMatrix[]={ x2, t, t, t, t, z3, t, t, t , t, y1, t,-z1,-x1, y, -t1};
		viewMatrixLoc = gl2.glGetUniformLocation(program, "viewMatrix");
		gl2.glUniformMatrix4fv(viewMatrixLoc, 1, false, viewMatrix, 0);
    }
    
    
    
    
    
    /**
     * This functions sets up the view and projection parameter for an orthographic
     * projection of the scene. See the assignment description for the values
     * for the projection parameters.
     *
     * You will need to write this function, and maintain all of the values needed
     * to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) program to which parameter values
     *    are to be sent
     *
     * @param gl2 - GL2 object on which all OpenGL calls are to be made
     *
     */
    private void viewTransformation(int program, GL2 gl2) {
       	
    	getNewAngle(t,-30.0f, 90.0f, gl2, program,   "angleLoc1");
		getNewAngle(t, 75.0f,     t, gl2, program,   "angleLoc2");
		getNewAngle(t, 2.50f,  3.5f, gl2, program,     "viewPt1");
		getNewAngle(-t1,   t,     t, gl2, program,  "viewPoint2");
		getNewAngle(t,   -t1,     t, gl2, program,   "upperView");
    }


    
    /**
     * This functions sets up the view and projection parameter for an orthographic
     * projection of the scene. See the assignment description for the values
     * for the projection parameters.
     *
     * You will need to write this function, and maintain all of the values needed
     * to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) program to which parameter values
     *    are to be sent
     * @param t2, t3, f, angle values that reference the view location of the objet.
     * @param name, name of the angle.
     *
     */
	private float[] getNewAngle(float t2, float t3, float f, GL2 gl2, int program, String name) {
		float[] temp	= new float[3];
			int angleLoc = 0;
		  	temp[0]   = t2; 
	        temp[1]   = t3; 
	        temp[2]   = f;
	        angleLoc   = gl2.glGetUniformLocation (program,name);
			gl2.glUniform3fv(angleLoc,1,temp,0);
		return temp;
	}
}
