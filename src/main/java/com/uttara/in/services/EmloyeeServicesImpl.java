package com.uttara.in.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uttara.in.Repository.EmployeeRepo;
import com.uttara.in.entity.EmployeeEntity;
import com.uttara.in.model.Employee;

@Service
public class EmloyeeServicesImpl implements EmployeeServices {
	@Autowired
	private EmployeeRepo employeerepo;

	public EmloyeeServicesImpl(EmployeeRepo employeerepo) {
		super();
		this.employeerepo = employeerepo;
	}

	public Employee createEmployee(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		employeerepo.save(employeeEntity);
		return employee;
	}

	public List<Employee> getAllEmployees() {
		List<EmployeeEntity> employeeEntities = employeerepo.findAll();
		List<Employee> employees = employeeEntities.stream()
				.map(emp -> new Employee(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getEmailId()))
				.collect(Collectors.toList());
		return employees;
	}

	public boolean deleteEmployee(Long id) {
		EmployeeEntity employee = employeerepo.findById(id).get();
		employeerepo.delete(employee);
		return true;
	}

	public Employee getEmployeeById(Long id) {
		EmployeeEntity employeeEntity = employeerepo.findById(id).get();
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeEntity, employee);
		return employee;
	}

	public Employee updateEmployee(Long id, Employee employee) {
		EmployeeEntity employeeEntity = employeerepo.findById(id).get();
		employeeEntity.setFirstName(employee.getFirstName());
		employeeEntity.setLastName(employee.getLastName());
		employeeEntity.setEmailId(employee.getEmailId());
		employeerepo.save(employeeEntity);
		return employee;
	}

}
