package constsw.grupoum.courses.domain.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

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

    @Mapping(target = "bibliography", qualifiedByName = "bibliographyToRefBibliography")
    CourseDTO courseToCourseDTO(Course course);

    Collection<CourseDTO> collectionUsertoCollectionResponseUsers(Collection<Course> courses);

    CourseDTO newCourseDTOToCourseDTO(NewCourseDTO course);

    @Mapping(target = "id", defaultExpression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "bibliography", source = "bibliography", qualifiedByName = "refBibliographyToBibliography")
    Course courseDTOWithoutIdToCourseWithId(CourseDTO course);

    @Named("refBibliographyToBibliography")
    default Collection<Book> refBibliographyToBibliography(Collection<String> isbns) {
        return null;
    }

    @Named("bibliographyToRefBibliography")
    default Collection<String> bibliographyToRefBibliography(Collection<Book> books) {
        return books == null ? null
                : books
                        .stream()
                        .map(book -> book.getISBN13())
                        .collect(Collectors.toList());
    }

}
