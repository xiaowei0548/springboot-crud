package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    /**
     * 查询所有元列表 返回列表页面
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中进行共享
        model.addAttribute("emps",employees);

        //thymeleaf自动会拼串
        //classpath： /templates/xxxx.html
        return "emp/list";
    }
}
