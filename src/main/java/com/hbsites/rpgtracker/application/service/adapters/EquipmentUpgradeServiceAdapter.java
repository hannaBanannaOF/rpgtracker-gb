package com.hbsites.rpgtracker.application.service.adapters;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.commons.domain.service.VersionedService;
import com.hbsites.rpgtracker.application.service.interfaces.EquipmentUpgradeService;
import com.hbsites.rpgtracker.application.service.v1.EquipmentUpgradeServiceV1;
import com.hbsites.rpgtracker.domain.dto.EquipmentUpgradeDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EquipmentUpgradeServiceAdapter extends VersionedService<EquipmentUpgradeService> implements EquipmentUpgradeService {

    @Inject
    private EquipmentUpgradeServiceV1 equipmentUpgradeServiceV1;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getAll(params);
    }

    @Override
    public Uni<EquipmentUpgradeDTO> create(DefaultParams params, EquipmentUpgradeDTO create) {
        return getServiceByApiVersion(params.getApiVersion()).create(params, create);
    }

    @Override
    public Uni<EquipmentUpgradeDTO> getOne(CRUDGetOneParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getOne(params);
    }

    @Override
    public Uni<EquipmentUpgradeDTO> update(CRUDUpdateParams params, EquipmentUpgradeDTO updated) {
        return getServiceByApiVersion(params.getApiVersion()).update(params, updated);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return getServiceByApiVersion(params.getApiVersion()).delete(params);
    }

    @Override
    protected EquipmentUpgradeService getServiceByApiVersion(int apiVersion) {
        return switch (apiVersion) {
            default -> equipmentUpgradeServiceV1;
        };
    }
}
