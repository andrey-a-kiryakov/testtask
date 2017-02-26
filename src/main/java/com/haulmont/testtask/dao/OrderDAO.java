package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Order;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kiryakov Andrey
 */
public class OrderDAO extends AbstractDAO{

    @Override
    public ArrayList getAll() {
        ArrayList resultList;
        resultList = new ArrayList();
        
        try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (Statement statement = connection.getConnection().createStatement()){
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
                    
                    while (resultSet.next()) {
                        Order order = new Order();
                      /*  client.setId(resultSet.getLong("ID"));
                        client.setMiddlename(resultSet.getString("middlename"));
                        client.setName(resultSet.getString("name"));
                        client.setSourname(resultSet.getString("sourname"));
                        client.setTel(resultSet.getString("tel"));*/
                        resultList.add(order);  
                    }
                    statement.close();
                } finally {
                    connection.closeConnection();
                }    
             }
        } catch (SQLException e) {
        }
        return resultList;
    }
    
    @Override
    public boolean update(AbstractElement element) {
       try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (PreparedStatement statement = connection.getConnection().prepareStatement(
                        "UPDATE orders SET (client_id, description, start_date, end_date, price, "
                                + "type) VALUES (?, ?, ?, ?, ?, ?) WHERE ID=" + Long.toString(element.getId()))){
                    statement.setLong(1, ((Order)element).getClientsId());
                    statement.setString(2, ((Order)element).getDescription());
                    statement.setDate(3, new Date(((Order)element).getStartDate()));
                    statement.setDate(4, new Date(((Order)element).getEndDate()));
                    statement.setFloat(5, ((Order)element).getPrice());
                    statement.setInt(6, ((Order)element).getStatus());
                    statement.executeUpdate();
                    statement.executeUpdate();
                    statement.close();
                } finally {
                    connection.closeConnection();
                }    
             }
        } catch (SQLException e) {
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
                    statement.executeUpdate("DELETE FROM orders WHERE ID=" + Long.toString(id));
                    statement.close();
                } finally {
                    connection.closeConnection();
                }    
             }
        } catch (SQLException e) {
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
                        "INSERT INTO orders (client_id, description, start_date, end_date, price, "
                                + "type) VALUES (?, ?, ?, ?, ?, ?)", new String[] {"ID"})){
                    statement.setLong(1, ((Order)element).getClientsId());
                    statement.setString(2, ((Order)element).getDescription());
                    statement.setDate(3, new Date(((Order)element).getStartDate()));
                    statement.setDate(4, new Date(((Order)element).getEndDate()));
                    statement.setFloat(5, ((Order)element).getPrice());
                    statement.setInt(6, ((Order)element).getStatus());
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
            return false;
        }
        return true;
    }   
}