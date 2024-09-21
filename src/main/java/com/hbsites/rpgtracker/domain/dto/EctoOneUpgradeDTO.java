package com.hbsites.rpgtracker.domain.dto;

import com.hbsites.commons.domain.interfaces.CRUDSpecificDTO;
import com.hbsites.rpgtracker.infraestructure.entity.EctoOneUpgradeEntity;
import com.hbsites.rpgtracker.infraestructure.entity.EctoOneUpgradeEntity_;
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
public class EctoOneUpgradeDTO implements CRUDSpecificDTO<EctoOneUpgradeEntity> {

    private UUID id;
    private String name;
    private BigDecimal cost;
    private String description;

    @Override
    public EctoOneUpgradeEntity toEntity(boolean newEntity) {
        EctoOneUpgradeEntity e = new EctoOneUpgradeEntity();
        e.setId(newEntity ? UUID.randomUUID() : this.getId());
        e.setName(this.getName());
        e.setCost(this.getCost());
        e.setDescription(this.getDescription());
        return e;
    }
}
