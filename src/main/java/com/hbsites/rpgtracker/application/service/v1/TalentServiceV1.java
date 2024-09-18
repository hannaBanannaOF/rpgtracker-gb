package com.hbsites.rpgtracker.application.service.v1;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.interfaces.CRUDService;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.interfaces.TalentService;
import com.hbsites.rpgtracker.domain.dto.TalentDTO;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.TalentRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TalentServiceV1 implements TalentService {

    @Inject
    TalentRepository talentRepository;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return talentRepository.getAll(params.getPage()).onItem()
                .transform(talentEntities ->
                        talentEntities.stream().<BasicListDTO>map(talentEntity ->
                                BasicListDTO.builder().uuid(talentEntity.getId()).description(talentEntity.getTalentName()).build()
                        ).toList()
                );
    }

    @Override
    public Uni<TalentDTO> create(DefaultParams params, TalentDTO create) {
        return talentRepository.create(create.toEntity(true)).onItem().transform(talentEntity ->
                TalentDTO.builder()
                        .id(talentEntity.getId())
                        .name(talentEntity.getTalentName())
                        .trait(talentEntity.getTrait())
                        .build()
        );
    }

    @Override
    public Uni<TalentDTO> getOne(CRUDGetOneParams params) {
        return talentRepository.getOne(params.getUuid()).onItem().transform(talentEntity ->
                TalentDTO.builder()
                        .id(talentEntity.getId())
                        .name(talentEntity.getTalentName())
                        .trait(talentEntity.getTrait())
                        .build()
        );
    }

    @Override
    public Uni<TalentDTO> update(CRUDUpdateParams params, TalentDTO updated) {
        return talentRepository.update(params.getUuid(), updated.toEntity(false)).onItem().transform(talentEntity ->
                TalentDTO.builder()
                        .id(talentEntity.getId())
                        .name(talentEntity.getTalentName())
                        .trait(talentEntity.getTrait())
                        .build()
        );
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return talentRepository.delete(params.getUuid());
    }
}
