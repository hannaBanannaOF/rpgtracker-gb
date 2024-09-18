package com.hbsites.rpgtracker.application.resource;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.interfaces.CRUDResource;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.adapters.EctoOneServiceAdapter;
import com.hbsites.rpgtracker.domain.dto.EctoOneDTO;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/gb/api/ecto-one")
@RolesAllowed("user")
public class EctoOneResource implements CRUDResource<EctoOneDTO> {

    @Inject
    private EctoOneServiceAdapter ectoOneServiceAdapter;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return ectoOneServiceAdapter.getAll(params);
    }

    @Override
    public Uni<EctoOneDTO> create(DefaultParams params, EctoOneDTO create) {
        return ectoOneServiceAdapter.create(params, create);
    }

    @Override
    public Uni<EctoOneDTO> getOne(CRUDGetOneParams params) {
        return ectoOneServiceAdapter.getOne(params);
    }

    @Override
    public Uni<EctoOneDTO> update(CRUDUpdateParams params, EctoOneDTO updated) {
        return ectoOneServiceAdapter.update(params, updated);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return ectoOneServiceAdapter.delete(params);
    }
}
