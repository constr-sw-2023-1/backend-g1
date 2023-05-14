package constsw.grupoum.courses.domain.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "codcred")
    private String codcred;

    @Column(name = "workload")
    private String workload;

    @Column(name = "objectives")
    private List<String> objectives;

    @Column(name = "course_program")
    private String courseProgram;

    @JoinColumn(name = "unit_id")
    @ManyToOne
    private Unit units;

    @JoinColumn(name = "bibliography_id")
    @ManyToOne
    private LiteraryWork literaryWork;
}
