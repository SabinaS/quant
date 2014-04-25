/**
 * Explicit type for semantic node representing
 * a quantity (number + unit pair).
 *
 * @author Aubrey
 */

public class Quantity implements Node{
    
    private Node number;
    private Node unit;

    /**
     * Constructs a quantity with a given
     * number and unit.
     *
     * @param n number
     * @param u (may be null)
     */
    public Quantity(Node n, Node u){
        number = n;
        unit = u;
    }

    // children known at construction
    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        if(unit != null){
            Node[] children = { number, unit };
            return children;
        } else{
            Node[] children = { number };
            return children;
        }
    }

    public String[] translate(){
        // TODO
        String[] translation = new String[2];

        String[] num_tr = number.translate();
        String[] unit_tr = { "", "" };
        if(unit != null)  unit_tr = unit.translate();

        String literal = "factory.new NUMVAL("+
           (num_tr[2].equals(Number.INT_VAL) ? "new Integer(":"new Double(")+ 
           num_tr[1]+"), factory.new UNIT(\""+unit_tr[1]+"\",factory))";

        translation[0] = Translator.IN_PLACE;
        translation[1] = literal;
        return translation;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
