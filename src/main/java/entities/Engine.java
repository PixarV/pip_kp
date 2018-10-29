package entities;

import enums.TypeFuel;
import util.PostgreSQLEnumType;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "engines")
    Set<Model> models = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "engine")
    Set<FirmEngine> firms = new HashSet<>();

    public void addFirm(Firm firm) {
        FirmEngine firmEngine = FirmEngine.builder()
                .engine(this)
                .firm(firm)
                .build();

        firms.add(firmEngine);
        firm.getEngines().add(firmEngine);
    }

    public void removeFirm(Firm firm) {
        Predicate<FirmEngine> condition= firmEngine -> {
            Engine tempE = firmEngine.getEngine();
            Firm tempF = firmEngine.getFirm();
            return firm.equals(tempF) && this.equals(tempE);
        };

        firms.removeIf(condition);
        firm.getEngines().removeIf(condition);
    }
}
