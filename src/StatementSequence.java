/**
 * Semantic node representing a statement sequence.
 * 
 * @author Aubrey
 */

import java.util.*;

public class StatementSequence implements Node{

    private LinkedList<Node> statements;

    public StatementSequence(Node stmt){
        statements = new LinkedList<Node>();
        statements.add(stmt);
    }

    public void addChild(Node n){
        statements.add(n);
    }

    public Node[] getChildren(){
        Node[] children = (Node[]) statements.toArray();
        return children;
    }

    public String[] translate(){
        String block = "";
        for(Node child : statements){
            String[] cTranslation = child.translate();
            // line-level translations will be of length 2
            if(cTranslation[0].equals(Translator.IN_PLACE)){
                block = block + "\n" + cTranslation[1];
                if(child.getSemanticRepresentation().equals("stmt"))
                    block = block+";\n";
	    }
        }

        String[] translation = { Translator.IN_PLACE, block };
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
