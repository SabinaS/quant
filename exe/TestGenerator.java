import java.io.*;

public class TestGenerator {

    public static void main(String[] args) {
        TestGenerator tg = new TestGenerator();
        tg.genAllTests();
        // tg.genComplexTest();
    }

    public BufferedWriter newBufferedWriter(String filename) {
        try {

          File file = new File(filename);

          if (!file.exists()) {
            file.createNewFile();
          }

          FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
          BufferedWriter bw = new BufferedWriter(fw);
          return bw;

        } catch (Exception e) {
            System.out.println("Error creating stream writers for " + filename);
            return newBufferedWriter(null);
        }
    }

    public void write(String program_file, String program_text,
                                    String output_file, String output_text) {

        try {
            BufferedWriter bw1 = this.newBufferedWriter("tests.qnt");
            BufferedWriter bw2 = this.newBufferedWriter("testResults.txt");

            bw1.write(program_text);
            bw2.write(output_text);

            bw1.close();
            bw2.close();
        } catch(Exception e) {
            // For now, do nothing.
            System.out.println("Exception occurred when writing to test program and output files");
        }
    }

    public void genAllTests() {
        this.genAsssignmentTests();
        this.genIterationTests();
        this.genConditionsTests();
        this.genRateConversionsTests();
        this.genArithmeticOpTests();
        this.genRelationalOpTests();
        this.genEqualityOpTests();
    }

    public void genAsssignmentTests() {
        String program_file = "assignmentTests";
        String output_file = "assignmentResult";
        String program_text = "";
        String result = "";

        // Check assignment keywords (is, set, to)
        for(int i = 0; i < 10; i++) {

            int ans = (int) (100*Math.random());

            program_text = "B is " + ans + ". Print B.\n";
            result = Integer.toString(ans) + "\n";
        }

        for(int i = 0; i < 10; i++) {

            int ans = (int) (100*Math.random());

            program_text += "Set B to " + ans + ". Print B.\n";
            result += Integer.toString(ans) + "\n";
        }

        this.write(program_file, program_text, output_file, result);
    }

    public void genIterationTests() {
        String program_file = "iterationTests";
        String output_file = "iterationResult";
        String program_text = "";
        String result = "";

        for(int i = 0; i < 10; i++) {

            int compare1 = (int) (50*Math.random() + 21);
            int compare2 = compare1 - ((int) (20*Math.random()));

            program_text += "B is " + compare2 + ". While B is less than " + compare1 + ", set B to B plus 1 and print B.\n";

            while(compare2 < compare1) {

                compare2++;
                result += compare2 + "\n";
            }

        }

        this.write(program_file, program_text, output_file, result);
    }

    public void genConditionsTests() {
        String program_file = "conditionsTests";
        String output_file = "conditionsResult";
        String program_text = "";
        String result = "";

        for(int i = 0; i < 10; i++) {

            int compare1 = (int) (10000*Math.random());
            int compare2 = (int) (10000*Math.random());

            program_text += "If " + compare1 + " is greater than "
                    + compare2 + ", then print \"Greater than\". Else, print \"Less than\".\n";

            result += compare1 > compare2 ? "Greater than\n" : "Less than\n";
        }

        this.write(program_file, program_text, output_file, result);
    }

    public void genRateConversionsTests() {
        String program_file = "rateConversionsTests";
        String output_file = "rateConversionsResult";
        String program_text = "";
        String result = "";

        for(int i = 0; i < 10; i++) {

            int rate = (int) (10*Math.random() + 1);
            int num1 = (int) (10*Math.random() + 1);
            int num2 = (int) (10*Math.random() + 1);

            program_text += "There are " + rate + " toes for every 1 foot. Set B to " + num1 + " toes. Print B plus " + num2 + " foot.\n";
            result += Integer.toString(num1 + num2*rate) + " toes\n";
        }

        this.write(program_file, program_text, output_file, result);
    }

