package constsw.grupoum.courses.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import constsw.grupoum.courses.application.usecase.CreateBookUC;
import constsw.grupoum.courses.application.usecase.FindAllBooksUC;
import constsw.grupoum.courses.application.usecase.FindBookByIsbn13UC;
import constsw.grupoum.courses.domain.dto.BookDTO;
import constsw.grupoum.courses.domain.exception.CourseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/courses/books")
@RestController
public class BookController {

    private final CreateBookUC create;

    private final FindAllBooksUC findAll;

    private final FindBookByIsbn13UC findByIsbn13UC;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookDTO book) {
        try {
            return ResponseEntity.ok(create.run(book));
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(findAll.run());
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/{isbn13}")
    public ResponseEntity<?> getById(@PathVariable String isbn13) {
        try {
            return ResponseEntity.ok(findByIsbn13UC.run(isbn13));
        } catch (CourseException e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

}
