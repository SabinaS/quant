import java.util.ArrayList;

public class FieldExpression implements Node{

    public static final String[] opMap = { "", "+", "-", "*", "/" };

    private Node l;
    private Node r;
    private String c;
    
    int operation;

    int typeIndex = 0;

    public FieldExpression(String identifier){ c = identifier; operation = -1; }

    public FieldExpression(Node left, Node right, int o){
        l = left; r = right; operation = o;
    }

    public ArrayList<String> getParts(){
        ArrayList<String> parts = new ArrayList<String>();
        if(l == null){ parts.add(c); return parts; }
        if(l.getClass().getName().equals("StringConstant") ||
              l.getClass().getName().equals("Quantity")){
            parts.add(l.translate()[1]);
        }
        
        ArrayList<String> lparts = ((FieldExpression) l).getParts();
        
        for(int i = 0; i < lparts.size(); i++)
            parts.add(lparts.get(i));
        
        parts.add(opMap[operation]);
        
        ArrayList<String> rparts = ((FieldExpression) r).getParts();
        
        for(int i = 0; i < rparts.size(); i++)
            parts.add(rparts.get(i));
        return parts;
    }  
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
