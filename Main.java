package best_shortest_path;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g=new Graph(15);
		Edge[] eg=new Edge[26];
		/*
		 * you need input edge list:
		 * frontVertex  endVertex weight
		 *  0 1 1
			0 2 4
			0 4 4
			1 3 2
			1 4 5
			2 3 5
			2 5 4
			2 7 8
			2 8 9
			3 4 6
			4 5 8
			4 7 6
			5 7 6
			5 6 10
			6 11 5
			6 9 1
			7 9 7
			8 10 5
			8 9 6
			9 11 9
			9 14 6
			10 12 5
			10 13 6
			11 12 5
			12 13 5
			13 14 5
		 */
		for(int i=0;i<eg.length;i++) {
				eg[i]=g.createEdge();	
		}	
		g.createGraph(eg);
		Dijkstra d=new Dijkstra(0,9);//from 0 to 9
		d.search(g.adjVex);
		d.printPath(g.adjVex);
	}

}
