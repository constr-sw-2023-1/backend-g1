package constsw.grupoum.courses.application.usecase.course;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Service;

import constsw.grupoum.courses.application.exception.ApplicationException;
import constsw.grupoum.courses.application.mapper.QueryMapper;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import constsw.grupoum.courses.domain.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindCourseByComplexQueryUC {

    private final CourseService courseService;

    private final QueryMapper queryMapper;

    public Collection<CourseDTO> run(Map<String, String> queryParams) throws CourseException, ApplicationException {
        return courseService.getByComplexQuery(queryMapper.mapToCollectionOfQueryParam(queryParams));
    }
}
