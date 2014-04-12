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

    /** Encoding for a string variable. */
    public static String STRING_TYPE = "String";
    /** Encoding for a quantity variable. */
    public static String QUANTITY_TYPE = "NUMVAL";

    private HashMap<String, Integer> classRecord;
    private HashMap<String, String> typeRecord;
    private HashMap<String, Integer> declarationRecord;

    public SymbolRecord(){
        classRecord = new HashMap<String,Integer>();
        typeRecord = new HashMap<String,String>();
        declarationRecord = new HashMap<String,Integer>();
    }

    /**
     * Sets the class of an identifier.
     *
     * @param key identifier to set
     * @param c class to which to set the identifier
     * @return 0 if successful, non-zero if key already was classed
     */
    public int setClass(String key, int c){
        if(classRecord.containsKey(key) &&
                classRecord.get(key) != c){
            return classRecord.get(key);
        }
        else{
            classRecord.put(key,c);
            return 0;
        }
    }

    /**
     * Sets the type of an identifier.
     */

    public void setType(String key, String t){
        typeRecord.put(key,t);
    }

    /**
     * Returns the type of an identifier in the record.
     */
    public String getType(String identifier){
        return typeRecord.get(identifier);
    }

    /**
     * Changes the value of the declaration record (0 or 1)
     * for a given identifier.
     */
    public void setDeclared(String identifier, int d){
        declarationRecord.put(identifier, d);
    }

    /**
     * Returns true if an identifier has been declared
     */
    public boolean isDeclared(String identifier){
        int dec = declarationRecord.get(identifier);
        return dec == 1 ? true : false;
    }
}
