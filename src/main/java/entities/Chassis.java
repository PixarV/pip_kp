package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "id_model", referencedColumnName = "id")
    Model model;

    String title;
    double carring;

    @Column(name = "turn_speed")
    double turnSpeed;
    double weight;

    // TODO: 10/22/18 Fetch type
    // TODO: 10/22/18 restrict
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @Builder.Default
    @JoinTable(name = "chassis_tower",
            joinColumns = @JoinColumn(name = "id_chassis", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_tower", referencedColumnName = "id")
    )
    List<Tower> towers = new ArrayList<>();
}
