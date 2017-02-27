package com.haulmont.testtask.gui;

import com.haulmont.testtask.dao.ClientDAO;
import com.haulmont.testtask.dao.OrderDAO;
import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Order;
import com.haulmont.testtask.model.OrderStatus;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Kiryakov Andrey
 */
public class OrderControlBlock extends AbstractControlBlock {
    private final TextField clientFilter;
    private final ComboBox statusSelectFilter;
    private final TextField descriptionFilter;
    private final Button okFilterButton;
    
    public OrderControlBlock() {
        
        clientFilter = new TextField("Клиент");
        statusSelectFilter = new ComboBox("Статус заказа");
        descriptionFilter = new TextField("Описание");
        okFilterButton = new Button("Применить фильтр");
        
        init();
    }

    @Override
    public void addButtonAction() {
        AbstractEditWindow mw = new OrderEditWindow(this, new Order(), " -> Добавить");
        this.getUI().addWindow(mw);
    }

    @Override
    public void editButtonAction() {
        if (getTable().getValue() != null) {
            AbstractEditWindow mw = new OrderEditWindow(this, (Order)getTable().getValue(), " -> Редактировать");
            
            mw.setMode(true);
            this.getUI().addWindow(mw);                
        }
    }

    @Override
    public void delButtonAction() {
        if (getTable().getValue() != null) {
            Order order = (Order)getTable().getValue();
            OrderDAO orderDAO = new OrderDAO();
            
            if (orderDAO.delete(order.getId())) {
                getTable().removeItem(order);
            } else {
                new Notification("ВНИМАНИЕ!", "Не удалось удалить заказ", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
            }
        }
    }
    
    private void init() {
        getTable().setWidth("65em");
        getTable().addContainerProperty("Клиент", String.class, null);
        getTable().addContainerProperty("Описание",  String.class, null);
        getTable().addContainerProperty("Дата создания",  Date.class, null);
        getTable().addContainerProperty("Дата окончания",  Date.class, null);
        getTable().addContainerProperty("Стоимость",  Float.class, null);
        getTable().addContainerProperty("Статус",  String.class, null);
        
        //Панель фильтра с горизонтальным выравниванием
        final HorizontalLayout filterLayout = new HorizontalLayout();
        filterLayout.setWidth("100%");
        filterLayout.setSpacing(true);
        
        statusSelectFilter.setNullSelectionAllowed(false);
        ArrayList statusList = new ArrayList();
        statusList.add(new OrderStatus(3));
        statusList.add(new OrderStatus(0));
        statusList.add(new OrderStatus(1));
        statusList.add(new OrderStatus(2));
        statusSelectFilter.addItems(statusList);
        statusSelectFilter.setValue(statusList.get(0));
        statusSelectFilter.setWidth("13em");
        
        clientFilter.setValue("");
        clientFilter.setWidth("15em");
        
        descriptionFilter.setValue("");
        descriptionFilter.setWidth("32em");
        
        okFilterButton.addClickListener(event -> {
            OrderDAO orderDAO = new OrderDAO();
           
            getTable().removeAllItems();
            addItemsToTable(orderDAO.getElementsAfterFilter(clientFilter.getValue(), ((OrderStatus)statusSelectFilter.getValue()).getStatus() , descriptionFilter.getValue()));
        });
        
        filterLayout.addComponents(clientFilter, statusSelectFilter, descriptionFilter);
        
        getGeneralPanel().addComponents(filterLayout, okFilterButton);
    }
    
    @Override
    public void addItemToTable (AbstractElement item) {
        ClientDAO clientDAO = new ClientDAO();
        AbstractElement client = clientDAO.getElementById(((Order)item).getClientsId());
        
        if (client != null) {
            getTable().addItem(new Object[]{
                client.toString(),
                ((Order)item).getDescription(),
                new Date(((Order)item).getStartDate()),
                new Date(((Order)item).getEndDate()),
                ((Order)item).getPrice(),
                new OrderStatus(((Order)item).getStatus()).toString()}, 
                    item);
        } else {
            new Notification("ВНИМАНИЕ!", "Не удалось найти клиента", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
        }
    }
}
