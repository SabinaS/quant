public class MemberRef implements Node{

    private Node object;
    private Node member;

    public MemberRef(Node o, Node m){
        object = o;
        member = m;
    }

    public void addChild(Node o){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {object, member};
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
