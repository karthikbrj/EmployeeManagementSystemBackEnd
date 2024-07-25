package com.uttara.in.services;

import java.util.List;

import com.uttara.in.model.Employee;

public interface EmployeeServices {
	public Employee createEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public boolean deleteEmployee(Long id);

	public Employee getEmployeeById(Long id);

	public Employee updateEmployee(Long id, Employee employee);
}
