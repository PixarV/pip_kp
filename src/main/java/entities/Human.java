package entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

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

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "human")
    Set<SpecializationHuman> specializations = new HashSet<>();

    public void addSpecialization(Specialization specialization) {
        SpecializationHuman specializationHuman = SpecializationHuman.builder()
                .human(this)
                .specialization(specialization)
                .build();

        specializations.add(specializationHuman);
    }

    public void removeSpecialization(Specialization specialization) {
        Predicate<SpecializationHuman> condition = specializationHuman -> {
            Human tempE = specializationHuman.getHuman();
            Specialization tempF = specializationHuman.getSpecialization();
            return specialization.equals(tempF) && this.equals(tempE);
        };

        specializations.removeIf(condition);
    }
}