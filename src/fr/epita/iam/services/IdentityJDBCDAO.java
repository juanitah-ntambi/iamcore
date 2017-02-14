/**
 * 
 */
package fr.epita.iam.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DaoInitializationException;

/**
 * @author tbrou
 *
 */
public class IdentityJDBCDAO {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Connection currentConnection;
	public IdentityJDBCDAO() {
			
		try {
			getConnection();
		} catch (SQLException e) {
			DaoInitializationException die = new DaoInitializationException();
			die.initCause(e);
			throw die;
		}
	}

	/**
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	private Connection getConnection() throws SQLException {
		

		String connectionString="";
		String user="";
		String pass="";
		File file = new File("configurations.properties");
		try (Scanner scanner = new Scanner(file)){
		
		while (scanner.hasNext()) {
			connectionString = scanner.nextLine();
			user = scanner.nextLine();
			pass = scanner.nextLine();
		 } 
		} catch(Exception e){
			LOGGER.log(Level.SEVERE,"File Not Found", e);
		}
		
		try {
			this.currentConnection.getSchema();
		} catch (Exception e) {
			LOGGER.log(Level.INFO,"New connection created", e);
					this.currentConnection = DriverManager.getConnection(connectionString, user, pass);
		}
		return this.currentConnection;
	}

	private void releaseResources() {
		try {
			this.currentConnection.close();
		} catch (Exception eResources) {
			LOGGER.log(Level.SEVERE,"Error encountered while Releasing Resources", eResources);
		}
	}

	/**
	 * Read all the identities from the database
	 * @return
	 * @throws SQLException
	 */
	public List<Identity> readAllIdentities() throws SQLException {
		List<Identity> identities = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			int uid = rs.getInt("IDENTITY_ID");
			String displayName = rs.getString("IDENTITY_DISPLAYNAME");
			String email = rs.getString("IDENTITY_EMAIL");
			String birthday = rs.getString("IDENTITY_BIRTHDATE");
			Identity identity = new Identity(String.valueOf(uid), displayName, email,String.valueOf(birthday));
			identities.add(identity);
		}
		statement.close();
		return identities;
		
	}

	public String identityBirthday(String birthdate){
		
		String newDateString= "";
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
	    Date birthday;
	    try {
	        birthday = df.parse(birthdate);
	        newDateString = df.format(birthday);	        
	    } catch (ParseException edate) {
	        LOGGER.log(Level.SEVERE,"Error encountered with parsing Date", edate);
	    }
	    return newDateString;
	}
	
	/**
	 * write an identity in the database
	 * @param identity
	 * @throws SQLException
	 */
	public void write(Identity identity) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			Connection witeConnection = getConnection();
		String sqlInstruction = "INSERT INTO IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_BIRTHDATE) VALUES(?,?,?)";
		pstmt = witeConnection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		pstmt.setString(3, identity.getbirthday());
		pstmt.execute();
		pstmt.close();
	   } catch (Exception e) {
		   LOGGER.log(Level.SEVERE,"Error encountered while saving Identity", e);
			      }finally {
			if (pstmt !=null)
			pstmt.close();
		}
	
	}
	
	public Boolean update(Identity identity) throws SQLException{
		PreparedStatement pstmt = null;
		Boolean rs = false;
		try{	
			Connection updateConnection = getConnection();
		String sqlInstruction = "UPDATE IDENTITIES SET IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL = ? , IDENTITY_BIRTHDATE = ? where IDENTITY_ID = ?";
		pstmt = updateConnection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		pstmt.setString(3, identity.getbirthday());
		pstmt.setInt(4, Integer.parseInt(identity.getUid()));
		rs = 0 == pstmt.executeUpdate();
		
			} catch(Exception e2){
				 LOGGER.log(Level.SEVERE,"Error encountered while updating Identity", e2);
			} finally {
			if (pstmt !=null)
			pstmt.close();
		}
		return rs;
	}
	
	public Boolean delete(String uid) throws SQLException{
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Boolean delrs = false;
			try{	
			Connection deleteConnection = getConnection();
			pstmt2 = deleteConnection.prepareStatement("select * from IDENTITIES where IDENTITY_ID =?");
			pstmt2.setInt(1,Integer.parseInt(uid));
			ResultSet rs = pstmt2.executeQuery();

			while (rs.next()) {
				String sqlInstruction = "DELETE FROM IDENTITIES where IDENTITY_ID = ?";
				pstmt = deleteConnection.prepareStatement(sqlInstruction);
				pstmt.setInt(1,Integer.parseInt(uid));
				delrs = 0 == pstmt.executeUpdate();
			}
				} catch(Exception e3){
					LOGGER.log(Level.SEVERE,"Error encountered while deleting Identity", e3);
		} finally {

			if (pstmt !=null)
				pstmt.close();
			if (pstmt2!=null)
				pstmt2.close();
			releaseResources();
			
		}
			return delrs;
	}
}
