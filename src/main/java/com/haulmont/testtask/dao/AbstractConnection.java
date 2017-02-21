package com.haulmont.testtask.dao;

import java.sql.Connection;

/**
 *
 * @author Alex
 */
public abstract class AbstractConnection {
    
    public abstract boolean connect ();
    public abstract Connection getConnection ();
    public abstract boolean closeConnection();
    
}
