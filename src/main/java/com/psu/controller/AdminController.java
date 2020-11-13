package com.psu.controller;

import com.psu.entity.Employee;
import com.psu.entity.User;
import com.psu.service.ClientService;
import com.psu.service.EmployeeService;
import com.psu.service.UserService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static jdk.jfr.internal.SecuritySupport.getResourceAsStream;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }


    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {

        if (action.equals("delete")){
            User user = new User();
            user.setId(userId);
            if(clientService.getClient(user) != null){
                clientService.deleteClient(clientService.getClient(user));
            }
            if(employeeService.getEmployee(user) != null){
                Employee employee = employeeService.getEmployee(user);
                employee.setPost(null);
                employeeService.deleteEmployee(employee);
            }

            userService.deleteUser(userId);
        }

        return "redirect:/admin";
    }


    @GetMapping("/admin/gt/{userId}")
    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }
    @RequestMapping(value = "report", method = RequestMethod.GET)
    public void report(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        response.addHeader("Content-disposition", "attachment; filename=user_list.pdf");

        ServletOutputStream servletOutputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint,servletOutputStream);

    }
}
