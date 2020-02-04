
class Node<E>{		//abstract in the type of elements of the wheel.
	E data;
	Node<E> prev;
	Node<E> next;
	
	public Node() {
		this.prev=this;
		this.next=this;
	}
	
	public Node(E value, Node<E> prev, Node<E> next) {
		this.data = value;
		this.prev = prev;
		this.next = next;
	}
	
	public void printValue() {
	   System.out.println(data);
	}

}
public class Wheels<E> {		//data structure wheels, which is a doubly-linked circular list.

    int size;
	Node<E> head;
	
	public Wheels() {		//constructor to create an empty wheel
		head = new Node<E>();
		size = 0;
	}
	
	public void emptyW() {		//empty the wheel
		size = 0;
		head.data = null;
		head.prev = head.next = head;
	}
	
    public boolean isEmpty() {		//the Boolean test for emptiness
    	return size == 0;
    	
    }
    
    public void rightW() {		//move the head pointer clockwise
    	head = head.next;
    }
    
    public void leftW() {		//move the head pointer anti-clockwise
    	head = head.prev;
    }
    
    public Node<E> headW() {		//reading the element under the head
    	return head;
    }
   
    public void insertW(E value) {	//insert a new element to the left(prev) of the head
    	if(head.data != null) {
    	Node<E> tmp = new Node<E>(value, head.prev, head);
    	head.prev.next= tmp;
    	head.prev =tmp;
    	head =tmp;	//set the node to be the new head element
    	}
    	else {
    		head.data=value;
    	}
    	size ++;
	}
    
    public Node<E> extractW() {		//delete and return the head element
    	Node<E> tmp = head;
    	tmp.prev.next=tmp.next;
    	tmp.next.prev=tmp.prev;
    	head = tmp.next;	//the new head to be right(next) of previous head element
    	size --;   	
    	return tmp;
    }
    
    public static void main(String[] args) {		//a test case, which is the same as the case shown on the lecture slides
    	Wheels<Integer> a = new Wheels<Integer>();
    	a.insertW(8);
    	a.insertW(4);
    	a.insertW(0);
    	a.insertW(5);
    	a.insertW(3);
    	a.insertW(2);
    	//a.leftW();
    	//a.insertW(7);
    	//a.extractW();  	
    	a.head.printValue();
    	System.out.println(a.size);
    }
}
