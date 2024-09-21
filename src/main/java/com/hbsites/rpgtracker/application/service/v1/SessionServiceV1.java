package com.hbsites.rpgtracker.application.service.v1;

import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.rpgtracker.application.service.interfaces.SessionService;
import com.hbsites.rpgtracker.domain.dto.SessionDTO;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.SessionRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class SessionServiceV1 implements SessionService {

    @Inject
    private SessionRepository sessionRepository;

    @Override
    public Uni<SessionDTO> getSessionByCoreId(CRUDGetOneParams params) {
        return sessionRepository.getByCoreSessionId(params.getUuid()).onItem()
                .transform(sessionEntity ->
                    SessionDTO.builder()
                            .id(sessionEntity.getId())
                            .coreSessionId(sessionEntity.getCoreSessionId())
                            .containmentGridCapacity(sessionEntity.getContainmentGridCapacity())
                            .headquarters(sessionEntity.getHeadquarters())
                            .teamSavings(sessionEntity.getTeamSavings())
                            .build()
                );
    }
}
