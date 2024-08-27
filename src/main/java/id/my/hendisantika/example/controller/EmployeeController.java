package id.my.hendisantika.example.controller;

import id.my.hendisantika.example.entity.Employee;
import id.my.hendisantika.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/employees/v1")
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

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/employee/v1/1 (or any other id)
     * Purpose: Fetches employee with the given id
     *
     * @param id - employee id
     * @return Employee with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }

    /**
     * This method is called when a POST request is made
     * URL: localhost:8080/employee/v1/
     * Purpose: Save an Employee entity
     *
     * @param employee - Request body is an Employee entity
     * @return Saved Employee entity
     */
    @PostMapping("/")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeService.saveEmployee(employee));
    }

    /**
     * This method is called when a PUT request is made
     * URL: localhost:8080/employee/v1/
     * Purpose: Update an Employee entity
     *
     * @param employee - Employee entity to be updated
     * @return Updated Employee
     */
    @PutMapping("/")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeService.updateEmployee(employee));
    }

    /**
     * This method is called when a PUT request is made
     * URL: localhost:8080/employee/v1/1 (or any other id)
     * Purpose: Delete an Employee entity
     *
     * @param id - employee's id to be deleted
     * @return a String message indicating employee record has been deleted successfully
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().body("Deleted employee successfully");
    }
}
