package constsw.grupoum.courses.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class LiteraryWork {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @PrimaryKeyJoinColumn(name = "type_id")
    @OneToOne
    private LiteraryWorkType literaryWorkType;
}
