public class Collection implements Node{

    String type;
    int ct;

    public Collection(String t, int c){ type = t; ct = c; }

    public void addChild(Node n){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        translation[1] = "factory.new "+type+"__COL(factory, "+ct+")";
        return translation;
    }

    public String getSemanticRepresentation(){
        return type+"__COL";
    }

}
