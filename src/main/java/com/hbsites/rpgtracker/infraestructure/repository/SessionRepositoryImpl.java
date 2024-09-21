package com.hbsites.rpgtracker.infraestructure.repository;

import com.hbsites.rpgtracker.infraestructure.entity.SessionEntity;
import com.hbsites.rpgtracker.infraestructure.entity.SessionEntity_;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.SessionRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.seasar.doma.jdbc.criteria.NativeSql;

import java.util.UUID;

@ApplicationScoped
public class SessionRepositoryImpl implements SessionRepository {

    @Inject
    private NativeSql nativeSql;

    @Override
    public Uni<SessionEntity> getByCoreSessionId(UUID uuid) {
        SessionEntity_ sessionEntity = new SessionEntity_();
        return Uni.createFrom().item(() -> nativeSql.from(sessionEntity).where(c -> c.eq(sessionEntity.coreSessionId, uuid)).fetchOne());
    }
}
