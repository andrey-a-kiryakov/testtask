package com.haulmont.testtask;

import com.haulmont.testtask.dao.SingleConnection;
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




/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout layout = new HorizontalLayout();
        
        WorkingPanel clientsPanel = new WorkingPanel("50%", "КЛИЕНТЫ");
        WorkingPanel ordersPanel = new WorkingPanel("50%", "ЗАКАЗЫ");
        
        AbstractControlBlock clientControlBlock = new ClientControlBlock();
        clientsPanel.addComponent(clientControlBlock);
        
        AbstractControlBlock orderControlBlock = new OrderControlBlock();
        ordersPanel.addComponent(orderControlBlock);
    
        layout.addComponents(clientsPanel, ordersPanel);
        layout.setSpacing(true);
        setContent(layout);
        
        SingleConnection c = SingleConnection.getInstance();
        c.closeConnection();
        
        /*HSQLDBConnect test = new HSQLDBConnect();
        if (!test.loadDriver()) return;
        if (!test.getConnection()) return;     
  
        test.createTable();
        test.fillTable();
        test.printTable();
        test.closeConnection();
        */
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
