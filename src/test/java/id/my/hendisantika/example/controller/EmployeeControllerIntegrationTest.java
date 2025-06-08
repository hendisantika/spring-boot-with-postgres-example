package id.my.hendisantika.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.example.config.TestContainersConfig;
import id.my.hendisantika.example.entity.Employee;
import id.my.hendisantika.example.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EmployeeControllerIntegrationTest extends TestContainersConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        Employee employee = createSampleEmployee();

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.first_name", is(employee.getFirstName())))
                .andExpect(jsonPath("$.last_name", is(employee.getLastName())))
                .andExpect(jsonPath("$.designation", is(employee.getDesignation())));
    }

    @Test
    void shouldGetEmployee() throws Exception {
        Employee employee = employeeRepository.save(createSampleEmployee());

        mockMvc.perform(get("/api/employees/{id}", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", is(employee.getFirstName())))
                .andExpect(jsonPath("$.last_name", is(employee.getLastName())));
    }

    @Test
    void shouldGetAllEmployees() throws Exception {
        employeeRepository.save(createSampleEmployee());
        employeeRepository.save(createSampleEmployee(2));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldUpdateEmployee() throws Exception {
        Employee employee = employeeRepository.save(createSampleEmployee());
        employee.setFirstName("UpdatedName");

        mockMvc.perform(put("/api/employees/{id}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", is("UpdatedName")));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        Employee employee = employeeRepository.save(createSampleEmployee());

        mockMvc.perform(delete("/api/employees/{id}", employee.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/employees/{id}", employee.getId()))
                .andExpect(status().isNotFound());
    }

    private Employee createSampleEmployee() {
        return createSampleEmployee(1);
    }

    private Employee createSampleEmployee(int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setAge(30);
        employee.setDesignation("Software Engineer");
        employee.setPhoneNumber("+1234567890");
        employee.setAddress("123 Tech Street");
        employee.setDateOfBirth(LocalDate.now().minusYears(30));
        employee.setHireDate(LocalDate.now());
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        return employee;
    }
}
