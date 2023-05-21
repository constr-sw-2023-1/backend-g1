package constsw.grupoum.courses.adapter.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import constsw.grupoum.courses.application.usecase.CreateBookUC;
import constsw.grupoum.courses.application.usecase.FindAllBooksUC;
import constsw.grupoum.courses.application.usecase.FindBookByIsbn13UC;
import constsw.grupoum.courses.application.usecase.FindBooksByComplexQueryUC;
import constsw.grupoum.courses.domain.dto.BookDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/courses/books")
@RestController
public class BookController {

    private final CreateBookUC create;

    private final FindAllBooksUC findAll;

    private final FindBookByIsbn13UC findByIsbn13UC;

    private final FindBooksByComplexQueryUC findByComplexQuery;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookDTO book) throws Throwable {
        return ResponseEntity.ok(create.run(book));
    }

    @GetMapping
    public ResponseEntity<?> getAll() throws Throwable {
        return ResponseEntity.ok(findAll.run());
    }

    @GetMapping("/{isbn13}")
    public ResponseEntity<?> getById(@PathVariable String isbn13) throws Throwable {
        return ResponseEntity.ok(findByIsbn13UC.run(isbn13));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getComplexQuery(@RequestParam Map<String, String> searchParams) throws Throwable {
        return ResponseEntity.ok(findByComplexQuery.run(searchParams));
    }

}
