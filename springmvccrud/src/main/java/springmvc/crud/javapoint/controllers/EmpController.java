package springmvc.crud.javapoint.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springmvc.crud.javapoint.beans.Emp;
import springmvc.crud.javapoint.dao.EmpDao;

@Controller
public class EmpController {
    @Autowired
    EmpDao dao;

    @RequestMapping("/empform")
    public String showform(Model m) {
        // empform.jsp post的資料存成emp()對象
        m.addAttribute("command", new Emp());
        return "empform";
    }

    // empform.jsp提交, emp()存到db ,重定向到viewemp請求
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("emp") Emp emp) {
        dao.save(emp);
        return "redirect:/viewemp";
    }

    // db查詢所有emp返回給list, 返回給viewemp.jsp顯示這些emp
    @RequestMapping("/viewemp")
    public String viewemp(Model m) {
        List<Emp> list = dao.getEmployees();
        m.addAttribute("list", list);
        return "viewemp";
    }

    // 由id找到要要修改的emp, 並傳給 empeditform.jsp
    @RequestMapping("/editemp/{id}")
    public String edit(@PathVariable("id") int id, Model m) {
        Emp emp = dao.getEmpById(id);
        m.addAttribute("command", emp);
        return "empeditform";
    }

    // db保存修改後的emp, 重定向到viewemp請求
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("emp") Emp emp) {
        dao.update(emp);
        return "redirect:/viewemp";
    }

    // db由id刪除emp
    @GetMapping("/deleteemp/{id}")
    public String delete(@PathVariable int id) {
        dao.delete(id);
        return "redirect:/viewemp";
    }
}
