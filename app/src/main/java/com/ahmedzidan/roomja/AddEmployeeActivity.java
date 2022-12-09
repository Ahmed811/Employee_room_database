package com.ahmedzidan.roomja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ahmedzidan.roomja.database.Entites.Employee;
import com.ahmedzidan.roomja.databinding.ActivityAddEmployeeBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class AddEmployeeActivity extends AppCompatActivity {
ActivityAddEmployeeBinding binding;
Calendar selectedDate;
public  static final String EMPLOYEE_KEY="employee_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityAddEmployeeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnEmployeeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String empName=binding.edFullName.getText().toString();
                String empEmail=binding.edEmployeeEmail.getText().toString();
                String empId=binding.edEmployeeNumber.getText().toString();
                if(TextUtils.isEmpty(empName)||TextUtils.isEmpty(empEmail)||TextUtils.isEmpty(empId)||selectedDate==null){
                    Toast.makeText(getBaseContext(), "Please Enter Full Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                long  id=Long.parseLong(empId);
                Employee employee=new Employee();
                employee.setName(empName);
                employee.setEmail(empEmail);
                employee.setId(id);
                employee.setBirthdate(selectedDate.getTime());
                Intent intent=new Intent();
                intent.putExtra(EMPLOYEE_KEY,employee);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        binding.btnEmployeeBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                    binding.btnEmployeeBirthdate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    selectedDate=Calendar.getInstance();
                    selectedDate.set(Calendar.YEAR,year);
                    selectedDate.set(Calendar.MONTH,monthOfYear);
                    selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    }
                }, Calendar.getInstance());
                datePickerDialog.show(getSupportFragmentManager(),null);
            }
        });
    }
}