package com.hbsites.rpgtracker.domain.dto;

import com.hbsites.commons.domain.interfaces.CRUDSpecificDTO;
import com.hbsites.rpgtracker.infraestructure.entity.EctoOneUpgradeEntity;
import com.hbsites.rpgtracker.infraestructure.entity.EquipmentUpgradeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentUpgradeDTO implements CRUDSpecificDTO<EquipmentUpgradeEntity> {

    private UUID id;
    private String name;
    private BigDecimal cost;
    private String description;

    @Override
    public EquipmentUpgradeEntity toEntity(boolean newEntity) {
        EquipmentUpgradeEntity e = new EquipmentUpgradeEntity();
        e.setId(newEntity ? UUID.randomUUID() : this.getId());
        e.setName(this.getName());
        e.setCost(this.getCost());
        e.setDescription(this.getDescription());
        return e;
    }
}
