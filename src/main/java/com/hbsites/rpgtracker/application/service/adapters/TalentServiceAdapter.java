package com.hbsites.rpgtracker.application.service.adapters;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.commons.domain.service.VersionedService;
import com.hbsites.rpgtracker.application.service.interfaces.TalentService;
import com.hbsites.rpgtracker.application.service.v1.TalentServiceV1;
import com.hbsites.rpgtracker.domain.dto.TalentDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TalentServiceAdapter extends VersionedService<TalentService> implements TalentService {

    @Inject
    TalentServiceV1 talentServiceV1;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getAll(params);
    }

    @Override
    public Uni<TalentDTO> create(DefaultParams params, TalentDTO create) {
        return getServiceByApiVersion(params.getApiVersion()).create(params, create);
    }

    @Override
    public Uni<TalentDTO> getOne(CRUDGetOneParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getOne(params);
    }

    @Override
    public Uni<TalentDTO> update(CRUDUpdateParams params, TalentDTO updated) {
        return getServiceByApiVersion(params.getApiVersion()).update(params, updated);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return getServiceByApiVersion(params.getApiVersion()).delete(params);
    }

    @Override
    protected TalentService getServiceByApiVersion(int apiVersion) {
        return switch(apiVersion){
            default -> talentServiceV1;
        };
    }
}
