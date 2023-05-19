package constsw.grupoum.courses.adapter.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import constsw.grupoum.courses.application.dto.CourseUpdateDTO;
import constsw.grupoum.courses.application.dto.NewCourseDTO;
import constsw.grupoum.courses.application.exception.ApplicationException;
import constsw.grupoum.courses.application.usecase.CreateCourseUC;
import constsw.grupoum.courses.application.usecase.DeleteCourseByIdUC;
import constsw.grupoum.courses.application.usecase.FindAllCoursesUC;
import constsw.grupoum.courses.application.usecase.FindCourseByComplexQueryUC;
import constsw.grupoum.courses.application.usecase.FindCourseByIdUC;
import constsw.grupoum.courses.application.usecase.UpdateCourseUC;
import constsw.grupoum.courses.domain.exception.CourseException;
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

    private final FindCourseByComplexQueryUC findByComplexQuery;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(findAll.run());
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(findByIdUC.run(id));
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable UUID id, @RequestBody CourseUpdateDTO courseDTO) {
        try {
            return ResponseEntity.ok(updateCourse.run(id, courseDTO));
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        try {
            deleteById.run(id);
            return ResponseEntity.ok().build();
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> postCourse(@RequestBody NewCourseDTO course) {
        try {
            return ResponseEntity.ok(createCourse.run(course));
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getComplexQuery(@RequestParam Map<String, String> searchParams) {
        try {
            return ResponseEntity.ok(findByComplexQuery.run(searchParams));
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        } catch (ApplicationException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

}
