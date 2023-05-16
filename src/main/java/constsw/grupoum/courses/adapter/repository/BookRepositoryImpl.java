package constsw.grupoum.courses.adapter.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.repository.mongo.BookRepositoryMongo;
import constsw.grupoum.courses.domain.entity.Book;
import constsw.grupoum.courses.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {

    private final BookRepositoryMongo bookRepositoryMongo;

    @Override
    public Book insert(Book book) {
        return bookRepositoryMongo.insert(book);
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepositoryMongo.findAll();
    }

    @Override
    public Optional<Book> findById(String isbn13) {
        return bookRepositoryMongo.findById(isbn13);
    }
}
