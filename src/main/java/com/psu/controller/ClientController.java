package com.psu.controller;

import com.psu.entity.*;
import com.psu.object.ListObjectExcursion;
import com.psu.repository.OrderRepository;
import com.psu.repository.UserRepository;
import com.psu.service.ClientService;
import com.psu.service.EmployeeService;
import com.psu.service.OrderService;
import com.psu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
        model.addAttribute("allExcursionsName", clientService.getAllExcName());
        model.addAttribute("name", String.class);

        return "clientWork";
    }

    @PostMapping("/clientWork")
    public String saveOrder(@RequestParam(required = true, defaultValue = "" ) String name,
                            @RequestParam(required = true, defaultValue = "" ) String action,
                            @RequestParam(required = true, defaultValue = "" ) String serial_id,
                            Model model){


        if(action.equals("delete")){
            if(serial_id.equals(""))
            {
                model.addAttribute("serialError", "Некорректный номер");
                model.addAttribute("allExcursions", clientService.allExcursion());
                model.addAttribute("allExcursionsName", clientService.getAllExcName());

                return "clientWork";
            }
            try {
                clientService.deleteExcursion(Long.valueOf(serial_id));

            }catch (NumberFormatException b){
                model.addAttribute("serialError", "Некорректный номер");
                model.addAttribute("allExcursions", clientService.allExcursion());
                model.addAttribute("allExcursionsName", clientService.getAllExcName());
                return "clientWork";

            }
            return "redirect:/clientWork";

        }

        orderService.saveOrder(name);
        return "redirect:/clientWork";
    }

    @GetMapping("/clientList")
    public String clientList(Model model) {
        model.addAttribute("allClients", clientService.allClients());
        return "clientList";
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
    @PostMapping("/clientNotOrderList")
    public String clientList(@RequestParam(defaultValue = "") String action,
                             @RequestParam(defaultValue = "" ) String serial_id,
                             Model model){

        if(action.equals("cancel")){
            if(serial_id.equals(""))
            {
                model.addAttribute("serialError", "Некорректный номер");
                model.addAttribute("myOrders", orderService.getNotMyOrder());

                return "clientNotOrderList";
            }
            try {
                clientService.cancelOrder(Long.valueOf(serial_id));
            }catch (NumberFormatException n){
                model.addAttribute("serialError", "Некорректный номер");
                model.addAttribute("myOrders", orderService.getNotMyOrder());
                return "clientNotOrderList";
            }
        }
        return "redirect:/clientNotOrderList";
    }

    @GetMapping("/clientMakeExcursion")
    public String clientMakeExcursion(Model model){
        model.addAttribute("objectsForm", new ListObjectExcursion());
        model.addAttribute("viewExcursionForm", new ViewExcursion());

        return "clientMakeExcursion";
    }

    @PostMapping("/clientMakeExcursion")
    public String clientPostExcursion(@ModelAttribute("objectsForm") @Valid ListObjectExcursion objectsForm,
                                      @ModelAttribute("viewExcursionForm") @Valid ViewExcursion viewExcursionForm,
                                        Model model){
        boolean resultCheck = true;

        if(objectsForm.getObjects().length == 0){
            model.addAttribute("objectsError","Выберите хотя бы одну программу");
            resultCheck = false;
        }
        if (objectsForm.getName().length() < 2){
            model.addAttribute("nameExcursionError", "Название должно содержать более 2-ух символов");
            resultCheck = false;
        }

        if(resultCheck == false)
            return "clientMakeExcursion";

        clientService.insertExcursion(objectsForm, viewExcursionForm);

        return "redirect:/clientWork";
    }


}
