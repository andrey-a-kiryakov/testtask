package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DAO класс для элементов "Клиент"
 * @author Kiryakov Andrey
 */
public class ClientDAO extends AbstractDAO{

    @Override
    public ArrayList getAll() {
        ArrayList resultList;
        resultList = new ArrayList();
        
        try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (Statement statement = connection.getConnection().createStatement()){
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
                    
                    while (resultSet.next()) {
                        resultList.add(resultSetToClient(resultSet));  
                    }
                    statement.close();
                } finally {
                    connection.closeConnection();
                }    
             }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }
    
    @Override
    public AbstractElement getElementById(long elementId) {
        try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (Statement statement = connection.getConnection().createStatement()){
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE ID=" + Long.toString(elementId) );
                    
                    while (resultSet.next()) {
                         return resultSetToClient(resultSet);  
                    }
                    statement.close();
                } finally {
                    connection.closeConnection();
                }    
             }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @Override
    public boolean update(AbstractElement element) {
       try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (PreparedStatement statement = connection.getConnection().prepareStatement(
                        "UPDATE clients SET (sourname, name, middlename, tel) = (?, ?, ?, ?) WHERE ID=" + Long.toString(element.getId()))){
                    statement.setString(1, ((Client)element).getSourname());
                    statement.setString(2, ((Client)element).getName());
                    statement.setString(3, ((Client)element).getMiddlename());
                    statement.setString(4, ((Client)element).getTel());
                    statement.executeUpdate();
                    statement.close();
                } finally {
                    connection.closeConnection();
                }    
             }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(long id) {
        try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (Statement statement = connection.getConnection().createStatement()){
                    statement.executeUpdate("DELETE FROM clients WHERE ID=" + Long.toString(id));
                    statement.close();
                } finally {
                    connection.closeConnection();
                }    
             }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    @Override
    public boolean create(AbstractElement element) {
        try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (PreparedStatement statement = connection.getConnection().prepareStatement(
                        "INSERT INTO clients (sourname, name, middlename, tel) VALUES (?, ?, ?, ?)", new String[] {"ID"})){
                    statement.setString(1, ((Client)element).getSourname());
                    statement.setString(2, ((Client)element).getName());
                    statement.setString(3, ((Client)element).getMiddlename());
                    statement.setString(4, ((Client)element).getTel());
                    statement.executeUpdate();
                                        
                    ResultSet result = statement.getGeneratedKeys();
                    if(result.next()) {
                        element.setId(result.getLong("ID"));
                    }
                    statement.close();
                } finally {
                    connection.closeConnection();
                }    
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    private Client resultSetToClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("ID"));
        client.setMiddlename(resultSet.getString("middlename"));
        client.setName(resultSet.getString("name"));
        client.setSourname(resultSet.getString("sourname"));
        client.setTel(resultSet.getString("tel"));
        return client;
    }
}