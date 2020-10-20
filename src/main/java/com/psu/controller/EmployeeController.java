package com.psu.controller;

import com.psu.repository.EmployeeRepository;
import com.psu.service.EmployeeService;
import com.psu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private OrderService orderService;

    private Long graphicIdEdit;

    @GetMapping("/employeeWork")
    public String employeeWork(Model model){

        return "employeeWork";
    }

    @GetMapping("/orderToComplete")
    public String orderToComplete(Model model){
        model.addAttribute("ordersToComplete", orderService.getNotCompleteOrder());

        return "orderToComplete";
    }

    @PostMapping("/orderToComplete")
    public String updateOrder(@RequestParam(required = true, defaultValue = "" ) Long orderId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model){

        if(action.equals("get")) {
            orderService.employeeGetOrder(orderId);
            employeeService.sendOrderToGraphic(orderId);
        }
        return "redirect:/orderToComplete";
    }

    @GetMapping("/orderNotComplete")
    public String notCompleteOrder(Model model){
        model.addAttribute("ordersNotComplete", orderService.getNotCompleteOrdersEmployee());

        return "orderNotComplete";
    }

    @PostMapping("/orderNotComplete")
    public String updateOrderToComplete(@RequestParam(required = true, defaultValue = "" ) Long orderId,
                                @RequestParam(required = true, defaultValue = "" ) String action,
                                Model model){
        if(action.equals("not_complete")) {
            orderService.employeeCompleteOrder(orderId);
        }
        return "redirect:/orderNotComplete";
    }
    @GetMapping("/orderComplete")
    public String completeOrder(Model model){
        model.addAttribute("ordersComplete", orderService.getCompleteOrdersEmployee());

        return "orderComplete";
    }

    @GetMapping("/employeeGraphic")
    public String employeeGraphic(Model model){
        model.addAttribute("employeeGraphic", employeeService.getGraphic());

        return "employeeGraphic";
    }
    @PostMapping("/employeeGraphic")
    public String employeeGraphic(@RequestParam(required = true, defaultValue = "" ) String action,
                                  @RequestParam(required = true, defaultValue = "" ) Long graphicId){

        if(action.equals("edit")) {
            this.graphicIdEdit = graphicId;
            return "redirect:/employeeGraphicEdit";
        }
        return "employeeGraphic";
    }
    @GetMapping("/employeeGraphicEdit")
    public String employeeGraphicEdit(Model model){
        model.addAttribute("dateStart", String.class);
        model.addAttribute("timeStart", String.class);
        model.addAttribute("timeEnd", String.class);

        return "employeeGraphicEdit";
    }
    @PostMapping("/employeeGraphicEdit")
    public String graphicEdit(@RequestParam(required = true, defaultValue = "" ) String dateStart,
                              @RequestParam(required = true, defaultValue = "" ) String timeStart, Model model){

        boolean resultCheck = true;

            if (!employeeService.checkDateStart(dateStart)) {
                model.addAttribute("dateStartError", "Некорректная дата начала экскурсии");
                resultCheck = false;
            }
            if (!employeeService.checkTimeStart(timeStart)) {
                model.addAttribute("timeStartError", "Некорректное время начала(Время работы с 10 до 20)");
                resultCheck = false;
            }

        if(!resultCheck)
            return "employeeGraphicEdit";

        employeeService.sendTimeToGraphic(dateStart, timeStart, graphicIdEdit);

        return "redirect:/employeeGraphic";
    }

}
