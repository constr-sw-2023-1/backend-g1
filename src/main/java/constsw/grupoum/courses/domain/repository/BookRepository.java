package constsw.grupoum.courses.domain.repository;

import java.util.Collection;
import java.util.Optional;

import constsw.grupoum.courses.domain.entity.Book;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.vo.QueryParam;

public interface BookRepository {

    Book insert(Book book);

    Collection<Book> findAll();

    Optional<Book> findById(String isbn13);

    Collection<Book> findByComplexQuery(Collection<QueryParam> queries) throws CourseException;

}
