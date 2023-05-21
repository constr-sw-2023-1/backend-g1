package constsw.grupoum.courses.domain.service;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.mapper.CourseMapper;
import constsw.grupoum.courses.domain.repository.CourseRepository;
import constsw.grupoum.courses.domain.vo.QueryParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public Collection<CourseDTO> getAll() throws CourseException {
        return courseMapper.toCourseDTOCollection(courseRepository.findAll());
    }

    public CourseDTO getById(UUID id) throws CourseException {
        return courseMapper.courseToCourseDTO(courseRepository.findById(id).orElse(null));
    }

    public void deleteById(UUID id) throws CourseException {
        courseRepository.deleteById(id);
    }

    public CourseDTO updateCourse(UUID id, CourseDTO courseDTO) throws CourseException {
        Course course = courseMapper.courseDTOToCourse(courseDTO);
        course.setId(id);

        return courseMapper.courseToCourseDTO(courseRepository.save(course));
    }

    public CourseDTO createCourse(CourseDTO course) throws CourseException {
        return courseMapper
                .courseToCourseDTO(courseRepository.insert(courseMapper.courseDTOWithoutIdToCourseWithId(course)));
    }

    public Collection<CourseDTO> getByComplexQuery(Collection<QueryParam> queries) throws CourseException {
        try {
            return courseMapper.toCourseDTOCollection(courseRepository.findByComplexQuery(queries));
        } catch (CourseException e) {
            throw e;
        } catch (Throwable e) {
            throw new CourseException(e);
        }
    }

    public CourseDTO patchCourse(UUID id, CourseDTO course) throws CourseException {
        return courseMapper
                .courseToCourseDTO(courseRepository.patch(id, courseMapper.courseDTOToCourse(course)));
    }

}
