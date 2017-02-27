package com.haulmont.testtask;

import com.haulmont.testtask.dao.AbstractDAO;
import com.haulmont.testtask.dao.ClientDAO;
import com.haulmont.testtask.dao.HSQLDBConnection;
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

@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout layout = new HorizontalLayout();
        
        WorkingPanel clientsPanel = new WorkingPanel("40%", "КЛИЕНТЫ");
        WorkingPanel ordersPanel = new WorkingPanel("60%", "ЗАКАЗЫ");
        
        AbstractControlBlock clientControlBlock = new ClientControlBlock();
        clientsPanel.addComponent(clientControlBlock);
        
        AbstractControlBlock orderControlBlock = new OrderControlBlock();
        ordersPanel.addComponent(orderControlBlock);
    
        layout.addComponents(clientsPanel, ordersPanel);
        layout.setSpacing(true);
        setContent(layout);
        
        HSQLDBDriverLoader.getInstance();
        ClientDAO clientDAO = new ClientDAO();
        OrderDAO orderDAO = new OrderDAO();
        HSQLDBConnection c = new HSQLDBConnection();
        //AbstractDAO.createProjectsTables();
        clientControlBlock.addItemsToTable(clientDAO.getAll());
        orderControlBlock.addItemsToTable(orderDAO.getAll());
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}