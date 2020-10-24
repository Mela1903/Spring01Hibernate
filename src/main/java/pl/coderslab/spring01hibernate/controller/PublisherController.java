package pl.coderslab.spring01hibernate.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Publisher;
import pl.coderslab.spring01hibernate.dao.PublisherDao;
import pl.coderslab.spring01hibernate.repository.PublisherRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/publisher", produces = "text/html; charset=UTF-8")
public class PublisherController {

    private final PublisherDao publisherDao;
    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherDao publisherDao, PublisherRepository publisherRepository) {
        this.publisherDao = publisherDao;
        this.publisherRepository = publisherRepository;
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

    @GetMapping("/all")
    public String findAll() {
        return "publisher/list";
    }

    @GetMapping("/addform")
    public String addFormToPublisher(Model m) {
        m.addAttribute("publisher", new Publisher());
        return "publisher/add-form";
    }

    @PostMapping("/addform")
    public String addFormPost(@ModelAttribute("publisher") @Valid Publisher publisher, BindingResult result, Model m){
        if (result.hasErrors()){
            return "publisher/add-form";
        }

        this.publisherDao.save(publisher);
        m.addAttribute("publisher", publisher);
        return "redirect:all";
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers(){
        return publisherDao.findAll();
    }

    @GetMapping("/findbynip/{nip}")
    @ResponseBody
    @Transactional
    public String getByNip(@PathVariable String nip) {
        Publisher publisher = this.publisherRepository.findOneByNip(nip);
        return publisher.toString();

    }

    @GetMapping("/findbyregon/{regon}")
    @ResponseBody
    @Transactional
    public String getByRegon(@PathVariable String regon) {
        Publisher publisher = this.publisherRepository.findOneByRegon(regon);
        return publisher.toString();

    }
}