/**
 * Record containing the structure of a 
 * defined object type.
 */

import java.util.ArrayList;

public class DefinedType{

    public static final String F_START = "[FUNC]";

    public String typeName;
    public String superType;
    public SymbolRecord symrecord;

    public ArrayList<String> fields;
    public ArrayList<String> fieldValues;
    public ArrayList<String> fieldTypes;

    public ArrayList<String> funcs;
    public ArrayList<ArrayList<String>> funcContents;
    public ArrayList<String> funcTypes;

    public DefinedType(String name, String stype, SymbolRecord rec){
        typeName = name;
        superType = stype;
        symrecord = rec;
        fields = new ArrayList<String>();
        fieldValues = new ArrayList<String>();
        fieldTypes = new ArrayList<String>();

        funcs = new ArrayList<String>();
        funcContents = new ArrayList<ArrayList<String>>();
        funcTypes = new ArrayList<String>();
    }
 

    public void inheritFrom(String sType){
        DefinedType parent = symrecord.getTypeObj(sType);
        if(parent == null) return;
        for(int i = 0; i < parent.fields.size(); i++){
            addField(parent.fields.get(i),parent.fieldTypes.get(i),
                        parent.fieldValues.get(i));
        }
    }

    public boolean inheritsFrom(String sType){
        String c = superType;
        System.out.println(sType+" against: "+c);
        if(c == null) return false;
        if(c != null && !c.equals("")){
            if(c.equals(sType)) return true;
            DefinedType t = symrecord.getTypeObj(c);
            if(t != null) return t.inheritsFrom(sType);
        }
        return false;
    }

    /** Add a field to a defined type. */

    public void popFunc(String func){
        ArrayList<String> parts = funcContents.get(funcs.indexOf(func));
        String fType = "NONE";
        for(int i = 0; i < parts.size(); i++){
            String part = parts.get(i);
            if(part.equals("+")||part.equals("-")||part.equals("*")||part.equals("/"))
                continue;
            if(part.indexOf("\"") == 0){ fType = "String"; continue; }
            if(part.indexOf("factory.")==0){ fType = "NUMVAL"; continue; }
            if(!fields.contains(parts.get(i))){
                System.out.println("Wait, you said you need a "+typeName+"'s "+parts.get(i)+
                        " to find its "+func+"... what's that?");
                System.exit(0);
            }
            String pType = fieldTypes.get(fields.indexOf(part));
            if(fType.equals("NONE")&&
                    pType.equals("String") || pType.equals("NUMVAL"))
                fType = pType;
            else if(pType.equals("String")) fType = "String";
            else if(!pType.equals("String") && !pType.equals("NUMVAL")){
                fType = "String";
            }
        }
        
        funcTypes.set(funcs.indexOf(func), fType);
    }    

    public void addFunc(String func, ArrayList<String> parts){
        if(fields.contains(func) || funcs.contains(func)){
            System.out.println("Woops!  You said what a "+typeName+"'s "+func+" is more than once!");
            System.exit(0);
        }
        String fType = "NONE";
        for(int i = 0; i < parts.size(); i++){
            String part = parts.get(i);
            if(part.equals("+")||part.equals("-")||part.equals("*")||part.equals("/"))
                continue;
            if(part.indexOf("\"") == 0){ fType = "String"; continue; }
            if(part.indexOf("factory.")==0){ fType = "NUMVAL"; continue; }
            if(!fields.contains(parts.get(i))){
                System.out.println("Wait, you said you need a "+typeName+"'s "+parts.get(i)+
                        " to find its "+func+"... what's that?");
                System.exit(0);
            }
            String pType = fieldTypes.get(fields.indexOf(part));
            if(fType.equals("NONE")&&
                    pType.equals("String") || pType.equals("NUMVAL"))
                fType = pType;
            else if(pType.equals("String")) fType = "String";
            else if(!pType.equals("String") && !pType.equals("NUMVAL")){
                fType = "String";
            }
        }
        
        funcs.add(func);
        funcContents.add(parts);
        funcTypes.add(fType);
    }

    public void addField(String field, String type, String val){
        if(funcs.contains(field)){ System.out.println("You already said what a "+typeName+"'s "+
                                       field+" is!"); System.exit(0); }
        if(fields.contains(field)){
            int index = fields.indexOf(field);
            fieldValues.set(index,val);
            setType(field,type);
            return;
        }
        fields.add(field);
        fieldValues.add(val);
        fieldTypes.add("");
        if(type == null) setType(field, SymbolRecord.UNKNOWN_TYPE);
        setType(field,type);
    }

    public void setValue(String field, String type, String value){
        int index;
        if( (index = fields.indexOf(field)) >= 0){
            fieldValues.set(index,value);
            setType(field,type);
        }
    }

    public void setType(String field, String type){
        int index;
        if( (index = fields.indexOf(field)) >= 0){
            if(type.equals(typeName)){
                System.out.println("Hold on! You can't put a "+type+" in a "+type+"!  Never-ending "+
                    type+"s!");
                System.exit(0);
            }
            fieldTypes.set(index,type);
        }
        symrecord.setType(typeName+"."+field,type);
    }

    public boolean linksToType(String type){
        for(int i = 0; i < fields.size(); i++){
            String t = fieldTypes.get(i);
            DefinedType to = symrecord.getTypeObj(t);
            if(t.equals(type)) return true;
            if(to!=null&&to.linksToType(type)) return true;
        }
        return false;
    }

    public void setValue(String field, String value){
        int index;
        if( (index = fields.indexOf(field)) >= 0){
            fieldValues.set(index,value);
        }
    }
    
}
