package com.haulmont.testtask.gui;

import com.haulmont.testtask.dao.ClientDAO;
import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Client;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

/**
 * Класс блока управления элементами модели "Клиент"
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
       
       mw.setEditMode(false);
       this.getUI().addWindow(mw);
    }

    @Override
    public void editButtonAction() {
        if (getTable().getValue() != null) {
            AbstractEditWindow mw = new ClientEditWindow(this, (Client)getTable().getValue(), " -> Редактировать");
            
            mw.setEditMode(true);
            this.getUI().addWindow(mw);                
        }
    }

    @Override
    public void delButtonAction() {
        if (getTable().getValue() != null) {
            Client client = (Client)getTable().getValue();
            ClientDAO clientDAO = new ClientDAO();
            
            if (clientDAO.delete(client.getId())) {
                getTable().removeItem(client);
            } else {
                new Notification("ВНИМАНИЕ!", "Не удалось удалить клиента.<br />"
                        + "Возможно у данного клиента имеются заказы.", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
            }
        }
    }
    
    private void init() {
        getTable().setWidth("30em");
        getTable().addContainerProperty("ФИО", String.class, null);
        getTable().addContainerProperty("Телефон",  String.class, null);
    }
    
    @Override
    public void addItemToTable (AbstractElement item) {
        getTable().addItem(new Object[]{item.toString(),((Client)item).getTel()}, item);
    }     
}