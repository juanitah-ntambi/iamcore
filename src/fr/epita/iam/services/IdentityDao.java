package fr.epita.iam.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.epita.iam.datamodel.Identity;

public interface IdentityDao {
	public ResultSet read(Connection conn, Identity user) throws SQLException;
	public void save(Connection conn, Identity user) throws SQLException;
	public void update(Connection conn,Identity user) throws SQLException;
	public void delete(Connection conn, Identity user) throws SQLException;
	public ResultSet get(Connection conn, Identity user) throws SQLException;
}
