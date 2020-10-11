package pl.coderslab.spring01hibernate.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.controller.entity.Author;
import pl.coderslab.spring01hibernate.controller.entity.Book;
import pl.coderslab.spring01hibernate.controller.entity.Publisher;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping(value = "/publisher", produces = "text/html; charset=UTF-8")
public class PublisherController {

    private final PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Helion");
        publisherDao.save(publisher);
        return "dodano";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    @Transactional
    public String getPublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        Hibernate.initialize(publisher.getBooks());
        return publisher.toString();
    }

    @RequestMapping("/update/{id}/{name}")
    @ResponseBody
    public String updateAuthor(@PathVariable long id, @PathVariable String name) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "usunięto publisher o id: " + publisher.getId();
    }

    @RequestMapping("/all")
    @ResponseBody
    @Transactional
    public String findAll() {
        List<Publisher> publishers = publisherDao.findAll();
        return publishers.toString();
    }
}