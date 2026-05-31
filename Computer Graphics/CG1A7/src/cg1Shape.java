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




public class cg1Shape extends simpleShape
{
	float currXTAngle = 0f, nextXTAngle = 0f, currZTAngle = 0f, nextZTAngle = 0f, tmpAngle = 0f;
	private float x, x1, y, z, z1, xx, xx1, yy, zz, t, tt, tp, tpp;
	private float radius, circleTAngle, circlePAngle;
	private int slices, stacks;
	
    /**
	 * constructor
	 */
	public cg1Shape()
	{
    	radius = 0.5f;
    	slices = 50;
    	stacks = 50;
    	
    	circleTAngle = 360f / slices;
    	circlePAngle = 180f / stacks;
	}
    
     
    /**
     * makeDefaultShape - creates a "unit" shape of your choice using your tesselation routines.
     * 
     *
     */
    public void makeDefaultShape ()
    {
    	
        // tessellate your favorite unit shape here.
    	makeSphere(radius, slices, stacks);
    }
    
    
	/**
	 * makeSphere - Create sphere of a given radius, centered at the origin, 
	 * using spherical coordinates with separate number of thetha and 
	 * tmpPAngle subdivisions.
	 *
	 * @param radius - Radius of the sphere
	 * @param slides - number of subdivisions in the tempAngle direction
	 * @param stacks - Number of subdivisions in the tmpPAngle direction.
	 *
	 * Can only use calls to addtriangleangle
	 */
	public void makeSphere (float radius, int slices, int stacks)
	{	
		
		
		float tmpPAngle;
		float temp, tmp;
    	
    	for(float tempAngle=0f; tempAngle < 360; tempAngle += circleTAngle){
    		
			settingXZ(tempAngle);
			
			if( tempAngle + circleTAngle == 360){
				nextXTAngle = radius;
				nextZTAngle = 0f;
			}
    		
    		int i =0;
    		while( i != stacks){
    			
    			tmpPAngle = i++*circlePAngle;
    			
    			// setting x coordinates for a triangle
    			temp = (float)Math.sin((Math.toRadians(tmpPAngle)));
    			x = currXTAngle * temp;
    			xx = nextXTAngle * temp;
    			
    			//setting x top coordinates for a triangle
    			tmp = (float)Math.sin((Math.toRadians(tmpPAngle + circlePAngle)));
    			x1 = currXTAngle * tmp;
    			xx1 = nextXTAngle * tmp;
    			
    			// setting y coordinates for a triangle
    			temp = (float)Math.cos((Math.toRadians(tmpPAngle)));
    			y = radius*temp;
    			
    			tmp = (float)Math.cos((Math.toRadians(tmpPAngle + circlePAngle)));
    			yy = radius*tmp;
    			
    			
    			// setting z coordinates for a triangle
    			z = currZTAngle * temp;;
    			zz = nextZTAngle * temp;
    			
    			// setting top coordinates for a triangle
    			z1 = currZTAngle * tmp;
    			
    			// setting theta and phi angles coordinates for a triangle
    			t  = tempAngle/360f;
    			tp = tmpPAngle/180f;
    			tt = (tempAngle + circleTAngle)/360;
    			tpp = (tmpPAngle + circlePAngle)/180f;
    		
    			// adding a triangle the specified coordinates
    			addTriangle(x, y, z, t, tp, xx, y, zz, tt, tpp, x1, yy, z1, t, tpp);
    			// adding a triangle to the top coordinates
				addTriangle(xx1, yy, zz, tt, tpp, x1, yy, z1, t, tpp, xx, y, zz, tt, tp);
    		}
    	}
		
	}


	private void settingXZ(float tempAngle) {
		
		tmpAngle = (float)Math.cos(Math.toRadians(tempAngle));
		currXTAngle = radius*tmpAngle;
		
		tmpAngle = (float)Math.cos(Math.toRadians(tempAngle + circleTAngle));
		nextXTAngle = radius*tmpAngle;
		
		tmpAngle = (float)Math.sin(Math.toRadians(tempAngle));
		currZTAngle = radius*tmpAngle;
		
		tmpAngle = (float)Math.sin(Math.toRadians(tempAngle + circleTAngle));
		nextZTAngle = radius*tmpAngle;
	}
	

    
    
}
