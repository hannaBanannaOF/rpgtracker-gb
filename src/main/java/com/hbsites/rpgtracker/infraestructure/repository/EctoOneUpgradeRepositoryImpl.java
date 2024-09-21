package com.hbsites.rpgtracker.infraestructure.repository;

import com.hbsites.rpgtracker.infraestructure.entity.EctoOneUpgradeEntity;
import com.hbsites.rpgtracker.infraestructure.entity.EctoOneUpgradeEntity_;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.EctoOneUpgradeRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EctoOneUpgradeRepositoryImpl implements EctoOneUpgradeRepository {

    @Inject
    private NativeSql nativeSql;

    @Inject
    private Entityql entityql;

    @Override
    public Uni<List<EctoOneUpgradeEntity>> getAll(int page) {
        EctoOneUpgradeEntity_ ectoOneUpgradeEntity = new EctoOneUpgradeEntity_();
        return Uni.createFrom().item(() -> nativeSql.from(ectoOneUpgradeEntity).fetch());
    }

    @Override
    public Uni<EctoOneUpgradeEntity> create(EctoOneUpgradeEntity create) {
        EctoOneUpgradeEntity_ ectoOneUpgradeEntity = new EctoOneUpgradeEntity_();
        return Uni.createFrom().item(() -> entityql.insert(ectoOneUpgradeEntity, create).execute().getEntity());
    }

    @Override
    public Uni<EctoOneUpgradeEntity> getOne(UUID uuid) {
        EctoOneUpgradeEntity_ ectoOneUpgradeEntity = new EctoOneUpgradeEntity_();
        return Uni.createFrom().item(() -> nativeSql.from(ectoOneUpgradeEntity).where(c -> c.eq(ectoOneUpgradeEntity.id, uuid)).fetchOne());
    }

    @Override
    public Uni<EctoOneUpgradeEntity> update(UUID uuid, EctoOneUpgradeEntity updated) {
        EctoOneUpgradeEntity_ ectoOneUpgradeEntity = new EctoOneUpgradeEntity_();
        return getOne(uuid).onItem().transform(ectoOneUpgrade -> {
            ectoOneUpgrade.setCost(updated.getCost());
            ectoOneUpgrade.setDescription(updated.getDescription());
            ectoOneUpgrade.setName(updated.getName());
            return entityql.update(ectoOneUpgradeEntity, ectoOneUpgrade).execute().getEntity();
        });
    }

    @Override
    public Uni<Void> delete(UUID uuid) {
        EctoOneUpgradeEntity_ ectoOneUpgradeEntity = new EctoOneUpgradeEntity_();
        return Uni.createFrom().item(() -> {
            nativeSql.delete(ectoOneUpgradeEntity).where(c -> c.eq(ectoOneUpgradeEntity.id, uuid)).execute();
            return null;
        });
    }
}
