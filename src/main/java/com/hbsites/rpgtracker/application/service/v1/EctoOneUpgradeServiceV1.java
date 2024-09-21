package com.hbsites.rpgtracker.application.service.v1;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.interfaces.EctoOneUpgradeService;
import com.hbsites.rpgtracker.domain.dto.EctoOneDTO;
import com.hbsites.rpgtracker.domain.dto.EctoOneUpgradeDTO;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.EctoOneUpgradeRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EctoOneUpgradeServiceV1 implements EctoOneUpgradeService {

    @Inject
    private EctoOneUpgradeRepository ectoOneUpgradeRepository;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return ectoOneUpgradeRepository.getAll(params.getPage()).onItem()
                .transform(equipmentEntities -> equipmentEntities.stream().<BasicListDTO>map(ectoOne ->
                                BasicListDTO.builder()
                                        .uuid(ectoOne.getId())
                                        .description(ectoOne.getName())
                                        .build()
                        ).toList()
                );
    }

    @Override
    public Uni<EctoOneUpgradeDTO> create(DefaultParams params, EctoOneUpgradeDTO create) {
        return ectoOneUpgradeRepository.create(create.toEntity(true)).onItem()
                .transform(ectoOneEntity ->
                        EctoOneUpgradeDTO.builder()
                                .id(ectoOneEntity.getId())
                                .cost(ectoOneEntity.getCost())
                                .name(ectoOneEntity.getName())
                                .description(ectoOneEntity.getDescription())
                                .build()
                );
    }

    @Override
    public Uni<EctoOneUpgradeDTO> getOne(CRUDGetOneParams params) {
        return ectoOneUpgradeRepository.getOne(params.getUuid()).onItem()
                .transform(ectoOneEntity ->
                        EctoOneUpgradeDTO.builder()
                                .id(ectoOneEntity.getId())
                                .cost(ectoOneEntity.getCost())
                                .name(ectoOneEntity.getName())
                                .description(ectoOneEntity.getDescription())
                                .build()
                );
    }

    @Override
    public Uni<EctoOneUpgradeDTO> update(CRUDUpdateParams params, EctoOneUpgradeDTO updated) {
        return ectoOneUpgradeRepository.update(params.getUuid(), updated.toEntity(false)).onItem()
                .transform(ectoOneEntity ->
                        EctoOneUpgradeDTO.builder()
                                .id(ectoOneEntity.getId())
                                .cost(ectoOneEntity.getCost())
                                .name(ectoOneEntity.getName())
                                .description(ectoOneEntity.getDescription())
                                .build()
                );
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return ectoOneUpgradeRepository.delete(params.getUuid());
    }
}
