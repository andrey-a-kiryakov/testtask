package com.haulmont.testtask.dao;

import java.sql.Connection;

/**
 * Абстрактный класс-предок для клсассов соединений с БД
 * @author  Andrey Kiryakov
 */
public abstract class AbstractConnection {
    
    public abstract boolean connect ();
    public abstract Connection getConnection ();
    public abstract boolean closeConnection();
}