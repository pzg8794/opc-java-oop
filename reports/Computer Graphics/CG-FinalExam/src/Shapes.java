import java.util.HashMap;
import java.util.Vector;


@SuppressWarnings("serial")
public class Shapes<T1, T2> extends HashMap<Integer, float[][]> {

	public Vector<float[]> newVector;
	public Vector<float[]> newVertrix;
	public static Vector<float[][]> vector;
	
	static float front [][]={{-0.5f,0.5f,0.5f}  ,{-0.5f,-0.5f,0.5f} ,{0.5f,-0.5f,0.5f}
	,{0.5f,0.5f,0.5f}}  ;
	static float back[][] ={{0.5f,0.5f,-0.5f} ,{0.5f,-0.5f,-0.5f}, {-0.5f,-0.5f,-0.5f}
	,{-0.5f,0.5f,-0.5f}};
	static float left[][] ={{-0.5f, 0.5f,-0.5f},{-0.5f,-0.5f,-0.5f},{-0.5f,-0.5f,0.5f} 
	,{-0.5f,0.5f,0.5f}} ;
	static float right [][] = {{0.5f,0.5f,0.5f}, {0.5f,-0.5f,0.5f}, {0.5f,-0.5f,-0.5f} 
	,{0.5f,0.5f,-0.5f}} ;
	static float top   [][] ={{-0.5f,0.5f,-0.5f} ,{-0.5f,0.5f,0.5f}  ,{0.5f,0.5f,0.5f}
	,{0.5f,0.5f,-0.5f}} ;
	static float bottom[][]={{-0.5f,-0.5f,0.5f},{-0.5f,-0.5f,-0.5f},{0.5f,-0.5f,-0.5f}
	,{0.5f,-0.5f,0.5f}} ;
	
	static float tAngle;
	public static Vector<float[][]> getVectorTriangle() {
		return vector;
	}
}
