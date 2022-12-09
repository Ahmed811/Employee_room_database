package com.ahmedzidan.roomja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.ahmedzidan.roomja.Adapters.EmployeeSpinnerAdapter;
import com.ahmedzidan.roomja.database.Entites.Employee;
import com.ahmedzidan.roomja.database.Entites.Salary;
import com.ahmedzidan.roomja.database.MyViewModel;
import com.ahmedzidan.roomja.databinding.ActivityAddSalaryBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddSalaryActivity extends AppCompatActivity {
ActivityAddSalaryBinding binding;
Calendar selectedDate;
MyViewModel myViewModel;
    EmployeeSpinnerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityAddSalaryBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

        setContentView(binding.getRoot());
        myViewModel=new  ViewModelProvider(this).get(MyViewModel.class);
        adapter=new EmployeeSpinnerAdapter(new ArrayList<>());
        binding.spinner.setAdapter(adapter);

        myViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });
       binding.btnSaveSalary.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            String amount=binding.edSalaryAmount.getText().toString();
            long id=binding.spinner.getSelectedItemId();
            if(TextUtils.isEmpty(amount)||selectedDate==null){
                Toast.makeText(AddSalaryActivity.this, "Please Fill Full Data", Toast.LENGTH_SHORT).show();
                return;
            }
            double dAmount=Double.parseDouble(amount);
               Salary salary=new Salary(dAmount,selectedDate.getTime(),id);
            myViewModel.insertSalary(salary);
            finish();
           }
       });

        binding.btnSalayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        binding.btnSalayDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
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