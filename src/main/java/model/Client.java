package model;

import java.util.Date;

public class Client {

    private String dni;
    private String name;
    private String surname1;
    private String surname2;
    private Date birthdate;
    private Nationality nationality;
    private Gender gender;
    private String address;
    private String email;

    public String getDni()
    {
        return dni;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname1()
    {
        return surname1;
    }

    public void setSurname1(String surname1)
    {
        this.surname1 = surname1;
    }

    public String getSurname2()
    {
        return surname2;
    }

    public void setSurname2(String surname2)
    {
        this.surname2 = surname2;
    }

    public Date getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
    }


    public Nationality getNationality()
    {
        return nationality;
    }

    public void setNationality(Nationality nationality)
    {
        this.nationality = nationality;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
