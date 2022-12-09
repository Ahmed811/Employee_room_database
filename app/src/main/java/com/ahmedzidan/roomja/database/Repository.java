package com.ahmedzidan.roomja.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ahmedzidan.roomja.database.DaoData.EmployeeDao;
import com.ahmedzidan.roomja.database.DaoData.SalaryDao;
import com.ahmedzidan.roomja.database.Entites.Employee;
import com.ahmedzidan.roomja.database.Entites.NameAndSalary;
import com.ahmedzidan.roomja.database.Entites.Salary;

import java.util.Date;
import java.util.List;

public class Repository {
    EmployeeDao employeeDao;
    SalaryDao salaryDao;
    public  Repository(Application application){
        MyRoomDatabase db=MyRoomDatabase.getDatabase(application);
        employeeDao=db.employeeDao();
        salaryDao=db.salaryDao();

    }

//Employee
 public    void insertEmployee(Employee...employee){
       MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
           @Override
           public void run() {
               employeeDao.insertEmployee(employee);
           }
       });
    }
    public void updateEmployee(Employee...employee){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDao.updateEmployee(employee);
            }
        });

    }
    public  void deleteEmployee(Employee...employee){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDao.deleteEmployee(employee);
            }
        });

    }
    public  void deleteEmployee(String email){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDao.deleteEmployee(email);
            }
        });

    }

    public   LiveData<List<Employee>> getAllEmployees(){

                return  employeeDao.getAllEmployees();


    }

    public  LiveData<List<Employee>>getEmployeesByEmail(String email){

                return  employeeDao.getEmployeesByEmail(email);


    }

    public LiveData<List<Employee>>getEmployeesByName(String name){

                return   employeeDao.getEmployeesByName(name);


    }

    //Salary

    void insertSalary(Salary salary){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.insertSalary(salary);
            }
        });

    };

    void updateSalary(Salary salary){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.updateSalary(salary);
            }
        });

    };

    void deleteSalary(Salary salary){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.deleteSalary(salary);
            }
        });

    };

    LiveData<List<Salary>> getEmployeeSalaries(long emp_id){

                return salaryDao.getEmployeeSalaries(emp_id);


    };

    LiveData<List<Salary>> getEmployeeSalaries(long emp_id, Date from , Date to){

                return salaryDao.getEmployeeSalaries(emp_id,from,to);

    };



    LiveData<List<Salary>> getEmployeeSalaries( Date from ,Date to){


                return salaryDao.getEmployeeSalaries(from,to);


    };
    LiveData<List<NameAndSalary>> getSalariesSum(){
        return salaryDao.getSalariesSum();
    }
    void getSalariesSum( long emp_id,DoubleValueListener listener){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                double value= salaryDao.getSalariesSum(emp_id);
                listener.onValueSubmit(value);
            }
        });

    };
}
