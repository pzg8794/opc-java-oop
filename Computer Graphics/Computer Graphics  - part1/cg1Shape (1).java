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

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class cg1Shape extends simpleShape
{
	/**
	 * constructor
	 */
	public cg1Shape()
	{
	}

	/**
	 * makeCube - Create a unit cube, centered at the origin, with a given number
	 * of subdivisions in each direction on each face.
	 *
	 * @param subdivision - number of equal subdivisons to be made in each 
	 *        direction along each face
	 *
	 * Can only use calls to addTriangle()
	 * 
	 */
	public void makeCube (int subdivisions)
	{
		Map<Integer, float[][]> mycube = new HashMap<Integer, float[][]>();
		float frontside[][]={{-0.5f,0.5f,0.5f},{-0.5f,-0.5f,0.5f},{0.5f,-0.5f,0.5f},{0.5f,0.5f,0.5f}},
				backside[][]={{0.5f,0.5f,-0.5f},{0.5f,-0.5f,-0.5f},{-0.5f,-0.5f,-0.5f},{-0.5f,0.5f,-0.5f}},
				leftside[][]={{-0.5f, 0.5f,-0.5f},{-0.5f,-0.5f,-0.5f},{-0.5f,-0.5f,0.5f},{-0.5f,0.5f,0.5f}},
				rightside[][]={{0.5f,0.5f,0.5f},{0.5f,-0.5f,0.5f},{0.5f,-0.5f,-0.5f},{0.5f,0.5f,-0.5f}},
				topside[][]={{-0.5f,0.5f,-0.5f},{-0.5f,0.5f,0.5f},{0.5f,0.5f,0.5f},{0.5f,0.5f,-0.5f}},
				bottomside[][]={{-0.5f,-0.5f,0.5f},{-0.5f,-0.5f,-0.5f},{0.5f,-0.5f,-0.5f},{0.5f,-0.5f,0.5f}};
		mycube.put(1, frontside);mycube.put(2, backside);mycube.put(3, leftside);
		mycube.put(4,rightside);mycube.put(5, topside);mycube.put(6, bottomside);
		for (int i=1;i<=mycube.size();i++) {
			Object acu = mycube.get(i);
			Vector<float[]> vecNew = new Vector<float[]>(), newVert = new Vector<float[]>();
			float totVert[][] = (float[][])acu;
			for(int k=0;k<totVert.length;k++) {
				float v1[] = totVert[k%4]; float v2[]=totVert[(k+1)%4];
				double t = 1.0;
				vecNew.add(v1);
				for(int j=1;j<subdivisions;j++) {
					t = (1.0/subdivisions)*j;
					float x=(float)((v1[0]*(1.0-t))+(t*v2[0])), y=(float)((v1[1]*(1.0-t))+(t*v2[1])), 
							z=(float)((v1[2]*(1.0-t))+(t*v2[2])), pt[]={x,y,z}; vecNew.add(pt);
				}
			}
			newVert.add((float[]) vecNew.elementAt(0));
			int firstRowSecElement=vecNew.size()-1;
			for(int a=0;a<subdivisions;a++) {
				newVert.add((float[]) vecNew.elementAt(firstRowSecElement--));
			}
			int index1=1,index2=vecNew.size()-subdivisions-1;
			double temp=1.0;
			for(int k=1;k<subdivisions;k++) {
				float v1[]=(float[])vecNew.get(index1++), v2[]=(float[])vecNew.get(index2--);
				newVert.add(v1);
				for(int h=1; h<subdivisions;h++) {
					temp = (1.0/subdivisions)*h;
					float x=(float) ((v1[0]*(1.0-temp))+(temp*v2[0])),y=(float)((v1[1]*(1.0-temp))+(temp*v2[1])),
							z=(float)((v1[2]*(1.0-temp))+(temp*v2[2])),point[]={x,y,z};newVert.add(point);
				}
				newVert.add(v2);
			}
			int cnt = subdivisions;
			newVert.add((float[]) vecNew.elementAt(cnt++));
			for(int a=0;a<subdivisions;a++) {
				newVert.add((float[]) vecNew.elementAt(cnt++));
			}
			vecNew=null;
			int inx1=0,inx2=inx1+subdivisions+1;
			for (int p=0;p<subdivisions;p++) {
				int cind=inx1, dind=inx2;
				for (int j=0; j<subdivisions; j++) {
					float pt1[] =(float[])newVert.get(cind),pt2[]=(float[])newVert.get(dind);
					float pt3[] =(float[])newVert.get(dind+1),pt4[]=(float[])newVert.get(cind+1);
					addTriangle(pt1[0],pt1[1],pt1[2],pt2[0],pt2[1],pt2[2],pt4[0],pt4[1],pt4[2]);cind++;
					addTriangle(pt2[0],pt2[1],pt2[2],pt3[0],pt3[1],pt3[2],pt4[0],pt4[1],pt4[2]);dind++;
				}
				inx1 += subdivisions+1; inx2=inx1+subdivisions+1;
			}
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
		if(radialDivisions<3)
			radialDivisions=3;
		double angleRad=Math.toRadians(360.0/radialDivisions), angle=angleRad;
		for(int i=0;i<radialDivisions;i++) {
			float x1=(float)(radius*Math.cos(angle)),z1=(float)(radius*Math.sin(angle));
			angle += angleRad;
			float x2=(float)(radius*Math.cos(angle)),z2=(float)(radius*Math.sin(angle));
			addTriangle(0.0f,0.5f,0.0f,x1,0.5f,z1,x2,0.5f,z2);addTriangle(0.0f,-0.5f,0.0f,x2,-0.5f,z2,x1,-0.5f,z1);
			float face[][]={{x1,0.5f,z1},{x1,-0.5f,z1},{x2,-0.5f,z2},{x2,0.5f,z2}};
			Vector<float[]> vecNew=new Vector<float[]>(), newVert = new Vector<float[]>();
			float totVert[][]=(float[][])face;
			for(int k=0;k<totVert.length;k++) {
				float v1[]=totVert[k%4], v2[]=totVert[(k+1)%4];
				double t=1.0; vecNew.add(v1);
				for(int j=1;j<heightDivisions;j++) {
					t=(1.0/heightDivisions)*j;
					float x=(float)((v1[0]*(1.0-t))+(t*v2[0])),y=(float)((v1[1]*(1.0-t))+(t*v2[1])),
							z=(float)((v1[2]*(1.0-t))+(t*v2[2])),pt[]={x,y,z};vecNew.add(pt);
				}
			}
			newVert.add((float[]) vecNew.elementAt(0));
			int firstRowSecElement=vecNew.size()-1;
			for(int a=0;a<heightDivisions;a++) {
				newVert.add((float[]) vecNew.elementAt(firstRowSecElement--));
			}
			int index1=1,index2=vecNew.size()-heightDivisions-1;
			double temp=1.0;
	
			for(int k=1;k<heightDivisions;k++) {
				float v1[]=(float[])vecNew.get(index1++), v2[]=(float[])vecNew.get(index2--); newVert.add(v1);
				for(int h=1; h<heightDivisions;h++) {
					temp = (1.0/heightDivisions)*h;
					float x=(float)((v1[0]*(1.0-temp))+(temp*v2[0])),y=(float)((v1[1]*(1.0-temp))+(temp*v2[1])),
							z=(float)((v1[2]*(1.0-temp))+(temp*v2[2])),point[]={x,y,z};newVert.add(point);
				}
				newVert.add(v2);
			}
			int cnt = heightDivisions;
			newVert.add((float[]) vecNew.elementAt(cnt++));
			for(int a=0;a<heightDivisions;a++) {
				newVert.add((float[]) vecNew.elementAt(cnt++));
			}
			vecNew = null;
			int inx1=0,inx2=inx1+heightDivisions+1;
	
			for (int p=0;p<heightDivisions;p++) {
				int cind=inx1, dind=inx2;
				for (int j=0; j<heightDivisions; j++) {
					float pt1[] =(float[])newVert.get(cind), pt2[]=(float[])newVert.get(dind);
					float pt3[] =(float[])newVert.get(dind + 1), pt4[]=(float[])newVert.get(cind+1);
					addTriangle(pt1[0],pt1[1],pt1[2],pt2[0],pt2[1],pt2[2],pt4[0],pt4[1],pt4[2]);cind++;
					addTriangle(pt2[0],pt2[1],pt2[2],pt3[0],pt3[1],pt3[2],pt4[0],pt4[1],pt4[2]);dind++;
				}
				inx1 += heightDivisions+1; inx2=inx1+heightDivisions+1;
			}
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
		if(radialDivisions<3)
			radialDivisions=3;
		double angleRad=Math.toRadians(360.00/radialDivisions);
		double angle=angleRad;

		for (int i=0;i<radialDivisions;i++) {
			float x1=(float)(radius*Math.cos(angle)), z1=(float)(radius*Math.sin(angle));
			angle += angleRad;
			float x2=(float)(radius*Math.cos(angle)), z2=(float)(radius*Math.sin(angle));
			addTriangle(0.0f,0.5f,0.0f,x1,-0.5f,z1,x2,-0.5f,z2);
			addTriangle(0.0f,-0.5f,0.0f,x2,-0.5f,z2,x1,-0.5f,z1);
			float[][] face={{0.0f,0.5f,0.0f},{x1,-0.5f,z1},{x2,-0.5f,z2},{0.0f,0.5f,0.0f}};
			Vector<float[]>vecNew=new Vector<float[]>(),newVert=new Vector<float[]>();
			float totVert[][]=(float[][])face;
			for(int k=0;k<totVert.length;k++) {
				float v1[]=totVert[k%4], v2[]=totVert[(k+1)%4]; double t=1.0; vecNew.add(v1);
				for(int j=1;j<heightDivisions;j++) {
					t=(1.0/heightDivisions)*j;
					float x=(float)((v1[0]*(1.0-t))+(t*v2[0])),y=(float)((v1[1]*(1.0-t))+(t*v2[1])),
							z=(float)((v1[2]*(1.0-t))+(t*v2[2])),pt[]={x,y,z};vecNew.add(pt);
				}
			}
			newVert.add((float[]) vecNew.elementAt(0));
			int firstRowSecElement=vecNew.size()-1;
			for(int a=0;a<heightDivisions;a++) {
				newVert.add((float[]) vecNew.elementAt(firstRowSecElement--));
			}
			int index1=1,index2=vecNew.size()-heightDivisions-1;
			double temp=1.0;

			for(int k=1;k<heightDivisions;k++) {
				float v1[]=(float[])vecNew.get(index1++), v2[]=(float[])vecNew.get(index2--); newVert.add(v1);
				for(int h=1; h<heightDivisions;h++) {
					temp = (1.0/heightDivisions)*h;
					float x=(float)((v1[0]*(1.0-temp))+(temp*v2[0])),y=(float)((v1[1]*(1.0-temp))+(temp*v2[1])),
							z=(float)((v1[2]*(1.0-temp))+(temp*v2[2])),point[]={x,y,z};newVert.add(point);
				}
				newVert.add(v2);
			}
			int cnt = heightDivisions;
			newVert.add((float[]) vecNew.elementAt(cnt++));
			for(int a=0;a<heightDivisions;a++) {
				newVert.add((float[]) vecNew.elementAt(cnt++));
			}
			vecNew = null;
			int inx1=0,inx2=inx1+heightDivisions+1;

			for (int p=0;p<heightDivisions;p++) {
				int cind=inx1, dind=inx2;
				for (int j=0; j<heightDivisions; j++) {
					float pt1[] =(float[])newVert.get(cind), pt2[]=(float[])newVert.get(dind);
					float pt3[] =(float[])newVert.get(dind + 1), pt4[]=(float[])newVert.get(cind+1);
					addTriangle(pt1[0],pt1[1],pt1[2],pt2[0],pt2[1],pt2[2],pt4[0],pt4[1],pt4[2]);cind++;
					addTriangle(pt2[0],pt2[1],pt2[2],pt3[0],pt3[1],pt3[2],pt4[0],pt4[1],pt4[2]);dind++;
				}
				inx1 += heightDivisions+1; inx2=inx1+heightDivisions+1;
			}
		}
	}

	public void toNormalize(float[] newmpt) {
		double tmp=Math.sqrt(newmpt[0]*newmpt[0]+newmpt[1]*newmpt[1]+newmpt[2]*newmpt[2]);
		for(int i=0;i<3;i++)
			newmpt[i]=(float)(newmpt[i]/(tmp*2));
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
	public void makeSphere (float radius, int slices, int stacks)
	{	//max limit is set to 4
		if(slices > 4) {
			slices = 4;
		}

		float a=(float)((2.0/(1 + Math.sqrt(5.0)))*0.5f), v0[]={0.0f,a,-0.5f}, v1[]={-a,0.5f,0.0f},v2[]={a,0.5f,0.0f}, v3[]={0.0f,a,0.5f},
				v4[]={-0.5f,0.0f,a}, v5[]={0.0f,-a,0.5f}, v6[]={0.5f,0.0f,a}, v7[]={0.5f,0.0f,-a}, v8[]={0.0f,-a,-0.5f}, v9[]={-0.5f,0.0f,-a},
				v10[]={-a,-0.5f,0.0f}, v11[]={a,-0.5f,0.0f};
		Vector<float[][]> triangles=new Vector<float[][]>();
		float tri0[][]={{v0[0],v0[1],v0[2]},{v1[0],v1[1],v1[2]},{v2[0],v2[1],v2[2]}}, tri1[][]= {{v3[0],v3[1],v3[2]},{v2[0],v2[1],v2[2]},{v1[0],v1[1],v1[2]}},
				tri2[][]={{v3[0],v3[1],v3[2]},{v4[0],v4[1],v4[2]},{v5[0],v5[1],v5[2]}},tri3[][]={{v3[0],v3[1],v3[2]},{v5[0],v5[1],v5[2]},{v6[0],v6[1],v6[2]}},
				tri4[][]={{v0[0],v0[1],v0[2]},{v7[0],v7[1],v7[2]},{v8[0],v8[1],v8[2]}},tri5[][]={{v0[0],v0[1],v0[2]},{v8[0],v8[1],v8[2]},{v9[0],v9[1],v9[2]}},
				tri6[][]={{v5[0],v5[1],v5[2]},{v10[0],v10[1],v10[2]},{v11[0],v11[1],v11[2]}}, tri7[][]={{v8[0],v8[1],v8[2]},{v11[0],v11[1],v11[2]},{v10[0],v10[1],v10[2]}},
				tri8[][]={{v1[0],v1[1],v1[2]},{v9[0],v9[1],v9[2]},{v4[0],v4[1],v4[2]}},tri9[][]={{v10[0],v10[1],v10[2]},{v4[0],v4[1],v4[2]},{v9[0],v9[1],v9[2]}},
				tri10[][]={{v2[0],v2[1],v2[2]},{v6[0],v6[1],v6[2]},{v7[0],v7[1],v7[2]}},tri11[][]={{v11[0],v11[1],v11[2]},{v7[0],v7[1],v7[2]},{v6[0],v6[1],v6[2]}},
				tri12[][]={{v3[0],v3[1],v3[2]},{v1[0],v1[1],v1[2]},{v4[0],v4[1],v4[2]}},tri13[][]={{v3[0],v3[1],v3[2]},{v6[0],v6[1],v6[2]},{v2[0],v2[1],v2[2]}},
				tri14[][]={{v0[0],v0[1],v0[2]},{v9[0],v9[1],v9[2]},{v1[0],v1[1],v1[2]}},tri15[][]={{v0[0],v0[1],v0[2]},{v2[0],v2[1],v2[2]},{v7[0],v7[1],v7[2]}},
				tri16[][]={{v8[0],v8[1],v8[2]},{v10[0],v10[1],v10[2]},{v9[0],v9[1],v9[2]}},tri17[][]={{v8[0],v8[1],v8[2]},{v7[0],v7[1],v7[2]},{v11[0],v11[1],v11[2]}},
				tri18[][]={{v5[0],v5[1],v5[2]},{v4[0],v4[1],v4[2]},{v10[0],v10[1],v10[2]}},tri19[][]={{v5[0],v5[1],v5[2]},{v11[0],v11[1],v11[2]},{v6[0],v6[1],v6[2]}};
		triangles.add(tri0);triangles.add(tri1);triangles.add(tri2);triangles.add(tri3);triangles.add(tri4);triangles.add(tri5);triangles.add(tri6);triangles.add(tri7);
		triangles.add(tri8);triangles.add(tri9);triangles.add(tri10);triangles.add(tri11);triangles.add(tri12);triangles.add(tri13);triangles.add(tri14);triangles.add(tri15);
		triangles.add(tri16);triangles.add(tri17);triangles.add(tri18);triangles.add(tri19); 
		Vector<float[][]> verx = new Vector<float[][]>();

		for(int i=0;i<slices;i++) {
			verx = subDivideTriangles(triangles); triangles = verx;
		}

		for(int i=0;i<triangles.size();i++) {
			float[][] newTri=(float[][])triangles.get(i);
			float ptn1[]=newTri[0], ptn2[]=newTri[1], ptn3[]=newTri[2];
			addTriangle(ptn1[0],ptn1[1],ptn1[2],ptn2[0],ptn2[1],ptn2[2],ptn3[0],ptn3[1],ptn3[2]);
		}
	}

	public Vector<float[][]> subDivideTriangles(Vector<float[][]> triangles) {
		Vector<float[][]>vec=new Vector<float[][]>();
		for(int k=0;k<triangles.size();k++) {
			float[][]triangle=(float[][])triangles.get(k); float tp = 0.5f; Vector<float[]> midpts = new Vector<float[]>();
			for(int i=0;i<triangle.length;i++) {
				float pt1[]=triangle[i%3],pt2[]=triangle[(i+1)%3], x=(float)((pt1[0]*(1.0-tp))+(tp*pt2[0])), y=(float)((pt1[1]*(1.0-tp))+(tp*pt2[1])),
						z=(float)((pt1[2]*(1.0-tp))+(tp*pt2[2])), xyz[]={x,y,z}; midpts.add(xyz);
			}
			float midpt1[]=(float[])midpts.get(0), midpt2[]=(float[])midpts.get(1), midpt3[]=(float[])midpts.get(2);
			toNormalize(midpt1); toNormalize(midpt2); toNormalize(midpt3); toNormalize(triangle[0]);toNormalize(triangle[1]); toNormalize(triangle[2]);
			float tri1[][]={{triangle[0][0],triangle[0][1],triangle[0][2]},{midpt1[0],midpt1[1],midpt1[2]},{midpt3[0],midpt3[1],midpt3[2]}},		
					tri2[][]={{midpt1[0],midpt1[1],midpt1[2]},{triangle[1][0],triangle[1][1],triangle[1][2]},{midpt2[0], midpt2[1], midpt2[2]}},
					tri3[][]={{midpt3[0],midpt3[1],midpt3[2]},{midpt2[0],midpt2[1],midpt2[2]},{triangle[2][0],triangle[2][1],triangle[2][2]}},
					tri4[][]={{midpt1[0],midpt1[1],midpt1[2]},{midpt2[0],midpt2[1],midpt2[2]},{midpt3[0],midpt3[1],midpt3[2]}}; 
			vec.add(tri1); vec.add(tri2); vec.add(tri3); vec.add(tri4);
		}
		return vec;
	}
    
    
    /**
     * makeDefaultShape - creates a "unit" shape of your choice using your tesselation routines.
     *  If you don't have working tessellation code for any of the shapes, you can use the supplied
     *  makeLameCube routine.
     *
     */
    public void makeDefaultShape () {
    	//makeCone (3, 3, 3);
    	makeSphere (0.3f, 50, 50);
        //makeLameCube();
    }
}
