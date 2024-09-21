package com.hbsites.rpgtracker.application.service.adapters;

import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.commons.domain.service.VersionedService;
import com.hbsites.rpgtracker.application.service.interfaces.SessionService;
import com.hbsites.rpgtracker.application.service.v1.SessionServiceV1;
import com.hbsites.rpgtracker.domain.dto.SessionDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class SessionServiceAdapter extends VersionedService<SessionService> implements SessionService {

    @Inject
    private SessionServiceV1 sessionServiceV1;

    @Override
    protected SessionService getServiceByApiVersion(int apiVersion) {
        return switch(apiVersion) {
            default -> sessionServiceV1;
        };
    }

    @Override
    public Uni<SessionDTO> getSessionByCoreId(CRUDGetOneParams params) {
        return getServiceByApiVersion(params.getApiVersion()).getSessionByCoreId(params);
    }
}
