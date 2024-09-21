package com.hbsites.rpgtracker.application.service.v1;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.interfaces.EctoOneUpgradeService;
import com.hbsites.rpgtracker.application.service.interfaces.EquipmentUpgradeService;
import com.hbsites.rpgtracker.domain.dto.EctoOneUpgradeDTO;
import com.hbsites.rpgtracker.domain.dto.EquipmentUpgradeDTO;
import com.hbsites.rpgtracker.infraestructure.repository.EquipmentUpgradeRepositoryImpl;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.EctoOneUpgradeRepository;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.EquipmentRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EquipmentUpgradeServiceV1 implements EquipmentUpgradeService {

    @Inject
    private EquipmentUpgradeRepositoryImpl equipmentUpgradeRepository;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return equipmentUpgradeRepository.getAll(params.getPage()).onItem()
                .transform(equipmentEntities -> equipmentEntities.stream().<BasicListDTO>map(ectoOne ->
                                BasicListDTO.builder()
                                        .uuid(ectoOne.getId())
                                        .description(ectoOne.getName())
                                        .build()
                        ).toList()
                );
    }

    @Override
    public Uni<EquipmentUpgradeDTO> create(DefaultParams params, EquipmentUpgradeDTO create) {
        return equipmentUpgradeRepository.create(create.toEntity(true)).onItem()
                .transform(equipmentUpgrade ->
                        EquipmentUpgradeDTO.builder()
                                .id(equipmentUpgrade.getId())
                                .cost(equipmentUpgrade.getCost())
                                .name(equipmentUpgrade.getName())
                                .description(equipmentUpgrade.getDescription())
                                .build()
                );
    }

    @Override
    public Uni<EquipmentUpgradeDTO> getOne(CRUDGetOneParams params) {
        return equipmentUpgradeRepository.getOne(params.getUuid()).onItem()
                .transform(equipmentUpgrade ->
                        EquipmentUpgradeDTO.builder()
                                .id(equipmentUpgrade.getId())
                                .cost(equipmentUpgrade.getCost())
                                .name(equipmentUpgrade.getName())
                                .description(equipmentUpgrade.getDescription())
                                .build()
                );
    }

    @Override
    public Uni<EquipmentUpgradeDTO> update(CRUDUpdateParams params, EquipmentUpgradeDTO updated) {
        return equipmentUpgradeRepository.update(params.getUuid(), updated.toEntity(false)).onItem()
                .transform(equipmentUpgrade ->
                        EquipmentUpgradeDTO.builder()
                                .id(equipmentUpgrade.getId())
                                .cost(equipmentUpgrade.getCost())
                                .name(equipmentUpgrade.getName())
                                .description(equipmentUpgrade.getDescription())
                                .build()
                );
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return equipmentUpgradeRepository.delete(params.getUuid());
    }
}
