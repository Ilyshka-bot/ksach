package com.psu.controller;

import com.psu.entity.*;
import com.psu.service.SecurityServices;
import com.psu.service.*;
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
    @Autowired
    private SecurityServices securityServices;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/regEmp")
    public String regEmp(Model model){
        model.addAttribute("userForm", new User());
        model.addAttribute("employeeForm", new Employee());
        model.addAttribute("postForm", new Post());

        return "regEmp";
    }

    @PostMapping("/regEmp")
    public String addEmp(@ModelAttribute("userForm") @Valid User userForm,
                         @ModelAttribute("employeeForm") @Valid Employee employeeForm,
                         @ModelAttribute("postForm") @Valid Post postForm,
                         BindingResult bindingResult, Model model) {
        boolean resultCheck = true;

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userForm.getPassword().equals("") || userForm.getPasswordConfirm().equals("")) {
            String emptyRes = "Заполните поле";
            if (userForm.getPassword().equals("")) model.addAttribute("passwordEmpty", emptyRes);
            if (userForm.getPasswordConfirm().equals("")) model.addAttribute("passwordError", emptyRes);
            resultCheck = false;
        }
        if (userService.isValidEmailAddress(userForm.getMail()) == false) {
            model.addAttribute("mailError", "Не валидная почта");
            resultCheck = false;
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            resultCheck = false;
        }
        if (!userService.checkFio(userForm.getFullname())) {
            model.addAttribute("fullnameError", "Некорректное ФИО");
            resultCheck = false;
        }

        if (!userService.checkUsername(userForm.getUsername())) {
            model.addAttribute("usernameError", "Некорректное имя пользователя");
            resultCheck = false;
        }
        if (!userService.checkUsernameInBD(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            resultCheck = false;
        }
        if (!employeeForm.getDate_end().equals("")) {
            if (!userService.isCorrectDate(employeeForm.getDate_start(), employeeForm.getDate_end(),"reg")) {
                model.addAttribute("dateendError", "Некорректная дата окончания");
                resultCheck = false;
            }
        }
        if (employeeForm.getExperience().matches("\\d+")){
            if (Integer.parseInt(employeeForm.getExperience()) < 0 || Integer.parseInt(employeeForm.getExperience()) > 65) {
                model.addAttribute("experienceError", "Некорректный стаж работы");
                resultCheck = false;
            }
        }
        else{
            model.addAttribute("experienceError", "Некорректный стаж работы");
            resultCheck = false;
        }

        if(!resultCheck) return "regEmp";

        if(postForm.getName().equals("Экскурсовод")) {
            employeeForm.setPost(postService.getPost("POST_GUIDE"));
        }
        else if(postForm.getName().equals("Работник")){
            employeeForm.setPost(postService.getPost("POST_WORKER"));
        }
        employeeService.saveEmployee(employeeForm, userForm);
        return "redirect:/";
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
        boolean resultCheck = true;

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if(userForm.getPassword().equals("") || userForm.getPasswordConfirm().equals("")){
            String emptyRes = "Заполните поле";
            if(userForm.getPassword().equals("")) model.addAttribute("passwordEmpty",emptyRes);
            if(userForm.getPasswordConfirm().equals("")) model.addAttribute("passwordError",emptyRes);
            resultCheck = false;
        }
        if(userService.isValidEmailAddress(userForm.getMail()) == false){
            model.addAttribute("mailError","Не валидная почта");
            resultCheck = false;
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            resultCheck = false;
        }
        if(!userService.checkFio(userForm.getFullname())) {
            model.addAttribute("fullnameError", "Некорректное ФИО");
            resultCheck = false;
        }
        if(!userService.checkUsername(userForm.getUsername())){
            model.addAttribute("usernameError", "Некорректное имя пользователя");
            resultCheck = false;
        }
        if (!userService.checkUsernameInBD(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            resultCheck = false;
        }
        if(!userService.checkPassportData(clientForm.getPassportData())){
            model.addAttribute("passportError", "Некорректные паспортные данные");
            resultCheck = false;
        }

        if(!resultCheck) return "registration";

        userService.saveUser(userForm);
        clientForm.setUser(userForm);
        clientService.saveClient(clientForm);
        securityServices.autoLogin(userForm.getUsername(),userForm.getPasswordConfirm());
        return "redirect:/";
    }
}