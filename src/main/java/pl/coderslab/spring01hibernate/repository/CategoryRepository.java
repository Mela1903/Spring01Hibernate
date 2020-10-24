package pl.coderslab.spring01hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernate.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

}
