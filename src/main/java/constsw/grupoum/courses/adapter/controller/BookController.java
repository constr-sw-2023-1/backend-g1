package constsw.grupoum.courses.adapter.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.application.usecase.book.CreateBookUC;
import constsw.grupoum.courses.application.usecase.book.FindAllBooksUC;
import constsw.grupoum.courses.application.usecase.book.FindBookByIsbn13UC;
import constsw.grupoum.courses.application.usecase.book.FindBooksByComplexQueryUC;
import constsw.grupoum.courses.domain.dto.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/courses/books")
@RestController
public class BookController {

    private final CreateBookUC create;

    private final FindAllBooksUC findAll;

    private final FindBookByIsbn13UC findByIsbn13UC;

    private final FindBooksByComplexQueryUC findByComplexQuery;

    @Operation(description = "Create new Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "409", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookDTO book) throws Throwable {
        return new ResponseEntity<BookDTO>(create.run(book), HttpStatus.CREATED);
    }

    @Operation(description = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping
    public ResponseEntity<?> getAll() throws Throwable {
        return ResponseEntity.ok(findAll.run());
    }

    @Operation(description = "Get Book from ISBN13")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/{isbn13}")
    public ResponseEntity<?> getById(@PathVariable String isbn13) throws Throwable {
        return ResponseEntity.ok(findByIsbn13UC.run(isbn13));
    }

    @Operation(description = "Get books with complexy query")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/search")
    public ResponseEntity<?> getComplexQuery(@RequestParam Map<String, String> searchParams) throws Throwable {
        return ResponseEntity.ok(findByComplexQuery.run(searchParams));
    }

}
