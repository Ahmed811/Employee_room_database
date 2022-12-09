package com.ahmedzidan.roomja.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ahmedzidan.roomja.database.DaoData.EmployeeDao;
import com.ahmedzidan.roomja.database.DaoData.SalaryDao;
import com.ahmedzidan.roomja.database.Entites.Employee;
import com.ahmedzidan.roomja.database.Entites.Salary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Salary.class, Employee.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract SalaryDao salaryDao();
    public abstract EmployeeDao employeeDao();

    private static volatile MyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyRoomDatabase.class, "employee_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}