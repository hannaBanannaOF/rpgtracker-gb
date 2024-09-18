package com.hbsites.rpgtracker.application.service.adapters;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.commons.domain.service.VersionedService;
import com.hbsites.rpgtracker.application.service.interfaces.EquipmentService;
import com.hbsites.rpgtracker.application.service.v1.EquipmentServiceV1;
import com.hbsites.rpgtracker.domain.dto.EquipmentDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EquipmentServiceAdapter extends VersionedService<EquipmentService> implements EquipmentService {

    @Inject
    EquipmentServiceV1 equipmentServiceV1;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getAll(params);
    }

    @Override
    public Uni<EquipmentDTO> create(DefaultParams params, EquipmentDTO create) {
        return getServiceByApiVersion(params.getApiVersion()).create(params, create);
    }

    @Override
    public Uni<EquipmentDTO> getOne(CRUDGetOneParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getOne(params);
    }

    @Override
    public Uni<EquipmentDTO> update(CRUDUpdateParams params, EquipmentDTO updated) {
        return getServiceByApiVersion(params.getApiVersion()).update(params, updated);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return getServiceByApiVersion(params.getApiVersion()).delete(params);
    }

    @Override
    protected EquipmentService getServiceByApiVersion(int apiVersion) {
        return switch(apiVersion){
            default -> equipmentServiceV1;
        };
    }
}
