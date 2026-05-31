
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
import javax.media.opengl.fixedfunc.*; 


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
    coordinates myPhong;
    
    /**
     * my canvas
     */
    GLCanvas myCanvas;
    
	private int tAngle;
	private float angles[], incr = 5.0f;

	private int trans;
	private float transXYZ[], transZ = 0.5f;

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
        myPhong = new coordinates();
        

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
    
    @SuppressWarnings("unused")
	private void errorCheck (GL2 gl2)
    {
        int code = gl2.glGetError();
        if (code == GL.GL_NO_ERROR) 
            System.err.println ("All is well");
        else
            System.err.println ("Problem - error code : " + code);
            
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
	        long dataSize = numVerts[i] * 4l * 4l;
	        int  vPosition = gl2.glGetAttribLocation (shaderProgID, "vPosition");
	        gl2.glEnableVertexAttribArray ( vPosition );
	        gl2.glVertexAttribPointer (vPosition, 4, GL.GL_FLOAT, false,
	                                       0, 0l);
	        int  vNormal = gl2.glGetAttribLocation (shaderProgID, "vNormal");
	        gl2.glEnableVertexAttribArray ( vNormal );
	        gl2.glVertexAttribPointer (vNormal, 3, GL.GL_FLOAT, false,
	                                   0, dataSize);
			
	         
	        // setup lighting and shading
//	        myPhong.setUpPhong (shaderProgID, gl2);
	       
	        if( viewMode == 1)
	        	myPhong.setUpMain1(shaderProgID, gl2, i, angles, transXYZ);
	        else if( viewMode == 2)
	        	myPhong.setUpMain2(shaderProgID, gl2, i, angles, transXYZ);
	        	
	        // draw your shapes
	        gl2.glDrawElements ( GL.GL_TRIANGLES, numVerts[i],  GL.GL_UNSIGNED_SHORT, 0l);
	        
			}
	        
			tAngle = gl2.glGetUniformLocation(shaderProgID, "theta");
			gl2.glUniform3fv (tAngle, 1, angles, 0);
		
			trans = gl2.glGetUniformLocation(shaderProgID, "trans");
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
		gl2.glClearColor (1.0f, 1.0f, 1.0f, 1.0f);
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

	
		for(int i = 0; i < objs; i++) {

			// make a shape 
			if(i == 0) {
				// clear the old shape
				myShape.clear();
				myShape.makeHead();
			}
			else if(i == 1) {
				// clear the old shape
				myShape.clear();
				myShape.makeCylinder(0.2f, 10, 10);
			}
			else if(i == 2) {
				// clear the old shape
				myShape.clear();
				myShape.makeCone(0.8f, 10, 10);	

			}
			else if(i == 3) {
				// clear the old shape
				myShape.clear();
				myShape.makeCylinder(0.2f, 10, 10);
			}
			else if(i == 4) {
				// clear the old shape
				myShape.clear();
				myShape.makeCylinder(0.2f, 10, 10);
			}
			else if(i == 5) {
				// clear the old shape
				myShape.clear();
				myShape.makeSphere(0.2f, 10, 10);
			}
			else if(i == 6) {
				// clear the old shape
				myShape.clear();
				myShape.makeSphere(0.2f, 10, 10);
			}
			else if(i == 7) {
				// clear the old shape
				myShape.clear();
				myShape.makeCylinder(0.2f, 10, 10);
			}
			else if( i == 8){
				// clear the old shape
				myShape.clear();
				myShape.makeCylinder(0.2f, 10, 10);
			}else if(i == 9) {
				// clear the old shape
				myShape.clear();
				myShape.makeSphere(0.2f, 10, 10);
			}
			else if(i == 10) {
				// clear the old shape
				myShape.clear();
				myShape.makeSphere(0.2f, 10, 10);
				
			}else if(i == 11) {
				// clear the old shape
				myShape.clear();
				myShape.makeSphere(0.2f, 10, 10);
			}
			else if(i == 12) {
				// clear the old shape
				myShape.clear();
				myShape.makeSphere(0.2f, 10, 10);
			}else{
				myShape.clear();
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
        case 'x': angles[0] -= incr; break;
        case 'y': angles[1] -= incr; break;
        case 'z': angles[2] -= incr; break;  
        case 'X': angles[0] += incr; break;
        case 'Y': angles[1] += incr; break;
        case 'Z': angles[2] += incr; break; 
        case 'f': transXYZ[2] += transZ; break;
        case 'r': transXYZ[2] -= transZ; break;
        case 'g': transXYZ[0] += transZ; break;
        case 'd': transXYZ[0] -= transZ; break;
        case 'w': transXYZ[1] += transZ; break;
        case 's': transXYZ[1] -= transZ; break;
        
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
	@SuppressWarnings("unused")
	public static void main(String [] args)
	{
		// GL setup
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);

		// create your tessMain
		tessMain myMain = new tessMain(canvas);


		Frame frame = new Frame("CG Final 3D Scene");
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
