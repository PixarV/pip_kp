package com.pip.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Data
@Wither
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Monster {
    // Model and engine
    int modelId;
    int engineId;
    String modelTitle;
    String engineTitle;

    // ammunition and weapon
    int amunitionId;
    int weaponId;
    String ammunitionTitle;
    String weaponTitle;

    // chassis and tower
    int chassisId;
    int towerId;
    String chassisTitle;
    String towerTitle;

    int firmId;
    String firmTitle;
    String engineSn;
    String towerSn;
    String weaponSn;
}
