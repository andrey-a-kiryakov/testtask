package com.haulmont.testtask.gui;

import com.haulmont.testtask.dao.ClientDAO;
import com.haulmont.testtask.model.Order;
import com.haulmont.testtask.model.OrderStatus;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Kiryakov Andrey
 */
public class OrderEditWindow extends AbstractEditWindow {
    private final Order order;
    private final TextArea decriptionTextArea;
    private final ComboBox clientSelect;
    private final DateField startDateSelect;
    private final DateField endDateSelect;
    private final TextField price;
    private final ComboBox statusSelect;
    
    public OrderEditWindow(AbstractControlBlock controlBlock, Order element, String header) {
        super(controlBlock, "Заказ " + header);
        this.order = element;
        decriptionTextArea  = new TextArea("Описание заказа");
        clientSelect = new ComboBox("Клиент");
        startDateSelect = new DateField("Дата создания заказа");
        endDateSelect = new DateField("Дата закрытия заказа");
        price = new TextField("Стоимость заказа (руб.)");
        statusSelect = new ComboBox("Статус заказа");
        
        init();
    }

    @Override
    public void okButtonAction() {
        
    }
    @Override
    public void cancelButtonAction() {
        close();
    }
    
    private void init() {
        decriptionTextArea.setRequired(true);
        decriptionTextArea.setValue(order.getDescription());
        decriptionTextArea.setWidth(ELEMENTS_WIDTH);
        
        clientSelect.setWidth(ELEMENTS_WIDTH);
        clientSelect.addItems(ClientDAO.getAll());
        
        startDateSelect.setWidth(ELEMENTS_WIDTH);
        startDateSelect.setValue(new Date());
        
        endDateSelect.setWidth(ELEMENTS_WIDTH);
        endDateSelect.setValue(new Date());
        
        price.setWidth(ELEMENTS_WIDTH);
        price.setValue(Float.toString(order.getPrice()));
        
        statusSelect.setWidth(ELEMENTS_WIDTH);
        ArrayList statusList = new ArrayList();
        statusList.add(new OrderStatus(0));
        statusList.add(new OrderStatus(1));
        statusList.add(new OrderStatus(2));
        statusSelect.addItems(statusList);
        
        getGeneralPanel().addComponents(decriptionTextArea, clientSelect, startDateSelect, 
                endDateSelect, price, statusSelect);
    }
    
}