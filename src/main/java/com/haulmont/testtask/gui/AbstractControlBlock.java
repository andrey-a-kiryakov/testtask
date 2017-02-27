package com.haulmont.testtask.gui;

import com.haulmont.testtask.model.AbstractElement;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import java.util.List;

/**
 * Абстрактный класс блока управления элементами модели с тремя кнопками и таблицей
 * @author Kiryakov Andrey
 */
public abstract class AbstractControlBlock extends HorizontalLayout{
   
    private final Button addButton;
    private final Button editButton;
    private final Button delButton;
    private final Table table;
    private final WorkingPanel generalPanel;

    public abstract void addButtonAction();
    public abstract void editButtonAction();
    public abstract void delButtonAction();
    
    public abstract void addItemToTable (AbstractElement item);
    
    public AbstractControlBlock() {
        generalPanel = new WorkingPanel("100%", null);
        addButton = new Button("Добавить");
        editButton = new Button("Редактировать");
        delButton = new Button("Удалить");
        table = new Table();
        init();
    }
    
    private void init() {
       
        //Панель кнопок с горизонтальным выравниванием
        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        
        buttonsLayout.setWidth("100%");
        buttonsLayout.setSpacing(true);
              
        addButton.addClickListener(event -> {
           addButtonAction();
        });
        
        editButton.addClickListener(event -> {
           editButtonAction();
        });
        
        delButton.addClickListener(event -> {
           delButtonAction();
        });
        
        table.setSelectable(true);
        table.setImmediate(true);
        
        buttonsLayout.addComponents(addButton, editButton, delButton);
        generalPanel.addComponents(buttonsLayout, table);
        addComponent(generalPanel);
    }
    
    public Table getTable() {
        return table;
    }
 
    public void addItemsToTable(List itemsList) {
        itemsList.forEach((item) -> {
            addItemToTable((AbstractElement)item);
        });
    }
    
    public WorkingPanel getGeneralPanel() {
        return generalPanel;
    }    
}