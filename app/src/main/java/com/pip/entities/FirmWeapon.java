package com.pip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;


@Data
@Entity
@Wither
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "firm_weapon")
public class FirmWeapon implements Serializable {

    @Id
    @Builder.Default
    @Column(name = "serial_no")
    String serialNo = UUID.randomUUID().toString();

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_firm", referencedColumnName = "id")
    @JsonIgnore
    Firm firm;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_weapon", referencedColumnName = "id")
    @JsonIgnore
    Weapon weapon;
}
