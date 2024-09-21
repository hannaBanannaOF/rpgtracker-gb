package com.hbsites.rpgtracker.application.service.interfaces;

import com.hbsites.commons.domain.params.CRUDGetOneParams;
import com.hbsites.rpgtracker.domain.dto.SessionDTO;
import io.smallrye.mutiny.Uni;

import java.util.UUID;

public interface SessionService {

    Uni<SessionDTO> getSessionByCoreId(CRUDGetOneParams params);

}
