package pl.coderslab.spring01hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Category;
import pl.coderslab.spring01hibernate.entity.Publisher;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findOneByTitle(String title);
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByCategoryId(long categoryId);
    List<Book> findAllByCategoryName(String name);


    List<Book> findAllByAuthor(Author author);
    List<Book> findAllByPublisher(Publisher publisher);
    List<Book> findAllByRatingGreaterThan(int rating);
    Book findFirstByCategoryOrderByTitleAsc(Category category);

}
