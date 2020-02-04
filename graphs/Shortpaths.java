import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Shortpaths {
	public static void main(String args[]) {
	    List<Integer> vertices1 =new ArrayList<Integer>();
	    List<Integer> vertices2 =new ArrayList<Integer>();
	    List<Double> length =new ArrayList<Double>();

		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the file path: ");		//take as input the name of the text
		String fn = null;
		if(sc.hasNextLine()) {fn=sc.nextLine();}
		sc.close();
		
		try {

			BufferedReader in =new BufferedReader(new FileReader(fn));
			String str;

			while((str=in.readLine())!=null) {
				Pattern p =Pattern.compile("\\(((.*?))\\)");
				Matcher m= p.matcher(str);
			
				while(m.find()) {
				    String[] instructions =new String[2];
				    instructions=m.group(1).split(",");	    
				    vertices1.add(Integer.valueOf(instructions[0]));
				    vertices2.add(Integer.valueOf(instructions[1]));
				    length.add(Double.valueOf(instructions[2]));   
				}
			}
			in.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}

		int max;
		max= Math.max(Collections.max(vertices1), Collections.max(vertices2));
			
		AdjacencyMatrix answer1 =new AdjacencyMatrix(max+1);
	    for(int i=0; i<length.size();i++)
	    answer1.add(vertices1.get(i),vertices2.get(i),length.get(i)); 
	    answer1.printSolution();		//output the adjacency matrix of the graph with the edges given in the file	    
	    
	    /* test cases for conversion function:
	    System.out.println("\n\n");
	    AdjacencyList l1=answer1.toAdjacencyList();
        l1.printSolution();
        
	    System.out.println("\n\n");
        AdjacencyMatrix m1=l1.toAdjacencyMatrix();
        m1.printSolution();
        
        System.out.println("\n\n");
        AdjacencyList l2=m1.toAdjacencyList();
        l2.printSolution();
        */
        
	    System.out.println("\n\n");
	    Dijkstras answer2= new Dijkstras(answer1.adjMatrix);
	    answer2.dijkstras(0);			//run Dijkstra's algorithm, specifying the lengths of the shortest paths from 0 to all other vertices
	    
	    System.out.println("\n\n");
	    FloydWarshall answer3 = new FloydWarshall();
	    answer3.floydWarshall(answer1.adjMatrix);  //the matrix that gives the minimum distance computed by the Floyd-Warshall algorithm
	    

	}
 
}
