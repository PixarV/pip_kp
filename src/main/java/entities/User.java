package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "userr")
public class User {

    @Id
    @SequenceGenerator(name="user_seq",
            sequenceName="userr_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="user_seq")
    private Integer id;


    private String name;
    private String surname;
}
