package com.haulmont.testtask.gui;

import com.vaadin.ui.Window;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author Kiryakov Andrey
 */
public abstract class AbstractEditWindow extends Window  {
       
    private final Button okButton;
    private final Button cancelButton;
    
    private final WorkingPanel generalPanel;

    private final AbstractControlBlock controlBlock;
    
    public abstract void okButtonAction();
    public abstract void cancelButtonAction();
    
    private boolean editMode;
    
    public AbstractEditWindow(AbstractControlBlock controlBlock, String header){
        super(header);
        editMode = false;
        this.controlBlock = controlBlock;
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
    
    public WorkingPanel getGeneralPanel() {
        return generalPanel;
    }

    public AbstractControlBlock getControlBlock() {
        return controlBlock;
    }
    
}
