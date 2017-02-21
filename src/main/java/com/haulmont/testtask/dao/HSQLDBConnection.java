package com.haulmont.testtask.dao;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Класс соединения с HSQLDB
 * @author Andrey Kiryakov
 */
public final class HSQLDBConnection extends AbstractConnection {
        
    private static final String DB_PATH = "jdbc:hsqldb:file:mydbs/";
    private static final String DB_NAME = "testdb";
    private static final String DB_LOGIN = "SA";
    private static final String DB_PASSWORD = "";
    
    private Connection connection;
    
    
    
    
    
    @Override
    public boolean connect () {
        try {
            connection = DriverManager.getConnection(DB_PATH + DB_NAME, DB_LOGIN, DB_PASSWORD);   
        } 
        catch (SQLException e) {
            new Notification("WARNING", "Соединение не создано", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
            
            return false;
        }
        return true;
    }
        
    @Override
    public Connection getConnection (){
        return connection;
    }
    
    @Override
    public final boolean closeConnection() {
  
        try {
            connection.createStatement().execute("SHUTDOWN");
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }  
    }
}
