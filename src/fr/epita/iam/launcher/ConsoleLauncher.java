/**
 * 
 */
package fr.epita.iam.launcher;

import java.util.Scanner;

import fr.epita.iam.business.CreateActivity;
import fr.epita.iam.business.DeleteIdentity;
import fr.epita.iam.business.UpdateIdentity;
import static fr.epita.iam.util.Println.*;


/**
 * Class which has the main method to Launch the application
 * @author Hot Chocolate
 */
public class ConsoleLauncher {
	
	private ConsoleLauncher() {}

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		println("Welcome to the IAM software");
		Scanner scanner = new Scanner(System.in);
		//authentication handled here to control access to the application
		if (!authenticate(scanner)){
			end(scanner);
			return;
		}
		
		//menu from which user select item to use after authentication
		println("Please select an action :");
		println("a. create an Identity");
		println("b. modify an Identity");
		println("c. delete an Identity");
		println("d. quit");
		String choice = scanner.nextLine();
		switch (choice) {
		case "a":
			//Method call to Create identity
			CreateActivity.execute(scanner);
			break;
		case "b":
			//method call to Modify Identity
			UpdateIdentity.execute(scanner);
			break;
			
		case "c":
			//method call to Delete identity
			DeleteIdentity.execute(scanner);
			break;
			
		case "d":
			//Quit the application
			end(scanner);
			return;
			
		default:
			println("Your choice is not recognized");
			break;
		}
			end(scanner);
	}

	/**
	 * @param scanner
	 */
	private static void end(Scanner scanner) {
		println("Thanks for using this application, good bye!");
		scanner.close();
	}

	/**
	 * Method to authenticate the user. Reads the user name and password from a file - dbconfig.properties
	 * @param scanner
	 */
	private static boolean authenticate(Scanner scanner) {
		println("Please type your login : ");
		String login = scanner.nextLine();
		
		println("Please type your password : ");
		String password = scanner.nextLine();
		
		if ("jntambi".equals(login) && "test".equals(password)){
			println("Athentication was successful");
			return true;
		}else{
			println("Athentication failed");
			return false;
		}
	}

}
