/**
 * Record containing the structure of a 
 * defined object type.
 */

import java.util.ArrayList;

public class DefinedType{

    public String typeName;
    public String superType;
    public SymbolRecord symrecord;

    public ArrayList<String> fields;
    public ArrayList<String> fieldValues;
    public ArrayList<String> fieldTypes;

    public DefinedType(String name, String stype, SymbolRecord rec){
        typeName = name;
        superType = stype;
        symrecord = rec;
        fields = new ArrayList<String>();
        fieldValues = new ArrayList<String>();
        fieldTypes = new ArrayList<String>();
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
    public void addField(String field, String type, String val){
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
            fieldTypes.set(index,type);
        }
        symrecord.setType(typeName+"."+field,type);
    }

    public void setValue(String field, String value){
        int index;
        if( (index = fields.indexOf(field)) >= 0){
            fieldValues.set(index,value);
        }
    }
    
}
