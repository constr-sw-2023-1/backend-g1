package constsw.grupoum.courses.domain.entity;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
public class Book {

    @MongoId
    private String ISBN13;

    @Field("title")
    private String title;

}
