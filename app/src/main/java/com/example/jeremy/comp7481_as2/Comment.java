package com.example.jeremy.comp7481_as2;

/**
 * Created by jeremy on 1/22/15.
 */
public class Comment {
    private long id;
    private String contact_name;
    private String contact_phone;
    private String contact_address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContactName() {
        return contact_name;
    }

    public void setContactName(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContactPhone() {
        return contact_phone;
    }

    public void setContactPhone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContactAddress(){
        return contact_address;
    }

    public void setContactAddress(String contact_address){
        this.contact_address = contact_address;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return contact_name;
    }
}
