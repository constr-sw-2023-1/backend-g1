package constsw.grupoum.courses.adapter.entity.mongo;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class BookRefMongo {

    @Field("isbn13")
    private String isbn13;

    @Field("title")
    private String title;

    @DBRef(lazy = false)
    private BookMongo book;
}
