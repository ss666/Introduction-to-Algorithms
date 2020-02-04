import java.util.LinkedList;

public class AdjacencyList {
	public LinkedList<Edge>[] adjList; //array of linkedlist
	public int vertices;
	
	public class Edge{
		int vertices;
		double weight;
		
		public Edge(int vertices, double weight){
			this.vertices=vertices;
			this.weight=weight;		
		}
		
	}
	
	public AdjacencyList(int vertices) {
		this.vertices=vertices;
		adjList=new LinkedList[vertices];
		for(int i=0; i<vertices;i++)
			adjList[i]=new LinkedList<>();	
	}
	
	public void add(int i, int j, double w) {
		Edge edge=new Edge(j,w);
		adjList[i].add(edge);	
	}
	
    public void printSolution() {
    	System.out.println("Adjacency List");
    	for (int i=0; i<vertices; i++) {
    		LinkedList<Edge> list= adjList[i];
    		System.out.println("");
    		for(int j=0; j<list.size(); j++) {
    			System.out.print("{"+list.get(j).vertices +","+list.get(j).weight+"}");
    		}
    	}
    }

    public AdjacencyMatrix toAdjacencyMatrix() {		//converse the adjacency list to adjacency matrix
    	AdjacencyMatrix m= new AdjacencyMatrix(vertices);
		for(int i=0; i<vertices;i++) {
			LinkedList<Edge> list =adjList[i];
			for(int j=0;j<list.size();j++)
				if(list.get(j)!=null)
				m.add(i,list.get(j).vertices,list.get(j).weight);
		}
		return m;
    	
    }
}
