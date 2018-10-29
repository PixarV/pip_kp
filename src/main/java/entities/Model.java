package entities;

import enums.TypeModel;
import util.PostgreSQLEnumType;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
public class Model implements Serializable {

    @Id
    @SequenceGenerator(name = "model_seq",
            sequenceName = "model_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "model_seq")
    int id;
    String title;

    @Type(type = "psql_enum")
    @Enumerated(EnumType.STRING)
    TypeModel type;

    @Column(name = "max_speed_forward")
    double maxSpeedForward;

    @Column(name = "max_speed_backward")
    double maxSpeedBackward;
    String armor;
    // TODO: 10/15/18 armor -> struct

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(name = "model_engine",
            joinColumns = @JoinColumn(name = "id_model", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_engine", referencedColumnName = "id")
    )
    Set<Engine> engines = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "model")
    Set<Chassis> chassis = new HashSet<>();
}



