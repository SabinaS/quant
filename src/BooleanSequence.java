/**
 * Explicit class defining the semantic node
 * representing a sequence of boolean expressions
 *
 * @author Aubrey
 */

import java.util.*;

public class BooleanSequence implements Node{

    /** Expression or sequence in the left portion of the sequece. */
    private Node left;
    private Integer operator;
    /** Expression or sequence in the right portion of the sequence. */
    private Node right;

    public BooleanSequence(Node l, Node r, int op){
        left = l;
        right = r;
        operator = op;
    }

    // Do not need to implement -- no children
    // are added after construction.
    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = { left, right };
        return children;
    }

    public String[] translate(){
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        if(operator == -1)
            translation[1] = left.translate()[1];
        else{
            translation[1] = "("+left.translate()[1]+
                ((operator == Node.SEM_AND) ? "&&" : "||") +
                right.translate()[1]+")";
        }
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
