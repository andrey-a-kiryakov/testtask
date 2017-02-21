package com.haulmont.testtask.dao;

import java.util.List;

/*
 * Абстрактный класс DAO описывающий общие методы взаимодействия с БД.
 * @author Andrey Kiryakov
 * @param <E>
 * @param <K>
 */
public abstract class AbstractController<E, K> {
    
    private SingleConnection connection = null;
    
    public abstract List<E> getAll();
    public abstract E getElementById(K id);
    public abstract E update(E element);
    public abstract boolean delete(K id);
    public abstract boolean create(E element);
    
    public AbstractController() {
        
        connection = SingleConnection.getInstance();
    }
}
