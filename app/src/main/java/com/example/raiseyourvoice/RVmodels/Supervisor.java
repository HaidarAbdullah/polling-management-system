package com.example.raiseyourvoice.RVmodels;

public class Supervisor {
   public String name;
   public String contact_email;

    public Supervisor(String name, String contact_email) {
        this.name = name;
        this.contact_email = contact_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }
}
