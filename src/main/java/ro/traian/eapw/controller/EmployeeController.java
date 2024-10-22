package ro.traian.eapw.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ro.traian.eapw.service.EmployeeService;
import ro.traian.eapw.record.EmployeeRecord;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<EmployeeRecord> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public EmployeeRecord findEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping("/employees")
    public EmployeeRecord saveEmployee(@RequestBody EmployeeRecord employeeRecord) {
        return employeeService.saveEmployee(employeeRecord);
    }

    @PatchMapping("/employee/{id}")
    public EmployeeRecord updateEmployee(@PathVariable Long id, @RequestBody EmployeeRecord employeeRecord) {
        return employeeService.updateEmployee(id, employeeRecord);
    }

    @DeleteMapping("/employee/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

}
