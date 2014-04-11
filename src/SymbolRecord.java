/**
 * Simplified symbol table.  Tracks the scope and
 * various attributes of identifiers.
 *
 * @author Aubrey
 */

import java.util.HashMap;

public class SymbolRecord{
 
    /** Index within the record of the map of variable class. */
    private static int R_CLASS = 0;

    public static String[] CLASSES = { "none", "unit", "variable" };
    /** Encoding for an identifier declared as a unit. */
    public static int UNIT_CLASS = 1;
    /** Encoding for an identifier declared as a variable. */
    public static int VARIABLE_CLASS = 2;

    private HashMap<String,Integer> record;

    public SymbolRecord(){
        record = new HashMap<String,Integer>();
    }

    /**
     * Sets the class of an identifier.
     *
     * @param key identifier to set
     * @param c class to which to set the identifier
     * @return 0 if successful, non-zero if key already was classed
     */
    public int setClass(String key, int c){
        if(record.containsKey(key) &&
                record.get(key) != c){
            return record.get(key);
        }
        else{
            record.put(key,c);
            return 0;
        }
    }
}
