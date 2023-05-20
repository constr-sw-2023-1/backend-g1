package constsw.grupoum.courses.adapter.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.exception.RepositoryConnectionException;
import constsw.grupoum.courses.adapter.exception.RepositoryDuplicateKeyException;
import constsw.grupoum.courses.adapter.mapper.mongo.MongoEntitiesMapper;
import constsw.grupoum.courses.adapter.repository.mongo.BookRepositoryMongo;
import constsw.grupoum.courses.domain.entity.Book;
import constsw.grupoum.courses.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {

    private final BookRepositoryMongo bookRepositoryMongo;

    private final MongoEntitiesMapper mapper;

    @Override
    public Book insert(Book book) {
        try {
            return mapper.toBook(bookRepositoryMongo.insert(mapper.toMongoBook(book)));
        } catch (DuplicateKeyException e) {
            throw new RepositoryDuplicateKeyException(String.format("%s already registered", book.getIsbn13()), e);
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }

    @Override
    public Collection<Book> findAll() {
        try {
            return mapper.toBookCollection(bookRepositoryMongo.findAll());
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }

    @Override
    public Optional<Book> findById(String isbn13) {
        try {
            return bookRepositoryMongo.findById(isbn13).map(book -> mapper.toBook(book));
        } catch (Exception e) {
            throw new RepositoryConnectionException(e);
        }
    }
}
