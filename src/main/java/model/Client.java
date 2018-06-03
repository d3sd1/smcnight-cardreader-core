package model;

import java.util.Date;

public class Client {

    private String dni;
    private String name;
    private String surname1;
    private String surname2;
    private Date birthDate;
    private String serialNumber;
    private String nationality;
    private String biometric;
    private boolean conflictive;

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

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public String getBiometric()
    {
        return biometric;
    }

    public void setBiometric(String biometric)
    {
        this.biometric = biometric;
    }

    public boolean isConflictive()
    {
        return conflictive;
    }

    public void setConflictive(boolean conflictive)
    {
        this.conflictive = conflictive;
    }
    
}
