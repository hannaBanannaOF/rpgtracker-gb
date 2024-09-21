package com.hbsites.rpgtracker.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    private UUID id;
    private UUID coreSessionId;
    private Boolean headquarters;
    private BigDecimal teamSavings;
    private Boolean containmentGridCapacity;
}
