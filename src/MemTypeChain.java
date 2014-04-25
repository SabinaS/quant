import java.util.LinkedList;
/**
 * Explicit type definition for the semantic node
 * representing a chain of member types
 *
 * @author Ashley
 */
public class MemTypeChain implements Node{
    
    private LinkedList<Node> memTypes;
    
    public MemTypeChain(Node memType)
    {
    	  memTypes = new LinkedList<Node>();
    	  memTypes.add(memType);
    }
    
    public void addChild(Node n){
        memTypes.add(n);
    }

    public Node[] getChildren(){
        Node[] children = new Node[mems.size()];
        for(int i = 0; i< memTypes.size(); i++)
        {
        	children[i] = memTypes.get(i);
        }
        return children;
    }

    public String[] translate(){
        String[] translation = new String[2];
        translation[0] = Translator.IN_PLACE;
        //herp derp for loop- might have to change
        //array len to 1+typeMems.size()
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
