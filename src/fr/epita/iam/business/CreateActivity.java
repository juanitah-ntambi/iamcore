/**
 * 
 */
package fr.epita.iam.business;

import static fr.epita.iam.util.Println.println;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;
/**
 * @author tbrou
 * Here we Create and Save the identity
 */
public class CreateActivity {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private CreateActivity(){
		
	}
	
	
	public static void execute(Scanner scanner){
		println("Identity Creation");
		println("please input the displayName");
		String displayName = scanner.nextLine();
		println("please input the email address");
		String email = scanner.nextLine();
		println("please input the birthday Date in the format MM/dd/yyyy");
		IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();
		String birthday = identityWriter.identityBirthday(scanner.nextLine());
		
		Identity identity = new Identity("",displayName, email,birthday);
				
		try {
			identityWriter.write(identity);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,"There is an error while saving idenity to Database", e);
			}
		println("creation Done");
	}
}
