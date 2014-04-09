/**
* Explicit type for semantic node representing
* multiplication. (Is a leaf--has no children.)
*
* @author Sabina
*/

public class Multiply implements Node{
    
    //Left node to multiply
    private Node leftNode;
    
    //Right node to multiply
    private Node rightNode; 

    //Node to put value into
    private Node valueNode; 
    
    /**
* Multiplies two nodes and creates 
* another node containing that value.
*
* @param left Left Node
* @param right Right Node
*/
    public Multiply(Node left, Node right){
        leftNode = left;
	rightNode = right; 
	valueNode = leftNode * rightNode; 
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
