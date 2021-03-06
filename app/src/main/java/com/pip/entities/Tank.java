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

import static lombok.AccessLevel.PRIVATE;

@Data
@Wither
@Entity
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Tank implements Serializable {

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

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "tank")
    @JsonIgnoreProperties("tank")
    Set<Human> humans = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(name = "tank_specialization",
            joinColumns = @JoinColumn(name = "id_tank", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_specialization", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("tanks")
    Set<Specialization> specializations = new HashSet<>();
}
