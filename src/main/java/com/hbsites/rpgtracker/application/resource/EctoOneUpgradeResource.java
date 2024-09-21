package com.hbsites.rpgtracker.application.resource;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.interfaces.CRUDResource;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.adapters.EctoOneUpgradeServiceAdapter;
import com.hbsites.rpgtracker.domain.dto.EctoOneUpgradeDTO;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/gb/api/ecto-one/upgrades")
@RolesAllowed("user")
public class EctoOneUpgradeResource implements CRUDResource<EctoOneUpgradeDTO> {

    @Inject
    private EctoOneUpgradeServiceAdapter edEctoOneUpgradeServiceAdapter;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return edEctoOneUpgradeServiceAdapter.getAll(params);
    }

    @Override
    public Uni<EctoOneUpgradeDTO> create(DefaultParams params, EctoOneUpgradeDTO create) {
        return edEctoOneUpgradeServiceAdapter.create(params, create);
    }

    @Override
    public Uni<EctoOneUpgradeDTO> getOne(CRUDGetOneParams params) {
        return edEctoOneUpgradeServiceAdapter.getOne(params);
    }

    @Override
    public Uni<EctoOneUpgradeDTO> update(CRUDUpdateParams params, EctoOneUpgradeDTO updated) {
        return edEctoOneUpgradeServiceAdapter.update(params, updated);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return edEctoOneUpgradeServiceAdapter.delete(params);
    }
}
