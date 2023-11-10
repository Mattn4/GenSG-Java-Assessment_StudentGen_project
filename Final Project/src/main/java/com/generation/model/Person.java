package com.generation.model;

import java.text.SimpleDateFormat;
import java.util.Date;

abstract public class Person
{
    private final String id;

    private final String name;

    private final String email;

    private final Date birthDate;

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    protected Person( String id, String name, String email, Date birthDate )
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    @Override
    public String toString()
    {
        return "id='" + id + '\'' + ", name='" + name + '\'' + ", email='" + email + '\'' + ", birthDate=" + sdf.format(birthDate);
    }
}
