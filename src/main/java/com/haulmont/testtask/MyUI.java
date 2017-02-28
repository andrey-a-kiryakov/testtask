package com.haulmont.testtask;

import com.haulmont.testtask.dao.AbstractDAO;
import com.haulmont.testtask.dao.ClientDAO;
import com.haulmont.testtask.dao.HSQLDBDriverLoader;
import com.haulmont.testtask.dao.OrderDAO;
import com.haulmont.testtask.gui.ClientControlBlock;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.HorizontalLayout;

import com.haulmont.testtask.gui.WorkingPanel;
import com.haulmont.testtask.gui.AbstractControlBlock;
import com.haulmont.testtask.gui.OrderControlBlock;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout vLayout = new VerticalLayout();
        final HorizontalLayout hLayout = new HorizontalLayout();
        vLayout.setMargin(false);
        vLayout.setSpacing(false);
        
        WorkingPanel clientsPanel = new WorkingPanel("40%", "КЛИЕНТЫ");
        WorkingPanel ordersPanel = new WorkingPanel("60%", "ЗАКАЗЫ");
        
        AbstractControlBlock clientControlBlock = new ClientControlBlock();
        clientsPanel.addComponent(clientControlBlock);
        
        AbstractControlBlock orderControlBlock = new OrderControlBlock();
        ordersPanel.addComponent(orderControlBlock);
    
        hLayout.addComponents(clientsPanel, ordersPanel);
        hLayout.setSpacing(true);
        vLayout.addComponents(new Label("<div align=center><h2><b>&nbsp;&nbsp;&nbsp;Автомастерская</b> - система ввода и отображения информации о заказах</h2></div>",
                ContentMode.HTML), hLayout);
        
        setContent(vLayout);
        
        HSQLDBDriverLoader.getInstance();
        ClientDAO clientDAO = new ClientDAO();
        OrderDAO orderDAO = new OrderDAO();
        //AbstractDAO.createProjectsTables();
        clientControlBlock.addItemsToTable(clientDAO.getAll());
        orderControlBlock.addItemsToTable(orderDAO.getAll());
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}