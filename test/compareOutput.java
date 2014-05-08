
import java.io.*;

public class compareOutput {

	public static void main(String[] args) {
		
		// output of generateTest will be piped to output.txt
		
		try {
		
			BufferedReader br1 = new BufferedReader(new FileReader("output.txt"));

			BufferedReader br2 = new BufferedReader(new FileReader("test_output.txt"));
			
			String output;
			
			String correct_output;
			
			while ( ((output = br1.readLine()) != null) && ((correct_output = br2.readLine()) != null)) {
				
				if( output.substring(output.length() - 1).equals(" ")) output = output.substring(0, output.length() - 1); 

				if( !output.equals(correct_output) )
					System.out.println(output + " - " + correct_output);
				
	//			assert output.equals(correct_output);
				
			}

			
		} catch(Exception e) {
			
			
		}
			
	}

}
