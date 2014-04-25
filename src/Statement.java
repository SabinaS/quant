/**
 * Explicit node type for a generic statement
 * which contains a specific statement 
 * (in Quant's current version, either assignment
 * or operative.)
 *
 * @author Aubrey
 */

public class Statement implements Node{

    /**
     * Specific statement (assignment or operative[e.g. 'Print'])
     * held by the generic statement.
     */
    private Node containedStatement;

    /**
     * Constructs a generic statement node for
     * a given specific statement.
     *
     * @param statement specific statement to wrap with
     * the constructed Statement
     */
    public Statement(Node statement){
        containedStatement = statement;
    }

    // Semantically, a statement's children will
    // not change after being constructed.
    public void addChild(Node child){
        return;
    }

    // the statement will always have only one
    // element
    public Node[] getChildren(){
        Node[] children = { containedStatement };
        return children;
    }

    public String[] translate(){
        // Simply return the translation of the wrapped statement.
        return containedStatement.translate();
    }

    public String getSemanticRepresentation(){
        // TODO
        return "stmt";
    }
}
