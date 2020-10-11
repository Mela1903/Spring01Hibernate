package pl.coderslab.spring01hibernate.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.controller.entity.Author;
import pl.coderslab.spring01hibernate.controller.entity.Person;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.PersonDao;

import javax.transaction.Transactional;

@Controller
@RequestMapping(value = "/person", produces = "text/html; charset=UTF-8")
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String newPerson() {
        Person person = new Person();
        person.setLogin("flaku");
        person.setPassword("melunia");
        person.setEmail("aga@gmail.com");
        personDao.save(person);
        return "dodano nowego użytkownika";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id) {
        Person person = personDao.findById(id);
//        Hibernate.initialize(author.getBook());
        return person.toString();
    }

    @RequestMapping("/update/{id}/{login}")
    @ResponseBody
    public String updatePerson(@PathVariable long id, @PathVariable String login) {
        Person person = personDao.findById(id);
        person.setLogin(login);
        personDao.update(person);
        return person.toString();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable long id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
        return "usunięto użytkownika o id: " + person.getId();
    }
}