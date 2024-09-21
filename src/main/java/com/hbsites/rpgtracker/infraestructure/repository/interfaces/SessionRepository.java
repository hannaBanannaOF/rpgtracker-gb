package com.hbsites.rpgtracker.infraestructure.repository.interfaces;

import com.hbsites.rpgtracker.infraestructure.entity.SessionEntity;
import io.smallrye.mutiny.Uni;

import java.util.UUID;

public interface SessionRepository {
    Uni<SessionEntity> getByCoreSessionId(UUID uuid);
}
