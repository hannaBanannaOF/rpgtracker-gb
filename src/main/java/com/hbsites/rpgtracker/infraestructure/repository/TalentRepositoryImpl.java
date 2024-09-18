package com.hbsites.rpgtracker.infraestructure.repository;

import com.hbsites.commons.domain.dto.BasicListDTO;
import com.hbsites.rpgtracker.infraestructure.entity.TalentEntity;
import com.hbsites.rpgtracker.infraestructure.entity.TalentEntity_;
import com.hbsites.rpgtracker.infraestructure.repository.interfaces.TalentRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TalentRepositoryImpl implements TalentRepository {

    @Inject
    private NativeSql nativeSql;
    @Inject
    private Entityql entityql;

    @Override
    public Uni<List<TalentEntity>> getAll(int page) {
        TalentEntity_ talentEntity = new TalentEntity_();
        return Uni.createFrom().item(() -> nativeSql.from(talentEntity).fetch());
    }

    @Override
    public Uni<TalentEntity> create(TalentEntity create) {
        TalentEntity_ talentEntity = new TalentEntity_();
        return Uni.createFrom().item(() -> entityql.insert(talentEntity, create).execute().getEntity());
    }

    @Override
    public Uni<TalentEntity> getOne(UUID uuid) {
        TalentEntity_ talentEntity = new TalentEntity_();
        return Uni.createFrom().item(() -> nativeSql.from(talentEntity).where(c -> c.eq(talentEntity.id, uuid)).fetchOne());
    }

    @Override
    public Uni<TalentEntity> update(UUID uuid, TalentEntity updated) {
        TalentEntity_ talentEntity = new TalentEntity_();
        return getOne(uuid).onItem().transform(talentEntity1 -> {
            talentEntity1.setTalentName(updated.getTalentName());
            talentEntity1.setTrait(updated.getTrait());
            return entityql.update(talentEntity, talentEntity1).execute().getEntity();
        });
    }

    @Override
    public Uni<Void> delete(UUID uuid) {
        TalentEntity_ talentEntity = new TalentEntity_();
        return Uni.createFrom().item(() -> {
            nativeSql.delete(talentEntity).where(c -> c.eq(talentEntity.id, uuid)).execute();
            return null;
        });
    }
}
