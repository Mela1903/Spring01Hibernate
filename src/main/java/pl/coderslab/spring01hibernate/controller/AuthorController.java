package pl.coderslab.spring01hibernate.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.controller.entity.Author;
import pl.coderslab.spring01hibernate.dao.AuthorDao;

import javax.transaction.Transactional;

@Controller
@RequestMapping(value = "/author", produces = "text/html; charset=UTF-8")
public class AuthorController {

    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String newAuthor() {
        Author author = new Author();
        author.setFirstName("Bruce");
        author.setFirstName("Eckel");
        authorDao.save(author);
        return "dodano nowego autora";
    }

    @GetMapping(path = "/get/{id}")
    @ResponseBody
    @Transactional
    public String getAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        Hibernate.initialize(author.getBook());
        return author.toString();
    }

    @RequestMapping(path = "/author/update/{id}/{firstName}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String updateAuthor(@PathVariable long id, @PathVariable String firstName) {
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        authorDao.update(author);
        return author.toString();
    }

    @RequestMapping(path = "/author/delete/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "usunięto";
    }
}