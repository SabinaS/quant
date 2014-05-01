public class BlankNode implements Node{

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        String[] translation = {"",""};
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
