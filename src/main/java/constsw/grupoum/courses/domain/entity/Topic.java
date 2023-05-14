package constsw.grupoum.courses.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "name")
    private String name;
}
