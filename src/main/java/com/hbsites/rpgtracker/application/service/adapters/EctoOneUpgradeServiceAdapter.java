package com.hbsites.rpgtracker.application.service.adapters;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.commons.domain.service.VersionedService;
import com.hbsites.rpgtracker.application.service.interfaces.EctoOneUpgradeService;
import com.hbsites.rpgtracker.application.service.v1.EctoOneUpgradeServiceV1;
import com.hbsites.rpgtracker.domain.dto.EctoOneUpgradeDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EctoOneUpgradeServiceAdapter extends VersionedService<EctoOneUpgradeService> implements EctoOneUpgradeService {

    @Inject
    private EctoOneUpgradeServiceV1 ectoOneUpgradeServiceV1;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getAll(params);
    }

    @Override
    public Uni<EctoOneUpgradeDTO> create(DefaultParams params, EctoOneUpgradeDTO create) {
        return getServiceByApiVersion(params.getApiVersion()).create(params, create);
    }

    @Override
    public Uni<EctoOneUpgradeDTO> getOne(CRUDGetOneParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getOne(params);
    }

    @Override
    public Uni<EctoOneUpgradeDTO> update(CRUDUpdateParams params, EctoOneUpgradeDTO updated) {
        return getServiceByApiVersion(params.getApiVersion()).update(params, updated);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return getServiceByApiVersion(params.getApiVersion()).delete(params);
    }

    @Override
    protected EctoOneUpgradeService getServiceByApiVersion(int apiVersion) {
        return switch (apiVersion) {
            default -> ectoOneUpgradeServiceV1;
        };
    }
}
