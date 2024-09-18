package com.hbsites.rpgtracker.application.resource;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.interfaces.CRUDResource;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.adapters.EquipmentServiceAdapter;
import com.hbsites.rpgtracker.domain.dto.EquipmentDTO;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/gb/api/equipment")
@RolesAllowed("user")
public class EquipmentResource implements CRUDResource<EquipmentDTO> {

    @Inject
    private EquipmentServiceAdapter equipmentServiceAdapter;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return equipmentServiceAdapter.getAll(params);
    }

    @Override
    public Uni<EquipmentDTO> create(DefaultParams params, EquipmentDTO create) {
        return equipmentServiceAdapter.create(params, create);
    }

    @Override
    public Uni<EquipmentDTO> getOne(CRUDGetOneParams params) {
        return equipmentServiceAdapter.getOne(params);
    }

    @Override
    public Uni<EquipmentDTO> update(CRUDUpdateParams params, EquipmentDTO updated) {
        return equipmentServiceAdapter.update(params, updated);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return equipmentServiceAdapter.delete(params);
    }
}
