package com.hbsites.rpgtracker.domain.dto;

import com.hbsites.commons.domain.interfaces.CRUDSpecificDTO;
import com.hbsites.rpgtracker.infraestructure.entity.EctoOneEntity;
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
public class EctoOneDTO implements CRUDSpecificDTO<EctoOneEntity> {

    private UUID id;
    private String name;
    private Integer carryWeight;
    private BigDecimal cost;
    private Integer seats;

    @Override
    public EctoOneEntity toEntity(boolean newEntity) {
        EctoOneEntity e = new EctoOneEntity();
        e.setId(newEntity ? UUID.randomUUID() : this.getId());
        e.setCost(this.getCost());
        e.setCarryWeight(this.getCarryWeight());
        e.setName(this.getName());
        e.setSeats(this.getSeats());
        return e;
    }
}
