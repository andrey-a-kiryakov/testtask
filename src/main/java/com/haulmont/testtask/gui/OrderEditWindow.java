package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.Order;

/**
 *
 * @author Alex
 */
public class OrderEditWindow extends AbstractModalWindow{
    
    private Order order;
    
    public OrderEditWindow(AbstractControlBlock controlBlock, Order element, String header) {
        super(controlBlock, "Заказ " + header);
        
    }

    @Override
    public void okButtonAction() {
        
    }

    @Override
    public void cancelButtonAction() {
        close();
    }
    
}
