
import java.io.*;

public class TestChecker {

	public static void main(String[] args) {

		// Should specify results file and output file as command-line
		// arguments
		if (args.length != 2) {
			System.out.println("No test results and/or output file specified.");
			System.exit(0);
		}

		try {

			BufferedReader br1 = new BufferedReader(new FileReader(args[0]));
			BufferedReader br2 = new BufferedReader(new FileReader(args[1]));

			String output;
			String correct_output;

			while ( ((output = br1.readLine()) != null) && ((correct_output = br2.readLine()) != null)) {

				if( output.substring(output.length() - 1).equals(" ")) output = output.substring(0, output.length() - 1);

				if( !output.equals(correct_output) )
					System.out.println(output + " - " + correct_output);
			}

		} catch(Exception e) {


		}

	}

}
