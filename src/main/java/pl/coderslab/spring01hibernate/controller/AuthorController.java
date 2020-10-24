package pl.coderslab.spring01hibernate.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Publisher;
import pl.coderslab.spring01hibernate.repository.AuthorRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/author", produces = "text/html; charset=UTF-8")
public class AuthorController {

    private final AuthorDao authorDao;
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorDao authorDao, AuthorRepository authorRepository) {
        this.authorDao = authorDao;
        this.authorRepository = authorRepository;
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

    @GetMapping("/addform")
    public String addFormToAuthor(Model m) {
        m.addAttribute("author", new Author());
        return "author/add-form";
    }

    @PostMapping("/addform")
    public String addFormPost(@ModelAttribute("author") @Valid Author author, BindingResult result, Model m){
        if (result.hasErrors()){
            return "author/add-form";
        }

        this.authorDao.save(author);
        m.addAttribute("author", author);
        return "redirect:all";
    }

    @ModelAttribute("authors")
    public List<Author> authors(){
        return authorDao.findAll();
    }

    @GetMapping("/all")
    public String findAll() {
        return "author/list";
    }


    @GetMapping("/findbyemail/{email}")
    @ResponseBody
    @Transactional
    public String getByEmail(@PathVariable String email) {
        Author author = this.authorRepository.findByEmail(email);
        return author.toString();

    }

    @GetMapping("/findbypesel/{pesel}")
    @ResponseBody
    @Transactional
    public String getByPesel(@PathVariable String pesel) {
        Author author = this.authorRepository.findByPesel(pesel);
        return author.toString();

    }

    @GetMapping("/findbylastname/{lastName}")
    @ResponseBody
    @Transactional
    public String getByLastName(@PathVariable String lastName) {
        List<Author> authors = this.authorRepository.findAllByLastName(lastName);
        return authors.toString();
    }
}