package id.my.hendisantika.example.controller;

import id.my.hendisantika.example.entity.Employee;
import id.my.hendisantika.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-with-postgres-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/08/24
 * Time: 07.34
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/employee/v1")
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/employee/v1/
     * Purpose: Fetches all the employees in the employee table
     *
     * @return List of Employees
     */
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }
}
