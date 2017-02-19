package com.haulmont.testtask;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.HorizontalLayout;

import com.haulmont.testtask.gui.WorkingPanel;
import com.haulmont.testtask.gui.ControlBlock;



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
        
        ControlBlock clientEditBlock = new ControlBlock();
        clientsPanel.addComponent(clientEditBlock);
        
        ControlBlock orderEditBlock = new ControlBlock();
        ordersPanel.addComponent(orderEditBlock);
        
     
       // setContent(topPanelLayout);
     /*   
        final TextField name = new TextField();
        name.setCaption("Type your name here:");
     
        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(topPanelLayout, name, button);
     */   
        layout.addComponents(clientsPanel, ordersPanel);
        //layout.setMargin(true);
        layout.setSpacing(true);
        
       setContent(layout);
      
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
