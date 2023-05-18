package constsw.grupoum.courses.adapter.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnitTopic {

    @Field(name = "number", targetType = FieldType.INT32)
    private int number;

    @Field(name = "name", targetType = FieldType.STRING)
    private String name;

}
