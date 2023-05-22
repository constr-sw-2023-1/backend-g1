package constsw.grupoum.courses.domain.entity;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseSyllabus {

    @NonNull
    private String description;

    private Collection<SyllabusUnit> units;

}
