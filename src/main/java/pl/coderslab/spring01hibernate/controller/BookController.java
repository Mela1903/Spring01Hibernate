package pl.coderslab.spring01hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Category;
import pl.coderslab.spring01hibernate.entity.Publisher;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.BookDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;
import pl.coderslab.spring01hibernate.repository.AuthorRepository;
import pl.coderslab.spring01hibernate.repository.BookRepository;
import pl.coderslab.spring01hibernate.repository.CategoryRepository;
import pl.coderslab.spring01hibernate.repository.PublisherRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/book", produces = "text/html; charset=UTF-8")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
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
//        Book book = bookDao.findById(id);
        Book book = this.bookRepository.getOne(id);
//        Hibernate.initialize(book.getPublisher());
//        Hibernate.initialize(book.getAuthor());
        return book.toString();
    }

    @RequestMapping("/update/{id}/{title}")
    @ResponseBody
    @Transactional
    public String updateBook(@PathVariable long id, @PathVariable String title ) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "usunięto";
    }

    @RequestMapping("/all")
    @ResponseBody
    @Transactional
    public String findAll() {
//        List<Book> books = bookDao.findAll();
        List<Book> books1 = this.bookRepository.findAll();
        return books1.toString();
    }

    @RequestMapping("/byminrating/{minRating}")
    @ResponseBody
    @Transactional
    public String findAll(@PathVariable int minRating) {
        List<Book> books = bookDao.findByRatingGT(minRating);
        return books.toString();
    }

    @RequestMapping("/get/author/{id}")
    @ResponseBody
    @Transactional
    public String findWithSpecificAuthor(@PathVariable Long id) {
        List<Book> books = bookDao.findWithSpecificAuthor(id);
        return books.toString();
    }

    @RequestMapping("/get/publisher/{id}")
    @ResponseBody
    @Transactional
    public String findWithSpecificPublisher(@PathVariable Long id) {
        List<Book> books = bookDao.findWithSpecificPublisher(id);
        return books.toString();
    }

    @GetMapping("/title/{title}")
    @ResponseBody
    @Transactional
    public String getByTitle(@PathVariable String title) {
        Book book = this.bookRepository.findOneByTitle(title);
        return book.toString();

    }

    @GetMapping("/byCat/{catId}")
    @ResponseBody
    @Transactional
    public String getByCategory(@PathVariable Long catId) {
        Category category = this.categoryRepository.getOne(catId);
        List<Book> books = this.bookRepository.findAllByCategory(category);
        return books.toString();

    }

    @GetMapping("/catId/{catId}")
    @ResponseBody
    @Transactional
    public String getByCategoryId(@PathVariable Long catId) {
        List<Book> books = this.bookRepository.findAllByCategoryId(catId);
        return books.toString();
    }

    @GetMapping("/catName/{catName}")
    @ResponseBody
    @Transactional
    public String getByCategoryId(@PathVariable String catName) {
        List<Book> books = this.bookRepository.findAllByCategoryName(catName);
        return books.toString();
    }

    @GetMapping("/byAut/{autId}")
    @ResponseBody
    @Transactional
    public String getByAuthor(@PathVariable Long autId) {
        Author author = this.authorRepository.getOne(autId);
        List<Book> books = this.bookRepository.findAllByAuthor(author);
        return books.toString();

    }

    @GetMapping("/byPub/{pubId}")
    @ResponseBody
    @Transactional
    public String getByPublisher(@PathVariable Long pubId) {
        Publisher publisher = this.publisherRepository.getOne(pubId);
        List<Book> books = this.bookRepository.findAllByPublisher(publisher);
        return books.toString();

    }

    @GetMapping("/rating/{rating}")
    @ResponseBody
    @Transactional
    public String getByCategoryId(@PathVariable int rating) {
        List<Book> books = this.bookRepository.findAllByRatingGreaterThan(5);
        return books.toString();
    }

    @GetMapping("/onebycat/{catId}")
    @ResponseBody
    @Transactional
    public String getFirstByCategory(@PathVariable Long catId) {
        Category category = this.categoryRepository.getOne(catId);
        Book book = this.bookRepository.findFirstByCategoryOrderByTitleAsc(category);
        return book.toString();

    }

}