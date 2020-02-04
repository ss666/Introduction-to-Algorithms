
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class heapRun {										//the main program
	public static void main(String[] args) {
		FibonacciHeaps fh=new FibonacciHeaps();
		String[] instructions = null;
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the file path: ");		// input the name of the text file which contains the name of operation (insert with an integer /extract /minimum)
		String fn = null;
		if(sc.hasNextLine()) {fn=sc.nextLine();}
		sc.close();
		try {

			BufferedReader in =new BufferedReader(new FileReader(fn));
			String str;

			while((str=in.readLine())!=null) {
				
				str=str.trim();
				instructions=str.split(" ");
			}
			in.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		execute(instructions, fh);
	}
   
	public static void execute (String[] instructions, FibonacciHeaps fh) {
				for(int i=0; i<instructions.length-1;i++) {
					if (instructions[i].equalsIgnoreCase("INSERT")) {
						i++;
						fh.insertH(Integer.parseInt(instructions[i]));
					}
					else if (instructions[i].equalsIgnoreCase("EXTRACT")){
						fh.extractH();
					}
					
					else if (instructions[i].equalsIgnoreCase("MINIMUM")){
						fh.minimumH();					
					}		
				}
			}

	
}
