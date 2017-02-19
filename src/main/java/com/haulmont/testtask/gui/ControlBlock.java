package com.haulmont.testtask.gui;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/*
 * Класс блока управления элементами модели с тремя кнопками и списком
 * @author striped
 */
public class ControlBlock extends HorizontalLayout{
   
    private Button addButton = null;
    private Button editButton = null;
    private Button delButton = null;
    
    private UI UI = null;
    
    public ControlBlock(UI UI) {
        super();
        this.UI = UI;
        init();
    }
    
    private void init() {
       
        //Основная панель с вертикальным выравниванием
        WorkingPanel generalPanel = new WorkingPanel("100%", null);
        
        //Панель кнопок с горизонтальным выравниванием
        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        
        buttonsLayout.setWidth("100%");
        buttonsLayout.setSpacing(true);
        
        addButton = new Button("Добавить");
        editButton = new Button("Редактировать");
        delButton = new Button("Удалить");
        
        addButton.addClickListener((Button.ClickEvent event) -> {
            ModalWindow mw = new ClientEditWindow(null, " -> Добавить");
            UI.addWindow(mw);
        });
        
        buttonsLayout.addComponents(addButton, editButton, delButton);
        
        generalPanel.addComponent(buttonsLayout);
        
        addComponent(generalPanel);
        
   } 
}
