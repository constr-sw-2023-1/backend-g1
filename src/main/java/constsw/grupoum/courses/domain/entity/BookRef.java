package constsw.grupoum.courses.domain.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class BookRef {

    @NonNull
    private String isbn13;

    private String title;

}
