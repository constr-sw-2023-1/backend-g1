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
public class CourseUnitMongo {

    @Field(name = "number", targetType = FieldType.INT32)
    private Integer number;

    @Field(name = "name", targetType = FieldType.STRING)
    private String name;

    @Field(name = "topics", targetType = FieldType.ARRAY)
    private Collection<UnitTopicMongo> topics;

}
