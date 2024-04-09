package com.s1410.calme.Domain.Dtos.request;

import com.s1410.calme.Domain.Utils.RelationType;
import jakarta.validation.constraints.NotNull;

public record RequestEditRelationAA(
        @NotNull Long assistantId,
        @NotNull Long assistedId,
        @NotNull RelationType relationType) {
}
