package constsw.grupoum.courses.adapter.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import constsw.grupoum.courses.application.usecase.DeleteCourseByIdUC;
import constsw.grupoum.courses.application.usecase.FindCourseByIdUC;
import constsw.grupoum.courses.domain.exception.CourseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/courses")
@RestController
public class CourseController {

    private final FindCourseByIdUC findByIdUC;

    private final DeleteCourseByIdUC deleteById;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(findByIdUC.run(id));
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

    @GetMapping("")
    public ResponseEntity<?> getComplexQuery(@RequestParam Map<String, String> searchParams) {
        return ResponseEntity.ok(searchParams);
    }

}
