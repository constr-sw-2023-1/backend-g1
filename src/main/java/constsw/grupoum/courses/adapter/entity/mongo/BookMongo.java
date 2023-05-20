package constsw.grupoum.courses.adapter.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document(collection = "books", language = "json")
public class BookMongo {

    @MongoId
    private String isbn13;

    @Field("title")
    private String title;

    @Field("author")
    private String author;

    @Field("year")
    private Integer year;

    @Field("pages")
    private Integer pages;

    @Field("language")
    private String language;

    @Field("editor")
    private String editor;

    @Field("genre")
    private String genre;
}
