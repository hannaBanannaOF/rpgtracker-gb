package com.hbsites.rpgtracker.application.service.adapters;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.commons.domain.service.VersionedService;
import com.hbsites.rpgtracker.application.service.interfaces.EctoOneService;
import com.hbsites.rpgtracker.application.service.interfaces.EquipmentService;
import com.hbsites.rpgtracker.application.service.v1.EctoOneServiceV1;
import com.hbsites.rpgtracker.domain.dto.EctoOneDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EctoOneServiceAdapter extends VersionedService<EctoOneService> implements EctoOneService {

    @Inject
    EctoOneServiceV1 ectoOneServiceV1;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getAll(params);
    }

    @Override
    public Uni<EctoOneDTO> create(DefaultParams params, EctoOneDTO create) {
        return getServiceByApiVersion(params.getApiVersion()).create(params, create);
    }

    @Override
    public Uni<EctoOneDTO> getOne(CRUDGetOneParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getOne(params);
    }

    @Override
    public Uni<EctoOneDTO> update(CRUDUpdateParams params, EctoOneDTO updated) {
        return getServiceByApiVersion(params.getApiVersion()).update(params, updated);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return getServiceByApiVersion(params.getApiVersion()).delete(params);
    }

    @Override
    protected EctoOneService getServiceByApiVersion(int apiVersion) {
        return switch (apiVersion) {
            default -> ectoOneServiceV1;
        };
    }
}
