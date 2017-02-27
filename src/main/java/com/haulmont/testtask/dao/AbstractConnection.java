package com.haulmont.testtask.dao;

import java.sql.Connection;

/**
 * Абстрактный класс-обертка для клсассов соединений с БД
 * @author  Andrey Kiryakov
 */
public abstract class AbstractConnection {
    
    public abstract boolean connect ();
    public abstract Connection getConnection ();
    public abstract boolean closeConnection();
}