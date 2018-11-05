package entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Chassis implements Serializable {

    @Id
    @SequenceGenerator(name = "chassis_seq",
            sequenceName = "chassis_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "chassis_seq")
    int id;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "id_model", referencedColumnName = "id")
    Model model;

    String title;
    double carring;

    @Column(name = "turn_speed")
    double turnSpeed;
    double weight;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(name = "chassis_tower",
            joinColumns = @JoinColumn(name = "id_chassis", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_tower", referencedColumnName = "id")
    )
    Set<Tower> towers = new HashSet<>();
}
