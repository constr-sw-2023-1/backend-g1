package constsw.grupoum.courses.adapter.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.application.usecase.course.CreateCourseUC;
import constsw.grupoum.courses.application.usecase.course.DeleteCourseByIdUC;
import constsw.grupoum.courses.application.usecase.course.FindAllCoursesUC;
import constsw.grupoum.courses.application.usecase.course.FindCourseByComplexQueryUC;
import constsw.grupoum.courses.application.usecase.course.FindCourseByIdUC;
import constsw.grupoum.courses.application.usecase.course.PatchCourseUC;
import constsw.grupoum.courses.application.usecase.course.UpdateCourseUC;
import constsw.grupoum.courses.application.usecase.course.book.CreateCourseBookUC;
import constsw.grupoum.courses.application.usecase.course.book.DeleteCourseBookByIsbnUC;
import constsw.grupoum.courses.application.usecase.course.book.FindCourseBooksUC;
import constsw.grupoum.courses.application.usecase.course.syllabus.FindCourseSyllabusUC;
import constsw.grupoum.courses.application.usecase.course.syllabus.unit.CreateCourseUnitUC;
import constsw.grupoum.courses.application.usecase.course.syllabus.unit.DeleteCourseUnitUC;
import constsw.grupoum.courses.application.usecase.course.syllabus.unit.FindAllCourseUnitsUC;
import constsw.grupoum.courses.application.usecase.course.syllabus.unit.FindCourseUnitUC;
import constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic.CreateTopicUC;
import constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic.DeleteTopicUC;
import constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic.FindAllTopicsUC;
import constsw.grupoum.courses.application.usecase.course.syllabus.unit.topic.FindTopicUC;
import constsw.grupoum.courses.domain.dto.BookRefDTO;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import constsw.grupoum.courses.domain.dto.NewCourseDTO;
import constsw.grupoum.courses.domain.dto.PatchCourseDTO;
import constsw.grupoum.courses.domain.dto.SyllabusUnitDTO;
import constsw.grupoum.courses.domain.dto.UnitTopicDTO;
import constsw.grupoum.courses.domain.entity.CourseSyllabus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/courses")
@SecurityRequirement(name = "bearerAuth")
@RestController
public class CourseController {

    private final FindAllCoursesUC findAll;
    private final FindCourseByIdUC findByIdUC;
    private final DeleteCourseByIdUC deleteById;
    private final UpdateCourseUC updateCourse;
    private final CreateCourseUC createCourse;
    private final FindCourseByComplexQueryUC findByComplexQuery;
    private final PatchCourseUC patchCourse;

    private final FindCourseBooksUC findCourseBooks;
    private final CreateCourseBookUC createCourseBook;
    private final DeleteCourseBookByIsbnUC deleteCourseBookByIsbn;

    private final FindCourseSyllabusUC findCourseSyllabus;

    private final FindAllCourseUnitsUC findAllCourseUnits;
    private final FindCourseUnitUC findCourseUnit;
    private final CreateCourseUnitUC createCourseUnit;
    private final DeleteCourseUnitUC deleteCourseUnit;

    private final FindAllTopicsUC findTopics;
    private final CreateTopicUC createTopic;
    private final FindTopicUC findTopic;
    private final DeleteTopicUC deleteTopic;

    @Operation(description = "Get all courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = CourseDTO.class)))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(required = true) Map<String, String> searchParams) throws Throwable {
        return searchParams.size() == 0 ? ResponseEntity.ok(findAll.run())
                : ResponseEntity.ok(findByComplexQuery.run(searchParams));
    }

    @Operation(description = "Get course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) throws Throwable {
        return ResponseEntity.ok(findByIdUC.run(id));
    }

