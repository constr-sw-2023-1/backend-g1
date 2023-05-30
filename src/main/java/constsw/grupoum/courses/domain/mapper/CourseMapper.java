package constsw.grupoum.courses.domain.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.CourseUnitDTO;
import constsw.grupoum.courses.domain.dto.NewCourseDTO;
import constsw.grupoum.courses.domain.dto.PatchCourseDTO;
import constsw.grupoum.courses.domain.dto.UnitTopicDTO;
import constsw.grupoum.courses.domain.entity.BookRef;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.entity.CourseUnit;
import constsw.grupoum.courses.domain.entity.UnitTopic;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toCourse(CourseDTO course);

    Course updateCourse(@MappingTarget() Course entity, CourseDTO dto);

    Course toCourse(PatchCourseDTO course);

    @Mapping(target = "id", defaultExpression = "java(java.util.UUID.randomUUID())")
    Course toCourseWithId(CourseDTO course);

    CourseDTO toCourseDTO(Course course);

    CourseDTO toCourseDTO(NewCourseDTO course);

    CourseDTO toCourseDTO(PatchCourseDTO course);

    Collection<CourseDTO> toCourseDTOCollection(Collection<Course> courses);

    Collection<BookRef> toBookRefCollection(Collection<BookRefDTO> booksRefs);

    BookRef toBookRef(BookRefDTO book);

    BookRefDTO toBookRefDTO(BookRef book);

    //Collection<SyllabusUnitDTO> toSyllabusUnitDTOCollection(Collection<SyllabusUnit> unitsSyllabusUnits);
    Collection<CourseUnitDTO> toCourseUnitDTOCollection(Collection<CourseUnit> unitsCourseUnits);

    //SyllabusUnitDTO toSyllabusUnitDTO(SyllabusUnit unit);
    CourseUnitDTO toCourseUnitDTO(CourseUnit unit);

    UnitTopicDTO toUnitTopicDTO(UnitTopic unitTopic);

    Collection<UnitTopicDTO> toUnitTopicDTOCollection(Collection<UnitTopic> unitTopics);

}
