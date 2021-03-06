package com.pip.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pip.enums.Approve;
import com.pip.enums.FirmSpecializtion;
import com.pip.util.PostgreSQLEnumType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
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
@TypeDef(name = "psql_enum", typeClass = PostgreSQLEnumType.class)
public class Firm implements Serializable, UserDetails {

    @Id
    @SequenceGenerator(name = "firm_seq",
            sequenceName = "firm_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "firm_seq")
    int id;
    String title;
    String email;
    String password;

    @Type(type = "psql_enum")
    @Enumerated(EnumType.STRING)
    FirmSpecializtion specialization;

    @Type(type = "psql_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "firm_status")
    Approve status;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "firm")
    @JsonIgnoreProperties("firm")
    Set<FirmEngine> engines = new HashSet<>();

    public void addEngine(Engine engine) {
        FirmEngine firmEngine = FirmEngine.builder()
                .engine(engine)
                .firm(this)
                .build();

        engines.add(firmEngine);
        engine.getFirms().add(firmEngine);
    }

    public void removeFirm(Engine engine) {
        Predicate<FirmEngine> condition = firmEngine -> {
            Engine tempE = firmEngine.getEngine();
            Firm tempF = firmEngine.getFirm();
            return engine.equals(tempE) && this.equals(tempF);
        };

        engines.removeIf(condition);
        engine.getFirms().removeIf(condition);
    }

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "firm")
    @JsonIgnoreProperties("firm")
    Set<FirmWeapon> weapon = new HashSet<>();

    public void addWeapon(Weapon weapon) {
        FirmWeapon firmWeapon = FirmWeapon.builder()
                .weapon(weapon)
                .firm(this)
                .build();

        this.weapon.add(firmWeapon);
        weapon.getFirms().add(firmWeapon);
    }

    public void removeWeapon(Weapon weapon) {
        Predicate<FirmWeapon> condition = firmWeapon -> {
            Weapon tempE = firmWeapon.getWeapon();
            Firm tempF = firmWeapon.getFirm();
            return weapon.equals(tempE) && this.equals(tempF);
        };

        this.weapon.removeIf(condition);
        weapon.getFirms().removeIf(condition);
    }

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "firm")
    @JsonIgnoreProperties("firm")
    Set<FirmTower> towers = new HashSet<>();

    public void addTower(Tower tower) {
        FirmTower firmTower = FirmTower.builder()
                .tower(tower)
                .firm(this)
                .build();

        this.towers.add(firmTower);
        tower.getFirms().add(firmTower);
    }

    public void removeTower(Tower tower) {
        Predicate<FirmTower> condition = firmTower -> {
            Tower tempE = firmTower.getTower();
            Firm tempF = firmTower.getFirm();
            return tower.equals(tempE) && this.equals(tempF);
        };

        towers.removeIf(condition);
        tower.getFirms().removeIf(condition);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
