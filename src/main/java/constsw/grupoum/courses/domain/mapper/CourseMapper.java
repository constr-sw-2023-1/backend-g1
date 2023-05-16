package constsw.grupoum.courses.domain.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.application.dto.NewCourseDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.entity.Book;
import constsw.grupoum.courses.domain.entity.Course;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "bibliography", source = "bibliography", qualifiedByName = "refBibliographyToBibliography")
    Course courseDTOToCourse(CourseDTO course);

    @Mapping(target = "bibliography", source = "bibliography", qualifiedByName = "bibliographyToRefBibliography")
    CourseDTO courseToCourseDTO(Course course);

    Collection<CourseDTO> collectionUsertoCollectionResponseUsers(Collection<Course> courses);

    CourseDTO newCourseDTOToCourseDTO(NewCourseDTO course);

    @Mapping(target = "id", defaultExpression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "bibliography", source = "bibliography", qualifiedByName = "refBibliographyToBibliography")
    Course courseDTOWithoutIdToCourseWithId(CourseDTO course);

    @Named("refBibliographyToBibliography")
    default Collection<Book> refBibliographyToBibliography(Collection<String> isbns) {
        if (isbns == null) {
            return null;
        }
        List<Book> books = new ArrayList<Book>();
        for (String isbn : isbns) {
            Book book = new Book();
            book.setISBN13(isbn);
            books.add(book);
        }
        return books;
    }

    @Named("bibliographyToRefBibliography")
    default Collection<String> bibliographyToRefBibliography(Collection<Book> books) {
        if (books == null) {
            return null;
        }
        List<String> isbns = new ArrayList<String>();
        for (Book book : books) {
            if (book != null) {
                isbns.add(book.getISBN13());
            }
        }
        return isbns;
    }

}
