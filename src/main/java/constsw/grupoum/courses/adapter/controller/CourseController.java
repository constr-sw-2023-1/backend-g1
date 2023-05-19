package constsw.grupoum.courses.adapter.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import constsw.grupoum.courses.application.dto.CourseUpdateDTO;
import constsw.grupoum.courses.application.dto.NewCourseDTO;
import constsw.grupoum.courses.application.usecase.CreateCourseUC;
import constsw.grupoum.courses.application.usecase.DeleteCourseByIdUC;
import constsw.grupoum.courses.application.usecase.FindAllCoursesUC;
import constsw.grupoum.courses.application.usecase.FindCourseByIdUC;
import constsw.grupoum.courses.application.usecase.UpdateCourseUC;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/courses")
@RestController
public class CourseController {

    private final FindAllCoursesUC findAll;

    private final FindCourseByIdUC findByIdUC;

    private final DeleteCourseByIdUC deleteById;

    private final UpdateCourseUC updateCourse;

    private final CreateCourseUC createCourse;

    @GetMapping
    public ResponseEntity<?> getAll() throws Throwable {
        return ResponseEntity.ok(findAll.run());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) throws Throwable {
        return ResponseEntity.ok(findByIdUC.run(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable UUID id, @RequestBody CourseUpdateDTO courseDTO)
            throws Throwable {
        return ResponseEntity.ok(updateCourse.run(id, courseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) throws Throwable {
        deleteById.run(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> postCourse(@RequestBody NewCourseDTO course) throws Throwable {
        return ResponseEntity.ok(createCourse.run(course));
    }

}
