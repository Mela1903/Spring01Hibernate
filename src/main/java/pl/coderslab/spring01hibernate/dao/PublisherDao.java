package pl.coderslab.spring01hibernate.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.spring01hibernate.controller.entity.Book;
import pl.coderslab.spring01hibernate.controller.entity.Publisher;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public void update(Publisher publisher) {
        entityManager.merge(publisher);
    }

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void delete(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ?
                publisher : entityManager.merge(publisher));
    }
    public List<Publisher> findAll(){
        Query query = entityManager.createQuery("SELECT b FROM Publisher b");
        return query.getResultList();
    }

    public List<Publisher> findAllWithBind(){
        Query query = entityManager.createQuery("SELECT p FROM Publisher p");
        return query.getResultList();
    }
}
