package model;

public class Customers {

    private int Id;
    private String Name;
    private String Address;
    private String Postal;
    private String Phone;
    private int Division;
    private int Country;

    public Customers(int id, String name, String address, String postal, String phone, int division, int country) {
        Id = id;
        Name = name;
        Address = address;
        Postal = postal;
        Phone = phone;
        Division = division;
        Country = country;
    }


    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPostal() {
        return Postal;
    }

    public String getPhone() {
        return Phone;
    }

    public int getDivision() {
        return Division;
    }

    public int getCountry() {
        return Country;
    }

}