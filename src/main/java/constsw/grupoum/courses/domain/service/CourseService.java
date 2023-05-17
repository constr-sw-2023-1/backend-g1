package constsw.grupoum.courses.domain.service;

import java.util.UUID;

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

    public void deleteById(UUID id) throws CourseException {
        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            throw new CourseException(e);
        }
    }

    public CourseDTO updateCourse(UUID id, CourseDTO courseDTO) throws CourseException {
        try {
            //courseMapper.courseDTOToCourse(courseDTO);
            //courseRepository.save(courseDTO);
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

}
