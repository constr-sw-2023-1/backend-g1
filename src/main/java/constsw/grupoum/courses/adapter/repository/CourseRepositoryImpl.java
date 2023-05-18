package constsw.grupoum.courses.adapter.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import constsw.grupoum.courses.adapter.repository.mongo.CourseRepositoryMongo;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.repository.CourseRepository;
import constsw.grupoum.courses.domain.vo.QueryParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseRepositoryMongo courseRepositoryMongo;

    @Override
    public Optional<Course> findById(UUID id) {
        return courseRepositoryMongo.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        courseRepositoryMongo.deleteById(id);
    }

    @Override
    public Course insert(Course course) {
        return courseRepositoryMongo.insert(course);
    }

    @Override
    public Collection<Course> findByComplexQuery(Collection<QueryParam> queries) {

    
        

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByComplexQueries'");
    }
}
