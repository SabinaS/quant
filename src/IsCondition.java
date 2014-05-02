/**
 * Explicit class definition for a semantic node
 * representing an is condition.
 */

public class IsCondition implements Node{

    /** Semantic node for the condition of the block. */
    private Node condition;

    public IsCondition(Node c){
        condition = c;
    }

    public void addChild(Node c){ return; }

    public Node[] getChildren(){
        Node[] children = { condition };
        return children;
    }

    public String[] translate(){
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        translation[1] = "if("+condition.translate()[1]+"){\n"+
                         "System.out.println(\"Yes!\"); } else{ System.out.println(\"No!\"); }\n";
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }
}
