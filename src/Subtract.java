/**
* Explicit type for semantic node representing
* subtraction. (Is a leaf--has no children.)
*
* @author Sabina
*/

public class Subtract implements Node{
    
    //Left node to divide
    private Node leftNode;
    
    //Right node to divide
    private Node rightNode; 

    //Node to put value into
    private Node valueNode; 
    
    /**
* Subtract two nodes and create
* another node containing that value.
*
* @param left Left Node
* @param right Right Node
*/
    public Subtract(Node left, Node right){
        leftNode = left;
	rightNode = right; 
	valueNode = leftNode - rightNode; 
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
