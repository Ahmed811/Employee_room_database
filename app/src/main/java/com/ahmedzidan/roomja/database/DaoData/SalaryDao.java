package com.ahmedzidan.roomja.database.DaoData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.ahmedzidan.roomja.database.DaoData.DateConverter;
import com.ahmedzidan.roomja.database.Entites.NameAndSalary;
import com.ahmedzidan.roomja.database.Entites.Salary;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters({DateConverter.class})
public interface SalaryDao {

    @Insert
    void insertSalary(Salary salary);
    @Update
    void updateSalary(Salary salary);
    @Delete
    void deleteSalary(Salary salary);

    @Query("select * from Salary where empId=:emp_id order by date ASC")
    LiveData<List<Salary>> getEmployeeSalaries(long emp_id);

    @Query("select * from Salary where empId=:emp_id and date>=:from and date<=:to order by date ASC")
    LiveData<List<Salary>> getEmployeeSalaries(long emp_id, Date from ,Date to);



    @Query("select * from Salary where  date>=:from and date<=:to order by date ASC")
    LiveData<List<Salary>> getEmployeeSalaries( Date from ,Date to);

    @Query("select sum(amount) from Salary where empId=:emp_id")
    double getSalariesSum( long emp_id);
    @Query("select sum(salary.amount) as _salary,employee.name as _name from Salary INNER JOIN employee on Salary.empId =employee.id group by name")
    LiveData<List<NameAndSalary>> getSalariesSum();


//
//    @Query("select * from salary")
//    LiveData<List<Salary>>getSalaries();
}
