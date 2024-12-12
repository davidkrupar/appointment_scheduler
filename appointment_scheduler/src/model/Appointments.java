package model;

import java.time.LocalDateTime;

public class Appointments {
    private int Id;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private int Customer;
    private int User;
    private int Contact;
    private String ContactText;

    public Appointments(
            int id,
            String title,
            String description,
            String location,
            String type,
            LocalDateTime start,
            LocalDateTime end,
            int customer,
            int user,
            int contact,
            String contactText) {
        Id = id;
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Customer = customer;
        User = user;
        Contact = contact;
        ContactText = contactText;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getLocation() {
        return Location;
    }

    public String getType() {
        return Type;
    }

    public LocalDateTime getStart() {
        return Start;
    }

    public LocalDateTime getEnd() {
        return End;
    }

    public int getCustomer() {
        return Customer;
    }

    public int getUser() {
        return User;
    }

    public int getContact() {
        return Contact;
    }

    public String getContactText() {
        return ContactText;
    }
}
