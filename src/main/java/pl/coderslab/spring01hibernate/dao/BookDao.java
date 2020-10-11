package pl.coderslab.spring01hibernate.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import pl.coderslab.spring01hibernate.controller.entity.Author;
import pl.coderslab.spring01hibernate.controller.entity.Book;
import pl.coderslab.spring01hibernate.controller.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book));
    }

    public Book findBookWithPublisherById(Long id) {
        Book book = findById(id);
        Hibernate.initialize(book.getPublisher());
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAll(){
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    public List<Book> findByRatingGT(int rating) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating > :minRating");
        query.setParameter("minRating", rating);
        return query.getResultList();
    }

    public List<Book> findWithAnyPublisher() {
        Query query = entityManager.createQuery("SELECT b FROM Book b JOIN b.publisher");
        return query.getResultList();
    }

    public List<Book> findWithSpecificPublisher(Long id) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher.id = :publisherId");
        query.setParameter("publisherId", id);
        return query.getResultList();
    }

    public List<Book> findWithSpecificAuthor(Long id) {
        Query query = entityManager.createQuery("SELECT b FROM Book b JOIN b.author p WHERE p.id = :authorId");
        query.setParameter("authorId", id);
        return query.getResultList();
    }

}
