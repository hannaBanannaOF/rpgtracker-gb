package com.hbsites.rpgtracker.application.resource;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.interfaces.CRUDResource;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.adapters.EquipmentUpgradeServiceAdapter;
import com.hbsites.rpgtracker.domain.dto.EquipmentUpgradeDTO;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/gb/api/equipment/upgrades")
@RolesAllowed("user")
public class EquipmentUpgradeResource implements CRUDResource<EquipmentUpgradeDTO> {

    @Inject
    private EquipmentUpgradeServiceAdapter equipmentUpgradeServiceAdapter;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return equipmentUpgradeServiceAdapter.getAll(params);
    }

    @Override
    public Uni<EquipmentUpgradeDTO> create(DefaultParams params, EquipmentUpgradeDTO create) {
        return equipmentUpgradeServiceAdapter.create(params, create);
    }

    @Override
    public Uni<EquipmentUpgradeDTO> getOne(CRUDGetOneParams params) {
        return equipmentUpgradeServiceAdapter.getOne(params);
    }

    @Override
    public Uni<EquipmentUpgradeDTO> update(CRUDUpdateParams params, EquipmentUpgradeDTO updated) {
        return equipmentUpgradeServiceAdapter.update(params, updated);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return equipmentUpgradeServiceAdapter.delete(params);
    }
}
