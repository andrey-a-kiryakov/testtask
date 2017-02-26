package com.haulmont.testtask.gui;

import com.haulmont.testtask.dao.ClientDAO;
import com.haulmont.testtask.dao.OrderDAO;
import com.haulmont.testtask.model.AbstractElement;
import com.haulmont.testtask.model.Order;
import com.haulmont.testtask.model.OrderStatus;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.Page;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
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
    private final TextField priceField;
    private final ComboBox statusSelect;
  
    private final RegexpValidator descriptionValidator;
    private final RegexpValidator priceValidator;
    
    public OrderEditWindow(AbstractControlBlock controlBlock, Order element, String header) {
        super(controlBlock, "Заказ " + header);
        this.order = element;
        decriptionTextArea  = new TextArea("Описание заказа");
        clientSelect = new ComboBox("Клиент");
        startDateSelect = new DateField("Дата создания заказа");
        endDateSelect = new DateField("Дата закрытия заказа");
        priceField = new TextField("Стоимость заказа (руб.)");
        statusSelect = new ComboBox("Статус заказа");
        descriptionValidator = new RegexpValidator("^.{1,1024}$", true, "Это поле должно содержать от 1 до 1024 символов");
        priceValidator = new RegexpValidator("^[0-9]{1,9}\\.[0-9]{1,2}$", true, "Формат поля: [рррр.кк]");
        
        init();
    }

    @Override
    public void okButtonAction() {
        if (descriptionValidator.isValid(decriptionTextArea.getValue()) && priceValidator.isValid(priceField.getValue())
                && !clientSelect.isEmpty() && !statusSelect.isEmpty() && !startDateSelect.isEmpty() && !endDateSelect.isEmpty()) {
            order.setDescription(decriptionTextArea.getValue());
            order.setStartDate(startDateSelect.getValue().getTime());
            order.setEndDate(endDateSelect.getValue().getTime());
            order.setPrice(Float.valueOf(priceField.getValue()));
            order.setStatus(((OrderStatus)statusSelect.getValue()).getStatus());
            order.setClientsId(((AbstractElement)clientSelect.getValue()).getId());
            OrderDAO orderDao = new OrderDAO();
            
            if (!this.isEditMode()) {
                if (orderDao.create(order)) {
                    getControlBlock().addItemToTable(order);
                    close();
                } else {
                    new Notification("ВНИМАНИЕ!", "Не удалось создать новый заказ", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
                }
            } else {
                if (orderDao.update(order)) {
                    Table table = getControlBlock().getTable();
                    
                    //table.getItem(table.getValue()).getItemProperty("ФИО").setValue(client);
                    //table.getItem(table.getValue()).getItemProperty("Телефон").setValue(client.getTel());
                    close();
                } else{
                    new Notification("ВНИМАНИЕ!", "Не удалось изменить данные заказа", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
                }
            }
        } else {
            new Notification("ВНИМАНИЕ!", "Одно или несколько полей содержат неправильные данные<br /> "
                    + "либо незаполнены", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
        }
    }
    
    @Override
    public void cancelButtonAction() {
        close();
    }
    
    private void init() {
        ClientDAO clientDAO = new ClientDAO();
        ArrayList clientsList = clientDAO.getAll();
        
        decriptionTextArea.setRequired(true);
        decriptionTextArea.setValue(order.getDescription());
        decriptionTextArea.setWidth(ELEMENTS_WIDTH);
        decriptionTextArea.addValidator(descriptionValidator);
        
        clientSelect.setWidth(ELEMENTS_WIDTH);
        clientSelect.setRequired(true);
        clientSelect.addItems(clientsList);
        for (Object client : clientsList) {
            if (((AbstractElement)client).getId() == order.getClientsId()) {
                clientSelect.setValue(client);
                break;
            }
        }
        
        startDateSelect.setWidth(ELEMENTS_WIDTH);
        startDateSelect.setValue(new Date(order.getStartDate()));
        startDateSelect.setDateFormat("dd.MM.yyyy");
               
        endDateSelect.setWidth(ELEMENTS_WIDTH);
        endDateSelect.setValue(new Date(order.getEndDate()));
        endDateSelect.setDateFormat("dd.MM.yyyy");
        
        priceField.setWidth(ELEMENTS_WIDTH);
        priceField.setRequired(true);
        priceField.setValue(Float.toString(order.getPrice()));
        priceField.addValidator(priceValidator);
        
        statusSelect.setWidth(ELEMENTS_WIDTH);
        statusSelect.setNullSelectionAllowed(false);
        ArrayList statusList = new ArrayList();
        statusList.add(new OrderStatus(0));
        statusList.add(new OrderStatus(1));
        statusList.add(new OrderStatus(2));
        statusSelect.addItems(statusList);
        statusSelect.setValue(statusList.get(order.getStatus()));
        
        getGeneralPanel().addComponents(decriptionTextArea, clientSelect, startDateSelect, 
                endDateSelect, priceField, statusSelect);
    }
    
}