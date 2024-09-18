package com.hbsites.rpgtracker.domain.dto;

import com.hbsites.commons.domain.interfaces.CRUDSpecificDTO;
import com.hbsites.rpgtracker.domain.enumeration.ETrait;
import com.hbsites.rpgtracker.infraestructure.entity.TalentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TalentDTO implements CRUDSpecificDTO<TalentEntity> {
    private UUID id;
    private String name;
    private ETrait trait;

    @Override
    public TalentEntity toEntity(boolean newEntity) {
        TalentEntity e = new TalentEntity();
        e.setId(newEntity ? UUID.randomUUID() : this.getId());
        e.setTalentName(this.getName());
        e.setTrait(this.getTrait());
        return e;
    }
}
