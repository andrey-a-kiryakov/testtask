package com.haulmont.testtask.gui;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/*
 * Класс блока управления с тремя кнопками и списком
 * @author striped
 */
public class ControlBlock extends HorizontalLayout{
   
    protected Button addButton = null;
    protected Button editButton = null;
    protected Button delButton = null;
    
    
    public ControlBlock() {
        init();
    }
    
    protected final void init() {
       
        //Основная панель с вертикальным выравниванием
        WorkingPanel generalPanel = new WorkingPanel("100%", null);
        
        //Панель кнопок с горизонтальным выравниванием
        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        
        buttonsLayout.setWidth("100%");
        buttonsLayout.setSpacing(true);
        
        addButton = new Button("Добавить");
        editButton = new Button("Редактировать");
        delButton = new Button("Удалить");
        buttonsLayout.addComponents(addButton, editButton, delButton);
        
        //Добавляем в основную панель панель с кнопками
        generalPanel.addComponent(buttonsLayout);
        
        addComponent(generalPanel);
        
   } 
}
