package entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

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
}
