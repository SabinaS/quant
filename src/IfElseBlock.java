/**
 * Explicit class definition for a semantic node
 * representing an if-then-else block.
 */

public class IfElseBlock implements Node{

    /** Semantic node for the condition of the block. */
    private Node condition;
    /** Semantic node for the statement sequence to evaluate if
     *  the condition is true.
     */
    private Node trueSequence;
    /** Statement sequence to evaluate if condition is false. */
    private Node falseSequence;

    public IfElseBlock(Node c, Node t, Node f){
        condition = c;
        trueSequence = t;
        falseSequence = f;
    }

    public void addChild(Node c){ return; }

    public Node[] getChildren(){
        Node[] children = { condition, trueSequence, falseSequence };
        return children;
    }

    public String[] translate(){
        String[] arr = { "" };
        return arr;
    }

    public String getSemanticRepresentation(){
        return "";
    }
}
