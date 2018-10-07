package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    private Integer id;

    private String name;
    private String surname;
}
