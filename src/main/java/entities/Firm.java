package entities;


import enums.FirmSpecializtion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import util.PostgreSQLEnumType;

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
    @OneToMany(mappedBy = "firm")
    List<FirmEngine> engines = new ArrayList<>();

    // TODO: 10/25/10 get weapon methods
    @Builder.Default
    @OneToMany(mappedBy = "firm", cascade = {CascadeType.MERGE})
    List<FirmWeapon> weapon = new ArrayList<>();

    // TODO: 10/25/10 get tower methods
    @Builder.Default
    @OneToMany(mappedBy = "firm")
    List<FirmTower> towers = new ArrayList<>();
}