    @Operation(description = "Update course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable UUID id, @RequestBody NewCourseDTO courseDTO)
            throws Throwable {
        return ResponseEntity.ok(updateCourse.run(id, courseDTO));
    }

    @Operation(description = "Delete course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) throws Throwable {
        deleteById.run(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Post course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PostMapping
    public ResponseEntity<?> postCourse(@RequestBody NewCourseDTO course) throws Throwable {
        return new ResponseEntity<CourseDTO>(createCourse.run(course), HttpStatus.CREATED);
    }

    @Operation(description = "Patch course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCourse(@PathVariable UUID id, @RequestBody PatchCourseDTO course) throws Throwable {
        return ResponseEntity.ok(patchCourse.run(id, course));
    }

    @Operation(description = "Get all books for a course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = BookRefDTO.class)))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/{id}/books")
    public ResponseEntity<?> getBooks(@PathVariable UUID id) throws Throwable {
        return ResponseEntity.ok(findCourseBooks.run(id));
    }

    @Operation(description = "Post books for a course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BookRefDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "409", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PostMapping("/{id}/books")
    public ResponseEntity<?> postBook(@PathVariable UUID id, BookRefDTO book) throws Throwable {
        return new ResponseEntity<BookRefDTO>(createCourseBook.run(id, book), HttpStatus.CREATED);
    }

    @Operation(description = "Delete books for a course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @DeleteMapping("/{id}/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID id, @PathVariable String isbn) throws Throwable {
        deleteCourseBookByIsbn.run(id, isbn);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Get syllabus for a course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = CourseSyllabus.class)))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/{id}/syllabus")
    public ResponseEntity<?> getSyllabus(@PathVariable UUID id) throws Throwable {
        return ResponseEntity.ok(findCourseSyllabus.run(id));
    }

    @Operation(description = "Get all units for syllabus for a course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = SyllabusUnitDTO.class)))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/{id}/syllabus/units")
    public ResponseEntity<?> getUnits(@PathVariable UUID id)
            throws Throwable {
        return ResponseEntity.ok(findAllCourseUnits.run(id));
    }

    @Operation(description = "Post units for a syllabus for a course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SyllabusUnitDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "409", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PostMapping("/{id}/syllabus/units")
    public ResponseEntity<?> postUnit(@PathVariable UUID id, @RequestBody SyllabusUnitDTO unit)
            throws Throwable {
        return new ResponseEntity<SyllabusUnitDTO>(createCourseUnit.run(id, unit), HttpStatus.CREATED);
    }

    @Operation(description = "Get a unit by unit number and course id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SyllabusUnitDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/{id}/syllabus/units/{unitNumber}")
    public ResponseEntity<?> getUnit(@PathVariable UUID id, @PathVariable int unitNumber)
            throws Throwable {
        return ResponseEntity.ok(findCourseUnit.run(id, unitNumber));
    }

    @Operation(description = "Delete unit by unit number and course id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @DeleteMapping("/{id}/syllabus/units/{unitNumber}")
    public ResponseEntity<?> deleteUnit(@PathVariable UUID id, @PathVariable int unitNumber)
            throws Throwable {
        deleteCourseUnit.run(id, unitNumber);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Get all topics by unit number and course id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = UnitTopicDTO.class)))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/{id}/syllabus/units/{unitNumber}/topics/")
    public ResponseEntity<?> getTopics(@PathVariable UUID id, @PathVariable int unitNumber)
            throws Throwable {
        return ResponseEntity.ok(findTopics.run(id, unitNumber));
    }

    @Operation(description = "Post topic by unit number and course id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UnitTopicDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "409", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PostMapping("/{id}/syllabus/units/{unitNumber}/topics/")
    public ResponseEntity<?> postTopics(@PathVariable UUID id, @PathVariable int unitNumber,
            @RequestBody UnitTopicDTO topic)
            throws Throwable {
        return new ResponseEntity<UnitTopicDTO>(createTopic.run(id, unitNumber, topic), HttpStatus.CREATED);
    }

    @Operation(description = "Get a topic by topic number, unit number and course id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UnitTopicDTO.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/{id}/syllabus/units/{unitNumber}/topics/{topicNumber}")
    public ResponseEntity<?> getTopic(@PathVariable UUID id, @PathVariable int unitNumber,
            @PathVariable int topicNumber)
            throws Throwable {
        return ResponseEntity.ok(findTopic.run(id, unitNumber, topicNumber));
    }

    @Operation(description = "Delete topic by topic number, unit number and course id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @DeleteMapping("/{id}/syllabus/units/{unitNumber}/topics/{topicNumber}")
    public ResponseEntity<?> deleteTopic(@PathVariable UUID id, @PathVariable int unitNumber,
            @PathVariable int topicNumber)
            throws Throwable {
        deleteTopic.run(id, unitNumber, topicNumber);
        return ResponseEntity.noContent().build();
    }

}
