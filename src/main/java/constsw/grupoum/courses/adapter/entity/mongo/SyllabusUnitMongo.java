package constsw.grupoum.courses.adapter.entity.mongo;

import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

public class SyllabusUnitMongo {

    @Field(name = "description", targetType = FieldType.STRING)
    private String description;

    @Field(name = "units", targetType = FieldType.ARRAY)
    private Collection<SyllabusUnitMongo> units;

}
