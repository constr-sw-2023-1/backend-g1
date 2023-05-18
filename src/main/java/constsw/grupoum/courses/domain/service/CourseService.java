package constsw.grupoum.courses.domain.service;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
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

    public CourseDTO getById(UUID id) throws CourseException {
        try {
            return courseMapper.courseToCourseDTO(courseRepository.findById(id).orElse(null));
        } catch (Exception e) {
            throw new CourseException(e);
        }
    }

    public void deleteById(UUID id) throws CourseException {
        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            throw new CourseException(e);
        }
    }

    public CourseDTO createCourse(CourseDTO course) throws CourseException {
        try {
            return courseMapper
                    .courseToCourseDTO(courseRepository.insert(courseMapper.courseDTOWithoutIdToCourseWithId(course)));
        } catch (Exception e) {
            throw new CourseException(e);
        }
    }

    public Collection<CourseDTO> getByComplexQuery(Collection<QueryParam> queries) throws CourseException {
        try {
            return courseMapper.courseCollectionToCourseDTOCollection(courseRepository.findByComplexQuery(queries));
        } catch (Exception e) {
            throw new CourseException(e);
        }
    }

}
