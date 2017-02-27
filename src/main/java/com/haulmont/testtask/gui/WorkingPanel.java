package com.haulmont.testtask.gui;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
/**
 * Класс панели c вертикальным выравниванием и заголовком
 * Используется во многих GUI элементах
 * @author Kiryakov Andrey
 */
public class WorkingPanel extends VerticalLayout{
    private Label header = null;
    
    public WorkingPanel (String width, String panelHeader){
        super();
        if (panelHeader != null) {
            header = new Label(panelHeader);
        }
        init (width);
    }
    
    private void init(String width) {
        setWidth(width);
        setSpacing(true);
        //setMargin(true);
        if (header != null) {
            addComponent(header);
        }
    }
}