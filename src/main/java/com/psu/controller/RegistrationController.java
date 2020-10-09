package com.psu.controller;

import com.psu.entity.Client;
import com.psu.entity.Employee;
import com.psu.entity.Post;
import com.psu.entity.User;
import com.psu.service.ClientService;
import com.psu.service.EmployeeService;
import com.psu.service.PostService;
import com.psu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(){

        return "index";
    }
    @GetMapping("/news")
    public String news(){

        return "news";
    }
    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/regEmp")
    public String admin(Model model){
        model.addAttribute("userForm", new User());
        model.addAttribute("employeeForm", new Employee());
        model.addAttribute("postForm", new Post());



        return "regEmp";
    }

    @PostMapping("/regEmp")
    public String addEmp(@ModelAttribute("userForm") @Valid User userForm,
                         @ModelAttribute("employeeForm") @Valid Employee employeeForm,
                         @ModelAttribute("postForm") @Valid Post postForm,
                         BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            return "regEmp";
        }
        if(userService.isValidEmailAddress(userForm.getMail()) == false){
            model.addAttribute("mailError","Не валидная почта");
            return "regEmp";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "regEmp";
        }
        if (!userService.saveEmployee(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "regEmp";
        }

        employeeForm.setUser(userForm);
        if(postForm.getName().equals("Экскурсовод")) {
            employeeForm.setPost(postService.getPost("POST_GUIDE"));
        }
        else if(postForm.getName().equals("Работник")){
            employeeForm.setPost(postService.getPost("POST_WORKER"));
        }

        employeeService.saveEmployee(employeeForm);

        return "admin";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("clientForm", new Client());

        return "registration";
    }

    @PostMapping("/registration")
   public String addClient(@ModelAttribute("userForm") @Valid User userForm,
                          @ModelAttribute("clientForm") @Valid Client clientForm,
                          BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if(userService.isValidEmailAddress(userForm.getMail()) == false){
            model.addAttribute("mailError","Не валидная почта");
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        clientForm.setUser(userForm);
        clientService.saveClient(clientForm);
        return "redirect:/";
    }

}