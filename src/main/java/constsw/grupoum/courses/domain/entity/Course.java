package constsw.grupoum.courses.domain.entity;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.DBRef;
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
public class Course {

    @MongoId(value = FieldType.OBJECT_ID)
    private UUID id;

    @Field("name")
    private String name;

    @Field("codcred")
    private String codCred;

    @Field("workload")
    private int workload;

    @Field("objectives")
    private Collection<String> objectives;

    @Field("syllabus")
    private CourseSyllabus syllabus;

    @DBRef
    private Collection<Book> bibliography;

}
