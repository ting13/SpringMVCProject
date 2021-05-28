package com.example.springboot.controller;

import com.example.springboot.dao.DepartmentDao;
import com.example.springboot.dao.EmployeeDao;
import com.example.springboot.entities.Department;
import com.example.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查詢所有員工返回列表頁面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        //放在請求域中
        model.addAttribute("emps", employees);
        //thymeleaf默認就會拼串
        //classpath:/templates/XXX.html

        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //來到添加頁面
        //先查出所有部門在頁面顯示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";

    }

    //員工添加
    //SpringMVC 自動將請求參數和入參對象一一綁定
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息："+employee);
        employeeDao.save(employee);
        //轉發到員工列表頁面
        return "redirect:/emps";
    }

    //來到修改頁面,查出當前員工,在頁面回顯
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathParam("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        //頁面要顯示所有的部門列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        //回到修改頁面(add是一個修改添加頁面)
        return "emp/add";
    }

   //員工修改
    @PutMapping("/emp")
   public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
   }

   //員工刪除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
System.out.println("1111");
        employeeDao.delete(id);
System.out.println("2222");
        return "redirect:/emps";
    }
}
