package com.haulmont.testtask.model;

import java.util.Date;

/**
 * Класс представления элемента "Заказ" модели
 * @author Kiryakov Andrey
 */
public class Order extends AbstractElement{
    private String description;
    private long clientsId;
    private float price;
    private long startDate;
    private long endDate;
    
    private int status;

    public Order() {
        this.description = "";
        this.clientsId = -1;
        this.price = (float) 0.0;
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

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

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