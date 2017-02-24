package com.haulmont.testtask.model;

/**
 *
 * @author Kiryakov Andrey 
 */
public abstract class AbstractElement {
    
    private long id;

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }
}
