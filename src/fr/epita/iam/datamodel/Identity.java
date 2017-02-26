/**
 * 
 */
package fr.epita.iam.datamodel;

/**
 * Class for the Identity Definition also contains the getters and setters for the Identity data type
 * @author Hot Chocolate
 */
public class Identity {
	
	private String uid;
	private String displayName;
	private String email;
	private String birthday;
	
	/**
	 * @param uid
	 * @param displayName
	 * @param email
	 * @param birthdate 
	 */
	public Identity(String uid, String displayName, String email, String birthdate) {
		this.uid = uid;
		this.displayName = displayName;
		this.email = email;
		this.birthday = birthdate;
	}
	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the user_id to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the birthday
	 */
	public String getbirthday() {
		return birthday;
	}
	/**
	 * @param birthday
	 */
	public void setbirthday(String birthday) {
		this.birthday = birthday;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [uid=" + uid + ", displayName=" + displayName + ", email=" + email + ",birthday=" + birthday + "]";
	}
}
