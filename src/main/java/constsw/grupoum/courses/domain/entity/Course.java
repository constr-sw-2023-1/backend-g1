package constsw.grupoum.courses.domain.entity;

import java.util.Collection;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {

    private UUID id;

    private String name;

    private String codCred;

    private Integer workload;

    private Collection<String> objectives;

    private CourseSyllabus syllabus;

    private Collection<BookRef> bibliography;

}
