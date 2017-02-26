package com.haulmont.testtask.model;

import java.util.Date;

/**
 *
 * @author striped
 */
public class Order extends AbstractElement{
    private String description;
    private long clientsId;
    private float price;
   // private Date startDate;
   // private Date endDate;
    private long startDate;
    private long endDate;
    
    private int status;

    public Order() {
        this.description = "";
        this.clientsId = -1;
        this.price = (float) 0.01;
        //this.startDate = new Date();
        //this.endDate = new Date();
        this.startDate = new Date().getTime();
        this.endDate = new Date().getTime();
        this.status = 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getClientsId() {
        return clientsId;
    }

    public void setClientsId(long clientsID) {
        this.clientsId = clientsID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
/*
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
 */   
    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
/*
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
*/
    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    @Override
    public String toString(){
        return description;
    }    
}