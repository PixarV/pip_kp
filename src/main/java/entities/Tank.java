package entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Tank {

    @Id
    @SequenceGenerator(name = "tank_seq",
            sequenceName = "tank_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tank_seq")
    int id;

    @Column(name = "id_model")
    int idModel;

    @Column(name = "id_chassis")
    int idChassis;

    @Column(name = "sn_engine")
    String snEngine;

    @Column(name = "sn_tower")
    String snTower;

    @Column(name = "sn_weapon")
    String snWeapon;

    @Column(name = "team_number")
    int teamNumber;
}
