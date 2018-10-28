package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
// TODO: 10/27/18 and dao!!! 
public class Relation {
    @Id
    @SequenceGenerator(name = "relation_seq",
            sequenceName = "relation_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "relation_seq")
    private int id;

//    @Column(name = "id_human")
//    int idHuman;
//
//    int id_parent;
//
//
//    @ManyToOne(cascade = {
//            CascadeType.MERGE,
//            CascadeType.REFRESH
//    })
//    @JoinColumn(name = "id", referencedColumnName = "id_parent")
//    Relation parent;
//
//    int stage;
}
