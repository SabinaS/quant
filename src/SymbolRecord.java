/**
 * Simplified symbol table.  Tracks the scope and
 * various attributes of identifiers.
 *
 * @author Aubrey
 */

import java.util.HashMap;
import java.util.ArrayList;

public class SymbolRecord{
 
    /** Index within the record of the map of variable class. */
    private static int R_CLASS = 0;

    public static String[] CLASSES = { "none", "unit", "variable", "object type" };
    /** Encoding for an identifier declared as a unit. */
    public static int UNIT_CLASS = 1;
    /** Encoding for an identifier declared as a variable. */
    public static int VARIABLE_CLASS = 2;
    /** Encoding for an identifier declared as an object type. */
    public static int TYPE_CLASS = 3;
    /** Encoding for a user-defined class. */
    public static int UDEF_CLASS = 4;
    /** Encoding for a field function of an object. */
    public static int TFUNC_CLASS = 5;

    /** Encoding for an unknown type. */
    public static String UNKNOWN_TYPE = "!TUNKNOWN";
    /** Encoding for a string variable. */
    public static String STRING_TYPE = "String";
    /** Encoding for a quantity variable. */
    public static String QUANTITY_TYPE = "NUMVAL";

    public static int TYPE_DEFINED = -2;
    public static int SUPERTYPE_UNDEFINED = -1;

    HashMap<String, Integer> classRecord;
    private HashMap<String, String> typeRecord;
    private HashMap<String, Integer> declarationRecord;

    public ArrayList<DefinedType> objTypes;
    private HashMap<String,DefinedType> typeMap;

    public SymbolRecord(){
        classRecord = new HashMap<String,Integer>();
        typeRecord = new HashMap<String,String>();
        declarationRecord = new HashMap<String,Integer>();

        objTypes = new ArrayList<DefinedType>();
        typeMap = new HashMap<String,DefinedType>();
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

    public int getClass(String key){
        if(key == null || !classRecord.containsKey(key)) return 0;
        return classRecord.get(key);
    }

    /**
     * Sets the type of an identifier.
     */

    public void setType(String key, String t){
        typeRecord.put(key,t);
    }

    public int defineType(String typeName, String sType){
        if(typeMap.containsKey(typeName))
            return SymbolRecord.TYPE_DEFINED;
        if(sType!=null&&!typeMap.containsKey(sType))
            return SymbolRecord.SUPERTYPE_UNDEFINED;
        DefinedType type = new DefinedType(typeName, sType, this);
        if(sType!=null) type.inheritFrom(sType);
        objTypes.add(type);
        typeMap.put(typeName,type);
        return 0;
    }

    public boolean typeIsDefined(String typeName){
        return typeMap.containsKey(typeName);
    }

    public DefinedType getTypeObj(String typeName){
        return typeMap.get(typeName);
    }

    public void addTypeFields(String typeName, ArrayList<String> fields){
        DefinedType type = typeMap.get(typeName);
        if(type==null) return;
        for(int i = 0; i < fields.size(); i++){
            type.addField(fields.get(i), "Object", "");
        }
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
