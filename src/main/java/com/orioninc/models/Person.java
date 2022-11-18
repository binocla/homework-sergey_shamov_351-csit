package com.orioninc.models;

import io.smallrye.common.constraint.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public record Person(@NotBlank @Schema(example = "Sergey Shamov") String name, @NotNull @Positive @Schema(example = "20") Integer age) {
}
