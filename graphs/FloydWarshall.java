public class FloydWarshall { //update the solution matrix by considering all vertices as an intermediate vertex.
	
	 void floydWarshall(double graph[][]) {
	 int vertices=graph.length;
	 double[][] dist =graph;
	 int i,j,k;
 	 
	 for(int x=0; x<vertices; x++) dist[x][x]=0;  //set the distance from one vertex to itself to zero.
	 
	 for(k=0;k<vertices;k++) //k is an intermediate vertex in the shortest path from i to j, we update the value of dist[i][j]
		 for(i=0;i<vertices;i++)
			 for(j=0;j<vertices;j++) 
				if(dist[i][k]+dist[k][j]< dist[i][j])
					dist[i][j]=dist[i][k]+dist[k][j];
	 
	 printSolution(dist,vertices);
	 }

	void printSolution(double dist[][], int vertices) {
		System.out.println("Floyd-Warshall Matrix");
		for(int x=0; x<vertices;x++)
			System.out.print(" "+x);
		for (int x=0; x<vertices; x++) {
			System.out.println("\n");
			System.out.print(x);
			for(int y=0; y<vertices;y++) {
				double edge=dist[x][y];
				System.out.print(" "+edge); 
			}
			
		} 
	}
	
}
					
			 
