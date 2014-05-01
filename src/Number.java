/**
 * Explicit type representing a semantic
 * node for an integer/rational numeric
 * constant.
 * 
 * @author Aubrey
 */

public class Number implements Node{

    public static final String INT_VAL = "INT";
    public static final String RAT_VAL = "RAT";

    /** Value if number is rational. */
    private double rVal;
    /** Value if number is an integer. */
    int iVal;
    /** Semantic flag denoting whether the number
     *  is rational or an integer. */
    private int const_type;

    /**
     * Constructs a Number node for an
     * integer constant.
     */
    public Number(int i){
        rVal = 0;
        iVal = i;
        const_type = Node.SEM_INT_CONST;
    }

    public int getNumCat(){ return const_type; }

    /**
     * Constructs a Number node for a rational
     * constant.
     */
    public Number(double r){
        rVal = r;
        iVal = 0;
        const_type = Node.SEM_RAT_CONST;
    }

    // Leaf.  Has no children.
    public void addChild(Node child){ return; }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        //TODO
        String[] translation = new String[3];
        translation[0] = Translator.IN_PLACE;
        translation[1] = const_type == Node.SEM_INT_CONST ? 
            ""+iVal : ""+rVal; 
        // Add non-literal applying directive at end
        // to denote constant type.
        if(const_type == Node.SEM_INT_CONST){
            translation[2] = INT_VAL;
        }
        else
            translation[2] = RAT_VAL;
        return translation;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
