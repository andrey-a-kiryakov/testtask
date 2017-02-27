package com.haulmont.testtask.dao;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

/**
 * Класс загрузчика драйвера HSQLDB, реализован паттерн Singleton.
 * @author Kiryakov Andrey
 */
public class HSQLDBDriverLoader {
   
    private static final String DRIVER_NAME = "org.hsqldb.jdbc.JDBCDriver";
    private static HSQLDBDriverLoader instanseof;
    
    public HSQLDBDriverLoader() {
         loadDriver();     
    }
    
    public static final HSQLDBDriverLoader getInstance () {
        if (instanseof == null) {
            instanseof = new HSQLDBDriverLoader ();
        }
        return instanseof;
    }
   
    private void loadDriver() {
        try {   
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            new Notification("WARNING", "Driver not load", Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
        }  
    }
}