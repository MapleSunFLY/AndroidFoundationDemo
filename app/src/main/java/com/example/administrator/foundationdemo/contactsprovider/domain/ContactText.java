package com.example.administrator.foundationdemo.contactsprovider.domain;

/**
 * Created by Administrator on 2016/12/20.
 */
public class ContactText {

    private String name;
    private String phone;
    private String Email;

    public ContactText(){

    }

    public ContactText(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        Email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
