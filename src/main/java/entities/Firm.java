package entities;

import enums.FirmSpecializtion;
import util.PostgreSQLEnumType;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
public class Firm {

    @Id
    @SequenceGenerator(name = "firm_seq",
            sequenceName = "firm_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "firm_seq")
    int id;
    String title;

    @Type(type = "psql_enum")
    @Enumerated(EnumType.STRING)
    FirmSpecializtion specialization;

    // TODO: 10/25/10 get engine methods
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "firm")
    Set<FirmEngine> engines = new HashSet<>();

    // TODO: 10/25/10 get weapon methods
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "firm")
    Set<FirmWeapon> weapon = new HashSet<>();

    // TODO: 10/25/10 get tower methods
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "firm")
    Set<FirmTower> towers = new HashSet<>();
}
