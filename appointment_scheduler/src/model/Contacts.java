package model;

public class Contacts {
    private int Id;
    private String Name;
    private String Email;

    public Contacts(int id, String name, String email) {
        Id = id;
        Name = name;
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    @Override
    public String toString() {
        return Name;
    }
}
