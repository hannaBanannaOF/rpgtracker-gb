package com.hbsites.rpgtracker.application.resource;

import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.rpgtracker.application.service.adapters.SessionServiceAdapter;
import com.hbsites.rpgtracker.domain.dto.SessionDTO;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/gb/api/session")
@RolesAllowed("user")
public class SessionResource {
    @Inject
    private SessionServiceAdapter sessionServiceAdapter;

    @GET
    @Path("/{uuid}")
    public Uni<SessionDTO> getSessionDetails(CRUDGetOneParams params) {
        return sessionServiceAdapter.getSessionByCoreId(params);
    }
}
