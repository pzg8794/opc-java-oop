/**
 *
 * textureParams.java
 *
 * Simple class for setting up the textures for the textures
 * Assignment.
 *
 * Students are to complete this class.
 *
 */

import java.io.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.texture.*;

public class coordinates
{
    // materials / lighting data
    private float lightpos[] = {0.0f, 2.0f, 3.0f, 0.8f};
    private float lightColor[] = {1.0f, 1.5f, 1.5f, 2.0f};
    private float diffuse[] = {0.75f, 0.7f, 2.0f, 0.0f};
    
    int diff_color, spec_color, spec_exp, light_color, light_position;
    
    int theta, trans, scale, eyepoint, up, lookat, left, right, top, bottom, near, far, choice;
    
	/**
	 * constructor
	 */
	public coordinates()
	{
        
	}
    
    /**
     * This functions loads texture data to the GPU.
     *
     * You will need to write this function, and maintain all of the values needed
     * to be sent to the various shaders.
     *
     * @param filename - The name of the texture file.
     *
     */
    public void loadTexture (String filename)
    {
    	Texture tex_id;
    	try {
    	  InputStream stream = getClass().getResourceAsStream(filename);
    	  TextureData data = TextureIO.newTextureData(GLProfile.getDefault(), 
    	                         stream, false, "jpg");
    	  tex_id = TextureIO.newTexture(data);
    	 }
    	 catch (IOException exc) {
    	      // Do error handling here
    	 }
    }

    
    /**
     * This functions sets up the parameters
     * for texture use.
     *
     * You will need to write this function, and maintain all of the values needed
     * to be sent to the various shaders.
     *
     * @param shaderProgID - The ID of an OpenGL (GLSL) shaderProgID to which parameter values
     *    are to be sent
     *
     * @param gl2 - GL2 object on which all OpenGL calls are to be made
     *
     */
    public void setUpTextures (int shaderProgID, GL2 gl2, int objID)
    {
    	gl2.glEnable(GL.GL_TEXTURE_2D);
    	gl2.glActiveTexture(0);
    }

    
    public void setUpMain1 (int shaderProgID, GL2 gl2, int objID, float angles[], float transXYZ[])
    {
    	// Here's code for the diffuse component.
        int light = gl2.glGetUniformLocation( shaderProgID , "lightPosition" );
        int lightc = gl2.glGetUniformLocation( shaderProgID , "lightColor" );
        int diff = gl2.glGetUniformLocation( shaderProgID , "diffuseColor" );
        
        gl2.glUniform4fv( light , 1 , lightpos, 0 );
        gl2.glUniform4fv( lightc , 1 , lightColor, 0 );
        gl2.glUniform4fv( diff , 1 , diffuse, 0 );
        
    	switch(objID) {
    	case 0:
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 0.0f,-30.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,1.0f,1.0f,1.0f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,1.0f,0.0f,-1.0f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 1.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
            break;
            
    	case 1:
    		
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 0.0f, 0.0f, 360.0f);
    		
    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.1f, 0.25f,0.1f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans, 0.335f, 1.4f, 1.0f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 1.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.69f, 0.0f, 8.0f, 1.0f);
    		break;
    		
    	case 2:
    		
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 0.0f, 0.0f,360.f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,.6f, 2.3f,.6f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,1.0f, -1.8f,-1.0f);

    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);
            
            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    	case 3:
    		
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, -2.0f, 40.0f, 70.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.1f, 1.4f,0.1f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans, 1.0f,-2.7f,-2.08f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    		
    	case 4:
    		
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 30.0f, 90.0f,120.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.1f, 1.8f,0.1f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,1.3f,-1.8f,-0.9f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    		
    	case 5:
    		
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 30.0f,20.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.2f,0.2f,0.2f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,0.35f,-2.4f,-1.2f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    		
    	case 6:
    		
     		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 30.0f, 20.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.2f, 0.2f,0.2f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans, 1.19f, -1.5f, 0.2f);
    		
     		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    		
    	case 7:
      		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 90.0f, 90.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.1f, 0.5f, 0.1f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,0.6f,-3.3f,-1.08f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    		
    	case 8:
    		
     		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 90.0f, 90.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.1f, 0.5f, 0.1f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,1.45f,-3.55f,-1.08f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    		
    	case 9:
    		
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 90.0f,90.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.25f,0.2f,0.25f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,0.17f,-2.2f, 0.2f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    		
    	case 10:
    		
      		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 90.0f, 90.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.25f, 0.2f,0.25f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans, 1.05f,-2.5f, 0.2f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    		
    	case 11:
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, -30.0f, -20.0f, -360.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.2f,0.2f,0.2f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,0.4f, 1.2f, 0.2f);
    		
//    		float lightColor[] = {3.0f, 1.5f, 1.5f, 3.0f};
//    		this.lightColor = lightColor;
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
    		
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;
    		
    	case 12:
    		
     		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 30.0f, 20.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.2f, 0.2f,0.2f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
