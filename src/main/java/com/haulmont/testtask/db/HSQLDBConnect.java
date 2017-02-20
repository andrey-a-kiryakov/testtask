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
            e.printStackTrace(); 
            return false;
        }  
  
        return true;
    }

 public boolean getConnection() {
  
  try {
   String path = "E:\\Java\\hsqldb\\data\\";
   String dbname = "mydb";
   String connectionString = "jdbc:hsqldb:file:"+path+dbname;
   String login = "testtask";
   String password = "testtask";
   connection = DriverManager.getConnection(connectionString, login, password);
   
  } catch (SQLException e) {
   System.out.println("Соединение не создано");
   e.printStackTrace();
   return false;
  }
  return true;
 }
 
 public void createTable() {
  try {
   Statement statement = connection.createStatement();   
   String sql = "CREATE TABLE testTable (id IDENTITY , value VARCHAR(255))";  
   statement.executeUpdate(sql);
  } catch (SQLException e) {
   
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
