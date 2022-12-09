package com.ahmedzidan.roomja.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedzidan.roomja.R;
import com.ahmedzidan.roomja.database.DoubleValueListener;
import com.ahmedzidan.roomja.database.Entites.Employee;
import com.ahmedzidan.roomja.database.Entites.NameAndSalary;
import com.ahmedzidan.roomja.database.MyViewModel;
import com.ahmedzidan.roomja.databinding.CustomSalaryEmployeesBinding;

import java.util.List;

public class SalariesEmployeesAdapter extends RecyclerView.Adapter<SalariesEmployeesAdapter.SalariesEmployeesViewHolder> {
    private List<NameAndSalary>employees;
    private MyViewModel myViewModel;
public SalariesEmployeesAdapter(List<NameAndSalary>employees,MyViewModel myViewModel){
    this.employees=employees;
    this.myViewModel=myViewModel;
}

    public List<NameAndSalary> getEmployees() {
        return employees;
    }

    public void setEmployees(List<NameAndSalary> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SalariesEmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SalariesEmployeesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_salary_employees, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SalariesEmployeesViewHolder holder, int position) {

    holder.bind(employees.get(position),myViewModel);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }


    static class SalariesEmployeesViewHolder extends RecyclerView.ViewHolder{
   // CustomSalaryEmployeesBinding binding;
   NameAndSalary employee;
        private final TextView tvName;
        private final TextView tvSalary;
        public SalariesEmployeesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvSalary=itemView.findViewById(R.id.tv_salary);
          //  binding=CustomSalaryEmployeesBinding.bind(itemView);
        }
        public void bind(NameAndSalary employee,MyViewModel myViewModel){
         this.employee=employee;
         tvName.setText(employee.get_name());

              tvSalary.setText(String.valueOf(employee.get_Salary()));

        }
    }
}
