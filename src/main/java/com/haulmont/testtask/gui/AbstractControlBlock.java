package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.AbstractElement;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

/*
 * Абстрактный класс блока управления элементами модели с тремя кнопками и списком
 * @author striped
 */
public abstract class AbstractControlBlock extends HorizontalLayout{
   
    private final Button addButton;
    private final Button editButton;
    private final Button delButton;
    
    protected final Table table;
    
    public abstract void addButtonAction();
    public abstract void editButtonAction();
    public abstract void delButtonAction();
    
    public abstract void addItemToTable (AbstractElement item);
    
    public AbstractControlBlock() {
        super();
        
        addButton = new Button("Добавить");
        editButton = new Button("Редактировать");
        delButton = new Button("Удалить");
        table = new Table();
        
        init();
    }
    
    private void init() {
       
        //Основная панель с вертикальным выравниванием
        WorkingPanel generalPanel = new WorkingPanel("100%", null);
        
        //Панель кнопок с горизонтальным выравниванием
        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        
        buttonsLayout.setWidth("100%");
        buttonsLayout.setSpacing(true);
                
        addButton.addClickListener((Button.ClickEvent event) -> {
           addButtonAction();
        });
        
        editButton.addClickListener((Button.ClickEvent event) -> {
           editButtonAction();
        });
        
        delButton.addClickListener((Button.ClickEvent event) -> {
           delButtonAction();
        });
        
        table.setWidth("400px");
        table.setSelectable(true);
        table.setImmediate(true);
        
        buttonsLayout.addComponents(addButton, editButton, delButton);
        generalPanel.addComponents(buttonsLayout, table);
        addComponent(generalPanel);
        
   }    
}
