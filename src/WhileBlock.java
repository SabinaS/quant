/**
 * Explicit class definition for a semantic node
 * representing a while block.
 */

public class WhileBlock implements Node{

    /** Semantic node for the condition of the block. */
    private Node condition;
    /** Semantic node for the statement sequence to evaluate if
     *  the condition is true.
     */
    private Node trueSequence;

    public WhileBlock(Node c, Node t){
        condition = c;
        trueSequence = t;
    }

    public void addChild(Node c){ return; }

    public Node[] getChildren(){
        Node[] children = { condition, trueSequence };
        return children; 
    }

    public String[] translate(){
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        translation[1] = "while("+condition.translate()[1]+"){\n"+
                         trueSequence.translate()[1]+"}\n";
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }
}
