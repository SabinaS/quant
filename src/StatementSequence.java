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
        String[] translation = {""};
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
