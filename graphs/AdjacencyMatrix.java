public class AdjacencyMatrix {
		public double[][] adjMatrix;		
		public int vertices;
		
	public AdjacencyMatrix(int vertices) {
		adjMatrix= new double[vertices][vertices]; ////a 2-dimensional array which has the size VxV (V:the number of vertices)
		for(int x=0;x<vertices;x++)
			for(int y=0;y<vertices;y++)
				adjMatrix[x][y]=Double.MAX_VALUE;  //the default distance is the largest positive finite value
		this.vertices=vertices;
	}
	
	
	public void add(int i, int j,double w) {
		adjMatrix[i][j]=w;
	}
	
	public void printSolution() {		//print the adjacency matrix
		System.out.println("Adjacency Matrix");
		for(int i=0; i<vertices;i++)
			System.out.print(" "+i);
		for (int i=0; i<vertices; i++) {
			System.out.println("\n");
			System.out.print(i);
			for(int j=0; j<vertices;j++) {
				double edge=adjMatrix[i][j];
				if(edge== Double.MAX_VALUE) System.out.print(" .");
				else System.out.print(" "+edge); 
			}
			
		} 
	}
	
    public AdjacencyList toAdjacencyList() {				//converse the adjacency matrix to adjacency list
    	AdjacencyList l= new AdjacencyList(vertices);
    		for(int i=0; i<vertices;i++)
    			for(int j=0;j<vertices;j++)
    				if(adjMatrix[i][j]!=Double.MAX_VALUE)
    					l.add(i,j,adjMatrix[i][j]);
    		return l;
    }
   
	
	
}
