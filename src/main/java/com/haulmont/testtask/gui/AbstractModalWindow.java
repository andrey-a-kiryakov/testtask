package com.haulmont.testtask.gui;

import com.vaadin.ui.Window;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author striped
 */
public abstract class AbstractModalWindow extends Window  {
       
    private final Button okButton;
    private final Button cancelButton;
    
    protected final WorkingPanel generalPanel;
    
    public abstract void okButtonAction();
    public abstract void cancelButtonAction();
    
    public AbstractModalWindow(String header){
        super(header);
        okButton = new Button("ОК");
        cancelButton = new Button("Отмена");
        generalPanel = new WorkingPanel("100%", null);
        init();
    }
    
    
    private void init() {
                
        setModal(true);
        center();
        setClosable(false);
        setResizable(false);
        
        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);
               
        cancelButton.addClickListener((Button.ClickEvent event) -> {
            cancelButtonAction();
        });
        
        okButton.addClickListener((Button.ClickEvent event) -> {
            okButtonAction();
        });
        
        buttonsLayout.addComponents(okButton, cancelButton);
        generalPanel.addComponent(buttonsLayout);
        setContent(generalPanel);
        
        
    }
}