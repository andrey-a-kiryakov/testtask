package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.Order;

/**
 *
 * @author Alex
 */
public class OrderEditWindow extends AbstractModalWindow{
    
    private Order order;
    
    public OrderEditWindow(Order element, String header) {
        super("Заказ " + header);
        
    }

    @Override
    public void okButtonAction() {
        
    }

    @Override
    public void cancelButtonAction() {
        close();
    }
    
}
