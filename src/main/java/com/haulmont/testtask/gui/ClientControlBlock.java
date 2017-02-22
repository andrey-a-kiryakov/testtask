package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Client;
import com.vaadin.data.Item;

/**
 *
 * @author Kiryakov Andrey
 */
public class ClientControlBlock extends AbstractControlBlock {

    public ClientControlBlock() {
        super();
        init();
    }

    @Override
    public void addButtonAction() {
       AbstractEditWindow mw = new ClientEditWindow(this, new Client(), " -> Добавить");
       mw.setMode(false);
       this.getUI().addWindow(mw);
    }

    @Override
    public void editButtonAction() {
        
            if (getTable().getValue() != null) {
                
                //Item row = table.getItem(table.getValue());
                AbstractEditWindow mw = new ClientEditWindow(this, (Client)getTable().getItem(getTable().getValue()).getItemProperty("ФИО").getValue(), " -> Редактировать");
                mw.setMode(true);
                this.getUI().addWindow(mw);                
            }
    }

    @Override
    public void delButtonAction() {
       
    }
    
    private void init() {
        getTable().addContainerProperty("ФИО", Client.class, null);
        getTable().addContainerProperty("Телефон",  String.class, null);
        
    }
    
    @Override
    public void addItemToTable (AbstractElement item) {
        
        Item row = getTable().getItem(getTable().addItem());
        row.getItemProperty("ФИО").setValue(item);
        row.getItemProperty("Телефон").setValue(((Client)item).getTel());
      
    }   
}
