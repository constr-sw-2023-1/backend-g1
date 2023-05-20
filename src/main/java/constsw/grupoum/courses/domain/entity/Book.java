package constsw.grupoum.courses.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    private String isbn13;

    private String title;

    private String author;

    private Integer year;

    private Integer pages;

    private String language;

    private String editor;

    private String genre;
}
