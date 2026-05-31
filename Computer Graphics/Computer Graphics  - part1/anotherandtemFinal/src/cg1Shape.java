/**
 * cg1Shape.java
 *
 * Class that includes routines for tessellating a number of basic shapes
 *
 * Students are to supply their implementations for the
 * functions in this file using the function "addtriangleangle()" to do the 
 * tessellation.
 *
 */

import java.util.Vector;


public class cg1Shape extends simpleShape
{
	Shapes<Integer, float[][]> cubeShape = new Shapes<Integer, float[][]>();
	int counter = 0;
	float firstVertix[];
	float secondVertix[];
	float currentVertix[][];
	float x, y, z;
	float [] x1, y1, z1, pts;
	
	float xx1, yy1, zz1;
	float xx, yy, zz;
	float x11, y11, z11;
	float xx2, yy2, zz2;
	
	float tX = 0, tY = 0, tZ = 0;
	float tX1 = 0, tY1 = 0, tZ1 = 0;
	float tX2 = 0, tY2 = 0, tZ2 = 0;
	float tX3 = 0, tY3 = 0, tZ3 = 0;
	float tX4 = 0, tY4 = 0, tZ4 = 0;
	float x32 = 0, y32 = 0, z32 = 0; 
	Shapes<?, ?> vectorTriangle;
	
	float currXTAngle = 0f, nextXTAngle = 0f, currZTAngle = 0f, nextZTAngle = 0f, tmpAngle = 0f;
	private float  sx1, sz1,t, tt, tp, tpp;
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
    	
