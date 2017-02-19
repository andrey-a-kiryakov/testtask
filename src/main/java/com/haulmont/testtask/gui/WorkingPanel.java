package com.haulmont.testtask.gui;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
/*
 * Класс основной рабочей панели c вертикальным выравниванием и заголовком
 * @author striped
 */
public class WorkingPanel extends VerticalLayout{
    
    protected Label header = null;
    
    public WorkingPanel (String width, String panelHeader){
        if (panelHeader != null) {
            header = new Label(panelHeader);
        }
        init (width);
        
    }
    
    protected final void init(String width) {
        setWidth(width);
        setSpacing(true);
        setMargin(true);
        if (header != null) {
            addComponent(header);
        }
        
    }
}