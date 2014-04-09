/**
 * Explicit internal node type for the
 * line-block level (root) node.
 *
 * @author Aubrey
 */

import java.util.*;

public class LineBlock implements Node{
    /**
     * Internal structure of contained lines.
     * Queue preferred for FIFO ordering in
     * translation.
     */
    private Queue<Node> lines;

    /**
     * A LineBlock must be constructed with an
     * initial line (firstLine).
     *
     * @param firstLine first line to add
     */
    public LineBlock(Node firstLine){
        lines = new LinkedList<Node>();
        lines.add(firstLine);
    }
   
    // Add child to queue of nodes.
    public void addChild(Node child){
        lines.add(child);
    }

    // Return an array of the queue contents.
    public Node[] getChildren(){
        Object[] raw = lines.toArray();
        Node[] nodeArray = new Node[raw.length];

        for(int i = 0; i < raw.length; i++)
            nodeArray[i] = (Node) raw[i];

        return nodeArray;
    }

    /** 
     * Translates a line block (the entire program).
     * Only exception to the directive-literal format.
     * returns ordered Java blocks.  (See Translator.java
     * for ordering.)
     *
     * @author Aubrey
     */
    public String[] translate(){
        // TODO
        Node[] children = getChildren();
        String mainBlock = "";
        
        for(int i = 0; i < children.length; i++){
            Node child = children[i];
            String[] cTranslation = child.translate();
            // line-level translations will be of length 2
            if(cTranslation[0].equals(Translator.IN_PLACE))
                mainBlock = mainBlock + "\n" + cTranslation[1];
        }

        String[] translation = { mainBlock };
        return translation;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
