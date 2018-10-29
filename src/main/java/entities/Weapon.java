package entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
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
public class Weapon implements Serializable {

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "weapons")
    Set<Ammunition> ammunition = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "weapons")
    Set<Tower> towers = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "weapon")
    Set<FirmWeapon> firms = new HashSet<>();

    public void addFirm(Firm firm) {
        FirmWeapon firmWeapon = FirmWeapon.builder()
                .weapon(this)
                .firm(firm)
                .build();

        firms.add(firmWeapon);
        firm.getWeapon().add(firmWeapon);
    }

    public void removeFirm(Firm firm) {
        Predicate<FirmWeapon> condition = firmWeapon -> {
            Weapon tempE = firmWeapon.getWeapon();
            Firm tempF = firmWeapon.getFirm();
            return firm.equals(tempF) && this.equals(tempE);
        };

        firms.removeIf(condition);
        firm.getWeapon().removeIf(condition);
    }
}
