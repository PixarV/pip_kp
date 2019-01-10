package com.pip.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Wither
@Builder
@Component
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
    @JsonIgnoreProperties("weapons")
    @ManyToMany(mappedBy = "weapons")
    Set<Ammunition> ammunition = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("weapons")
    @ManyToMany(mappedBy = "weapons")
    Set<Tower> towers = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "weapon")
    @JsonIgnoreProperties("weapon")
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
