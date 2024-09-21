package com.hbsites.rpgtracker.infraestructure.entity;

import com.hbsites.commons.infrastructure.database.entity.BaseEntity;
import com.hbsites.commons.infrastructure.database.entitylistener.BaseEntityListener;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(listener = BaseEntityListener.class, metamodel = @Metamodel)
@Table(name = "session")
@Getter
@Setter
public class SessionEntity extends BaseEntity {
    @Column(name = "id")
    @Id
    private UUID id;

    @Column(name = "core_session_id")
    private UUID coreSessionId;

    @Column(name = "headquarters")
    private Boolean headquarters;

    @Column(name = "team_savings")
    private BigDecimal teamSavings;

    @Column(name = "containment_grid_capacity")
    private Boolean containmentGridCapacity;
}
