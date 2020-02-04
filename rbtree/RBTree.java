

public class RBTree {
	public final boolean RED=false;
	public final boolean BLACK=true;
	
	public class Node{
		int val;
		Node left;
		Node right;
		Node parent;
		Boolean color;
		public Node (int val,Node parent) {
			this.val=val;
			this.parent=parent;
			this.color=RED;			//the default color is RED, to make the color of newly inserted nodes as RED.
		}
	}
	
	public Node root;

	
	public Node insertNode(Node node, int val,Node parent){
		if(node==null)  node=new Node(val,parent);	
		else if(node.val>val) {
			node.left=insertNode(node.left,val,node);}
		else {
			node.right=insertNode(node.right,val,node);}
		return node;
	}
	
	 public Node searchNode(Node node,int value) {
		 if(node==null) return null;
		 if(node.val==value) return node;
		 else return value<node.val? searchNode(node.left,value):searchNode(node.right,value);

	 }
	 
	 public void deleteNode(Node node) {
		 Node parent, child; 		//the parent and child of the node to be deleted
		 boolean color;     //the color of the node to be deleted
		 if(node.left!=null &&node.right!=null) {		//if the node is an internal node
			 Node replace=node;		//the node that replace the node to be deleted
			 replace=node.right;
			 while(replace.left!=null) replace=replace.left;
			 parent=replace.parent;
			 child=replace.right; 
			 color=replace.color; 
			 if(node.parent!=null) {
				 if(node==node.parent.left) node.parent.left=replace;
				 else node.parent.right=replace;
			 }
			 else root=replace;
			 if(parent==node) parent=replace; 
			 else {							  
				 	if(child!=null) {
				 		child.parent=parent;				 	}	
				 		parent.left=child;
						
				 				
				 	replace.right=node.right;
				 	node.right.parent=replace;
			 }
			 replace.parent=node.parent;
			 replace.color=node.color;
			 replace.left=node.left;
			 node.left.parent=replace;
			 
			 if(color==BLACK) fixDeletion(child,parent);  //If the color of the node to be deleted is BLACK, we need to fix it. If the node is RED, just delete it.
			 node=null;
			 return;
		 }
		 
		 if(node.left!=null)  child=node.left;		//If the node has one child
		 else child=node.right;
		 parent=node.parent;
		 color=node.color;
		 if(parent!=null) {
			 if(node==parent.left) parent.left=child;
			 else parent.right=child;
		 }
		 else root=child;
		 if(child!=null) child.parent=parent;
		 if(color==BLACK) fixDeletion(child,parent); 
		 node=null;		 
	 }
	 
	 public void fixDeletion(Node node,Node parent) {
		 Node brother;
		 while((node==null ||node.color==BLACK) && node!=root) {		
			 if(parent.left==node) {
				 brother=parent.right;
				 if(brother.color==RED) {		//if the sibling of the node is RED
					 brother.color=BLACK;
					 parent.color=RED;
					 leftRotate(parent);		//perform the left rotation on parent, these reduce this case to cases below
					 brother=parent.right;
				 }
				 //the sibling of the node is black.
				 if((brother.left==null || brother.left.color==BLACK)&&	
						 (brother.right==null || brother.right.color==BLACK)) {		//both of sibling's children are BLACK, switch the color of sibling to RED, make the parent to be the node and repeat the process
					 brother.color=RED;
					 node=parent;
					 parent=node.parent;}
				 else  {if(brother.right==null||brother.right.color==BLACK) {		//sibling's left child is RED, right child is BLACK
					 brother.left.color=BLACK;
					 brother.color=RED;			//switch the color of the sibling and its left child, then perform right rotation, this transforms the tree into the case below.
					 rightRotate(brother);
					 brother=parent.right; 
				 }
				 brother.color=parent.color;		//The terminal case: the sibling is black, the right child of sibling is red.
				 parent.color=BLACK;		//change the color of right child of sibling to black, and change the parent to black then perform the left rotation. This way we remove the extra black node.
				 brother.right.color=BLACK;
				 leftRotate(parent);
				 node=root;
				 break;
				 }
			 }
			 else {
				 brother=parent.left;
				 if(brother.color=RED) {
					 brother.color=BLACK;
					 parent.color=RED;
					 rightRotate(parent);
					 brother=parent.left;
				 }
				 if((brother.left==null || brother.left.color==BLACK)&&
						 (brother.right==null || brother.right.color==BLACK)) {
					 brother.color=RED;
					 node=parent;
					 parent=node.parent;			 
				 } else {
					 if(brother.left==null||brother.left.color==BLACK) {
						 brother.right.color=BLACK;
						 brother.color=RED;
						 leftRotate(brother);
						 brother=parent.left;
					 }
					 brother.color=parent.color;
					 parent.color=BLACK;
					 brother.left.color=BLACK;
					 rightRotate(parent);
					 node=root;
					 break;
					 }
				 }
			 }
		 if(node!=null) node.color=BLACK; //若x是根节点或为红色，直接染黑。执行此步前，左黑高=右黑高-1，执行此步后，左黑高=右黑高
		 }

