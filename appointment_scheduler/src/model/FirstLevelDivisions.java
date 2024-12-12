package model;

public class FirstLevelDivisions {
    private int Id;
    private String Division;


    public FirstLevelDivisions(int id, String division) {
        Id = id;
        Division = division;
    }

    public int getId() {
        return Id;
    }

    public String getDivision() {
        return Division;
    }

    @Override
    public String toString() {
        return Division;
    }
}
