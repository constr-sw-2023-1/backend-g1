package constsw.grupoum.courses.domain.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.application.dto.PatchCourseDTO;
import constsw.grupoum.courses.application.dto.NewCourseDTO;
import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.entity.BookRef;
import constsw.grupoum.courses.domain.entity.Course;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course courseDTOToCourse(CourseDTO course);

    Course updateCourse(@MappingTarget() Course entity, CourseDTO dto);

    Course courseUpdateDTOToCourse(PatchCourseDTO course);

    @Mapping(target = "id", defaultExpression = "java(java.util.UUID.randomUUID())")
    Course courseDTOWithoutIdToCourseWithId(CourseDTO course);

    CourseDTO courseToCourseDTO(Course course);

    CourseDTO toCourseDTO(NewCourseDTO course);

    CourseDTO toCourseDTO(PatchCourseDTO course);

    Collection<CourseDTO> toCourseDTOCollection(Collection<Course> courses);

    BookRef bookRefDTOToBookRef(BookRefDTO book);

    BookRefDTO bookRefToBookRefDTO(BookRef book);

}
