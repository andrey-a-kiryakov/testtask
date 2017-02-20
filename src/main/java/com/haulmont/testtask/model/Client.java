package com.haulmont.testtask.model;

/**
 *
 * @author striped
 */
public class Client extends AbstractElement {
    
    private String sourname;
    private String name;
    private String middlename;
    private Integer tel;
    
    public Client (String sourname, String name, String middlename, Integer tel) {
        this.sourname = sourname;
        this.name = name;
        this.middlename = middlename;
        this.tel = tel;
    }
    
    public Client (){
        
    }

    public String getSourname() {
        return sourname;
    }

    public void setSourname(String sourname) {
        this.sourname = sourname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }
    
    @Override
    public String toString() {
        return sourname + " " + name + " " + middlename;
    }
       
}
