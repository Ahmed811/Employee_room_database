package com.ahmedzidan.roomja.database.DaoData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.ahmedzidan.roomja.database.DaoData.DateConverter;
import com.ahmedzidan.roomja.database.Entites.Employee;

import java.util.List;

@Dao
@TypeConverters({DateConverter.class})
public interface EmployeeDao {

    @Insert
    void insertEmployee(Employee...employee);
    @Update
    void updateEmployee(Employee...employee);
    @Delete
    void deleteEmployee(Employee...employee);
    @Query("delete from employee where email=:email")
    void deleteEmployee(String email);

    @Query("select * from employee order by name ASC")
    LiveData<List<Employee>>getAllEmployees();

    @Query("select * from employee where email=:email")
    LiveData<List<Employee>>getEmployeesByEmail(String email);

    @Query("select * from employee where name like '%'||:name||'%' ")
    LiveData<List<Employee>>getEmployeesByName(String name);
}
