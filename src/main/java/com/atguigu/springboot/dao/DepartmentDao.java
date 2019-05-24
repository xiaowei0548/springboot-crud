package com.atguigu.springboot.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.atguigu.springboot.entities.Department;
import org.springframework.stereotype.Repository;


@Repository
public class DepartmentDao {

	private static Map<Integer, Department> departments = null;
	
	static{
		departments = new HashMap<Integer, Department>();
		
		departments.put(101, new Department(101, "CEO"));
		departments.put(102, new Department(102, "BIGDATA"));
		departments.put(103, new Department(103, "JAVA"));
		departments.put(104, new Department(104, "UI"));
		departments.put(105, new Department(105, "BI"));
	}
	
	public Collection<Department> getDepartments(){
		return departments.values();
	}
	
	public Department getDepartment(Integer id){
		return departments.get(id);
	}
	
}
