package com.ahmedzidan.roomja.database.Entites;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ahmedzidan.roomja.database.DaoData.DateConverter;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Employee.class,parentColumns = {"id"},childColumns = {"empId"},onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)})
@TypeConverters({DateConverter.class})
public class Salary {
    public Salary(int id, double amount, @NonNull Date date, @NonNull Long empId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.empId = empId;
    }
    public Salary(double amount, @NonNull Date date, @NonNull Long empId) {

        this.amount = amount;
        this.date = date;
        this.empId = empId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    @NonNull
    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(@NonNull Long empId) {
        this.empId = empId;
    }

    @PrimaryKey(autoGenerate = true)

    private int id;
@NonNull
    private double amount;
    @NonNull
    private Date date;
    @NonNull
    private Long empId;

    public Salary() {
    }
}
