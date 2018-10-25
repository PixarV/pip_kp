package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Chassis {
    @Id
    @SequenceGenerator(name="chassis_seq",
            sequenceName="chassis_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="chassis_seq")
    int id;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_model", referencedColumnName = "id")
    Model model;

    String title;
    double carring;

    @Column(name = "turn_speed")
    double turnSpeed;
    double weight;

    // TODO: 10/22/18 Fetch type
    // TODO: 10/22/18 restrict
    @Builder.Default
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
