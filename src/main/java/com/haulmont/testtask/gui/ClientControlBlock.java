package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.Client;

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
       AbstractModalWindow mw = new ClientEditWindow(null, " -> Добавить");
       this.getUI().addWindow(mw);
    }

    @Override
    public void editButtonAction() {
       
    }

    @Override
    public void delButtonAction() {
       
    }
    
    private void init() {
        table.addContainerProperty("№", Integer.class, null);
        table.addContainerProperty("ФИО", Client.class, null);
        table.addContainerProperty("Телефон",  Integer.class, null);
       
        
        table.addItem(new Object[]{1, new Client ("Кирьяков", "Андрей", "Александрович", 2788569), 2788569}, 1);
    }
    
}
