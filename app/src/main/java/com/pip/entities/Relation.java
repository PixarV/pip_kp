package com.pip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Relation implements Serializable {

    @Id
    @SequenceGenerator(name = "relation_seq",
            sequenceName = "relation_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "relation_seq")
    private int id;

    @OneToOne
    @JoinColumn(name = "id_human", referencedColumnName = "id")
    Human human;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_parent", referencedColumnName = "id")
    Relation parent;

    int stage;
}
