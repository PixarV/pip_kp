package com.pip.entities;

import com.pip.enums.TypeAmmunition;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.pip.util.PostgreSQLEnumType;

import javax.persistence.*;
import java.io.Serializable;
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
public class Ammunition implements Serializable {

    // TODO: 11/5/18 @JsonIgnore 
    @Id
    @SequenceGenerator(name="ammunition_seq",
            sequenceName="ammunition_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="ammunition_seq")
    int id;

    @Type(type = "psql_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "ammunition_type")
    TypeAmmunition type;

    int callibr;

    // TODO: 10/24/18 Nullable & not nullable 
    int breakage;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(name = "ammunition_weapon",
            joinColumns = @JoinColumn(name = "id_ammunition", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_weapon", referencedColumnName = "id")
    )
    Set<Weapon> weapons = new HashSet<>();
}
