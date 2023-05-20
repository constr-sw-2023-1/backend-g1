package constsw.grupoum.courses.adapter.entity.mongo;

import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseSyllabusMongo {

    @Field(name = "description", targetType = FieldType.STRING)
    private String description;

    @Field(name = "units", targetType = FieldType.ARRAY)
    private Collection<SyllabusUnitMongo> units;

}
