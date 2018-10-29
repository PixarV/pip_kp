package entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.TypeDef;
import util.PostgreSQLEnumType;

import javax.persistence.*;
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "towers")
    Set<Chassis> chassis = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(name = "tower_weapon",
            joinColumns = @JoinColumn(name = "id_tower", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_weapon", referencedColumnName = "id")
    )
    Set<Weapon> weapons = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "tower")
    Set<FirmTower> firms = new HashSet<>();

    public void addFirm(Firm firm) {
        FirmTower firmTower = FirmTower.builder()
                .tower(this)
                .firm(firm)
                .build();

        firms.add(firmTower);
        firm.getTowers().add(firmTower);
    }

    public void removeFirm(Firm firm) {
        Predicate<FirmTower> condition = firmTower -> {
            Tower tempE = firmTower.getTower();
            Firm tempF = firmTower.getFirm();
            return firm.equals(tempF) && this.equals(tempE);
        };

        firms.removeIf(condition);
        firm.getTowers().removeIf(condition);
    }
}
