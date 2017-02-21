package com.haulmont.testtask.db;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author striped
 */
public class HSQLDBConnect {
 
    Connection connection = null;
 
    public boolean loadDriver() {
        try {   
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } 
        catch (ClassNotFoundException e) {
            new Notification("WARNING", "Driver not load", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
            return false;
        }  
        new Notification("OK", "Driver load!", Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page.getCurrent());
        return true;
    }

    public boolean getConnection() {
        try {
            String path = "mypath/";
            String dbname = "testdb";
            String connectionString = "jdbc:hsqldb:file:" + path + dbname;
            String login = "SA";
            String password = "";
            connection = DriverManager.getConnection(connectionString, login, password);
            connection.getSchema();
            new Notification("OK", "Соединение установлено " + System.getenv("JAVA_HOME"), Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page.getCurrent());
        } 
        catch (SQLException e) {
            new Notification("WARNING", "Соединение не создано", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
            
            return false;
        }
        return true;
    }
 
    public void createTable() {
        try {
            Statement statement = connection.createStatement();   
            String sql = "CREATE CACHED TABLE testTable (id IDENTITY , value VARCHAR(255))";  
            statement.executeUpdate(sql);
        } 
        catch (SQLException e) {
            new Notification("WARNING", e.getMessage(), Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
        }
    }
 
 public void fillTable() {
  Statement statement;
  try {
   statement = connection.createStatement();
   String sql = "INSERT INTO testTable (value) VALUES('Вася')";
   statement.executeUpdate(sql);
   sql = "INSERT INTO testTable (value) VALUES('Петя')";
   statement.executeUpdate(sql);
   sql = "INSERT INTO testTable (value) VALUES('Саша')";
   statement.executeUpdate(sql);
   sql = "INSERT INTO testTable (value) VALUES('Катя')";
   statement.executeUpdate(sql);
   sql = "INSERT INTO testTable (value) VALUES('Света')";
   statement.executeUpdate(sql);
   
  } catch (SQLException e) {   
   e.printStackTrace();
  }     
 }
 
 public void printTable() {
  Statement statement;
  try {
   statement = connection.createStatement();
   String sql = "SELECT id, value FROM testTable";
   ResultSet resultSet = statement.executeQuery(sql);
    
   while (resultSet.next()) {
    System.out.println(resultSet.getInt(1) + " "
    + resultSet.getString(2));
   }
   
  } catch (SQLException e) {   
   e.printStackTrace();
  }     
 }

 public void closeConnection() {
  
  Statement statement;
  try {
   statement = connection.createStatement();
   String sql = "SHUTDOWN";
   statement.execute(sql);
   
  } catch (SQLException e) {
   e.printStackTrace();
  }  
 }
 
}
