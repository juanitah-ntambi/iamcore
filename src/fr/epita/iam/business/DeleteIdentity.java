/**
 * 
 */
package fr.epita.iam.business;

import static fr.epita.iam.util.Println.println;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author Hot Chocolate
 *
 */
public class DeleteIdentity {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private DeleteIdentity(){
		
	}
	public static void execute(Scanner scanner){
		println("Identity Deletion");
				
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
			}
		println("please input the ID of the Identity you wish to delete");
		String uid = scanner.nextLine();
		IdentityJDBCDAO identityDelete = new IdentityJDBCDAO();		
			try {
				if(identityDelete.delete(uid)){
				println("Identity Successuflly deleted");
				}
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE,"There is an error while deleting identity from Database.", e);
			}
		
	}
	

}
