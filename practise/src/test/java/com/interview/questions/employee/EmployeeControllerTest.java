package com.interview.questions.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(new Employee("Alice", "HR"), new Employee("Bob", "Finance"));
        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"));

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee("Charlie", "IT");
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Charlie\", \"department\":\"IT\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie"))
                .andExpect(jsonPath("$.department").value("IT"));

        verify(employeeService, times(1)).saveEmployee(any(Employee.class));
    }

    @Test
    public void testGetEmployeeByName() throws Exception {
        Employee employee = new Employee("David", "Sales");
        when(employeeService.getEmployeeByName("David")).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/employees/David"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("David"))
                .andExpect(jsonPath("$.department").value("Sales"));

        verify(employeeService, times(1)).getEmployeeByName("David");
    }

    @Test
    public void testGetEmployeeByName_NotFound() throws Exception {
        when(employeeService.getEmployeeByName("Eve")).thenReturn(Optional.empty());

        mockMvc.perform(get("/employees/Eve"))
                .andExpect(status().isNotFound());

        verify(employeeService, times(1)).getEmployeeByName("Eve");
    }
}