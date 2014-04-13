/**
 * Explicit class defining the semantic node
 * representing a sequence of boolean expressions
 *
 * @author Aubrey
 */

import java.util.*;

public class BooleanSequence implements Node{

    /** Component boolean expressions. */
    private LinkedList<Node> expressions;
    /** Boolean operators between expressions. */
    private LinkedList<Integer> operators;

    public BooleanSequence(Node exp){
        expressions = new LinkedList<Node>();
        expressions.add(exp);
        operators = new LinkedList<Integer>();
    }

    /**
     * Combines boolean sequences.
     */
    public void addExp(BooleanSequence seq, int boolop){
        operators.add(boolop);
        int[] ops = seq.getOps();
        Node[] exps = seq.getExpressions();
        for(int i = 0; i < ops.length; i++){
            expressions.add(exps[i]);
            if(i > 0)
                operations.add(ops[i-1]);
        }
    }

    public Node[] getExps(){ return expressions.toArray(); }
    public int[] getOps(){ return operations.toArray(); }

    /**
     * When a boolean expression is added,
     * so is an operator.  Boolean Sequence
     * implements an addExpression function
     * to be used instead.
     */
    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = expressions.toArray();
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
