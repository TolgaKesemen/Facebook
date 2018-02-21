

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Take files (users.txt, commands.txt) as arguments, read them 
 * line by line, and splitting each line with respect to 'tab' character
 * then calling appropriate method in UserCollcetion class(Write).
 * @author Onur Tolga KESEMEN
 * 
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		try {
			FileWriter writer = new FileWriter("output.txt");
			BufferedWriter buffer = new BufferedWriter(writer);
			UserCollection r = null;
			Scanner scanner = new Scanner(new File(args[0]));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				String[] words = line.split("\t");
				r = new UserCollection(words);
				}
			scanner = new Scanner(new File(args[1]));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				buffer.write("-----------------------\n");
				buffer.write("Command: " + line);
				buffer.write("\n");
				String[] words = line.split("\t");
				r.Write(words,buffer);
				buffer.write("\n");
			}
			scanner.close();
			buffer.close();
			} catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
			}
	}

}
