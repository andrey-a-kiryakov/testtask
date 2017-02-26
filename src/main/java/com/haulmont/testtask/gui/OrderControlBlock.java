package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Client;
import com.haulmont.testtask.model.Order;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kiryakov Andrey
 */
public class OrderControlBlock extends AbstractControlBlock {

    public OrderControlBlock() {
        init();
    }

    @Override
    public void addButtonAction() {
        AbstractEditWindow mw = new OrderEditWindow(this, new Order(), " -> Добавить");
        this.getUI().addWindow(mw);
    }

    @Override
    public void editButtonAction() {
        
    }

    @Override
    public void delButtonAction() {
        
    }
    
    private void init() {
        getTable().setWidth("45em");
        getTable().addContainerProperty("Клиент", Client.class, null);
        getTable().addContainerProperty("Описание",  String.class, null);
        getTable().addContainerProperty("Дата создания",  Date.class, null);
        getTable().addContainerProperty("Дата окончания",  Date.class, null);
        getTable().addContainerProperty("Стоимость",  Float.class, null);
        getTable().addContainerProperty("Статус",  Integer.class, null);
    }
    
    
    @Override
    public void addItemToTable (AbstractElement item) {
        
    }
}
