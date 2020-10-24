package pl.coderslab.spring01hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.entity.*;
import pl.coderslab.spring01hibernate.dao.*;

import javax.transaction.Transactional;

@Controller
@RequestMapping(value = "/perdet", produces = "text/html; charset=UTF-8")
public class PersonDetailsController {

    private final PersonDetailsDao personDetailsDao;
    private final PersonDao personDao;

    @Autowired
    public PersonDetailsController(PersonDetailsDao personDetailsDao, PersonDao personDao) {
        this.personDetailsDao = personDetailsDao;
        this.personDao = personDao;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addPersonDetails() {
        PersonDetails details = new PersonDetails();
        details.setFirstName("Aga");
        details.setLastName("Flak");
        details.setStreet("Nullo");
        details.setStreetNumber(19);
        details.setCity("Kraków");

        Person person = new Person();
        person.setLogin("dami");
        person.setPassword("tronik");
        person.setEmail("damian@gmail.com");
        personDao.save(person);
        details.setPerson(person);
        personDetailsDao.save(details);

        return "dodano detale użytkownika o id: " + details.getId();
    }

    @GetMapping("/addwp")
    @ResponseBody
    public String addDetailsWithPerson() {
        PersonDetails details = new PersonDetails();
        details.setFirstName("Damian");
        details.setLastName("Luje Ponce");
        details.setStreet("Primavera");
        details.setStreetNumber(23);
        details.setCity("Quito");
        Person person = new Person();
        person.setLogin("damisrami");
        personDao.save(person);
        details.setPerson(person);
        personDetailsDao.save(details);
        return "dodano detale użytkownika o id: " + details.getId() + " i loginie: " + person.getLogin();
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    @Transactional
    public String getBook(@PathVariable long id) {
        PersonDetails details = personDetailsDao.findById(id);
//        Hibernate.initialize(details.getPerson());
        return details.toString();
    }

    @PutMapping("/update/{id}/{title}")
    @ResponseBody
    public String updateDetails(@PathVariable long id, @PathVariable String street) {
        PersonDetails details = personDetailsDao.findById(id);
        details.setStreet(street);
        personDetailsDao.update(details);
        return details.toString();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteDetails(@PathVariable long id) {
        PersonDetails details = personDetailsDao.findById(id);
        personDetailsDao.delete(details);
        return "usunięto użytkownika";
    }
}