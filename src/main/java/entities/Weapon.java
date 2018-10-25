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
    Set<Ammunition> ammunition = new HashSet<>();

    @Builder.Default
    @ManyToMany(mappedBy = "weapons")
    Set<Tower> towers = new HashSet<>();

    // TODO: 10/25/18 get firm methods
    @Builder.Default
    @OneToMany(mappedBy = "weapon")
    Set<FirmWeapon> firms = new HashSet<>();
}
