package com.ahmedzidan.roomja.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ahmedzidan.roomja.R;
import com.ahmedzidan.roomja.database.Entites.Employee;

import java.util.List;

public class EmployeeSpinnerAdapter extends BaseAdapter {
    private List<Employee>employees;

    public EmployeeSpinnerAdapter(List<Employee> employees) {
        this.employees = employees;
    }
public List<Employee>getEmployees(){
        return employees;
}

public void setEmployees(List<Employee> employees){
this.employees=employees;
notifyDataSetChanged();
}
    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Employee getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return employees.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        if(v==null){
            v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_spinner_item,viewGroup,false);

        }
        TextView tvEmpName=v.findViewById(R.id.tv_employee_name);
        Employee e=getItem(i);
        tvEmpName.setText(e.getName());
        return v;


    }
}
