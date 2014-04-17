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
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        translation[1] = "if("+condition.translate()[1]+"){\n"+
                         trueSequence.translate()[1]+"}\n";
        if(falseSequence!=null)
            translation[1] = translation[1] + "else{\n" +
            falseSequence.translate()[1]+"}\n";
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }
}
