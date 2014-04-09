/**
* Explicit type for semantic node representing
* addition. (Is a leaf--has no children.)
*
* @author Sabina
*/

public class Add implements Node{
    
    //Left node to divide
    private Node leftNode;
    
    //Right node to divide
    private Node rightNode; 

    //Node to put value into
    private Node valueNode; 
    
    /**
* Add two nodes and create
* another node containing that value.
*
* @param left Left Node
* @param right Right Node
*/
    public Add(Node left, Node right){
        leftNode = left;
	rightNode = right; 
	valueNode = leftNode + rightNode; 
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
