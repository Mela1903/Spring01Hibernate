package pl.coderslab.spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.model.Person2;

@Controller
@RequestMapping(value = "/person", produces = "text/html; charset=UTF-8")
public class Person2Controller {

    @GetMapping("/form")
    public String personForm(){
        return "person/form";
    }

    @PostMapping("/form")
    public String personFormPost(@RequestParam String login,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 Model m){
        Person2 person2 = new Person2();
        person2.setLogin(login);
        person2.setPassword(password);
        person2.setEmail(email);

        m.addAttribute("person", person2);
        return "person/show";

    }
    @GetMapping("/formbind")
    public String personFormBind(Model m){
        m.addAttribute("person", new Person2());
        return "person/form-bind";
    }

    @PostMapping("/formbind")
    public String personFormBindPost(@ModelAttribute Person2 person2, Model m){
        m.addAttribute("person", person2);
        return "person/show";
    }



}
