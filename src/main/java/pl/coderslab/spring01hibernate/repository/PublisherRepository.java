package pl.coderslab.spring01hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Publisher;


public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findOneByNip(String nip);
    Publisher findOneByRegon(String regon);

}
