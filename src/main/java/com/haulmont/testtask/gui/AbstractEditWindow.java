package com.haulmont.testtask.gui;

import com.vaadin.ui.Window;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Kiryakov Andrey
 */
public abstract class AbstractEditWindow extends Window  {
    public final static String ELEMENTS_WIDTH = "400";   
    
    private final Button okButton;
    private final Button cancelButton;
    private final VerticalLayout generalPanel;
    private final AbstractControlBlock controlBlock;
    private final HorizontalLayout buttonsLayout;
            
    public abstract void okButtonAction();
    public abstract void cancelButtonAction();
    
    private boolean editMode;
    
    public AbstractEditWindow(AbstractControlBlock controlBlock, String header){
        super(header);
        
        editMode = false;
        this.controlBlock = controlBlock;
        okButton = new Button("ОК");
        cancelButton = new Button("Отмена");
        generalPanel = new VerticalLayout();
        buttonsLayout = new HorizontalLayout();
        init();
    }
    
    private void init() {
        setModal(true);
        center();
        setClosable(false);
        setResizable(false);
        generalPanel.setSpacing(true);
        generalPanel.setMargin(true);
        
        buttonsLayout.setSpacing(true);
               
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
    
    /**
     * Устанавливает режим работы.Если editMode = true - режим редактирования,
     * если false - режим создания нового AbstractElement.
     * @param editMode
     */
    public void setMode(boolean editMode) {
        this.editMode = editMode;
    }
    
    public boolean isEditMode() {
        return editMode;
    }
    
    public VerticalLayout getGeneralPanel() {
        return generalPanel;
    }

    public AbstractControlBlock getControlBlock() {
        return controlBlock;
    }
    
}