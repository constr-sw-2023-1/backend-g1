package constsw.grupoum.courses.adapter.repository.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import constsw.grupoum.courses.domain.entity.Course;

public interface CourseRepositoryJpa extends JpaRepository<Course, UUID> {

}
