package best_shortest_path;

import java.util.ArrayList;

public class Dijkstra{
	private FibonacciHeap queue;
	int start;
	int end;
	private ArrayList<FibNode> fb;
	public Dijkstra(int start,int end) {
		this.start=start;
		this.end=end;
		queue=new FibonacciHeap();
		fb=new ArrayList<FibNode>();
	}
	public void initailize(Vertex[] v) {
		v[start].setD(0);
		v[start].setEdgeCount(0);
	}
	public void relax(Vertex u, Vertex v, int weight) {
		if(v.d>u.d+ weight) {
			v.d=u.d+ weight;
			v.parent=u;
			v.edgeCount=u.edgeCount+1;
			 queue.decreaseKey(fb.get(v.id),v.d);
		}
		else if(v.d==u.d+weight) {
			if(v.edgeCount>u.edgeCount+1) {
				v.edgeCount=u.edgeCount+1;
				v.parent=u;
			}
		}
	}
	public void inputQueue(Vertex[] v) {
		if(v==null) {
			return;
		}
		for (int i=0;i<v.length;i++) {
			FibNode newnode=new FibNode(v[i].d);
			fb.add(newnode);
			queue.insert(newnode);
		}
	}
	public void search(Vertex[] v) {
		initailize(v);
		inputQueue(v);
		while(!queue.isEmpty()) {
			int min=fb.indexOf(queue.extractMin());
			for (int i=0;i<v.length;i++) {
				if(v[i].id==min) {
					Vertex current=v[i];
					while(current.nextVex!=null) {
						relax(v[i],v[current.nextVex.id],current.nextVex.weight);
						current=current.nextVex;
					}
				}
				else {
					continue;
				}
			}
		}
	}
	public void printPath(Vertex[] v) {
		Vertex current=v[end];
		System.out.printf("The best shortest path from %d to %d is: ",start,end);
		while(current!=v[start]) {
			System.out.print(current.id+ "<-");
			current=current.parent;
		}
		System.out.print(v[start].id);
	}
}