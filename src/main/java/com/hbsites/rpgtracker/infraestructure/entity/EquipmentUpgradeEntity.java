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
@Table(name = "equipment_upgrade")
@Getter
@Setter
public class EquipmentUpgradeEntity extends BaseEntity {
    @Column(name = "id")
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "description")
    private String description;
}