//    		float lightColor[] = {3.0f, 1.5f, 1.5f, 3.0f};
//    		this.lightColor = lightColor;
    		gl2.glUniform3f(trans, 0.8f, 1.08f, 0.2f);
    		
     		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

            diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
            gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
    		break;  		
    	}
  
    	
         eyepoint = gl2.glGetUniformLocation(shaderProgID,"eyepoint");
         gl2.glUniform3f(eyepoint, 0.0f, 3.0f,3.0f);
         
         up = gl2.glGetUniformLocation(shaderProgID, "up");
         gl2.glUniform3f(up,0.0f,1.0f,0.0f);
         
         lookat = gl2.glGetUniformLocation(shaderProgID, "lookat");
         gl2.glUniform3f(lookat,1.0f,0.0f,0.0f);
         
         left = gl2.glGetUniformLocation(shaderProgID, "left");
         gl2.glUniform1f(left, -1.5f);
         
         right = gl2.glGetUniformLocation(shaderProgID,"right");
         gl2.glUniform1f(right,1.0f);
         
         top = gl2.glGetUniformLocation(shaderProgID, "top");
         gl2.glUniform1f(top,1.5f);
         
         bottom = gl2.glGetUniformLocation(shaderProgID, "bottom");
         gl2.glUniform1f(bottom,-1.5f);
         
         near = gl2.glGetUniformLocation(shaderProgID,"near");
         gl2.glUniform1f(near,1.0f);
         
         far = gl2.glGetUniformLocation(shaderProgID, "far");
         gl2.glUniform1f(far, 8.5f);  
         
         choice = gl2.glGetUniformLocation(shaderProgID, "choice");
         gl2.glUniform1f(choice,2.0f);
         
     
         spec_color=gl2.glGetUniformLocation(shaderProgID, "spec_color");
         gl2.glUniform4f(spec_color, 1.0f, 1.0f, 1.0f, 1.0f);
         
         spec_exp=gl2.glGetUniformLocation(shaderProgID, "spec_exp");
         gl2.glUniform1f(spec_exp, 100.0f);         
    }
    
    
    
    public void setUpMain2 (int shaderProgID, GL2 gl2, int objID, float angles[], float transXYZ[])
    {
    	
    	switch(objID){
    	case 0:
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 0.0f,-30.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,1.0f,1.0f,1.0f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,1.0f,0.0f,-1.0f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);
    		break;
    		
    	case 1:
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 0.0f,-30.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,0.2f,0.2f,0.2f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,0.5f,1.0f,2.0f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);
    		break;
    		
    	case 2:
      		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 0.0f,-30.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,1.0f,1.0f,1.0f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,2.0f,0.0f,1.0f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);
    		break;
   default:
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
    		gl2.glUniform3f(theta, 0.0f,-30.0f,90.0f);

    		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
    		gl2.glUniform3f(scale,1.0f,1.0f,1.0f);

    		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
    		gl2.glUniform3f(trans,0.0f,-2.0f,2.0f);
    		
    		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
            gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
            
            light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
            gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);
            break;
    	}
    	
    	         
         eyepoint = gl2.glGetUniformLocation(shaderProgID,"eyepoint");
         gl2.glUniform3f(eyepoint, 0.0f, 3.0f,3.0f);
         
         up = gl2.glGetUniformLocation(shaderProgID, "up");
         gl2.glUniform3f(up,0.0f,1.0f,0.0f);
         
         lookat = gl2.glGetUniformLocation(shaderProgID, "lookat");
         gl2.glUniform3f(lookat,1.0f,0.0f,0.0f);
         
         left = gl2.glGetUniformLocation(shaderProgID, "left");
         gl2.glUniform1f(left, -1.5f);
         
         right = gl2.glGetUniformLocation(shaderProgID,"right");
         gl2.glUniform1f(right,1.0f);
         
         top = gl2.glGetUniformLocation(shaderProgID, "top");
         gl2.glUniform1f(top,1.5f);
         
         bottom = gl2.glGetUniformLocation(shaderProgID, "bottom");
         gl2.glUniform1f(bottom,-1.5f);
         
         near = gl2.glGetUniformLocation(shaderProgID,"near");
         gl2.glUniform1f(near,1.0f);
         
         far = gl2.glGetUniformLocation(shaderProgID, "far");
         gl2.glUniform1f(far, 8.5f);  
         
         choice = gl2.glGetUniformLocation(shaderProgID, "choice");
         gl2.glUniform1f(choice,1.0f);
         
         
         // Here's code for the diffuse component.
         int light = gl2.glGetUniformLocation( shaderProgID , "lightPosition" );
         int lightc = gl2.glGetUniformLocation( shaderProgID , "lightColor" );
         int diff = gl2.glGetUniformLocation( shaderProgID , "diffuseColor" );
         
         gl2.glUniform4fv( light , 1 , lightpos, 0 );
         gl2.glUniform4fv( lightc , 1 , lightColor, 0 );
         gl2.glUniform4fv( diff , 1 , diffuse, 0 );

         
         diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
         gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
         
         spec_color=gl2.glGetUniformLocation(shaderProgID, "spec_color");
         gl2.glUniform4f(spec_color, 1.0f, 1.0f, 1.0f, 1.0f);
         
         spec_exp=gl2.glGetUniformLocation(shaderProgID, "spec_exp");
         gl2.glUniform1f(spec_exp, 100.0f);         
    }
    
    
}
