/**
* Explicit type for semantic node representing
* logical AND. (Is a leaf--has no children.)
*
* @author Sabina
*/

public class LogicalAND implements Node{
    
    //Left node to divide
    private Node leftNode;
    
    //Right node to divide
    private Node rightNode; 

    //Node to put value into
    private Node valueNode; 
    
    /**
* Evaluate two nodes and create
* another node containing true or false.
*
* @param left Left Node
* @param right Right Node
*/
    public LogicalAND(Node left, Node right){
        leftNode = left;
	rightNode = right; 
	if ((leftNode == true) && (rightNode == true)) {
		valueNode = true; 
	}
	else {
		valueNode = false; 
	}	
    }

    // Leaf. has no children.
    public void addChild(Node child){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        // TODO
        String[] arr = {""};
        return arr;
    }

    public String getSemanticRepresentation(){
        return "";
    }
}
