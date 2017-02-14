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
 * @author tbrou
 *
 */
public class ConsoleLauncher {
	
	private ConsoleLauncher() {}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		println("Welcome to the IAM software");
		Scanner scanner = new Scanner(System.in);
		//authentication
		if (!authenticate(scanner)){
			end(scanner);
			return;
		}
		
		//menu
		println("Please select an action :");
		println("a. create an Identity");
		println("b. modify an Identity");
		println("c. delete an Identity");
		println("d. quit");
		String choice = scanner.nextLine();
		switch (choice) {
		case "a":
			//Create
			CreateActivity.execute(scanner);
			break;
		case "b":
			//Modify
			UpdateIdentity.execute(scanner);
			break;
			
		case "c":
			//Delete
			DeleteIdentity.execute(scanner);
			break;
			
		case "d":
			//Quit
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
