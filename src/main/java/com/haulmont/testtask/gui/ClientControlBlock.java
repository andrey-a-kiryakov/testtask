package com.haulmont.testtask.gui;

import com.haulmont.testtask.dao.ClientDAO;
import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Client;
import com.vaadin.data.Item;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

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
            Item selectedItem = getTable().getItem(getTable().getValue());
            AbstractEditWindow mw = new ClientEditWindow(this, (Client)selectedItem.getItemProperty("ФИО").getValue(), " -> Редактировать");
            
            mw.setMode(true);
            this.getUI().addWindow(mw);                
        }
    }

    @Override
    public void delButtonAction() {
        if (getTable().getValue() != null) {
            Item selectedItem = getTable().getItem(getTable().getValue());
            Client client = (Client)selectedItem.getItemProperty("ФИО").getValue();
            ClientDAO clientDAO = new ClientDAO();
            
            if (clientDAO.delete(client.getId())) {
                getTable().removeItem(client);
            } else {
                new Notification("ВНИМАНИЕ!", "Не удалось удалить клиента", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
            }
        }
    }
    
    private void init() {
        getTable().setWidth("25em");
        getTable().addContainerProperty("ФИО", Client.class, null);
        getTable().addContainerProperty("Телефон",  String.class, null);
    }
    
    @Override
    public void addItemToTable (AbstractElement item) {
        getTable().addItem(new Object[]{item,((Client)item).getTel()}, item);
    }     
}