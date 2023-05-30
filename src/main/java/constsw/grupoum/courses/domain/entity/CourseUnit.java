package constsw.grupoum.courses.domain.entity;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseUnit {

    @NonNull
    private Integer number;

    @NonNull
    private String name;

    private Collection<UnitTopic> topics;

}
