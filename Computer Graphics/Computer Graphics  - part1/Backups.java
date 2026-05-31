import java.util.Scanner;
import java.lang.Math;

class Backups {

	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		
		int numOfVertices = Integer.parseInt(userInput);
		String secondInput = sc.nextLine();
		String userInputSecondArray[] = secondInput.split(" ");
		double R  =  Double.parseDouble(userInputSecondArray[0]);
		int B  =  Integer.parseInt(userInputSecondArray[1]);
		int K =  Integer.parseInt(userInputSecondArray[2]);
		
		Node[] nodes = new Node[numOfVertices];

		for (int i = 0; i < numOfVertices; ++ i ) {
			String t = sc.nextLine();
			String[] tArray = t.split(" ");
			double x = Double.parseDouble(tArray[0]);
			double y = Double.parseDouble(tArray[1]);
			Node temp = new Node(i, x, y);
			nodes[i] = temp;
		}
		
		for (int i = 0; i < nodes.length; ++i) {
			Node current = nodes[i];
			for (int j = 0; j < nodes.length; ++j) {
				if (i != j && current.b < B && nodes[j].k < K && current.isNeighbor(nodes[j], R)) {
					current.b ++;
					nodes[j].k ++;
				}
			}
		}
		for (int i = 0; i < nodes.length; i++ ) {
			if (nodes[i].b < B || nodes[i].k > K) {
				System.out.println("NO");
				System.exit(0);
		}
	}
	System.out.println("YES");
					
	}

	public static int min (int a, int b ) {
		if (a < b ) return a; 
		else return b;
	}
	
}
class Node {
	public int data;
	public double x;
	public double y;
	public int b;
	public int k;
	public Node next = null;

	Node ( int d, double x, double y) {
		data = d;
		b = 0;
		k = 0;
		this.x = x;
		this.y = y;
	}
	public boolean isNeighbor (Node node, double R ) {
		double distance = Math.sqrt(( Math.pow(this.x - node.x, 2) + Math.pow(this.y - node.y, 2))); 
		if (distance <= R) return true;
		else return false;
	}
}

