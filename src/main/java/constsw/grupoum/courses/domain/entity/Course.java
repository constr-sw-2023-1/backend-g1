package constsw.grupoum.courses.domain.entity;

import java.util.Collection;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {

    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String codcred;

    private Integer workload;

    private Collection<String> objectives;

    private CourseSyllabus syllabus;

    private Boolean active;

    private Collection<BookRef> bibliography;

}
