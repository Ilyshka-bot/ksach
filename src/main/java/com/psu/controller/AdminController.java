package com.psu.controller;

import com.psu.entity.User;
import com.psu.service.ClientService;
import com.psu.service.EmployeeService;
import com.psu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                employeeService.deleteEmployee(employeeService.getEmployee(user));
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

}