	 public void fixViolationNode(Node node) {
		while(node.parent!=null && node.parent.color==RED ) {		//Do following if the node is not root and the color of the node's parent is red.
			Node uncle;
			if(node.parent==node.parent.parent.left) {   //LR
				uncle=node.parent.parent.right;
				if(uncle!=null && uncle.color==RED) {		//If the node's uncle is RED, change color of parent and uncle as BLACK, change color of grand parent as RED, change the node and the node's grandparent, repeat again.
				uncle.color=BLACK;
				node.parent.color=BLACK;
				uncle.parent.color=RED;
				node=uncle.parent;
				continue;
				}
				// If the node's uncle is BLACK
				if(node==node.parent.right) {		//Left Right Case: left rotate and apply Left Left Case
					node=node.parent;
					leftRotate(node);
				}
				node.parent.color=BLACK;		//Left Left Case: right rotate and swap colors of parent and grandparent
				node.parent.parent.color=RED;
				rightRotate(node.parent.parent);

			}
			else { 										
				uncle=node.parent.parent.left;
				if(uncle!=null && uncle.color==RED) {
				uncle.color=BLACK;
				node.parent.color=BLACK;
				uncle.parent.color=RED;
				node=uncle.parent;
				continue;
				}
				if(node==node.parent.left) {	//Right Left Case: right rotate and apply Right Right Case
					node=node.parent;
					rightRotate(node);
				}
				node.parent.color=BLACK;		//Right Right Case: left rotate and swap colors of parent and grandparent.
				node.parent.parent.color=RED;
				leftRotate(node.parent.parent);

				
			}
		}
		if(node.parent==null) node.color=BLACK;		//If the node is root, change color of the node as BLACK.

	} 
	
	public void leftRotate(Node node) {
		    Node child=node.right;
			node.right=child.left;
			if(child.left!=null) child.left.parent=node;
			child.parent=node.parent; 
			if(node.parent!=null) {
				if(node.parent.left==node) node.parent.left=child;
				else node.parent.right=child;
			}
			else root=child;
			child.left=node;
			node.parent=child;
	}
			
	
	public void rightRotate(Node node) {
			Node child=node.left;
			node.left=child.right;
			if(child.right!=null) child.right.parent=node;
			else node.left=child.right;
			child.parent=node.parent;
			if(node.parent!=null){
				if(node.parent.left==node) node.parent.left=child;
				else node.parent.right=child;
			}
			else root=child;
				child.right=node;
				node.parent=child;	
	}
	
	
	public void insert(int val) {
		root=insertNode(root,val,null);		//Perform standard BST insertion
		Node node=searchNode(root,val);
		fixViolationNode(node);		//Recoloring and Rotation 
	}
	
	public void search(int value) {
		if(root==null) System.out.println("There is not a tree");
		Node node=searchNode(root,value);
		if(node==null)  System.out.println("search for "+value+" - not found");
		else System.out.println("search for "+value+" - found");
	 }
	
	public void delete(int val) {
		if(root==null) System.out.println("There is not a tree");
		Node node=searchNode(root,val);
		if(node==null) return;
		else deleteNode(node);		//Perform standard BST delete first and handle various cases in fixDeletion().
	}
}
	