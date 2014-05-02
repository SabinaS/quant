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
    String name;
    String trDef = "";

    int ignoreDecl = 0;
    String oType;
    int ct = 0;
    String aType = "";
    /**
     * Constructs a variable node.
     * @param n name of variable
     * @param r reference to global symbol record
     */
    public Variable(String n, SymbolRecord r){
        name = n;
        symrecord = r;
        // Variable is a quantity by default.
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
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        
	String literal = ( ignoreDecl==1 || symrecord.isDeclared(name) ? "" : 
                           symrecord.getType(name) ) + " "+name;
        symrecord.setDeclared(name,1);
        if(trDef.equals(""))
            translation[1] = literal;
        else
            translation[1] = trDef;
        return translation;
    }
    
    public String getSemanticRepresentation(){
        // Return the type of the variable.
        return symrecord.getType(name);
    }

    public String getName(){ return name; }
}
