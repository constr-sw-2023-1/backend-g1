package constsw.grupoum.courses.domain.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.application.dto.NewCourseDTO;
import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.CourseUpdateDTO;
import constsw.grupoum.courses.domain.entity.BookRef;
import constsw.grupoum.courses.domain.entity.Course;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course courseDTOToCourse(CourseDTO course);

    Course courseUpdateDTOToCourse(CourseUpdateDTO course);

    @Mapping(target = "id", defaultExpression = "java(java.util.UUID.randomUUID())")
    Course courseDTOWithoutIdToCourseWithId(CourseDTO course);

    CourseDTO courseToCourseDTO(Course course);

    CourseDTO newCourseDTOToCourseDTO(NewCourseDTO course);

    Collection<CourseDTO> collectionUsertoCollectionResponseUsers(Collection<Course> courses);

    BookRef bookRefDTOToBookRef(BookRefDTO book);

    BookRefDTO bookRefToBookRefDTO(BookRef book);

}
