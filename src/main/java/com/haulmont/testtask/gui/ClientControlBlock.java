package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Client;
import com.vaadin.data.Item;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

/**
 *
 * @author Alex
 */
public class ClientControlBlock extends AbstractControlBlock {

    public ClientControlBlock() {
        super();
        init();
    }

    @Override
    public void addButtonAction() {
       AbstractModalWindow mw = new ClientEditWindow(this, new Client(), " -> Добавить");
       mw.setEditMode(false);
       this.getUI().addWindow(mw);
    }

    @Override
    public void editButtonAction() {
        
            if (table.getValue() != null) {
                
                //Item row = table.getItem(table.getValue());
                AbstractModalWindow mw = new ClientEditWindow(this, (Client)table.getItem(table.getValue()).getItemProperty("ФИО").getValue(), " -> Редактировать");
                mw.setEditMode(true);
                this.getUI().addWindow(mw);                
            }
    }

    @Override
    public void delButtonAction() {
       
    }
    
    private void init() {
        table.addContainerProperty("ФИО", Client.class, null);
        table.addContainerProperty("Телефон",  String.class, null);
        
    }
    
    @Override
    public void addItemToTable (AbstractElement item) {
        
        Item row = table.getItem(table.addItem());
        row.getItemProperty("ФИО").setValue(item);
        row.getItemProperty("Телефон").setValue(((Client)item).getTel());
      
    }   
}
