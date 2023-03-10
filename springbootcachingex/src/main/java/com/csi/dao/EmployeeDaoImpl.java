package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepository employeeRepositoryImpl;


    public Employee signUp(Employee employee) {

        return employeeRepositoryImpl.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;

        for (Employee employee : getAllData()) {

            if (employee.getEmpEmailId().equals(empEmailId) &&
                    employee.getEmpPassword().equals(empPassword)) {
                flag = true;
            }
        }
        return flag;
    }

    public Optional<Employee> getDataById(int empId) {

        return employeeRepositoryImpl.findById(empId);
    }

    public List<Employee> getAllData() {
        return employeeRepositoryImpl.findAll();
    }

    public void deleteById(int empId) {
        employeeRepositoryImpl.deleteById(empId);
    }

    public void deleteAllData() {

        employeeRepositoryImpl.deleteAll();
    }

    public Employee updatedata(Employee employee){

        return employeeRepositoryImpl.save(employee);
    }
    public void saveBulkOfData(List<Employee>employees) {

        for (Employee employee:employees){
        employeeRepositoryImpl.save(employee);}
    }
    public List<Employee> getDataByAnyInput(String input) {

        return employeeRepositoryImpl.findAll();



    }




}
