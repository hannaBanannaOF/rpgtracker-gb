package com.hbsites.rpgtracker.infraestructure.repository;

import com.hbsites.rpgtracker.infraestructure.entity.EquipmentUpgradeEntity;
import com.hbsites.rpgtracker.infraestructure.entity.EquipmentUpgradeEntity_;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.EquipmentUpgradeRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EquipmentUpgradeRepositoryImpl implements EquipmentUpgradeRepository {

    @Inject
    private NativeSql nativeSql;

    @Inject
    private Entityql entityql;

    @Override
    public Uni<List<EquipmentUpgradeEntity>> getAll(int page) {
        EquipmentUpgradeEntity_ equipmentUpgradeEntity = new EquipmentUpgradeEntity_();
        return Uni.createFrom().item(() -> nativeSql.from(equipmentUpgradeEntity).fetch());
    }

    @Override
    public Uni<EquipmentUpgradeEntity> create(EquipmentUpgradeEntity create) {
        EquipmentUpgradeEntity_ equipmentUpgradeEntity = new EquipmentUpgradeEntity_();
        return Uni.createFrom().item(() -> entityql.insert(equipmentUpgradeEntity, create).execute().getEntity());
    }

    @Override
    public Uni<EquipmentUpgradeEntity> getOne(UUID uuid) {
        EquipmentUpgradeEntity_ equipmentUpgradeEntity = new EquipmentUpgradeEntity_();
        return Uni.createFrom().item(() -> nativeSql.from(equipmentUpgradeEntity).where(c -> c.eq(equipmentUpgradeEntity.id, uuid)).fetchOne());
    }

    @Override
    public Uni<EquipmentUpgradeEntity> update(UUID uuid, EquipmentUpgradeEntity updated) {
        EquipmentUpgradeEntity_ equipmentUpgradeEntity = new EquipmentUpgradeEntity_();
        return getOne(uuid).onItem().transform(ectoOneUpgrade -> {
            ectoOneUpgrade.setCost(updated.getCost());
            ectoOneUpgrade.setDescription(updated.getDescription());
            ectoOneUpgrade.setName(updated.getName());
            return entityql.update(equipmentUpgradeEntity, ectoOneUpgrade).execute().getEntity();
        });
    }

    @Override
    public Uni<Void> delete(UUID uuid) {
        EquipmentUpgradeEntity_ equipmentUpgradeEntity = new EquipmentUpgradeEntity_();
        return Uni.createFrom().item(() -> {
            nativeSql.delete(equipmentUpgradeEntity).where(c -> c.eq(equipmentUpgradeEntity.id, uuid)).execute();
            return null;
        });
    }
}
