package constsw.grupoum.courses.adapter.entity.mongo;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "courses", language = "json")
public class CourseMongo {

    @MongoId(value = FieldType.OBJECT_ID)
    private UUID id;

    @Field("name")
    private String name;

    @Field("codcred")
    private String codCred;

    @Field("workload")
    private Integer workload;

    @Field("objectives")
    private Collection<String> objectives;

    @Field("syllabus")
    private CourseSyllabusMongo syllabus;

    @Field(name = "bibliography", targetType = FieldType.ARRAY)
    private Collection<BookRefMongo> bibliography;

}
