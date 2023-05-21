package constsw.grupoum.courses.domain.dto;

public record BookDTO(String isbn13,
        String title,
        String author,
        Integer year,
        Integer pages,
        String language,
        String editor,
        String genre) {

}
