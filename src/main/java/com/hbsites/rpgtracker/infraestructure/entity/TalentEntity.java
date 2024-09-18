package com.hbsites.rpgtracker.infraestructure.entity;

import com.hbsites.commons.infrastructure.database.entity.BaseEntity;
import com.hbsites.commons.infrastructure.database.entitylistener.BaseEntityListener;
import com.hbsites.commons.rpgtracker.domain.enumeration.ETRPGSystem;
import com.hbsites.rpgtracker.domain.enumeration.ETrait;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import java.util.UUID;

@Entity(listener = BaseEntityListener.class, metamodel = @Metamodel)
@Table(name = "talent")
@Getter
@Setter
public class TalentEntity extends BaseEntity {

    @Column(name = "id")
    @Id
    private UUID id;

    @Column(name = "name")
    private String talentName;

    @Column(name = "trait")
    private ETrait trait;
}
