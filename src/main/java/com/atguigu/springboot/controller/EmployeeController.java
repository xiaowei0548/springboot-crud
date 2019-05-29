package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
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

    /**
     * 来到员工添加页面
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        model.addAttribute("depts",departmentDao.getDepartments());
        //来到添加页面
        return "emp/add";
    }

    /**
     * 员工添加
     * @return
     * SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javabean入参的对象里面的属性名是一样的
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        // redirect: 表示重定向到一个地址
        // forward： 表示转发到一个地址
        Logger logger = LoggerFactory.getLogger(EmployeeController.class);
        logger.info("保存的员工信息： "+ employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 來导修改员工的页面，先查出当前员工，在页面显示
     * @return
     * @PathVariable获取路径变量
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp", employee);
        //显示所有的部门
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    /**
     * 员工修改
     * @return
     */
    @PutMapping("/emp") //以put的方式发过来的请求
    public String updateEmployee(Employee employee) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("【修改】的员工信息："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, Model model){
        employeeDao.delete(id);
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "redirect:/emps";
    }

}
