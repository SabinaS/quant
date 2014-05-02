public class ComplexIdentifier implements Node{

    String id = "";
    
    public String natural(){ return id.replace("_"," "); }
    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        String[] translation = {""};
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
