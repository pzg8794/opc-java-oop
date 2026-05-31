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
import javax.media.opengl.fixedfunc.*;
import Jama.*;
import java.nio.FloatBuffer; 
import javax.media.opengl.glu.GLU;

public class viewParams
{
	int theta;
	int trans;
	int scale;
	int eyepoint;
	int up;
	int lookat;
	int left;
	int right;
	int top;
	int bottom;
	int near;
	int far;
	int choice;
	/**
	 * constructor
	 */
	public viewParams()
	{
        
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
    public void setUpFrustrum (int program, GL2 gl2)
    {
    	theta = gl2.glGetUniformLocation (program, "theta");
        gl2.glUniform3f(theta, 0.0f,-30.0f,90.0f);
        
        scale = gl2.glGetUniformLocation (program, "scale");
    	gl2.glUniform3f(scale,1.0f,5.0f,1.0f);
         
        trans = gl2.glGetUniformLocation (program, "trans"); 
        gl2.glUniform3f(trans,1.0f,0.0f,-1.0f);
         
         eyepoint = gl2.glGetUniformLocation(program,"eyepoint");
         gl2.glUniform3f(eyepoint, 0.0f, 3.0f,3.0f);
         
         up = gl2.glGetUniformLocation(program, "up");
         gl2.glUniform3f(up,0.0f,1.0f,0.0f);
         
         lookat = gl2.glGetUniformLocation(program, "lookat");
         gl2.glUniform3f(lookat,1.0f,0.0f,0.0f);
         
         left = gl2.glGetUniformLocation(program, "left");
         gl2.glUniform1f(left, -1.5f);
         
         right = gl2.glGetUniformLocation(program,"right");
         gl2.glUniform1f(right,1.0f);
         
         top = gl2.glGetUniformLocation(program, "top");
         gl2.glUniform1f(top,1.5f);
         
         bottom = gl2.glGetUniformLocation(program, "bottom");
         gl2.glUniform1f(bottom,-1.5f);
         
         near = gl2.glGetUniformLocation(program,"near");
         gl2.glUniform1f(near,1.0f);
         
         far = gl2.glGetUniformLocation(program, "far");
         gl2.glUniform1f(far, 8.5f);  
         
         choice = gl2.glGetUniformLocation(program, "choice");
         gl2.glUniform1f(choice,1.0f);
    	
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
    public void setUpOrtho (int program, GL2 gl2)
    {
    	theta = gl2.glGetUniformLocation (program, "theta");
        gl2.glUniform3f(theta, 0.0f,-30.0f,90.0f);
        
        scale = gl2.glGetUniformLocation (program, "scale");
    	gl2.glUniform3f(scale,1.0f,5.0f,1.0f);
         
        trans = gl2.glGetUniformLocation (program, "trans"); 
        gl2.glUniform3f(trans,1.0f,0.0f,-1.0f);
         
         eyepoint = gl2.glGetUniformLocation(program,"eyepoint");
         gl2.glUniform3f(eyepoint, 0.0f, 3.0f,3.0f);
         
         up = gl2.glGetUniformLocation(program, "up");
         gl2.glUniform3f(up,0.0f,1.0f,0.0f);
         
         lookat = gl2.glGetUniformLocation(program, "lookat");
         gl2.glUniform3f(lookat,1.0f,0.0f,0.0f);
         
         left = gl2.glGetUniformLocation(program, "left");
         gl2.glUniform1f(left, -1.5f);
         
         right = gl2.glGetUniformLocation(program,"right");
         gl2.glUniform1f(right,1.0f);
         
         top = gl2.glGetUniformLocation(program, "top");
         gl2.glUniform1f(top,1.5f);
         
         bottom = gl2.glGetUniformLocation(program, "bottom");
         gl2.glUniform1f(bottom,-1.5f);
         
         near = gl2.glGetUniformLocation(program,"near");
         gl2.glUniform1f(near,1.0f);
         
         far = gl2.glGetUniformLocation(program, "far");
         gl2.glUniform1f(far, 8.5f);  
         
         choice = gl2.glGetUniformLocation(program, "choice");
         gl2.glUniform1f(choice,2.0f);
         
    }
	
}