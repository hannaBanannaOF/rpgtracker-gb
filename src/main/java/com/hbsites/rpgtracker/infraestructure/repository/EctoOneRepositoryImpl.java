package com.hbsites.rpgtracker.infraestructure.repository;

import com.hbsites.rpgtracker.infraestructure.entity.EctoOneEntity;
import com.hbsites.rpgtracker.infraestructure.entity.EctoOneEntity_;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.EctoOneRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EctoOneRepositoryImpl implements EctoOneRepository {

    @Inject
    private NativeSql nativesql;

    @Inject
    private Entityql entityql;

    @Override
    public Uni<List<EctoOneEntity>> getAll(int page) {
        EctoOneEntity_ ectoOneEntity = new EctoOneEntity_();
        return Uni.createFrom().item(() -> nativesql.from(ectoOneEntity).fetch());
    }

    @Override
    public Uni<EctoOneEntity> create(EctoOneEntity create) {
        EctoOneEntity_ ectoOneEntity = new EctoOneEntity_();
        return Uni.createFrom().item(() -> entityql.insert(ectoOneEntity, create).execute().getEntity());
    }

    @Override
    public Uni<EctoOneEntity> getOne(UUID uuid) {
        EctoOneEntity_ ectoOneEntity = new EctoOneEntity_();
        return Uni.createFrom().item(() -> nativesql.from(ectoOneEntity).where(c -> c.eq(ectoOneEntity.id, uuid)).fetchOne());
    }

    @Override
    public Uni<EctoOneEntity> update(UUID uuid, EctoOneEntity updated) {
        EctoOneEntity_ ectoOneEntity = new EctoOneEntity_();
        return getOne(uuid).onItem().transform(ectoOne -> {
            ectoOne.setCost(updated.getCost());
            ectoOne.setName(updated.getName());
            ectoOne.setSeats(updated.getSeats());
            ectoOne.setCarryWeight(updated.getCarryWeight());
            return entityql.update(ectoOneEntity, ectoOne).execute().getEntity();
        });
    }

    @Override
    public Uni<Void> delete(UUID uuid) {
        EctoOneEntity_ ectoOneEntity = new EctoOneEntity_();
        return Uni.createFrom().item(() -> {
            nativesql.delete(ectoOneEntity).where(c -> c.eq(ectoOneEntity.id, uuid)).execute();
            return null;
        });
    }
}
