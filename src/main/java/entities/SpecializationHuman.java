package entities;

import enums.TypeModel;
import util.PostgreSQLEnumType;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "specialization_human")
@TypeDef(name = "psql_enum", typeClass = PostgreSQLEnumType.class)
public class SpecializationHuman implements Serializable {

    @Id
    @SequenceGenerator(name = "specialization_human_seq",
            sequenceName = "specialization_human_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "specialization_human_seq")
    int id;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_human", referencedColumnName = "id")
    Human human;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_specialization", referencedColumnName = "id")
    Specialization specialization;

    @Column(name = "type_tank")
    TypeModel typeTank;
}
