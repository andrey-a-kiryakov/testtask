package com.haulmont.testtask.gui;

import com.haulmont.testtask.dao.ClientDAO;
import com.haulmont.testtask.model.Client;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

/**
 *
 * @author Kiryakov Andrey
 */
public class ClientEditWindow extends AbstractEditWindow{
    private final Client client;
    
    private final TextField  sournameTextField;
    private final TextField  nameTextField;
    private final TextField  middlenameTextField;
    private final TextField  telTextField;
    
    private final RegexpValidator namesValidator;
    private final RegexpValidator telValidator;
    
    
    public ClientEditWindow(AbstractControlBlock controlBlock, Client element, String header) {
        super(controlBlock, "Клиент " + header);
        
        client = element;
        
        sournameTextField = new TextField("Фамилия");
        nameTextField = new TextField("Имя");
        middlenameTextField = new TextField("Отчество");
        telTextField = new TextField("Телефон");
        
        namesValidator = new RegexpValidator("^[А-Яа-яЁёЙй\\-]{1,32}$", true, "Это поле должно содержать от 1 до 32 символов русcкого алфавита и -");
        telValidator = new RegexpValidator("^[0-9]{7,18}$", true, "Это поле должно содержать от 7 до 18 цифр");
        
        init ();
        
    }
    
    @Override
    public void okButtonAction() {
        if (namesValidator.isValid(sournameTextField.getValue()) && namesValidator.isValid(nameTextField.getValue()) 
                && namesValidator.isValid(middlenameTextField.getValue()) && telValidator.isValid(telTextField.getValue())
                    && !sournameTextField.isEmpty() && !nameTextField.isEmpty() && !middlenameTextField.isEmpty() && !telTextField.isEmpty()) {
            client.setSourname(sournameTextField.getValue());
            client.setMiddlename(middlenameTextField.getValue());
            client.setName(nameTextField.getValue());
            client.setTel(telTextField.getValue());
            
            if (!this.isEditMode()) {
                if (ClientDAO.create(client)) {
                    getControlBlock().addItemToTable(client);
                }
                close();
            }
            else {
                getControlBlock().getTable().getItem(getControlBlock().getTable().getValue()).getItemProperty("ФИО").setValue(client);
                getControlBlock().getTable().getItem(getControlBlock().getTable().getValue()).getItemProperty("Телефон").setValue(client.getTel());
                close();
            }
        } 
        else {
            new Notification("ВНИМАНИЕ!", "Одно или несколько полей содержат неправильные данные<br /> "
                    + "либо незаполнены", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
        }
    }
    
    @Override
    public void cancelButtonAction() {
       close();
    }
    
    private void init() {
        sournameTextField.setRequired(true);
        sournameTextField.addValidator(namesValidator);
        sournameTextField.setValue(client.getSourname());
        
        nameTextField.setRequired(true);
        nameTextField.addValidator(namesValidator);
        nameTextField.setValue(client.getName());
                
        middlenameTextField.setRequired(true);
        middlenameTextField.addValidator(namesValidator);
        middlenameTextField.setValue(client.getMiddlename());
                
        telTextField.setRequired(true);
        telTextField.addValidator(telValidator);
        telTextField.setValue(client.getTel());
        
        getGeneralPanel().addComponents(sournameTextField, nameTextField, middlenameTextField, telTextField);   
    }
}