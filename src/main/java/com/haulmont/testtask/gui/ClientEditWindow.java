package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.Client;

/**
 *
 * @author striped
 */
public class ClientEditWindow extends ModalWindow{
    
    private Client client;
    
    public ClientEditWindow(Client element, String header) {
        super(header);
    }
    
}
