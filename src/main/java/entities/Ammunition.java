package entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class Ammunition {
    
    @Id
    @SequenceGenerator(name="ammunition_seq",
            sequenceName="ammunition_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="ammunition_seq")
    int id;

    // TODO: 10/24/18 make enum
    @Column(name = "ammunition_type")
    String type;
    int callibr;

    // TODO: 10/24/18 Nullable & not nullable 
    int breakage;

    // TODO: 10/22/18 restrict
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
