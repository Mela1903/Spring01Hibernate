package pl.coderslab.spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.controller.entity.Author;
import pl.coderslab.spring01hibernate.controller.entity.Publisher;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;

@Controller
public class PublisherController {

    private final PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @RequestMapping(path = "/publisher/add", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String newPublisher() {
        Publisher publisher = new Publisher();
//        book.setTitle("Thinking in Java");
//        book.setRating(5);
//        book.setDescription("ble ble");
        publisherDao.savePublisher(publisher);
        return "dodano";
    }
    @RequestMapping(path = "/publisher/get/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String getPublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @RequestMapping(path = "/publisher/update/{id}/{name}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String updateAuthor(@PathVariable long id, @PathVariable String name ) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @RequestMapping(path = "/publisher/delete/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "usunięto";
    }
}