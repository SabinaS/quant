/**
* Explicit type for semantic node representing
* greater than. (Is a leaf--has no children.)
*
* @author Sabina
*/

public class GreaterThan implements Node{
    
    //Left node to divide
    private Node leftNode;
    
    //Right node to divide
    private Node rightNode; 

    //Node to put value into
    private Node valueNode; 
    
    /**
* Compare two nodes and create
* another node containing true or false.
*
* @param left Left Node
* @param right Right Node
*/
    public GreaterThan(Node left, Node right){
        leftNode = left;
	rightNode = right; 
	if (leftNode > rightNode) {
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
