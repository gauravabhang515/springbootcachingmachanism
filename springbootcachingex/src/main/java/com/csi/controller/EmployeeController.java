package com.csi.controller;

import com.csi.constants.EndPointConstants;
import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping(EndPointConstants.SIGNUP)

    public ResponseEntity<Employee> signUp(@RequestBody Employee employee) {

        return ResponseEntity.ok(employeeServiceImpl.signUp(employee));
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")

    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {

        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabtid/{empId}")


    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId) {
        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("Id not exist"));


        return ResponseEntity.ok(employeeServiceImpl.getDataById(employee1.getEmpId()));
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable int empId, @RequestBody Employee employee) {

        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("Id not exist"));


        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return ResponseEntity.ok(employeeServiceImpl.updatedata(employee1));
    }

    @GetMapping("/getalladata")

    public ResponseEntity<List<Employee>> getAllData() {

        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @DeleteMapping("/deletebyid/{empId}")

    public ResponseEntity<String> deleteById(@PathVariable int empId) {

        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("Deleted");

    }

    @DeleteMapping("/deletealldata")

    public ResponseEntity<String> deleteAllData() {
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("All Deleted");
    }

    @GetMapping("/sortbyname")

    public ResponseEntity<List<Employee>> sortByName() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
    }



    @PostMapping("/savebulkofdata")

    public ResponseEntity<String> saveBulkOfData(@RequestBody List<Employee> employees) {


        employeeServiceImpl.saveBulkOfData(employees);
        return ResponseEntity.ok("Data Saved Succesfully");
    }



    @GetMapping("/sortbydob")

    public ResponseEntity<List<Employee>> sortByDOB() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList()));
    }

    @GetMapping("/filterbysalary/{empSalary}")

    public ResponseEntity<List<Employee>> filterBySalary(@PathVariable double empSalary) {

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpSalary() >= empSalary).collect(Collectors.toList()));


    }
    @GetMapping("/getdatabyanyinput/{input}")

    public ResponseEntity<List<Employee>> getDataByAnyInput(@PathVariable String input){

        List<Employee> employeeList=new ArrayList<>();

        for (Employee employee:employeeServiceImpl.getAllData()){
            if(employee.getEmpName().equals(input)
            || String.valueOf(employee.getEmpSalary()).equals(input)
            ||String.valueOf(employee.getEmpContactNumber()).equals(input)){
                employeeList.add(employee);
            }
        }

        return ResponseEntity.ok(employeeList);
    }
    @GetMapping("/getdatabydob/{empDOB}")
    public ResponseEntity<List<Employee>> getDataByDOB(@PathVariable String empDOB){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<Employee> ll = new ArrayList<>();
        String dobDate = null;
        for(Employee employee: employeeServiceImpl.getAllData()){
            dobDate= dateFormat.format(employee.getEmpDOB());

            System.out.println(" from DB "+dobDate);
            if(dobDate.equals(empDOB)){
                ll.add(employee);
            }
        }
        return ResponseEntity.ok(ll);
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")

    public ResponseEntity<List<Employee>>  empContactNumber(@PathVariable long empContactNumber){

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp->emp.getEmpContactNumber()==empContactNumber).collect(Collectors.toList()));
    }




}
