/**
 * Interface for generic AST nodes.
 * (will structurally be either
 * single nodes or the root of a 
 * subtree.)
 *
 * @author Aubrey
 */

public interface Node
{
    /** Number of children held by a line containing a block. */
    public static final int CHILDREN_BLOCK_LINE = 1;

    /** Number of children held by a line with a terminator. */
    public static final int CHILDREN_TERMINAL_LINE = 2;

    /** Flag for value assignment in an AssignmentStatement. */
    public static final int ASSMNT_MODE = 1;
    /** Flag for inheritance declaration in an AssignmentStatement. */
    public static final int INHT_MODE = 2;

    /** Semantic flag for a print keyword. */
    public static final int SEM_PRINT_KWD = 1;

    /** Semantic flag for an integer constant. */
    public static final int SEM_INT_CONST = 1;
    /** Semantic flag for a rational number constant. */
    public static final int SEM_RAT_CONST = 2;

    /** Semantic flag for an imperative terminator (period). */
    public static final int IMP_TERM = 1;

    /** Semantic flag for an addition operation. */
    public static final int SEM_PLUS = 1;
    /** Semantic flag for a subtraction operation. */
    public static final int SEM_MINUS = 2;
    /** Semantic flag for a multiplication operation. */
    public static final int SEM_TIMES = 3;
    /** Semantic flag for a subtraction operation. */
    public static final int SEM_DIVIDE = 4;

    public static final int SEM_LESS_THAN = 1;
    public static final int SEM_GREATER_THAN = 2;
    public static final int SEM_EQUAL_TO = 3;
    public static final int SEM_NEQUAL_TO = 4;

    public static final int SEM_AND = 1;
    public static final int SEM_OR = 2;

    /** 
     * Connects a node to a child node
     * (structurally either a single 
     * node or a subtree).
     *
     * @param child child node to add
     */ 
    public void addChild(Node child);

    /**
     * Returns the child Nodes of the
     * given node as an array of nodes.
     */
    public Node[] getChildren();    

    /**
     * Returns a string translation of
     * either a node or the subtree for which
     * it is the root.
     *
     * Structure of returned array:
     * { TRANSLATION DIRECTIVE, STRING TRANSLATION };
     */
    public String[] translate();

    /**
     * Returns a string containing a 
     * semantic representation of the node
     * or the subtree for which it is the root.
     */
    public String getSemanticRepresentation();
}
