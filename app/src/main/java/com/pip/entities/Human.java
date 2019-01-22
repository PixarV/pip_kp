package com.pip.entities;

import com.pip.enums.Approve;
import com.pip.enums.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Human implements Serializable, UserDetails {
    @Id
    @SequenceGenerator(name = "human_seq",
            sequenceName = "human_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "human_seq")
    int id;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "id_tank", referencedColumnName = "id")
    Tank tank;

    String name;
    String email;
    String password;

    @Type(type = "psql_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "human_status")
    Approve status;

    @Type(type = "psql_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    UserRole role;

    @Column(name = "vacation_start")
    LocalDate vacationStart;

    @Column(name = "vacation_end")
    LocalDate vacationEnd;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "human")
    Set<SpecializationHuman> specializations = new HashSet<>();

    public void addSpecialization(Specialization specialization) {
        SpecializationHuman specializationHuman = SpecializationHuman.builder()
                .human(this)
                .specialization(specialization)
                .build();

        specializations.add(specializationHuman);
    }

    public void removeSpecialization(Specialization specialization) {
        Predicate<SpecializationHuman> condition = specializationHuman -> {
            Human tempE = specializationHuman.getHuman();
            Specialization tempF = specializationHuman.getSpecialization();
            return specialization.equals(tempF) && this.equals(tempE);
        };

        specializations.removeIf(condition);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}