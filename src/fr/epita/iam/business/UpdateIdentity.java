/**
 * 
 */
package fr.epita.iam.business;

import static fr.epita.iam.util.Println.println;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;
/**
 * @author HotChocolate
 * Here we Create and Save the identity
 */
public class UpdateIdentity {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private UpdateIdentity(){
		}
	
	public static void execute(Scanner scanner){
		println("Identity Update");
				
		IdentityJDBCDAO identityJDBCDAO = new IdentityJDBCDAO();
		List<Identity> identityList = new ArrayList<>();
		try {
			identityList = identityJDBCDAO.readAllIdentities();
			} catch (Exception e1) {
				LOGGER.log(Level.SEVERE,"There is an error while connecting to Database. Try again later", e1);
			}
			for (Identity current : identityList) {
			String listToPrint = current.toString();
			println(listToPrint);
			
			println("please input the ID of the user you wish to update");
			}
		
		String uid = scanner.nextLine();
		println("please input the Display Name to Update");
		String displayName = scanner.nextLine();
		println("please input the email address To Update");
		String email = scanner.nextLine();
		println("please input the birthday Date in the format MM/dd/yyyy");
		IdentityJDBCDAO identityUpdate = new IdentityJDBCDAO();
		String birthday = identityUpdate.identityBirthday(scanner.nextLine());
		
		Identity identity = new Identity(uid,displayName, email,birthday);
				
			try {
				if (identityUpdate.update(identity)){
				println("Identity Successuflly Updated");
				}
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE,"There is an error while Updating identity. Try again later", e);
			}
		
	}
}
