public class MemberRef implements Node{

    Node objIdent;
    String member;
    String type;
    String initChain;    

    boolean isDefined = true;

    public MemberRef(Node o, String m, String t){
        objIdent = o;
        member = m;
        type = t;
    }

    public String getName(){
        return ((Variable) objIdent).getName()+"."+member;
    }

    public void addChild(Node o){
        return;
    }

    public Node[] getChildren(){
        Node[] children = {objIdent};
        return children;
    }

    public String[] translate(){
        String[] translation;
            translation = new String[2];
            translation[0] = Translator.IN_PLACE;
            translation[1] = ((Variable) objIdent).getName()+"."+member;
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
