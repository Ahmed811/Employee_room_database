package com.ahmedzidan.roomja.database.Entites;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ahmedzidan.roomja.database.DaoData.DateConverter;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = "employee")
@TypeConverters({DateConverter.class})
public class Employee implements Serializable {
    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public Employee(){}
    public Employee(@NonNull long id, String name, String email, Date birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
    }

    @PrimaryKey
    @NonNull
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    private Date birthdate;
}
