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
    private static final String DB_URL = "jdbc:hsqldb:file:mydbs/testdb";
    private static final String DB_LOGIN = "SA";
    private static final String DB_PASSWORD = "";
    
    private Connection connection;
    
    @Override
    public final boolean connect () {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);   
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
        
    @Override
    public final Connection getConnection (){
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
