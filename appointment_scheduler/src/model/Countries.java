package model;

public class Countries {
    private int Id;
    private String Name;

    public Countries(int id, String name) {
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return Name;
    }

}
