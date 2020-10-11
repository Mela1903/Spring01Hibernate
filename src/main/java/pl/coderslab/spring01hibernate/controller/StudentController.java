package pl.coderslab.spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.spring01hibernate.model.Person2;
import pl.coderslab.spring01hibernate.model.Student;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/student", produces = "text/html; charset=UTF-8")
public class StudentController {

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }

//    @ModelAttribute("skills")
//    public List<String> skills() {
//        return Arrays.asList("Java", "C++", "PHP", "Ruby");
//    }

    @ModelAttribute("skills")
    public Map<String, String> getSkillsList() {
        Map<String, String> skillList = new HashMap<String, String>();
        skillList.put("Hibernate", "Hibernate");
        skillList.put("Spring", "Spring");
        skillList.put("Apache Wicket", "Apache Wicket");
        skillList.put("Struts", "Struts");
        return skillList;
    }

    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        return Arrays.asList("Singing", "Fishing", "Dancing", "Drinking");
    }

    @GetMapping("/formbind")
    public String studenFormBind(Model m){
        m.addAttribute("student", new Student());
        return "student/form-bind";
    }

    @PostMapping("/formbind")
    public String studentFormBindPost(@ModelAttribute Student student, Model m){
        m.addAttribute("student", student);
        return "student/show";
    }

}
