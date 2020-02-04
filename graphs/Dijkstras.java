public class Dijkstras {
	private int vertices;
	private double[][] graph;
	
	
	public Dijkstras(double[][] graph) {
		this.graph= graph;
		this.vertices=graph.length;
		
	}
	int minDistance(double dist[], boolean spSet[]) {
		double min=Double.MAX_VALUE; //initialise the minimum value
		int min_index=-1;
		for(int u=0; u<vertices;u++) 
			if(spSet[u]==false && dist[u]<=min) {  
				min=dist[u];
				min_index=u; 
			}			
		
		return min_index;	
	}
	
	void dijkstras(int src) {
		double dist[]=new double[vertices];  //distance from source to i
		boolean spSet[]=new boolean[vertices];  //shortest path set (whether the shortest distance is finalised) 
		for(int i=0;i<vertices;i++) {
			dist[i]=Integer.MAX_VALUE;		//initialise all distances as INFINITE and spSetp[] as false
		}
		dist[src]=0;	//distance of source from itself is zero
	
	for(int count=0; count<vertices-1; count++) {
		int u=minDistance(dist,spSet); //pick a vertex that is not in the shortest path set, and has a minimum distance value
		spSet[u]=true;		//include it to shortest path set
		for(int v=0; v<vertices;v++)
			if(!spSet[v]&& graph[u][v]!=0&& dist[u]!=Integer.MAX_VALUE &&dist[u]+graph[u][v]<dist[v]) 
				dist[v]=dist[u]+graph[u][v]; //update the distances of the connected vertices 
			
	}
	printSolution(dist,src);
	}
	
	void printSolution(double dist[], int src) {
		System.out.println("Dijkstra's Algorithm\nShort Paths from sources");
		for(int i=0;i<vertices;i++) System.out.println(src +"-->" +i+ " : "+dist[i]);
	}
}
