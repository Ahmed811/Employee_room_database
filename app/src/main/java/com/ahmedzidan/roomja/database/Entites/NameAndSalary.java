package com.ahmedzidan.roomja.database.Entites;

public class NameAndSalary {
 public    String _name;
 public    double _salary;

    public NameAndSalary(String _name, double _Salary) {
        this._name = _name;
        this._salary = _Salary;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public double get_Salary() {
        return _salary;
    }

    public void set_Salary(double _Salary) {
        this._salary = _Salary;
    }

    public NameAndSalary() {
    }
}
