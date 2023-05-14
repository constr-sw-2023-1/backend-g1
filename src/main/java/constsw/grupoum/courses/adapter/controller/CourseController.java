package constsw.grupoum.courses.adapter.controller;

import java.util.UUID;

import constsw.grupoum.courses.application.usecase.FindAllCourse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import constsw.grupoum.courses.application.usecase.FindCourseByIdUC;
import constsw.grupoum.courses.domain.exception.CourseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/courses")
@RestController
public class CourseController {

    private final FindCourseByIdUC findByIdUC;
    private final FindAllCourse findAll;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(findByIdUC.run(id));
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(findAll.run());
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

}
