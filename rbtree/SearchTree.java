import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class SearchTree {										//the main program
	public static void main(String[] args) {
		RBTree rb=new RBTree();
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the file path: ");		// input the name of the text file which contains the name of operation (search,insert,delete) and a number
		String fn = null;
		if(sc.hasNextLine()) {fn=sc.nextLine();}
		sc.close();
		try {

			BufferedReader in =new BufferedReader(new FileReader(fn));
			String str;

			while((str=in.readLine())!=null) {
				str=str.trim();
				String[] instructions=str.split(" ");
				for(int i=0; i<instructions.length-1;i+=2)
				checkRB(instructions[i],instructions[i+1],rb);
			}
			in.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
	
	public static void checkRB(String operation,String number, RBTree rb) {		//test function checkRB
		final int INSERT=0;
		final int DELETE=1;
		final int SEARCH=2;
		int op = 3;
		if(operation.equalsIgnoreCase("INSERT")) op=INSERT;
		else if(operation.equalsIgnoreCase("DELETE")) op=DELETE;
		else if(operation.equalsIgnoreCase("SEARCH")) op=SEARCH;
		
		switch(op) {
		case 0: rb.insert(Integer.parseInt(number)); break;
		case 1: rb.delete(Integer.parseInt(number)); break;
		case 2: rb.search(Integer.parseInt(number)); break;
		default :System.out.println("Something Wrong");
		}
		
		
	}
}
