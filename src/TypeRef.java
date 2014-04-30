public class TypeRef implements Node{

    private String identifier;
    private SymbolRecord symrecord;
  
    public TypeRef(String i, SymbolRecord s){
        identifier = i;
        symrecord = s;
    }

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String getType(){ return identifier; }

    public String[] translate(){
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        translation[1] = "factory.new "+identifier+"(factory)";
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
