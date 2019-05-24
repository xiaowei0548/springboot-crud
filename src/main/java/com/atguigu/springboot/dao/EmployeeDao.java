package com.atguigu.springboot.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	static{
		employees = new HashMap<Integer, Employee>();

		employees.put(1001, new Employee(1001, "chenhongwei", "xiaowei_0548@163.com", 1, new Department(101, "CEO")));
		employees.put(1002, new Employee(1002, "chenchengwei", "chenchengwei@163.com", 1, new Department(102, "BIDATA")));
		employees.put(1003, new Employee(1003, "chenhuangwei", "chenhuangwei@163.com", 0, new Department(103, "JAVA")));
		employees.put(1004, new Employee(1004, "chenlvwei", "chenlvwei@163.com", 0, new Department(104, "UI")));
		employees.put(1005, new Employee(1005, "chenqinwei", "chenqinwei@163.com", 1, new Department(105, "BI")));
	}
	
	private static Integer initId = 1006;
	
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	
	public Collection<Employee> getAll(){
		return employees.values();
	}
	
	public Employee get(Integer id){
		return employees.get(id);
	}
	
	public void delete(Integer id){
		employees.remove(id);
	}
}
