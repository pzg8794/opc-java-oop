
//
//  shaderMain.java
//  
//
//  Main class for lighting / shading assignment.
//
//  Students should not be modifying this file.
//

import java.awt.*;
import java.nio.*;
import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas; 


public class tessMain implements GLEventListener, KeyListener
{
        
	/**
	 * buffer info 
	 */
    private int vbuffer[];
    private int ebuffer[];
    int numVerts[];
    
    /**
     * program and variable IDs
     */
    public int shaderProgID;
    
    /**
     * shape info
     */
    cg1Shape myShape;
    Coordinates myPhong;
    
    /**
     * my canvas
     */
    GLCanvas myCanvas;
    
	private int tAngle;
	private float angles[];

	private float transXYZ[];

	static int objType;
	int viewMode, objs;

	/**
	 * constructor
	 */
	public tessMain(GLCanvas G)
	{
        vbuffer = new int [1];
        ebuffer = new int[1];
        numVerts = new int [1];
        
        myCanvas = G;
        myPhong = new Coordinates();
        

		objs = 14;
		angles = new float[3];
		angles[0] = 30.0f;
		angles[1] = 30.0f;
		angles[2] = 0.0f;

		transXYZ = new float[3];
		transXYZ[0] = 0.0f;
		transXYZ[1] = 0.0f;
		transXYZ[2] = 0.0f;

		myShape = new cg1Shape();

		vbuffer  = new int [objs];
		ebuffer  = new int [objs];
		numVerts = new int [objs];
		
		viewMode = 1;
		
		G.addGLEventListener (this);
        G.addKeyListener (this);
	}
    
	/**
	 * Called by the drawable to initiate OpenGL rendering by the client. 
	 */
	public void display(GLAutoDrawable drawable)
	{
		// get GL
		GL2 gl2 = (drawable.getGL()).getGL2();
        
        // clear your frame buffers
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );

        for( int i =0; i< objs; i++){
			
            // bind your vertex buffer
            gl2.glBindBuffer ( GL.GL_ARRAY_BUFFER, vbuffer[i]);
                
            // bind your element array buffer
            gl2.glBindBuffer ( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[i]);
			
            // set up your attribute variables
            gl2.glUseProgram (shaderProgID);
            long dataSize = myShape.getNVerts() * 4l * 4l;
            int  vPosition = gl2.glGetAttribLocation (shaderProgID, "vPosition");
            gl2.glEnableVertexAttribArray ( vPosition );
            gl2.glVertexAttribPointer (vPosition, 4, GL.GL_FLOAT, false,
                                           0, 0l);
            int  vNormal = gl2.glGetAttribLocation (shaderProgID, "vNormal");
            gl2.glEnableVertexAttribArray ( vNormal );
            gl2.glVertexAttribPointer (vNormal, 3, GL.GL_FLOAT, false,
                                       0, dataSize);
			
	         
	        // setup lighting and shading
	       myPhong.view(shaderProgID, gl2, i, angles, transXYZ, viewMode);

	        	
	        // draw your shapes
	        gl2.glDrawElements ( GL.GL_TRIANGLES, numVerts[i],  GL.GL_UNSIGNED_SHORT, 0l);
	        
			}
	        
			tAngle = gl2.glGetUniformLocation(shaderProgID, "theta");
			gl2.glUniform3fv (tAngle, 1, angles, 0);
		
			gl2.glGetUniformLocation(shaderProgID, "trans");
			gl2.glUniform3fv (tAngle, 1, angles, 0);