		cubeShape = new Shapes<Integer, float[][]>();
		cubeShape.put(1, Shapes.front);cubeShape.put(2, Shapes.back);cubeShape.put(3, Shapes.left);
		cubeShape.put(4,Shapes.right) ;cubeShape.put(5, Shapes.top) ;cubeShape.put(6, Shapes.bottom);
	}

	
	
	public void makeHead(){
		
	   	makeSphere(0.75f,30,30);
//    	makeSphere(0.2f,2,2);
//    	makeSphere(0.4f,2,2);
//    	makeCone(0.5f, 10, 10);	
    	makeCylinder(0.2f, 10, 10);
	}
	/**
	 * makeCube - Create a unit cube, centered at the origin, with a given number
	 * of subdivisions in each direction on each front.
	 *
	 * @param subdivision - number of equal subdivisons to be made in each 
	 *        direction along each front
	 *
	 * Can only use calls to addtriangleangle()
	 * 
	 */
	public void makeCube (int subdivisions)
	{
		for (int i=1;i<=cubeShape.size();i++) {
			Object currentSide = cubeShape.get(i);
			cubeShape.newVector = new Vector<float[]>();
			cubeShape.newVertrix = new Vector<float[]>();
			currentVertix = (float[][])currentSide;
			
			for(int i1=0;i1<currentVertix.length;i1++) {
				firstVertix  = currentVertix[i1%4]; 
				secondVertix = currentVertix[(i1+1)%4];
				double step   = 1.0;
				cubeShape.newVector.add(firstVertix);
				
				for(int j=1;j<subdivisions;j++) {
					
					step = (1.0/subdivisions)*j;
					setttingCoordinates(firstVertix, step, secondVertix);
					float points[]={x,y,z}; cubeShape.newVector.add(points);
				}
			}
			float [] tempV = (float[]) cubeShape.newVector.get(0);
			cubeShape.newVertrix.add(tempV);
			
			int rowOne=cubeShape.newVector.size();
			rowOne--;
			for(int j=0;j<subdivisions;j++) {
				tempV = (float[]) cubeShape.newVector.get(rowOne--);
				cubeShape.newVertrix.add(tempV);
			}
			
			int count1=1;
			int count2=cubeShape.newVector.size()-subdivisions-1;
			double temp=1.0;
			
			for(int j=1;j<subdivisions;j++) {
				tempV = (float[])cubeShape.newVector.get(count1++);
				firstVertix = tempV;
				tempV = (float[])cubeShape.newVector.get(count2--);
				secondVertix= tempV;
				cubeShape.newVertrix.add(firstVertix);
				
				for(int i1=1; i1<subdivisions;i1++) {
					temp = (1.0/subdivisions)*i1;
					setttingCoordinates(firstVertix, temp, secondVertix);
					cubeShape.newVertrix.add(pts);
				}
				cubeShape.newVertrix.add(secondVertix);
			}
			
			int counter = subdivisions;
			for(int n =-1;n<subdivisions;n++) {
				tempV =(float[]) cubeShape.newVector.get(counter++);
				cubeShape.newVertrix.add(tempV);
			}
			cubeShape.newVector.clear();
			
			int xCount=0;
			int xCount2= xCount + 1 + subdivisions;
			for (int n=0;n<subdivisions;n++) {
				int count11= xCount, count21= xCount2;
				for (int j=0; j<subdivisions; j++) {
					
					settingPoints(cubeShape,count11, cubeShape,count21);
					addTriangle(x1[0],x1[1],x1[2],y1[0],y1[1],y1[2],pts[0],pts[1],pts[2]);
					count11++;
					addTriangle(y1[0],y1[1],y1[2],z1[0],z1[1],z1[2],pts[0],pts[1],pts[2]);
					count21++;
				}
				xCount += subdivisions+1; xCount2=xCount+subdivisions+1;
			}
		}
	}
	
	
	
	
	private void settingPoints(Shapes<Integer, float[][]> cubeShape, int count1, Shapes<Integer, float[][]> cubeShape1, int count2) {
		x1= (float[])cubeShape.newVertrix.get(count1)  ;
		y1= (float[])cubeShape1.newVertrix.get(count2)  ;
		z1= (float[])cubeShape.newVertrix.get(count2+1); 
		pts= (float[])cubeShape1.newVertrix.get(count1+1);
		
	}

	private void setttingCoordinates(float[] firstVertix, double step, float[] secondVertix) {
		pts = new float[3];
		pts[0]=x=(float)((firstVertix[0]*(1.0-step))+(step*secondVertix[0]));
		pts[1]=y=(float)((firstVertix[1]*(1.0-step))+(step*secondVertix[1]));
		pts[2]=z=(float)((firstVertix[2]*(1.0-step))+(step*secondVertix[2]));
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
	//check
	public void makeCylinder (float radius, int radialDivisions, int heightDivisions)
	{	
		float y0 = 0.5f;
		float x1 = 0;
		float z1 = 0;
		float x = 0;
		float z = 0;
		
		float angle = 360 / radialDivisions;
		for (int i = 1; i <= radialDivisions; i++) {
			x = radius * (float) (Math.cos((angle * (Math.PI / 180))));
			z = radius * (float) (Math.sin((angle * (Math.PI / 180))));
			z = -z;
			if (i == 1) {
				if (heightDivisions == 1) {
					addTriangle(radius, 0.5f, 0.0f, x, 0.5f, z, 0.0f, 0.5f, 0.0f);
					addTriangle(radius, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, x, -0.5f, z);
					addTriangle(radius, 0.5f, 0.0f, x, -0.5f, z, x, 0.5f, z);
					addTriangle(radius, 0.5f, 0.0f, radius, -0.5f, 0.0f, x, -0.5f, z);
				} 
				else {
					y0 = 0.5f;
					addTriangle(radius, 0.5f, 0.0f, x, 0.5f, z, 0.0f, 0.5f, 0.0f);
					addTriangle(radius, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, x, -0.5f, z);
					for (int j = 1; j <= heightDivisions; j++) {
						float y1 = y0 - 1.0f / heightDivisions;
						addTriangle(radius, y0, 0.0f, x, y1, z, x, y0, z);
						addTriangle(radius, y0, 0.0f, radius, y1, 0.0f, x, y1, z);
						y0 = y1;
					}
				}
			}
			else if (i == radialDivisions) {
				if (heightDivisions == 1) {
					addTriangle(x1, 0.5f, z1, radius, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f);
					addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, radius, -0.5f, 0.0f);
					addTriangle(x1, 0.5f, z1, radius, -0.5f, 0.0f, radius, 0.5f, 0.0f);
					addTriangle(x1, 0.5f, z1, x1, -0.5f, z1, radius, -0.5f, 0.0f);
				} 
				else {
					y0 = 0.5f;
					addTriangle(x1, 0.5f, z1, radius, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f);
					addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, radius, -0.5f, 0.0f);
					for (int j = 1; j <= heightDivisions; j++) {
						float y1 = y0 - 1.0f / heightDivisions;
						addTriangle(x1, y0, z1, radius, y1, 0.0f, radius, y0,0.0f);
						addTriangle(x1, y0, z1, x1, y1, z1, radius, y1,0.0f);
						y0 = y1;
					}
				}
			}
			else if (i < radialDivisions) {				
				if (heightDivisions == 1) {
					addTriangle(x1, 0.5f, z1, x, 0.5f, z, 0.0f, 0.5f, 0.0f);
					addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, x, -0.5f, z);
					addTriangle(x1, 0.5f, z1, x, -0.5f, z, x, 0.5f, z);
					addTriangle(x1, 0.5f, z1, x1, -0.5f, z1, x, -0.5f, z);
				} 
				else {
					y0 = 0.5f;
					addTriangle(x1, 0.5f, z1, x, 0.5f, z, 0.0f, 0.5f, 0.0f);
					addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, x, -0.5f, z);
					for (int j = 1; j <= heightDivisions; j++) {
						float y1 = y0 - 1.0f / heightDivisions;
						addTriangle(x1, y0, z1, x, y1, z, x, y0, z);
						addTriangle(x1, y0, z1, x1, y1, z1, x, y1, z);
						y0 = y1;
					}
				}
			}			
			x1 = x;	z1 = z;
			angle = (float)( angle + (360.0f / radialDivisions));
		}
	}
	
    public void makeSphere (float radius, int slices, int stacks)
    {
    	if(slices > 5) 
			slices = 4;

		float a=(float)((2.0/(1 + Math.sqrt(5.0)))*0.5f);
		Vector<float[][]> triangleShapes=new Vector<float[][]>();
		float v0[]={0.0f,a,-0.5f};
		x = v0[0];
		y = v0[1];
		z = v0[2];
		
		float vertix1[]={-a,0.5f,0.0f};
		float x1 = vertix1[0];
		float y1 = vertix1[1];
		float z1 = vertix1[2];
		
		float vertix2[]={a,0.5f,0.0f};
		float x2 = vertix2[0];
		float y2 = vertix2[1];
		float z2 = vertix2[2];
		float tri0[][]={{x,y,z},{x1,y1,z1},{x2, y2, z2}};
		triangleShapes.add(tri0);
		
		float vertix3[]={0.0f,a,0.5f};
		float x3 = vertix3[0];
		float y3 = vertix3[1];
		float z3 = vertix3[2];
		float tri1[][]={{x3,y3,z3},{x2,y2,z2},{x1, y1, z1}};
		triangleShapes.add(tri1);
		
		float vertix4[]={-0.5f,0.0f,a};
		float x4 = vertix4[0];
		float y4 = vertix4[1];
		float z4 = vertix4[2];
		
		
		float vertix5[]={0.0f,-a,0.5f};
		float x5 = vertix5[0];
		float y5 = vertix5[1];
		float z5 = vertix5[2];
		float tri2[][]={{x3,y3,z3},{x4,y4,z4},{x5, y5, z5}};
		triangleShapes.add(tri2);
		
		float vertix6[] = null;
		if(counter == 0){
			float vertix66[]={0.0f,0.0f,0};
			vertix6 = vertix66;
		}else{
			float vertix66[]={0.5f,0.0f,a};;
			vertix6 = vertix66;
		}
		
			
		float x6 = vertix6[0];
		float y6 = vertix6[1];
		float z6 = vertix6[2];
		float tri3[][]={{x3,y3,z3},{x5,y5,z5},{x6,y6,z6}};
		triangleShapes.add(tri3);
		
		float vertix7[]={0.5f,0.0f,-a};
		float x7 = vertix7[0];
		float y7 = vertix7[1];
		float z7 = vertix7[2];
	
		
		float vertix8[]={0.0f,-a,-0.5f};
		float x8 = vertix8[0];
		float y8 = vertix8[1];
		float z8 = vertix8[2];
		float tri4[][]={{x,y,z},{x7,y7,z7},{x8,y8,z8}};
		triangleShapes.add(tri4);
		
		float vertix9[]={-0.5f,0.0f,-a};
		float x9 = vertix9[0];
		float y9 = vertix9[1];
		float z9 = vertix9[2];
		float tri5[][]={{x,y,z},{x8,y8,z8},{x9,y9,z9}};
		triangleShapes.add(tri5);
		
		float vertix10[]={-a,-0.5f,0.0f};
		float x10 = vertix10[0];
		float y10 = vertix10[1];
		float z10 = vertix10[2];
		
		float vertix11[]={a,-0.5f,0.0f};
		float x11 = vertix11[0];
		float y11 = vertix11[1];
		float z11 = vertix11[2];
		float tri6[][]={{x5,y5,z5},{x10,y10,z10},{x11,y11,z11}};
		triangleShapes.add(tri6);
		
		float tri7[][]={{x8,y8,z8},{x11,y11,z11},{x10,y10,z10}};
		triangleShapes.add(tri7);
		float tri8[][]={{x1, y1, z1},{x9, y9, z9},{x4, y4, z4}};
		triangleShapes.add(tri8);
		float tri9[][]={{x10,y10,z10},{x4, y4, z4},{x9, y9, z9}};
		triangleShapes.add(tri9);
		float tri10[][]={{x2,y2,z2},{x6,y6,z6},{x7,y7,z7}};
		triangleShapes.add(tri10);
		float tri11[][]={{x11,y11,z11},{x7,y7,z7},{x6,y6,z6}};
		triangleShapes.add(tri11);
		float tri12[][]={{x3,y3,z3},{x1, y1, z1},{x4, y4, z4}};
		triangleShapes.add(tri12);
		float tri13[][]={{x3,y3,z3},{x6,y6,z6},{x2,y2,z2}};
		triangleShapes.add(tri13);
		float tri14[][]={{x,y,z},{x9, y9, z9},{x1, y1, z1}};
		triangleShapes.add(tri14);
		float tri15[][]={{x,y,z},{x2,y2,z2},{x7,y7,z7}};
		triangleShapes.add(tri15);
		float tri16[][]={{x8,y8,z8},{x10,y10,z10},{x9, y9, z9}};
		triangleShapes.add(tri16);
		float tri17[][]={{x8,y8,z8},{x7,y7,z7},{x11,y11,z11}};
		triangleShapes.add(tri17);
		float tri18[][]={{x5,y5,z5},{x4, y4, z4},{x10,y10,z10}};
		triangleShapes.add(tri18);
		float tri19[][]={{x5,y5,z5},{x11,y11,z11},{x6,y6,z6}};
		triangleShapes.add(tri19); 

		for(int i=0;i!=slices;i++) {
			
			Shapes.vector=new Vector<float[][]>();
			for(int k=0;k<triangleShapes.size();k++) {
				float[][]triangle=(float[][])triangleShapes.get(k);
				float tp = 0.5f;
				
				Vector<float[]> middlePts = new Vector<float[]>();
				for(int i1=0;i1!=triangle.length;i1++) {
					float pts1[]=triangle[i1%3];
					float pts2[]=triangle[(i1+1)%3];
					setttingCoordinates(pts1, tp, pts2);
					middlePts.add(pts);
				}
				float middlePt1[]=(float[])middlePts.get(0);
				float middlePt2[]=(float[])middlePts.get(1);
				float middlePt3[]=(float[])middlePts.get(2);
				
				
				toNormalize(middlePt1); 
				x1 = middlePt1[0];
				y1 = middlePt1[1];
				z1 = middlePt1[2];
				
				toNormalize(middlePt2);
				x2 = middlePt2[0];
				y2 = middlePt2[1];
				z2 = middlePt2[2];
				
				toNormalize(middlePt3);
				x3 = middlePt3[0];
				y3 = middlePt3[1];
				z3 = middlePt3[2];
				
				toNormalize(triangle[0]);
				float xx1 = triangle[0][0];
				float yy1 = triangle[0][1];
				float zz1 = triangle[0][2];
				
				toNormalize(triangle[1]); 
				float xx2 = triangle[1][0];
				float yy2 = triangle[1][1];
				float zz2 = triangle[1][2];
				
				toNormalize(triangle[2]);
				float xx3 = triangle[2][0];
				float yy3 = triangle[2][1];
				float zz3 = triangle[2][2];
				
				float tri111[][]={{xx1,yy1,zz1},{x1,y1,z1}   ,{x3,y3,z3}};		
				float tri21[][] ={{x1,y1,z1}   ,{xx2,yy2,zz2},{x2,y2,z2}};
				float tri31[][] ={{x3,y3,z3}   ,{x2,y2,z2}   ,{xx3,yy3,zz3}};
				float tri41[][] ={{x1,y1,z1}   ,{x2,y2,z2}   ,{x3,y3,z3}}; 
				
				Shapes.vector.add(tri111);
				Shapes.vector.add(tri21); 
				Shapes.vector.add(tri31); 
				Shapes.vector.add(tri41);
			}
			triangleShapes = Shapes.vector;
		}

		for(int i=0;i<triangleShapes.size();i++) {
			float[][] resultTriangle=(float[][])triangleShapes.get(i);
			float p1[]=resultTriangle[0];
			float p2[]=resultTriangle[1];
			float p3[]=resultTriangle[2];
			addTriangle(p1[0],p1[1],p1[2],p2[0],p2[1],p2[2],p3[0],p3[1],p3[2]);
		}
		counter++;
	}

    
	public void toNormalize(float[] pt) {
		double tempPt=Math.sqrt(pt[0]*pt[0]+pt[1]*pt[1]+pt[2]*pt[2]);
		int i =0;
		while( i != 3){
			pt[i]=(float)(pt[i]/(tempPt*2));
		i++;
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
		{
			float tempx = 0;
			float tempz = 0;
			Shapes.tAngle = 360.0f/ radialDivisions;
			Shapes.tAngle = 360.0f/ radialDivisions;
			
			for (int i = 1; i <= radialDivisions; i++) {
				x = radius * (float) (Math.cos((Shapes.tAngle * (Math.PI / 180))));
				z = radius * (float) (Math.sin((Shapes.tAngle * (Math.PI / 180))));
				z = -z;
				
				float falseHeightDivs = 1 / (float) heightDivisions;
				float height = 1;
				float tmp = 0.5f;
				
				if (i == 1){
					addTriangle(radius, -tmp, 0.0f, 0.0f, -tmp, 0.0f, x, -tmp, z);
					tX = radius; 
					tY = -tmp;
					tZ = 0.0f;
					tX1 = x;
					tY1 = -tmp; 
					tZ1 = z;
				}

				else if (i == radialDivisions){

					addTriangle(tempx, -tmp, tempz, 0.0f, -tmp, 0.0f, radius, -tmp, 0.0f);
					tX2 = x; 
					tY2 = -tmp;
					tZ2 = z;
					tX3 = tempx; 
					tY3 = -tmp; 
					tZ3 = tempz;
				}

				else {
					addTriangle(tempx, -tmp, tempz, 0.0f, -tmp, 0.0f, x, -tmp, z);
					tX4 = x; 
					tY4 = -tmp; 
					tZ4 = z;
					x32 = tempx; 
					y32 = -tmp; 
					z32 = tempz;
				}

				
				for (int j = 1; j <= heightDivisions; j++) {
					if(i == 1) {
						xx1 = tX1 * height;
						yy1 = tY1;
						zz1 = tZ1 * height;
						xx = tX * (height - falseHeightDivs);
						yy = tY + falseHeightDivs;
						zz = tZ * (height - falseHeightDivs);
						x11 = tX * height;
						y11 = tY;
						z11 = tZ * height;
						addTriangle(xx1, yy1, zz1, xx, yy, zz, x11, y11, z11);
						
						xx2 = tX1 * (height - falseHeightDivs);
						yy2 = tY1 + falseHeightDivs;
						zz2 = tZ1 * (height - falseHeightDivs);
						addTriangle(xx, yy, zz, xx1, yy1, zz1,xx2, yy2, zz2);

						height -= falseHeightDivs; 
						tY1 += falseHeightDivs;
						tY += falseHeightDivs;
					}

					else if(i == radialDivisions) {
						xx1 = tX3 * height;
						yy1 = tY3;
						zz1 = tZ3 * height;
						xx = tX2 * height;
						yy = tY2;
						zz = tZ2 * height;
						x11 = tX2* (height - falseHeightDivs);
						y11 = tY2 + falseHeightDivs;
						z11 = tZ2 * (height - falseHeightDivs);
						addTriangle(xx1, yy1, zz1, xx, yy, zz, x11, y11, z11);
						
						xx2 = tX3 * (height - falseHeightDivs);
						yy2 = tY3 + falseHeightDivs;
						zz2 = tZ3 * (height - falseHeightDivs);
						addTriangle(x11, y11, z11, xx2, yy2, zz2, xx1, yy1, zz1);
						
						height -= falseHeightDivs; 
						tY3 += falseHeightDivs;
						tY2 += falseHeightDivs;
					}

					else {
						xx1 = x32 * height;
						yy1 = y32;
						zz1 = z32 * height;
						xx = tX4 * height;
						yy = tY4;
						zz = tZ4 * height;
						x11 = tX4* (height - falseHeightDivs);
						y11 = tY4 + falseHeightDivs;
						z11 = tZ4 * (height - falseHeightDivs);
						addTriangle(xx1, yy1, zz1, xx, yy, zz, x11, y11, z11);
						
						xx2 = x32 * (height - falseHeightDivs);
						yy2 = y32 + falseHeightDivs;
						zz2 = z32 * (height - falseHeightDivs);
						addTriangle(x11, y11, z11, xx2, yy2, zz2, xx1, yy1, zz1);
						
						height -= falseHeightDivs; 
						y32 += falseHeightDivs;
						tY4 += falseHeightDivs;
					}
				}
				tempx = x;	tempz = z;
				Shapes.tAngle += (360f / radialDivisions);
			}
		}
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
	public void makeSphere1 (float radius, int slices, int stacks)
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
    			sx1  = currXTAngle * tmp;
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
    			sz1 = currZTAngle * tmp;
    			
    			// setting theta and phi angles coordinates for a triangle
    			t  = tempAngle/360f;
    			tp = tmpPAngle/180f;
    			tt = (tempAngle + circleTAngle)/360;
    			tpp = (tmpPAngle + circlePAngle)/180f;
    		
    			// adding a triangle the specified coordinates
    			addTriangle(x, y, z, t, tp, xx, y, zz, tt, tpp, sx1, yy, sz1, t, tpp);
    			// adding a triangle to the top coordinates
				addTriangle(xx1, yy, zz, tt, tpp, sx1, yy, sz1, t, tpp, xx, y, zz, tt, tp);
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
