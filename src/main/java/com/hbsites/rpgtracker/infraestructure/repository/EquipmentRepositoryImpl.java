package com.hbsites.rpgtracker.infraestructure.repository;

import com.hbsites.rpgtracker.infraestructure.entity.EquipmentEntity;
import com.hbsites.rpgtracker.infraestructure.entity.EquipmentEntity_;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.EquipmentRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EquipmentRepositoryImpl implements EquipmentRepository {

    @Inject
    private NativeSql nativeSql;

    @Inject
    private Entityql entityql;

    @Override
    public Uni<List<EquipmentEntity>> getAll(int page) {
        EquipmentEntity_ equipmentEntity = new EquipmentEntity_();
        return Uni.createFrom().item(() -> nativeSql.from(equipmentEntity).fetch());
    }

    @Override
    public Uni<EquipmentEntity> create(EquipmentEntity create) {
        EquipmentEntity_ equipmentEntity = new EquipmentEntity_();
        return Uni.createFrom().item(() -> entityql.insert(equipmentEntity, create).execute().getEntity());
    }

    @Override
    public Uni<EquipmentEntity> getOne(UUID uuid) {
        EquipmentEntity_ equipmentEntity = new EquipmentEntity_();
        return Uni.createFrom().item(() -> nativeSql.from(equipmentEntity).where(c -> c.eq(equipmentEntity.id, uuid)).fetchOne());
    }

    @Override
    public Uni<EquipmentEntity> update(UUID uuid, EquipmentEntity updated) {
        EquipmentEntity_ equipmentEntity = new EquipmentEntity_();
        return getOne(uuid).onItem().transform(equipment -> {
            equipment.setEquipmentName(updated.getEquipmentName());
            equipment.setDescription(updated.getDescription());
            equipment.setWeight(updated.getWeight());
            equipment.setCost(updated.getCost());
            return entityql.update(equipmentEntity, equipment).execute().getEntity();
        });
    }

    @Override
    public Uni<Void> delete(UUID uuid) {
        EquipmentEntity_ equipmentEntity = new EquipmentEntity_();
        return Uni.createFrom().item(() -> {
            nativeSql.delete(equipmentEntity).where(c -> c.eq(equipmentEntity.id, uuid)).execute();
            return null;
        });
    }
}
