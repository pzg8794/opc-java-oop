import java.io.IOException;
import java.io.InputStream;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLProfile;

import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

/**
 * Class to locate objects according to its coordinates.
 * 
 * @author Piter Garcia
 *
 */
public class Coordinates {

	
    // materials / lighting data
    private float lightpos[]   	= {0.0f, 2.0f, 3.0f, 0.8f};
    private float lightColor[] 	= {1.0f, 1.5f, 1.5f, 2.0f};
    private float diffuse[]    	= {0.75f, 0.7f, 2.0f, 1.0f};

    
    /**
     * Applying different views of the art created
     * 
     * @param shaderProgID
     * @param gl2
     * @param objID
     * @param angles
     * @param transXYZ
     * @param viewMode
     */
   public void view (int shaderProgID, GL2 gl2, int objID, float angles[], float transXYZ[], int viewMode)
   {
	   int diff_color, spec_color, spec_exp, light_color, light_position = 0; 
	   int theta, trans, scale, eyepoint, up, lookat, left, right, top, bottom, near, far, choice;
	   
	   if( viewMode == 1){
		   //composed view of the robot.
	   
   	// Here's code for the diffuse component.
       int light = gl2.glGetUniformLocation( shaderProgID  , "lightPosition" );
       int lightc = gl2.glGetUniformLocation( shaderProgID , "lightColor" );
       int diff = gl2.glGetUniformLocation( shaderProgID   , "diffuseColor" );
       
       gl2.glUniform4fv( light , 1 , lightpos, 0 );
       gl2.glUniform4fv( lightc , 1 , lightColor, 0 );
       gl2.glUniform4fv( diff , 1 , diffuse, 0 );
       
   	switch(objID) {
   	case 0:
   	// creating the head of the robot.
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
   	//creating the neck of the robot.
   		
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
   	//creating the body of the robot.
   		
   		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
   		gl2.glUniform3f(theta, 0.0f, 0.0f,360.f);

   		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
   		gl2.glUniform3f(scale,.5f, 2.3f,.5f);

   		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
   		gl2.glUniform3f(trans,1.0f, -1.8f,-1.0f);

   		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
   	 gl2.glUniform4f(light_position, 1.0f, 2.0f, 3.0f, 1.0f);
           
        light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
        gl2.glUniform4f(light_position, 3.0f, 2.0f, 3.0f, 1.0f);
           
        diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
        gl2.glUniform4f(diff_color, 0.69f, 0.0f, 8.0f, 1.0f);
   		break;
   	case 3:
   	//creating the right arm of the robot.
   		
   		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
   		gl2.glUniform3f(theta, 0.0f, 40.0f, 70.0f);

   		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
   		gl2.glUniform3f(scale,0.1f, 1.3f,0.1f);

   		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
   		gl2.glUniform3f(trans, 0.65f, -1.43f, -0.9f);
   		
   		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
        gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
           
        light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
        gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

        diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
        gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
   		break;
   		
   	case 4:
   	//creating the left arm of the robot.
   		
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
   	//creating the left hand of the robot.
   		
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
   	//creating the right hand of the robot.
   		
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
   	//creating the left leg of the robot.
   	
     		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
   		gl2.glUniform3f(theta, 90.0f, 90.0f,90.0f);

   		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
   		gl2.glUniform3f(scale,0.1f, 0.5f, 0.1f);

   		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
   		gl2.glUniform3f(trans,0.7f,-3.3f,-1.08f);
   		
   		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
           gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
           
           light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
           gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

           diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
           gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
   		break;
   		
   	case 8:
   	//creating the right leg of the robot.
   		
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
   		gl2.glUniform3f(theta, 90.0f, 90.0f,90.0f);

   		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
   		gl2.glUniform3f(scale,0.1f, 0.5f, 0.1f);

   		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
   		gl2.glUniform3f(trans,1.35f,-3.55f,-1.08f);
   		
   		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
           gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
           
           light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
           gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

           diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
           gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
   		break;
   		
   	case 9:
   	//creating the left foot of the robot.
   		
   		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
   		gl2.glUniform3f(theta, 90.0f,90.0f,90.0f);

   		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
   		gl2.glUniform3f(scale,0.25f,0.15f,0.25f);

   		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
   		gl2.glUniform3f(trans,0.22f,-2.2f, 0.2f);
   		
   		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
        gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
           
        light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
        gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

        diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
        gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
   		break;
   		
   	case 10:
   		//creating the right foot of the robot.
   		
     		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
   		gl2.glUniform3f(theta, 90.0f, 90.0f,90.0f);

   		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
   		gl2.glUniform3f(scale,0.25f, 0.15f,0.25f);

   		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
   		gl2.glUniform3f(trans, 0.95f,-2.45f, 0.2f);
   		
   		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
           gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
           
           light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
           gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

           diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
           gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
   		break;
   		
   	case 11:
   	 	//creating the left eye of the robot.
   		
   		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
   		gl2.glUniform3f(theta, -30.0f, -20.0f, -360.0f);

   		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
   		gl2.glUniform3f(scale,0.2f,0.2f,0.2f);

   		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
   		gl2.glUniform3f(trans,0.4f, 1.2f, 0.2f);

   		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
   		
           gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
           
           light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
           gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

           diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
           gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
   		break;
   		
   	case 12:
   	   	//creating the right eye of the robot.
   		
    		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
   		gl2.glUniform3f(theta, 30.0f, 20.0f,90.0f);

   		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
   		gl2.glUniform3f(scale,0.2f, 0.2f,0.2f);

   		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 

   		gl2.glUniform3f(trans, 0.8f, 1.08f, 0.2f);
   		
   		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
        gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
           
        light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
        gl2.glUniform4f(light_position, 0.0f, 2.0f, 3.0f, 1.0f);

        diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
        gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, 1.0f);
   		break;  	
   		
   		
   	case 13:
   	//Decorating environment.
   		
		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
		gl2.glUniform3f(theta, 30.0f, 20.0f,90.0f);

		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
		gl2.glUniform3f(scale,6.2f, 6.2f,6.2f);

		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
		gl2.glUniform3f(trans, 1.88f, -2.08f, -5.2f);
		
		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
        gl2.glUniform4f(light_color, 1.0f, 1.0f, 1.0f, 1.0f);
       
        light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
        gl2.glUniform4f(light_position, 1.0f, 1.0f, 1.0f, 1.0f);

        diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
        gl2.glUniform4f(diff_color, 0.89f, 0.0f, 0.0f, -1.0f);
		break;  
		
   	default:
   		
   		theta = gl2.glGetUniformLocation (shaderProgID, "theta");
   		gl2.glUniform3f(theta, 90.0f, 90.0f, 90.0f);

   		scale = gl2.glGetUniformLocation (shaderProgID, "scale");
   		gl2.glUniform3f(scale, 0.5f, 2.5f, 0.5f);

   		trans = gl2.glGetUniformLocation (shaderProgID, "trans"); 
   		gl2.glUniform3f(trans, 0.1f, -1.8f, -0.1f);

   		light_color=gl2.glGetUniformLocation(shaderProgID, "light_color");
   		gl2.glUniform4f(light_position, 1.0f, 2.0f, 3.0f, 1.0f);
           
        light_position=gl2.glGetUniformLocation(shaderProgID, "light_position");
        gl2.glUniform4f(light_position, 3.0f, 2.0f, 3.0f, 1.0f);
           
        diff_color=gl2.glGetUniformLocation(shaderProgID, "diff_color");
        gl2.glUniform4f(diff_color, 0.69f, 0.0f, 8.0f, 1.0f);
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
        
	   }else if( viewMode == 2){
		   
		   // descomposed view of the robot.
	    	switch(objID){
	    	case 0:
	    		// Object which the head of the roobot was created.
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
	    		// object which the neck of the robot object was created.
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
	    		// object used for to build the body of the robot.
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
		   // all other objects used to build robot and environment.
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

	
	/**
	 * constructor
	 */
	public Coordinates()
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
    	
    	try {
    		
    	  InputStream stream 		 = getClass().getResourceAsStream(filename);
    	  TextureData texture_data 	 = TextureIO.newTextureData(GLProfile.getDefault(),stream,false,"jpg");
    	  TextureIO.newTexture(texture_data);
    	  
    	 }
    	 catch (IOException exc) {
    		 
    		 System.err.println("Error Loading Imagen\n" + exc);
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
    public void setUpTextures (int shaderProgID, GL2 gl2)
    {
    	
    	gl2.glEnable(GL.GL_TEXTURE_2D);
    	gl2.glActiveTexture(0);
    	
    }

}
