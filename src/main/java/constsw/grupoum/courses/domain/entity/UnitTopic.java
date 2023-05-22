package constsw.grupoum.courses.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnitTopic {

    @NonNull
    private Integer number;

    @NonNull
    private String name;

}
