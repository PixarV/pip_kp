package entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Human {
    @Id
    @SequenceGenerator(name = "human_seq",
            sequenceName = "human_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "human_seq")
    int id;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "id_tank", referencedColumnName = "id")
    Tank tank;

    String name;

    @Column(name = "vacation_start")
    LocalDate vacationStart;

    @Column(name = "vacation_end")
    LocalDate vacationEnd;
}