    public void genArithmeticOpTests() {
        String program_file = "arithmeticOpTests";
        String output_file = "arithmeticOpResult";
        String program_text = "";
        String result = "";

        for(int i = 0; i < 10; i++) {

            int compare1 = (int) (100*Math.random());
            int compare2 = (int) (100*Math.random());

            program_text += "Print " + compare1 + " + "
                    + compare2 + ".\n";

            result += Integer.toString(compare1 + compare2) + "\n";
        }

        for(int i = 0; i < 10; i++) {

            int compare1 = (int) (100*Math.random());
            int compare2 = (int) (100*Math.random());

            program_text += "Print " + compare1 + " plus "
                    + compare2 + ".\n";

            result += Integer.toString(compare1 + compare2) + "\n";
        }

        for(int i = 0; i < 10; i++) {

            int compare1 = (int) (100*Math.random());
            int compare2 = (int) (100*Math.random());

            program_text += "Print " + compare1 + " - "
                    + compare2 + ".\n";

            result += Integer.toString(compare1 + compare2) + "\n";
        }

        for(int i = 0; i < 10; i++) {

            int compare1 = (int) (100*Math.random());
            int compare2 = (int) (100*Math.random());

            program_text += "Print " + compare1 + " minus "
                    + compare2 + ".\n";

            result += Integer.toString(compare1 + compare2) + "\n";
        }

        for(int i = 0; i < 10; i++) {

            int compare1 = (int) (100*Math.random());
            int compare2 = (int) (100*Math.random());

            program_text += "Print " + compare1 + " * "
                    + compare2 + ".\n";

            result += Integer.toString(compare1 + compare2) + "\n";
        }

        for(int i = 0; i < 10; i++) {

            int compare1 = (int) (100*Math.random());
            int compare2 = (int) (100*Math.random());

            program_text += "Print " + compare1 + " times "
                    + compare2 + ".\n";

            result += Integer.toString(compare1 + compare2) + "\n";
        }

        for(int i = 0; i < 10; i++) {

            int denom = (int) (100*Math.random() + 1);
            int num = ((int) (20*Math.random() + 1))*denom;

            program_text += "Print " + num + " over "
                    + denom + ".\n";

            result += Integer.toString(num/denom) + "\n";
        }

        this.write(program_file, program_text, output_file, result);
    }

    public void genRelationalOpTests() {
        String program_file = "relationalOpTests";
        String output_file = "relationalOpResult";
        String program_text = "";
        String result = "";

        program_text += "If 1 > 2 or 2 > 3, then print \"True\". Else, print \"False\".\n";
        result += "False\n";
        program_text += "If 1 < 2 or 2 > 3, then print \"True\". Else, print \"False\".\n";
        result += "True\n";
        program_text += "If 1 > 2 or 2 < 3, then print \"True\". Else, print \"False\".\n";
        result += "True\n";
        program_text += "If 1 > 2 or 2 < 3, then print \"True\". Else, print \"False\".\n";
        result += "True\n";
        program_text += "If 1 < 2 or 2 < 3, then print \"True\". Else, print \"False\".\n";
        result += "True\n";
        program_text += "If 1 > 2 and 2 > 3, then print \"True\". Else, print \"False\".\n";
        result += "False\n";
        program_text += "If 1 < 2 and 2 > 3, then print \"True\". Else, print \"False\".\n";
        result += "False\n";
        program_text += "If 1 > 2 and 2 < 3, then print \"True\". Else, print \"False\".\n";
        result += "False\n";
        program_text += "If 1 < 2 and 2 < 3, then print \"True\". Else, print \"False\".\n";
        result += "True\n";

        this.write(program_file, program_text, output_file, result);
    }

    public void genEqualityOpTests() {
        String program_file = "equalityOpTests";
        String output_file = "equalityOpResult";
        String program_text = "";
        String result = "";


        for(int i = 0; i < 10; i++) {

            int a = (int) (100*Math.random());
            int b = (int) (100*Math.random());

            program_text += "If " + a + " + " + b + " = " + (a + b) + ", then print \"True\". Else, print \"False\".\n";
            result += "True\n";
        }

        for(int i = 0; i < 10; i++) {

            int a = (int) (100*Math.random());
            int b = (int) (100*Math.random());

            program_text += "If " + a + " + " + b + " equals " + (a + b) + ", then print \"True\". Else, print \"False\".\n";
            result += "True\n";

        }

        for(int i = 0; i < 10; i++) {

            int a = (int) (100*Math.random());
            int b = (int) (100*Math.random());

            program_text += "If " + a + " + " + b + " is equal to " + (a + b) + ", then print \"True\". Else, print \"False\".\n";
            result += "True\n";

        }

        for(int i = 0; i < 10; i++) {

            int a = (int) (100*Math.random());
            int b = (int) (100*Math.random());

            program_text += "If " + a + " + " + b + " != " + (a + b) + ", then print \"True\". Else, print \"False\".\n";
            result += "False\n";
        }

        for(int i = 0; i < 10; i++) {

            int a = (int) (100*Math.random());
            int b = (int) (100*Math.random());

            program_text += "If " + a + " + " + b + " is not equal to " + (a + b) + ", then print \"True\". Else, print \"False\".\n";
            result += "False\n";
        }


        this.write(program_file, program_text, output_file, result);
    }

    public void genComplexTest() {
        String program_file = "complexTests";
        String output_file = "complexResult";
        String program_text = "";
        String result = "";

        program_text +=
                    "A rectangle has width, height, and area.\n"
                    + "A is a rectangle.  A’s width is 2 inches.  A’s height is 10 inches.\n"
                    + "B is a rectangle. B’s width is 4 inches. B’s height is 2 inches.\n"
                    + "A’s area is A’s width times A’s height.\n"
                    + "B’s area is B’s width times B’s height.\n"
                    + "While B’s area is less than A’s area, \n"
                    + "     set B’s height to B’s height plus 1,\n"
                    + "     set B’s area to B’s width times B’s height,\n"
                    + "     and print B’s area.\n"
                    + "Print B’s height.\n";

        result += "...";

        this.write(program_file, program_text, output_file, result);
    }


}