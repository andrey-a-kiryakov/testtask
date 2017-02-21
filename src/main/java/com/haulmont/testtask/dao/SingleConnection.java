package com.haulmont.testtask.dao;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Класс единсвенного соединения с БД. Реализует паттерн Singleton.
 * @author Andrey Kiryakov
 */
public final class SingleConnection {
    
    private static final String DRIVER_NAME = "org.hsqldb.jdbc.JDBCDriver";
        
    private static final String DB_PATH = "jdbc:hsqldb:file:mydbs/";
    private static final String DB_NAME = "testdb";
    private static final String DB_LOGIN = "SA";
    private static final String DB_PASSWORD = "";
    
    private static SingleConnection instanseof;
    
    private Connection connection;
    
    private SingleConnection() {
         if (loadDriver()) {
             connect();
         }
    }
    
    public static final synchronized SingleConnection getInstance () {
        if (instanseof == null) {
            instanseof = new SingleConnection ();
        }
        return instanseof;
    }
    
    private boolean loadDriver() {
        try {   
            Class.forName(DRIVER_NAME);
        } 
        catch (ClassNotFoundException e) {
            new Notification("WARNING", "Driver not load", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
            return false;
        }  
        return true;
    }
    
    private boolean connect () {
        try {
            connection = DriverManager.getConnection(DB_PATH + DB_NAME, DB_LOGIN, DB_PASSWORD);   
        } 
        catch (SQLException e) {
            new Notification("WARNING", "Соединение не создано", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
            
            return false;
        }
        return true;
    }
    
    public final Connection getConnection (){
        return connection;
    }
    
    public final boolean closeConnection() {
  
    Statement statement;
        try {
            connection.createStatement().execute("SHUTDOWN");
            return true;
        } catch (SQLException e) {
            return false;
        }  
    }
}
