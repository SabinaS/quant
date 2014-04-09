/**
 * Explicit class definition for the
 * semantic node of a line terminator.
 */

public class Terminator implements Node{
    /** Encoded type of the terminator. */
    private int which;

    public Terminator(int w){ which = w; }

    public void addChild(Node c) { return; }

    public Node[] getChildren(){
        Node[] children = {};
        return children;
    }

    public String[] translate(){
        // TODO
        String[] arr = {};
        return arr;
    }

    public String getSemanticRepresentation(){
        // TODO
        return "";
    }
}
