package com.ahmedzidan.roomja;

import static com.ahmedzidan.roomja.AddEmployeeActivity.EMPLOYEE_KEY;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ahmedzidan.roomja.Adapters.SalariesEmployeesAdapter;
import com.ahmedzidan.roomja.database.Entites.Employee;
import com.ahmedzidan.roomja.database.Entites.NameAndSalary;
import com.ahmedzidan.roomja.database.MyViewModel;
import com.ahmedzidan.roomja.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
MyViewModel myViewModel;
    SalariesEmployeesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myViewModel=new ViewModelProvider(this).get(MyViewModel.class);
        adapter=new SalariesEmployeesAdapter(new ArrayList<NameAndSalary>(),myViewModel);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myViewModel.getSalariesSum().observe(this, new Observer<List<NameAndSalary>>() {
            @Override
            public void onChanged(List<NameAndSalary> employees) {

                adapter.setEmployees(employees);
            }
        });

        ActivityResultLauncher<Intent>ari=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()==RESULT_OK&&result.getData()!=null){
                            Employee employee= (Employee) result.getData().getSerializableExtra(EMPLOYEE_KEY);

                            myViewModel.insertEmployee(employee);
                        }
                    }
                });

       binding.fbAddEmployee.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            Intent intent=new Intent(getBaseContext(),AddEmployeeActivity.class);
            ari.launch(intent);
           }
       });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_salary:
            {
                Intent intent=new Intent(this,AddSalaryActivity.class);
                startActivity(intent);
                return true;
            }

        }
        return false;
    }
}