package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Order;
import java.util.List;

/**
 *
 * @author Kiryakov Andrey
 */
public class OrderControlBlock extends AbstractControlBlock {

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
    
    public void addItemToTable (AbstractElement item) {
        
    }
    
    public void addItemsToTable(List itemsList) {
        
    }
}
