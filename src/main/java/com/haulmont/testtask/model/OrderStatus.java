package com.haulmont.testtask.model;

/**
 * Класс для отображения статуса для элементов "Заказ" в гарфическом интерфейсе
 * @author Kiryakov Andrey
 */
public class OrderStatus {
    private int status;

    public OrderStatus(int status) {
        this.status = status;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        if (status == 1){
            return "Выполнен";
        }
        if (status == 2) {
            return "Принят клиентом";
        }
        if (status == 3) {
            return "Любой";
        }
        return "Запланирован";
    }   
}