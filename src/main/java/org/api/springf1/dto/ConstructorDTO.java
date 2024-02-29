package org.api.springf1.dto;

import lombok.Builder;

@Builder
public record ConstructorDTO (
        Long id,
        String ref,
        String name
) { }
