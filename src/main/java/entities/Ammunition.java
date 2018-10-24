package entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class Ammunition {
    
    @Id
    @SequenceGenerator(name="ammunition_seq",
            sequenceName="ammunition_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="ammunition_seq")
    private int id;

    // TODO: 10/24/18 make enum
    @Column(name = "ammunition_type")
    String type;
    int callibr;

    // TODO: 10/24/18 Nullable & not nullable 
    int breakage;

    // TODO: 10/22/18 Fetch type
    // TODO: 10/22/18 restrict
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @Builder.Default
    @JoinTable(name = "ammunition_weapon",
            joinColumns = @JoinColumn(name = "id_ammunition", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_weapon", referencedColumnName = "id")
    )
    List<Weapon> weapons = new ArrayList<>();
}
