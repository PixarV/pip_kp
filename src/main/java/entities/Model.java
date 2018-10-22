
package entities;

import enums.TypeModel;
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
public class Model {

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

    // TODO: 10/22/18 Fetch type 
    // TODO: 10/22/18 restrict 
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @Builder.Default
    @JoinTable(name = "model_engine",
            joinColumns = @JoinColumn(name = "id_model", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_engine", referencedColumnName = "id")
    )
    List<Engine> engines = new ArrayList<>();

    @OneToOne(mappedBy = "model")
    Chassis chassis;
}



