/**
 * Explicit class definition for a semantic
 * node representing a variable.
 * 
 * @author Aubrey
 */

public class Variable implements Node{
    /** Local reference to global symbol record.
     *  used to determine declarations in translation.
     */
    private SymbolRecord symrecord;
    private String name;

    /**
     * Constructs a variable node.
     * @param n name of variable
     * @param r reference to global symbol record
     */
    public Variable(String n, SymbolRecord r){
        name = n;
        symrecord = r;
        // Variable is a quantity by default.
        symrecord.setType(n, SymbolRecord.QUANTITY_TYPE);
        // Variable is not declared until its initial translation.
        symrecord.setDeclared(n, 0);
    }

    /**
     * Sets the variable type.
     */
    public void setType(String t){
        symrecord.setType(name, t);
    }

    // Leaf -- node will have no children.
    public Node[] getChildren(){
        Node[] arr = {};
        return arr;
    }

    public void addChild(Node c){ return; }

    public String[] translate(){
        String[] translation = {""};
        return translation;
    }
    
    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