			// draw your shapes
	        gl2.glDrawElements ( GL.GL_TRIANGLES, numVerts[0],  GL.GL_UNSIGNED_SHORT, 0l);             
	   }


	/**
	 * Notifies the listener to perform the release of all OpenGL 
	 * resources per GLContext, such as memory buffers and GLSL 
	 * programs.
	 */
	public void dispose(GLAutoDrawable drawable)
	{
            
	}
        
	/**
	 * Called by the drawable immediately after the OpenGL context is
	 * initialized. 
	 */
	public void init(GLAutoDrawable drawable)
	{
		// get the gl object
		GL2 gl2 = drawable.getGL().getGL2();
			
		// Load shaders
        shaderProgram myShaders = new shaderProgram();
		shaderProgID = myShaders.readAndCompile (gl2, "vshader.glsl", "fshader.glsl");
		if (shaderProgID == 0) {
			System.err.println ("Error setting up shaders");
			System.exit (1);
		}
			
		// Other GL initialization
		gl2.glEnable (GL.GL_DEPTH_TEST);
		gl2.glEnable (GL.GL_CULL_FACE);
        gl2.glCullFace ( GL.GL_BACK );
        gl2.glFrontFace(GL.GL_CCW);
		gl2.glClearColor (1.0f, 0.0f, 0.0f, 0.0f);
	
        gl2.glDepthFunc (GL.GL_LEQUAL);
        gl2.glClearDepth (1.0f);
		
		// initially create a new Shape
		createShapes(gl2);
			
	}
        
	/**
	 * Called by the drawable during the first repaint after the component
	 * has been resized. 
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                     int height)
	{
            
	}



	/**
	 * creates a new shape
	 */
	public void createShapes(GL2 gl2)
	{
    	// clear the old shape
		myShape = new cg1Shape();

		int i = 0;
		for(i = 0; i < objs; i++) {

			// clear the old shape
	        myShape.clear();
	        
	        switch(i){
	        case 0: 
	        	// creating the head of the robot.
		        myShape.makeHead();
	        	break;
	        case 1:
	        	//creating the neck of the robot.
	        	myShape.makeCylinder(0.2f, 10, 10);
	        	break;
	        case 2:
	        	//creating the body of the robot.
	        	myShape.makeCone(0.8f, 10, 10);	
	        	break;
	        case 3:
	        case 4:
	        	//creating the right arm of the robot.
	        	//creating the left arm of the robot.
	        	myShape.makeCylinder(0.2f, 10, 10);
	        	
	        	break;
	        case 5:
	        case 6:
	        	//creating the left hand of the robot.
	        	//creating the right hand of the robot.
	        	myShape.makeSphere(0.2f, 10, 10);
	
	        	break;
	        case 7:
	        case 8:
	        	//creating the left leg of the robot.
	        	//creating the right leg of the robot.
	        	myShape.makeCylinder(0.2f, 10, 10);

	        	break;
	        case 9:
	        case 10:
	        case 11:
	        case 12:
	        	//creating the left foot of the robot.
	        	//creating the right foot of the robot.
	        	//Decorating environment.
	        	myShape.makeSphere(0.2f, 10, 10);
	        		break;
	        default:
	        	break;
	        }
			// get your vertices and elements
			Buffer points = myShape.getVerticies();
			Buffer elements = myShape.getElements();
			Buffer normals = myShape.getNormals();

			// set up the vertex buffer
			int bf[] = new int[1];
			gl2.glGenBuffers (1, bf, 0);
			vbuffer[i] = bf[0];
			long vertBsize = myShape.getNVerts() * 4l * 4l;
			long ndataSize = myShape.getNVerts() * 3l * 4l;
			gl2.glBindBuffer ( GL.GL_ARRAY_BUFFER, vbuffer[i]);
			gl2.glBufferData ( GL.GL_ARRAY_BUFFER, vertBsize + ndataSize , null, GL.GL_STATIC_DRAW);
			gl2.glBufferSubData ( GL.GL_ARRAY_BUFFER, 0, vertBsize, points);
			gl2.glBufferSubData ( GL.GL_ARRAY_BUFFER, vertBsize, ndataSize, normals);

			gl2.glGenBuffers (1, bf, 0);
			ebuffer[i] = bf[0];
			long eBuffSize = myShape.getNVerts() * 2l;
			gl2.glBindBuffer ( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[i]);
			gl2.glBufferData ( GL.GL_ELEMENT_ARRAY_BUFFER, eBuffSize,elements, 
					GL.GL_STATIC_DRAW);

			numVerts[i] = myShape.getNVerts();
		}
	}

	 /**
     * Because I am a Key Listener...we'll only respond to key presses
     */
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    /** 
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e)
    {
        // Get the key that was pressed
        char key = e.getKeyChar();
        
        // Respond appropriately
        switch( key ) {
        
		case '1': viewMode = 1; break;
        case '2': viewMode = 2; break;
      
        		case 'q': case 'Q':
                System.exit( 0 );
                break;
        }
        
        // do a redraw
        myCanvas.display();
    }
    
        
    /**
     * main program
     */
    public static void main(String [] args)
    {
        // GL setup
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);
		
		new tessMain(canvas);
        
        
        Frame frame = new Frame("CG1 - FINAL EXAMN II");
        frame.setSize(512, 512);
        frame.add(canvas);
        frame.setVisible(true);
        
        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
