package com.haulmont.testtask.model;

/**
 *
 * @author Kiryakov Andrey
 */
public class Client extends AbstractElement {
    
    private String sourname;
    private String name;
    private String middlename;
    private String tel;
    
    public Client (String sourname, String name, String middlename, String tel) {
        this.sourname = sourname;
        this.name = name;
        this.middlename = middlename;
        this.tel = tel;
    }
    
    public Client (){
        
        setId (-1);
        sourname = "";
        name = "";
        middlename = "";
        tel = "";
        
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    @Override
    public String toString() {
        return sourname + " " + name + " " + middlename;
    }
       
}
