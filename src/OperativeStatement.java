/**
 * Explicit type for a node representing
 * an operative statement.
 *
 * @author Aubrey
 */

public class OperativeStatement implements Node{

    /** Keyword in the operative statement */
    private Node keyword;

    /** Value on which to operate. */
    private Node value;

    /**
     * Constructs an OperativeStatement node
     * for a given keyword and value.
     *
     * @param k semantic node for the keyword
     * @param v semantic node for the value
     */
    public OperativeStatement(Node k, Node v){
        keyword = k;
        value = v;
    }

    // operative statement children are specified 
    // at time of constructor call
    public void addChild(Node child){
        return;
    }

    // operator, then value
    public Node[] getChildren(){
        Node[] children = { keyword, value };
        return children;
    }
    
    public String[] translate(){
        //TODO
        String[] arr = {""};
        return arr;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
