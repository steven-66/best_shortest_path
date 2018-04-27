package best_shortest_path;

import java.util.Scanner;

public class Graph {
	Scanner sc;
	Vertex[] adjVex;
	public Graph(int count){
		sc = new Scanner(System.in);
		adjVex=new Vertex[count];

	}
	public void createGraph(Edge[] e) {		
		for(int i=0;i<adjVex.length;i++) {
			Vertex temp=adjVex[i];
			for(int j=0;j<e.length;j++) {
				if(e[j].fronVex==adjVex[i].id) {
					Vertex v=new Vertex(e[j].endVex);
					 temp.nextVex=v;
					 temp=v;
					 temp.weight=e[j].weight;
				}
				else {
					continue;
				}
			}
		}
	}
	
	public  Edge createEdge() {
		Edge e=null;
		if(sc.hasNext()) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			int z=sc.nextInt();
			e=new Edge(x,y,z);
			if(adjVex[x]==null) {
				adjVex[x]=new Vertex(x);
			}
			if(adjVex[y]==null) {
				adjVex[y]=new Vertex(y);
			}
		}
		return e;
	}
	public Vertex createVertex(int id) {
		
			Vertex v=new Vertex(id);	
			return v;
	}
}
class Vertex{
	Vertex nextVex;// adjacency vertex
	Vertex parent;
	int edgeCount; //num of edges to get to this vertex 
	int d; //upper bound of amount of cost
	int id;//the name of a vertex
	int weight;
	public Vertex() {
		
		edgeCount=100;
		d=100;
		
	}
	public Vertex getNextVex() {
		return nextVex;
	}
	public void setNextVex(Vertex nextVex) {
		this.nextVex = nextVex;
	}
	public int getEdgeCount() {
		return edgeCount;
	}
	public void setEdgeCount(int edgeCount) {
		this.edgeCount = edgeCount;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public Vertex(int id) {
		edgeCount=100;
		d=100;
		weight=0;
		nextVex=null;
		this.id=id;
	}
}
class Edge{
	public int getFronVex() {
		return fronVex;
	}
	public void setFronVex(int fronVex) {
		this.fronVex = fronVex;
	}
	public int getEndVex() {
		return endVex;
	}
	public void setEndVex(int endVex) {
		this.endVex = endVex;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	int fronVex;
	int endVex;
	int weight;
	public Edge(int fronVex, int endVex, int weight) {
		this.fronVex=fronVex;
		this.endVex=endVex;
		this.weight=weight;
	}
}