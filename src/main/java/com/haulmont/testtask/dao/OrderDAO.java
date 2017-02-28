package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Order;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DAO класс для элементов "Заказ"
 * @author Kiryakov Andrey
 */
public class OrderDAO extends AbstractDAO{

    @Override
    public ArrayList getAll() {
        ArrayList resultList = new ArrayList();
        
        try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (Statement statement = connection.getConnection().createStatement()){
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
                    while (resultSet.next()) {
                       resultList.add(resultSetToOrder(resultSet));  
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
    public boolean update(AbstractElement element) {
       try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (PreparedStatement statement = connection.getConnection().prepareStatement(
                        "UPDATE orders SET (client_id, description, start_date, end_date, price, "
                                + "type) = (?, ?, ?, ?, ?, ?) WHERE ID=" + Long.toString(element.getId()))){
                    statement.setLong(1, ((Order)element).getClientsId());
                    statement.setString(2, ((Order)element).getDescription());
                    statement.setDate(3, new Date(((Order)element).getStartDate()));
                    statement.setDate(4, new Date(((Order)element).getEndDate()));
                    statement.setFloat(5, ((Order)element).getPrice());
                    statement.setInt(6, ((Order)element).getStatus());
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
                    statement.executeUpdate("DELETE FROM orders WHERE ID=" + Long.toString(id));
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
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }   

    /**
    * Выполняет выбор из БД заказов с условиями по клиенту, типу, описанию
    * @param clientSubString - подстрока поиска по ФИО клиента
    * @param status - статус заказа
    * @param descriptionSubString - подстрока поиска описания заказа
    * @return ArrayList заказов, удовлетворяющих условиям
    */
    public ArrayList getElementsAfterFilter(String clientSubString, int status, String descriptionSubString) {
       ArrayList resultList = new ArrayList();
        
        try {
            AbstractConnection connection = new HSQLDBConnection();
            if (connection.connect()) {
                try (Statement statement = connection.getConnection().createStatement()){
                    String sql = "SELECT * FROM orders WHERE LOCATE('" + descriptionSubString + "', description) > 0" 
                            + "AND type = " + String.valueOf(status);
                    if (status > 2) {
                        sql = "SELECT * FROM orders WHERE LOCATE('" + descriptionSubString + "', description) > 0";
                    }
                    ResultSet resultSet = statement.executeQuery(sql);
                    
                    while (resultSet.next()) {
                       ResultSet resultSet1 = statement.executeQuery("SELECT * FROM clients WHERE id = " + resultSet.getLong("client_id") + 
                               "AND (LOCATE('" + clientSubString + "', sourname) > 0 OR LOCATE('" + clientSubString + "', name) > 0 OR LOCATE('" + clientSubString + "', middlename) > 0)" ); 
                       
                       while (resultSet1.next()) {
                            resultList.add(resultSetToOrder(resultSet));  
                       }
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
    
    private Order resultSetToOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("ID"));
        order.setClientsId(resultSet.getLong("client_id"));
        order.setDescription(resultSet.getString("description"));
        order.setStartDate(resultSet.getDate("start_date").getTime());
        order.setEndDate(resultSet.getDate("end_date").getTime());
        order.setPrice(resultSet.getFloat("price"));
        order.setStatus(resultSet.getInt("type"));
        return order;
    }

    @Override
    public AbstractElement getElementById(long elementId) {
        return null;
    }
}