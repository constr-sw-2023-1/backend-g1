package constsw.grupoum.courses.domain.service;

import java.util.List;
import java.util.UUID;

import constsw.grupoum.courses.domain.entity.Course;
import org.springframework.stereotype.Service;

import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.mapper.CourseMapper;
import constsw.grupoum.courses.domain.repository.CourseRepository;
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

    public List<CourseDTO> getAll() throws CourseException {

        try {
            List<Course> courses = courseRepository.findAll();
            List<CourseDTO> coursesDTO = (List<CourseDTO>) courseMapper.collectionUsertoCollectionResponseUsers(courses);
            return coursesDTO;
        } catch (Exception e) {
            throw new CourseException(e);
        }

    }

}
