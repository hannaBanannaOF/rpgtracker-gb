package com.hbsites.rpgtracker.application.service.v1;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.interfaces.EquipmentService;
import com.hbsites.rpgtracker.domain.dto.EquipmentDTO;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.EquipmentRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EquipmentServiceV1 implements EquipmentService {

    @Inject
    private EquipmentRepository equipmentRepository;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return equipmentRepository.getAll(params.getPage()).onItem()
                .transform(equipmentEntities -> equipmentEntities.stream().<BasicListDTO>map(equipmentEntity ->
                                BasicListDTO.builder()
                                        .uuid(equipmentEntity.getId())
                                        .description(equipmentEntity.getEquipmentName())
                                        .build()
                        ).toList()
                );
    }

    @Override
    public Uni<EquipmentDTO> create(DefaultParams params, EquipmentDTO create) {
        return equipmentRepository.create(create.toEntity(true)).onItem()
                .transform(equipmentEntity ->
                        EquipmentDTO.builder()
                                .id(equipmentEntity.getId())
                                .cost(equipmentEntity.getCost())
                                .name(equipmentEntity.getEquipmentName())
                                .weight(equipmentEntity.getWeight())
                                .description(equipmentEntity.getDescription())
                                .build()
                );
    }

    @Override
    public Uni<EquipmentDTO> getOne(CRUDGetOneParams params) {
        return equipmentRepository.getOne(params.getUuid()).onItem().transform(equipmentEntity ->
                EquipmentDTO.builder()
                        .id(equipmentEntity.getId())
                        .cost(equipmentEntity.getCost())
                        .name(equipmentEntity.getEquipmentName())
                        .weight(equipmentEntity.getWeight())
                        .description(equipmentEntity.getDescription())
                        .build()
        );
    }

    @Override
    public Uni<EquipmentDTO> update(CRUDUpdateParams params, EquipmentDTO updated) {
        return equipmentRepository.update(params.getUuid(), updated.toEntity(false)).onItem()
                .transform(equipmentEntity ->
                        EquipmentDTO.builder()
                                .id(equipmentEntity.getId())
                                .cost(equipmentEntity.getCost())
                                .name(equipmentEntity.getEquipmentName())
                                .weight(equipmentEntity.getWeight())
                                .description(equipmentEntity.getDescription())
                                .build()
                );
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return equipmentRepository.delete(params.getUuid());
    }
}
