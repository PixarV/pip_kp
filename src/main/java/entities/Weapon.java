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
public class Weapon {

    @Id
    @SequenceGenerator(name = "weapon_seq",
            sequenceName = "weapon_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "weapon_seq")
    int id;
    String title;
    int callibr;
    double weight;

    @Builder.Default
    @ManyToMany(mappedBy = "weapons")
    List<Ammunition> ammunition = new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "weapons")
    List<Tower> towers = new ArrayList<>();

    // TODO: 10/25/18 get firm methods
    @Builder.Default
    @OneToMany(mappedBy = "weapon")
    List<FirmWeapon> firms = new ArrayList<>();
}
