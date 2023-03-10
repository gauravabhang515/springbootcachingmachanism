package com.csi.service;

import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl {
    @Autowired
    EmployeeDaoImpl employeeDaoImpl;


    public Employee signUp(Employee employee) {

        return employeeDaoImpl.signUp(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {

        return employeeDaoImpl.signIn(empEmailId,empPassword);
    }

    @Cacheable(value = "empId")
    public Optional<Employee> getDataById(int empId) {

        log.info("Fetching Employee Data from DB");
        return employeeDaoImpl.getDataById(empId);
    }

    public List<Employee> getAllData() {
        return employeeDaoImpl.getAllData();
    }

    public void deleteById(int empId) {
        employeeDaoImpl.deleteById(empId);
    }

    public void deleteAllData() {

        employeeDaoImpl.deleteAllData();
    }
    public Employee updatedata(Employee employee){

        return employeeDaoImpl.updatedata(employee);
    }

    public void saveBulkOfData(List<Employee >employees){
         employeeDaoImpl.saveBulkOfData(employees);
    }
    public List<Employee> getDataByAnyInput(String input) {

        return employeeDaoImpl.getDataByAnyInput(input);
}

    public List<Employee> getDataByUsingAnyInput(String input) {
        return employeeDaoImpl.getDataByAnyInput(input);
    }


}
