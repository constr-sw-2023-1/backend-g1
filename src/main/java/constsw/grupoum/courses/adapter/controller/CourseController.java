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

import constsw.grupoum.courses.application.dto.CourseUpdateDTO;
import constsw.grupoum.courses.application.dto.ErrorDTO;
import constsw.grupoum.courses.application.dto.NewCourseDTO;
import constsw.grupoum.courses.application.usecase.CreateCourseUC;
import constsw.grupoum.courses.application.usecase.DeleteCourseByIdUC;
import constsw.grupoum.courses.application.usecase.FindAllCoursesUC;
import constsw.grupoum.courses.application.usecase.FindCourseByComplexQueryUC;
import constsw.grupoum.courses.application.usecase.FindCourseByIdUC;
import constsw.grupoum.courses.application.usecase.PatchCourseUC;
import constsw.grupoum.courses.application.usecase.UpdateCourseUC;
import constsw.grupoum.courses.domain.dto.CourseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final PatchCourseUC patchCourse;

    @Operation(description = "Get all courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = CourseDTO.class)))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping
    public ResponseEntity<?> getAll() throws Throwable {
        return ResponseEntity.ok(findAll.run());
    }

    @Operation(description = "Get course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) throws Throwable {
        return ResponseEntity.ok(findByIdUC.run(id));
    }

    @Operation(description = "Update course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable UUID id, @RequestBody CourseUpdateDTO courseDTO)
            throws Throwable {
        return ResponseEntity.ok(updateCourse.run(id, courseDTO));
    }

    @Operation(description = "Delete course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
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
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PostMapping
    public ResponseEntity<?> postCourse(@RequestBody NewCourseDTO course) throws Throwable {
        return new ResponseEntity<CourseDTO>(createCourse.run(course), HttpStatus.CREATED);
    }

    @Operation(description = "Get courses with complexy query")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = CourseDTO.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @GetMapping("/search")
    public ResponseEntity<?> getComplexQuery(@RequestParam Map<String, String> searchParams) throws Throwable {
        return ResponseEntity.ok(findByComplexQuery.run(searchParams));
    }

    @Operation(description = "Patch course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))) })
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCourse(@PathVariable UUID id, @RequestBody CourseUpdateDTO course) throws Throwable {
        return ResponseEntity.ok(patchCourse.run(id, course));
    }

}
