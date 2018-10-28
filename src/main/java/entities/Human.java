package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Human {
    @Id
    @SequenceGenerator(name = "chassis_seq",
            sequenceName = "chassis_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "chassis_seq")
    int id;

//    @OneToOne(cascade = {
//            CascadeType.MERGE,
//            CascadeType.REFRESH
//    })
//    @JoinColumn(name = "id_model", referencedColumnName = "id")
//    Model model;

    String name;

    @Column(name = "vacation_start")
    LocalDate vacationStart;

    @Column(name = "vacation_end")
    LocalDate vacationEnd;
}
