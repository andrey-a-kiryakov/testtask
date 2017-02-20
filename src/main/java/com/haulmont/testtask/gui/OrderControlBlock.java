package com.haulmont.testtask.gui;

/**
 *
 * @author Alex
 */
public class OrderControlBlock extends AbstractControlBlock {

    @Override
    public void addButtonAction() {
        AbstractModalWindow mw = new OrderEditWindow(null, " -> Добавить");
       this.getUI().addWindow(mw);
    }

    @Override
    public void editButtonAction() {
        
    }

    @Override
    public void delButtonAction() {
        
    }
    
}
