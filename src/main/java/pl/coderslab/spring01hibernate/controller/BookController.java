package pl.coderslab.spring01hibernate.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.controller.entity.Author;
import pl.coderslab.spring01hibernate.controller.entity.Book;
import pl.coderslab.spring01hibernate.controller.entity.Publisher;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.BookDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/book", produces = "text/html; charset=UTF-8")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    @Autowired
    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addBook() {
        Book book = new Book();
        book.setTitle("Thinking in Java");
        book.setDescription("Programming");
        book.setRating(5);
        bookDao.save(book);
        return "dodano nową książkę o id: " + book.getId();
    }

    @GetMapping("/addwp")
    @ResponseBody
    public String addBookWithPublisher() {
        Book book = new Book();
        book.setTitle("Thinking in Java");
        Publisher publisher = new Publisher();
        publisher.setName("Helion");
        publisherDao.save(publisher);
        book.setPublisher(publisher);
        bookDao.save(book);
        return "dodano nową książkę o id: " + book.getId() + " i publisher o id: " + publisher.getId();
    }

    @GetMapping("/addwa")
    @ResponseBody
    public String addBookWithTwoAuthors() {

        List<Author> authors = new ArrayList<>();
        Author aga = authorDao.findById(1);
        Author damian = authorDao.findById(2);
        authors.add(aga);
        authors.add(damian);

        Book book = new Book();
        book.setTitle("Thinking in Java");
        book.setAuthor(authors);
        bookDao.save(book);

        return "dodano nową książkę o id: " + book.getId();
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    @Transactional
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        Hibernate.initialize(book.getPublisher());
        Hibernate.initialize(book.getAuthor());
        return book.toString();
    }

    @PutMapping("/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title ) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "usunięto";
    }
}