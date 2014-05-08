
import java.io.*;

public class generateTest {

	static FileWriter fw1;
	static BufferedWriter bw1;
	static FileWriter fw2;
	static BufferedWriter bw2;

	public static void main(String[] args) {

		try {

			File test_program = new File("test_program.qnt");
			test_program.createNewFile();

			File test_output = new File("test_output.txt");
			test_output.createNewFile();

			fw1 = new FileWriter(test_program.getAbsoluteFile());
			bw1 = new BufferedWriter(fw1);
			fw2 = new FileWriter(test_output.getAbsoluteFile());
			bw2 = new BufferedWriter(fw2);

			String content;

			// Check assignment keywords (is, set, to)
			for(int i = 0; i < 10; i++) {

				int ans = (int) (100*Math.random());

				content = "B is " + ans + ". Print B.";

				write(content, Integer.toString(ans));

			}

			for(int i = 0; i < 10; i++) {

				int ans = (int) (100*Math.random());

				content = "Set B to " + ans + ". Print B.";

				write(content, Integer.toString(ans));
			}

			// Check iteration (while)

			for(int i = 0; i < 10; i++) {

				int compare1 = (int) (50*Math.random() + 21);
				int compare2 = compare1 - ((int) (20*Math.random()));

				content = "B is " + compare2 + ". While B is less than " + compare1 + ", set B to B plus 1 and print B.";

				bw1.write(content + "\n");

				while(compare2 < compare1) {

					compare2++;
					bw2.write(compare2 + "\n");

				}

			}

			// check conditions

			for(int i = 0; i < 10; i++) {

				int compare1 = (int) (10000*Math.random());			
				int compare2 = (int) (10000*Math.random());

				content = "If " + compare1 + " is greater than "
						+ compare2 + ", then print \"Greater than\". Else, print \"Less than\".";

				String result = compare1 > compare2 ? "Greater than" : "Less than";

				write(content, result);

			}

			// check rate conversion (there is, there are)

			for(int i = 0; i < 10; i++) {

				int rate = (int) (10*Math.random() + 1);
				int num1 = (int) (10*Math.random() + 1);
				int num2 = (int) (10*Math.random() + 1);

				content = "There are " + rate + " toes for every 1 foot. Set B to " + num1 + " toes. Print B plus " + num2 + " foot.";

				write(content, Integer.toString(num1 + num2*rate) + " toes");
			}

			// check operators (relational, logical, arithmetic)

			//arithmetic operators

			for(int i = 0; i < 10; i++) {

				int compare1 = (int) (100*Math.random());
				int compare2 = (int) (100*Math.random());

				content = "Print " + compare1 + " + " 
						+ compare2 + ".";

				write(content, Integer.toString(compare1 + compare2));
			}

			for(int i = 0; i < 10; i++) {

				int compare1 = (int) (100*Math.random());
				int compare2 = (int) (100*Math.random());

				content = "Print " + compare1 + " plus " 
						+ compare2 + ".";

				write(content, Integer.toString(compare1 + compare2));
			}

			for(int i = 0; i < 10; i++) {

				int compare1 = (int) (100*Math.random());
				int compare2 = (int) (100*Math.random());

				content = "Print " + compare1 + " - " 
						+ compare2 + ".";

				write(content, Integer.toString(compare1 - compare2));
			}

			for(int i = 0; i < 10; i++) {

				int compare1 = (int) (100*Math.random());
				int compare2 = (int) (100*Math.random());

				content = "Print " + compare1 + " minus " 
						+ compare2 + ".";

				write(content, Integer.toString(compare1 - compare2));
			}

			for(int i = 0; i < 10; i++) {

				int compare1 = (int) (100*Math.random());
				int compare2 = (int) (100*Math.random());

				content = "Print " + compare1 + "*" 
						+ compare2 + ".";

				write(content, Integer.toString(compare1*compare2));
			}

			for(int i = 0; i < 10; i++) {

				int compare1 = (int) (100*Math.random());
				int compare2 = (int) (100*Math.random());

				content = "Print " + compare1 + " times " 
						+ compare2 + ".";

				write(content, Integer.toString(compare1*compare2));
			}
/*
			for(int i = 0; i < 10; i++) {

				int compare1 = (int) (100*Math.random());
				int compare2 = (int) (100*Math.random());

				content = "Print " + compare1 + " multiplied by " 
						+ compare2 + ".";

				write(content, Integer.toString(compare1*compare2));
			}

			for(int i = 0; i < 10; i++) {

				int denom = (int) (100*Math.random() + 1);
				int num = ((int) (20*Math.random() + 1))*denom;

				content = "Print " + num + "/" 
						+ denom + ".";

				write(content, Integer.toString(num/denom));
			}
*/			
			for(int i = 0; i < 10; i++) {

				int denom = (int) (100*Math.random() + 1);
				int num = ((int) (20*Math.random() + 1))*denom;

				content = "Print " + num + " over " 
						+ denom + ".";

				write(content, Integer.toString(num/denom));
			}
/*			
			for(int i = 0; i < 10; i++) {

				int denom = (int) (100*Math.random() + 1);
				int num = ((int) (20*Math.random() + 1))*denom;

				content = "Print " + num + " divided by " 
						+ denom + ".";

				write(content, Integer.toString(num/denom));
			}
*/			
			//logical operators

			write("If (1 > 2) or (2 > 3), then print \"True\". Else, print \"False\".", "False" );
			write("If (1 < 2) or (2 > 3), then print \"True\". Else, print \"False\".", "True" );
			write("If (1 > 2) or (2 < 3), then print \"True\". Else, print \"False\".", "True" );
			write("If (1 < 2) or (2 < 3), then print \"True\". Else, print \"False\".", "True" );

			write("If (1 > 2) and (2 > 3), then print \"True\". Else, print \"False\".", "False" );
			write("If (1 < 2) and (2 > 3), then print \"True\". Else, print \"False\".", "False" );
			write("If (1 > 2) and (2 < 3), then print \"True\". Else, print \"False\".", "False" );
			write("If (1 < 2) and (2 < 3), then print \"True\". Else, print \"False\".", "True" );

			// equality operators

			for(int i = 0; i < 10; i++) {

				int a = (int) (100*Math.random());
				int b = (int) (100*Math.random());

				content = "If (" + a + " + " + b + " = " + (a + b) + "), then print \"True\". Else, print \"False\".";

				write(content, "True");
			}

			for(int i = 0; i < 10; i++) {

				int a = (int) (100*Math.random());
				int b = (int) (100*Math.random());

				content = "If (" + a + " + " + b + " equals " + (a + b) + "), then print \"True\". Else, print \"False\".";

				write(content, "True");

			}

			for(int i = 0; i < 10; i++) {

				int a = (int) (100*Math.random());
				int b = (int) (100*Math.random());

				content = "If (" + a + " + " + b + " is equal to " + (a + b) + "), then print \"True\". Else, print \"False\".";

				write(content, "True");

			}

			for(int i = 0; i < 10; i++) {

				int a = (int) (100*Math.random());
				int b = (int) (100*Math.random());

				content = "If (" + a + " + " + b + " != " + (a + b) + "), then print \"True\". Else, print \"False\".";

				write(content, "False");

			}
/*
			for(int i = 0; i < 10; i++) {

				int a = (int) (100*Math.random());
				int b = (int) (100*Math.random());

				content = "If (" + a + " + " + b + " does not equal " + (a + b) + "), then print \"True\". Else, print \"False\".";

				write(content, "False");

			}
*/
			for(int i = 0; i < 10; i++) {

				int a = (int) (100*Math.random());
				int b = (int) (100*Math.random());

				content = "If (" + a + " + " + b + " is not equal to " + (a + b) + "), then print \"True\". Else, print \"False\".";

				write(content, "False");

			}


			bw1.close();
			bw2.close();

		} catch(Exception e) {


		}

	}

	public static void write(String program, String output) {

		try {

			bw1.write(program + "\n");

			bw2.write(output + "\n");

		} catch(Exception e) {

		}

	}

}
