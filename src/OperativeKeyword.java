/**
 * Explicit type definition for the semantic
 * node representing an operative keyword.
 * (Is a leaf.  No nodes contained.) 
 *
 * @author Aubrey
 */

public class OperativeKeyword implements Node{
    
    /** Wrapped keyword (encoded). */
    private int kwd;

    /**
     * Constructs an OperativeKeyword node,
     * wrapping a specified keyword (encoded).
     *
     * @param k keyword represented
     */
    public OperativeKeyword(int k){
        kwd = k;
    }

    // Children known at construction.
    public void addChild(Node child){
        return;
    }

    // Leaf.  No children.
    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        // Aubrey
        // implement using a case for each
        // semantic keyword
        String[] arr;

        switch (kwd){
            case Node.SEM_PRINT_KWD:
                arr = new String[4];
                arr[0] = Translator.IN_PLACE;
                arr[1] = "System.out.println(";
                arr[2] = Translator.AFTER_SIBLING;
                arr[3] = ".toString())";
                break;
            default:
                arr = new String[1];
                arr[0] = "";
        } 

        return arr;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
