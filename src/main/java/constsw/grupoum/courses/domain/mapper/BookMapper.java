package constsw.grupoum.courses.domain.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.domain.dto.BookDTO;
import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.entity.Book;
import constsw.grupoum.courses.domain.entity.BookRef;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookDTOToBook(BookDTO book);

    BookDTO bookToBookDTO(Book book);

    BookRefDTO toBookRefDTO(Book book);

    Collection<BookRef> toBookRefCollection(Collection<BookRefDTO> bookRef);

    Collection<BookDTO> collectionOfBookToCollectionOfBookDTO(Collection<Book> books);
}
