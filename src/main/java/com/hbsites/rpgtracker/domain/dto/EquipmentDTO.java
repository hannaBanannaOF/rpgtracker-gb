package com.hbsites.rpgtracker.domain.dto;

import com.hbsites.commons.domain.interfaces.CRUDSpecificDTO;
import com.hbsites.rpgtracker.infraestructure.entity.EquipmentEntity;
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
public class EquipmentDTO implements CRUDSpecificDTO<EquipmentEntity> {

    private UUID id;
    private String name;
    private String description;
    private Integer weight;
    private BigDecimal cost;

    @Override
    public EquipmentEntity toEntity(boolean newEntity) {
        EquipmentEntity e = new EquipmentEntity();
        e.setId(newEntity ? UUID.randomUUID() : this.getId());
        e.setCost(this.getCost());
        e.setDescription(this.getDescription());
        e.setWeight(this.getWeight());
        e.setEquipmentName(this.getName());
        return e;
    }
}
