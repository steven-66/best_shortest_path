package best_shortest_path;



public class FibonacciHeap{
    private int num;         
    private FibNode min;       
    public FibonacciHeap() {
        this.num = 0;
        this.min = null;
    } 
    private void removeNode(FibNode node) {
        node.left.right = node.right;
        node.right.left = node.left;
    }
    private void addNode(FibNode node, FibNode pointer) {
        node.left        = pointer.left;
        pointer.left.right  = node;
        node.right       = pointer;
        pointer.left        = node;
    }
    public void insert(FibNode node) {
        if (num == 0)
            min = node;
        else {
            addNode(node, min);
            if (node.key < min.key)
                min = node;
        }
        num++;
    }  
    public void insert(int key) {
        FibNode node=null;
        node = new FibNode(key);
        insert(node);
    }
    private void catList(FibNode a, FibNode b) {
        FibNode tmp;
        tmp           = a.right;
        a.right       = b.right;
        b.right.left  = a;
        b.right       = tmp;
        tmp.left      = b;
    }
    public void union(FibonacciHeap other) {
        if (other==null)
            return ;

        if((this.min) == null) {              
            this.min = other.min;
            this.num = other.num;
            other = null;
        } else if((other.min) == null) {        
            other = null;
        } else {                               
            catList(this.min, other.min) ;

            if (this.min.key > other.min.key)
                this.min = other.min;
            this.num += other.num;
            other = null;;
        }
    }
    public boolean isEmpty() {
    	return num==0;
    }
  
    public FibNode getMin() {
        FibNode p = min;

        if (p == p.right)
            min = null;
        else {
            removeNode(p);
            min = p.right;
        }
        p.left = p.right = p;
        return p;
    }

    private void link(FibNode node, FibNode pointer) {
        removeNode(node);
        if (pointer.child == null)
            pointer.child = node;
        else
            addNode(node, pointer.child);

        node.parent = pointer;
        pointer.degree++;
        node.marked = false;
    }
     
    private void consolidate() {
        int maxDegree = (int) Math.floor(Math.log(num) / Math.log(2.0));
        int D = maxDegree + 1;
        FibNode[] cons = new FibNode[D+1];
        for (int i = 0; i < D; i++)
            cons[i] = null;
        while (min != null) {
            FibNode x = getMin();            
            int d = x.degree;                        
            while (cons[d] != null) {
                FibNode y = cons[d];                
                if (x.key > y.key) {    
                    FibNode tmp = x;
                    x = y;
                    y = tmp;
                }
                link(y, x);    
                cons[d] = null;
                d++;
            }
            cons[d] = x;
        }
        min = null;
        for (int i=0; i<D; i++) {

            if (cons[i] != null) {
                if (min == null)
                    min = cons[i];
                else {
                    addNode(cons[i], min);
                    if ((cons[i]).key < min.key)
                        min = cons[i];
                }
            }
        }
    }
    public FibNode extractMin() {
        FibNode m = min;
        if(m==null) {
        	System.out.println("GG");
        	return m;
        }
        while (m.child != null) {
            FibNode child = m.child;
            removeNode(child);
            if (child.right == child) {
                m.child = null;
            }
            else {
                m.child = child.right;
            }   
            addNode(child, min);
            child.parent = null;
        }
        removeNode(m);
        if (m.right == m)
            min = null;
        else {
            min = m.right;
            consolidate();
        }
        num--;
        return m;
    }
    private void renewDegree(FibNode parent, int degree) {
        parent.degree -= degree;
        if (parent. parent != null)
            renewDegree(parent.parent, degree);
    }
    private void cut(FibNode node, FibNode parent) {
        removeNode(node);
        renewDegree(parent, node.degree);
 
        if (node == node.right) 
            parent.child = null;
        else 
            parent.child = node.right;

        node.parent = null;
        node.left = node.right = node;
        node.marked = false;
     
        addNode(node, min);
    }
    private void cascadingCut(FibNode node) {
        FibNode parent = node.parent;

        if (parent != null) {
            if (node.marked == false) 
                node.marked = true;
            else {
                cut(node, parent);
                cascadingCut(parent);
            }
        }
    }
    public void decreaseKey(FibNode node, int key) {
        if (min==null ||node==null) 
            return ;

        if (key > node.key) {
            System.out.printf("decrease failed: the new key(%d) is no smaller than current key(%d)\n", key, node.key);
            return ;
        }

        FibNode parent = node.parent;
        node.key = key;
        if (parent!=null && (node.key < parent.key)) {
       
            cut(node, parent);
            cascadingCut(parent);
        }

       
        if (node.key < min.key)
            min = node;
    }
}
class FibNode {
    public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	int key;            
	int degree;            
	FibNode left;        
    FibNode right;        
    FibNode child;        
    FibNode parent;        
    boolean marked;     

    public FibNode(int key) {
        this.key    = key;
        this.degree = 0;
        this.marked = false;
        this.left   = this;
        this.right  = this;
        this.parent = null;
        this.child  = null;
    }
	public FibNode() {
		// TODO Auto-generated constructor stub
	}

}
