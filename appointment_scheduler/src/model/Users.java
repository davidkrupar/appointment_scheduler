package model;

public class Users {
    private int Id;
    private String Name;
    private String Password;

    public Users(int id, String name, String password) {
        Id = id;
        Name = name;
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }
}
