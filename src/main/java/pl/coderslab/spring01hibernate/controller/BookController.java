package pl.coderslab.spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.controller.entity.Book;
import pl.coderslab.spring01hibernate.dao.BookDao;

@Controller
public class BookController {

    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @RequestMapping(path = "/book/add", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String newBook() {
        Book book = new Book();
//        book.setTitle("Thinking in Java");
//        book.setRating(5);
//        book.setDescription("ble ble");
        bookDao.saveBook(book);
        return "dodano nową książkę";
    }
    @RequestMapping(path = "/book/get/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping(path = "/book/update/{id}/{title}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title ) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping(path = "/book/delete/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "usunięto";
    }
}