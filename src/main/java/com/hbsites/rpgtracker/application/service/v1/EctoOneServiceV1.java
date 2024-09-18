package com.hbsites.rpgtracker.application.service.v1;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.interfaces.EctoOneService;
import com.hbsites.rpgtracker.domain.dto.EctoOneDTO;
import com.hbsites.rpgtracker.domain.dto.EquipmentDTO;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.EctoOneRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EctoOneServiceV1 implements EctoOneService {

    @Inject
    private EctoOneRepository ectoOneRepository;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return ectoOneRepository.getAll(params.getPage()).onItem()
                .transform(equipmentEntities -> equipmentEntities.stream().<BasicListDTO>map(ectoOne ->
                                BasicListDTO.builder()
                                        .uuid(ectoOne.getId())
                                        .description(ectoOne.getName())
                                        .build()
                        ).toList()
                );
    }

    @Override
    public Uni<EctoOneDTO> create(DefaultParams params, EctoOneDTO create) {
        return ectoOneRepository.create(create.toEntity(true)).onItem()
                .transform(ectoOneEntity ->
                        EctoOneDTO.builder()
                                .id(ectoOneEntity.getId())
                                .cost(ectoOneEntity.getCost())
                                .name(ectoOneEntity.getName())
                                .carryWeight(ectoOneEntity.getCarryWeight())
                                .seats(ectoOneEntity.getSeats())
                                .build()
                );
    }

    @Override
    public Uni<EctoOneDTO> getOne(CRUDGetOneParams params) {
        return ectoOneRepository.getOne(params.getUuid()).onItem().transform(ectoOneEntity ->
                EctoOneDTO.builder()
                        .id(ectoOneEntity.getId())
                        .cost(ectoOneEntity.getCost())
                        .name(ectoOneEntity.getName())
                        .carryWeight(ectoOneEntity.getCarryWeight())
                        .seats(ectoOneEntity.getSeats())
                        .build()
        );
    }

    @Override
    public Uni<EctoOneDTO> update(CRUDUpdateParams params, EctoOneDTO updated) {
        return ectoOneRepository.update(params.getUuid(), updated.toEntity(false)).onItem().transform(ectoOneEntity ->
                EctoOneDTO.builder()
                        .id(ectoOneEntity.getId())
                        .cost(ectoOneEntity.getCost())
                        .name(ectoOneEntity.getName())
                        .carryWeight(ectoOneEntity.getCarryWeight())
                        .seats(ectoOneEntity.getSeats())
                        .build()
        );
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return ectoOneRepository.delete(params.getUuid());
    }
}
