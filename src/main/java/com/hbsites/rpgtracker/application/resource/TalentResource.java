package com.hbsites.rpgtracker.application.resource;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.commons.domain.interfaces.CRUDResource;
import com.hbsites.commons.domain.params.CRUDDeleteParams;
import com.hbsites.commons.domain.params.CRUDGetAllParams;
import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.params.CRUDUpdateParams;
import com.hbsites.commons.domain.params.DefaultParams;
import com.hbsites.rpgtracker.application.service.adapters.TalentServiceAdapter;
import com.hbsites.rpgtracker.domain.dto.TalentDTO;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/gb/api/talents")
@RolesAllowed("user")
public class TalentResource implements CRUDResource<TalentDTO> {

    @Inject
    private TalentServiceAdapter talentServiceAdapter;

    @Override
    public Uni<List<BasicListDTO>> getAll(CRUDGetAllParams params) {
        return talentServiceAdapter.getAll(params);
    }

    @Override
    public Uni<TalentDTO> getOne(CRUDGetOneParams params) {
        return talentServiceAdapter.getOne(params);
    }

    @Override
    public Uni<Void> delete(CRUDDeleteParams params) {
        return talentServiceAdapter.delete(params);
    }

    @Override
    public Uni<TalentDTO> update(CRUDUpdateParams params, TalentDTO updated) {
        return talentServiceAdapter.update(params, updated);
    }

    @Override
    public Uni<TalentDTO> create(DefaultParams params, TalentDTO create) {
        return talentServiceAdapter.create(params, create);
    }
}
