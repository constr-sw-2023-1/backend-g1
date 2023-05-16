package constsw.grupoum.courses.domain.repository;

import java.util.Collection;
import java.util.Optional;

import constsw.grupoum.courses.domain.entity.Book;

public interface BookRepository {

    Book insert(Book book);

    Collection<Book> findAll();

    Optional<Book> findById(String isbn13);

}
