package com.psu.controller;

import com.psu.entity.*;
import com.psu.repository.OrderRepository;
import com.psu.repository.UserRepository;
import com.psu.service.ClientService;
import com.psu.service.EmployeeService;
import com.psu.service.OrderService;
import com.psu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class ClientController{
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping("/clientWork")
    public String clientWork(Model model){
        model.addAttribute("allExcursions", clientService.allExcursion());
        model.addAttribute("viewExcursionForm", new ViewExcursion());

        return "clientWork";
    }

    @PostMapping("/clientWork")
    public String saveOrder(@ModelAttribute("viewExcursionForm") @Valid ViewExcursion viewExcursion, Model model){

        orderService.saveOrder(viewExcursion);
        return "clientWork";
    }

    @GetMapping("/clientList")
    public String clientList(Model model) {
        model.addAttribute("allClients", clientService.allClients());
        return "clientList";
    }


    @PostMapping("/clientList")
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
    @GetMapping("/clientOrderList")
    public String getOrder(Model model){
        model.addAttribute("myOrders", orderService.getMyOrders());
        return "clientOrderList";
    }

    @GetMapping("/clientNotOrderList")
    public String clientNotOrderList(Model model){
        model.addAttribute("myOrders", orderService.getNotMyOrder());
        return "clientNotOrderList";
    }

}
