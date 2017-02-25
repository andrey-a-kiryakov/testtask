package com.haulmont.testtask.model;

import java.util.Date;

/**
 *
 * @author striped
 */
public class Order extends AbstractElement{
    private String description;
    private long clientsID;
    private float price;
    private String startDate;
    private String endDate;
    private int status;

    public Order() {
        this.description = "";
        this.clientsID = -1;
        this.price = 0;
        this.startDate = "";
        this.endDate = "";
        this.status = 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getClientsID() {
        return clientsID;
    }

    public void setClientsID(long clientsID) {
        this.clientsID = clientsID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String toString(){
        return description;
    }    
}