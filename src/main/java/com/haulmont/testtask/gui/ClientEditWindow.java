package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.Client;
import com.vaadin.ui.TextField;

/**
 *
 * @author striped
 */
public class ClientEditWindow extends AbstractModalWindow{
    
    private Client client;
    
    private final TextField  sournameTextField;
    private final TextField  nameTextField;
    private final TextField  middlenameTextField;
    private final TextField  telTextField;
    
    public ClientEditWindow(Client element, String header) {
        super("Клиент " + header);
        
        sournameTextField = new TextField("Фамилия");
        nameTextField = new TextField("Имя");
        middlenameTextField = new TextField("Отчество");
        telTextField = new TextField("Телефон");
        
        generalPanel.addComponents(sournameTextField, nameTextField, middlenameTextField, telTextField);
    }
    
    @Override
    public void okButtonAction() {
        
    }
    
    @Override
    public void cancelButtonAction() {
       close();
    }
 
}
