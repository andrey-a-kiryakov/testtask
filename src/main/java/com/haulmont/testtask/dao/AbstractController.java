package com.haulmont.testtask.dao;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/*
 * Абстрактный класс DAO описывающий общие методы взаимодействия с БД.
 * @author Andrey Kiryakov
 * @param <E>
 * @param <K>
 */
public abstract class AbstractController<E, K> {
    
    public abstract List<E> getAll();
    public abstract E getElementById(K id);
    public abstract E update(E element);
    public abstract boolean delete(K id);
    public abstract boolean create(E element);
    
    public static void createProjectsTables() {
        try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (Statement statement = connection.getConnection().createStatement()){
                    String sql = "CREATE MEMORY TABLE clients (id BIGINT IDENTITY PRIMARY KEY, value VARCHAR(255))";  
                    statement.executeUpdate(sql);
                    statement.close();
                } finally {
                    connection.closeConnection();
                }
                    
             }
            
        } catch (SQLException e) {
            new Notification("WARNING", e.getMessage(), Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
        } 
    }
       
}