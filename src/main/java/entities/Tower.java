package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.TypeDef;
import util.PostgreSQLEnumType;

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
@TypeDef(name = "psql_enum", typeClass = PostgreSQLEnumType.class)
public class Tower {

    @Id
    @SequenceGenerator(name = "tower_seq",
            sequenceName = "tower_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tower_seq")
    int id;
    String title;
    // TODO: 10/15/18 armor -> struct
    String armor;

    @Column(name = "turn_speed")
    double turnSpeed;

    @Column(name = "view_radius")
    double viewRadius;
    double weight;

    @Builder.Default
    @ManyToMany(mappedBy = "towers")
    Set<Chassis> chassis = new HashSet<>();

    // TODO: 10/22/18 Fetch type
    // TODO: 10/22/18 restrict
    @Builder.Default
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(name = "tower_weapon",
            joinColumns = @JoinColumn(name = "id_tower", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_weapon", referencedColumnName = "id")
    )
    Set<Weapon> weapons = new HashSet<>();

    // TODO: 10/25/18 get firm methods
    @Builder.Default
    @OneToMany(mappedBy = "tower")
    Set<FirmTower> firms = new HashSet<>();
}
