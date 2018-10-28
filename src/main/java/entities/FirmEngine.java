package entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "firm_engine")
public class FirmEngine {

    @Id
    @Builder.Default
    @Column(name = "serial_no")
    String serialNo = UUID.randomUUID().toString();

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_firm", referencedColumnName = "id")
    Firm firm;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_engine", referencedColumnName = "id")
    Engine engine;
}
