package entities;

import enums.TypeFuel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
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
public class Engine {

    @Id
    @SequenceGenerator(name = "engine_seq",
            sequenceName = "engine_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "engine_seq")
    int id;
    String title;

    @Type(type = "psql_enum")
    @Enumerated(EnumType.STRING)
    TypeFuel fuel;

    int power;
    double weight;

    @Builder.Default
    @ManyToMany(mappedBy = "engines")
    Set<Model> models = new HashSet<>();

    // TODO: 10/25/18 get firm methods
    @Builder.Default
    @OneToMany(mappedBy = "engine")
    Set<FirmEngine> firms = new HashSet<>();
}
