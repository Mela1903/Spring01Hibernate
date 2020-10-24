package pl.coderslab.spring01hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Publisher;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.BookDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/book", produces = "text/html; charset=UTF-8")
public class BookFormController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    @Autowired
    public BookFormController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/addform")
    public String addFormToBook(Model m) {
        m.addAttribute("book", new Book());
        return "book/add-form";
    }

    @PostMapping("/addform")
    public String addFormPost(@ModelAttribute("book") @Valid Book book, BindingResult result, Model m){
        if (result.hasErrors()){
            return "book/add-form";
        }

        this.bookDao.save(book);
        m.addAttribute("book", book);
        return "redirect:list";
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers(){
        return publisherDao.findAllWithBind();
    }

    @GetMapping("/list")
    public String list(){
        return "book/list";
    }

    @ModelAttribute("books")
    public List<Book> books(){
        return bookDao.findAll();
    }

    @GetMapping("/addform2")
    public String addFormToBook2(Model m) {
        m.addAttribute("book", new Book());
        return "book/add-form";
    }

    @PostMapping("/addform2")
    public String addFormPost2(@ModelAttribute Book book, Model m){
        this.bookDao.save(book);
        m.addAttribute("book", book);
        return "redirect:list";
    }
}