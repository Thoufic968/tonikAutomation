package com.tonik.utility;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.sql.*;

public class DB_Utilites {
    public static Connection connection = null;
    public static Session session = null;
    public static String sshUser = Utilities.propertyFileReader("db_ssh_username","DataBase");
    public static String sshHost = Utilities.propertyFileReader("db_sshHost","DataBase");
    public static int sshPort = Integer.parseInt(Utilities.propertyFileReader("db_sshPort","DataBase"));
    public static String sshPrivateKey = Utilities.propertyFileReader("db_ssh_filekeypath","DataBase");
    public static String dbUser = Utilities.propertyFileReader("db_username","DataBase");
    public static String dbPassword = Utilities.propertyFileReader("db_password","DataBase");
    public static String dbHost = Utilities.propertyFileReader("db_remotehost","DataBase");
    public static int dbPort = Integer.parseInt(Utilities.propertyFileReader("db_remoteport","DataBase")); // or your RDS port
    public  static int localPort = Integer.parseInt(Utilities.propertyFileReader("db_localPort","DataBase")); // Local port for SSH tunnel
    /**
     * Common method to open SSH
     * @param dbname
     * @return
     * @throws JSchException
     * @throws SQLException
     * @throws JSchException
     */
    public static Connection openSSH(String dbname) throws JSchException, SQLException, JSchException {
        // SSH tunnel configuration
        JSch jsch = new JSch();
        jsch.addIdentity(sshPrivateKey);
        session = jsch.getSession(sshUser, sshHost, sshPort);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        session.setPortForwardingL(localPort, dbHost, dbPort);
        String dbUrl = "jdbc:mysql://localhost:" + localPort + "/"+dbname;
        connection = DriverManager.getConnection(dbUrl, dbUser,dbPassword);
        return connection;
    }

    /**
     * Common method to close connection
     */
    public static void closeConnection() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
    /**
     * Common method to initiate connection
     * @param dataBaseName
     * @throws SQLException
     * @throws JSchException
     */
    public static void initiateConnections(String dataBaseName) throws SQLException, JSchException {
            connection = openSSH("customer");
    }
    /**
     * Common method to select Query
     * @param dataBaseName
     * @param query
     * @return
     * @throws SQLException
     * @throws JSchException
     */
    public static String selectQuery(String dataBaseName,String query) throws SQLException, JSchException {
        ResultSet resultSet = null;
        String s = "";
        try
        {
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
              s= resultSet.getString(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return s;
    }
    /**
     * Common method to update Query
     * @param query
     * @return
     */
    public static String updateQuery(String query)
    {
        try
        {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return query;
    }
    /**
     * Common method to delete Query
     * @param query
     * @return
     */
    public static ResultSet deleteQuery(String query)
    {
        ResultSet resultSet = null;
        try
        {
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static int insertQuery(String query) {
        int rowsInserted = 0;
        try
        {
            Statement stmt = connection.createStatement();
            rowsInserted = stmt.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rowsInserted;
    }
    public static void closeConnections()
    {
        try
        {
            if (connection != null && !connection.isClosed())
            {
                connection.close();
            }
            if (session != null && session.isConnected())
            {
                session.disconnect();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}