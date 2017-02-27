package com.haulmont.testtask.model;

/**
 * Абстрактный класс элементов модели
 * @author Kiryakov Andrey 
 */
public abstract class AbstractElement {
    private long id;
    
    public AbstractElement() {
        this.id = -1;
    }
    
    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }
}