package constsw.grupoum.courses.domain.entity;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseSyllabus {

    private String description;

    private Collection<SylabusUnit> units;

}
