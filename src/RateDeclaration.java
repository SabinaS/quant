public class RateDeclaration implements Node {

    private Node number1;
    private Node unit1;
    private Node number2;
    private Node unit2;

    /**
    *
    * Constructs a... based on the ratio of the first number-unit pair
    * and the second number-unit pair
    *
    * @param n1 the first number
    * @param u1 the first number's unit
    * @param n1 the second number
    * @param u2 the second number's unit
    *
    */
    public RateDeclaration(Node n1, Node u1, Node n2, Node u2)
    {
    	number1 = n1;
    	unit1 = u1;
    	number2 = n2;
    	unit2 = u2;
    }

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
        translation[1] = "factory.registerRate("
                         + unit1.translate()[1]
                         + ","
                         + unit2.translate()[1]
                         + ","
                         + (new Double(number1.translate()[1]) / new Double(number2.translate()[1]))
                         + ")";
        return translation;
    }

    public String getSemanticRepresentation(){
        return "";
    }

}
