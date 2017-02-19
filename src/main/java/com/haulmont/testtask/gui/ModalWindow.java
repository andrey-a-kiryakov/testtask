package com.haulmont.testtask.gui;

import com.vaadin.ui.Window;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author striped
 */
public abstract class ModalWindow extends Window  {
       
    protected final Button okButton;
    private final Button cancelButton;
    
    public ModalWindow( String header){
        super(header);
        okButton = new Button("ОК");
        cancelButton = new Button("Отмена");
        init();
    }
    
    private void init() {
                
        //Основная панель с вертикальным выравниванием
        WorkingPanel generalPanel = new WorkingPanel("100%", null);
        setModal(true);
        center();
        setClosable(false);
        setResizable(false);
        
        //Панель кнопок с горизонтальным выравниванием
        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
               
        cancelButton.addClickListener((Button.ClickEvent event) -> {
            close();
        });
        
        buttonsLayout.addComponents(okButton, cancelButton);
        
        generalPanel.addComponent(buttonsLayout);
        
        setContent(generalPanel);
        
        
    }
}
