package org.api.springf1.dto;

import lombok.Builder;

@Builder
public record DriverDTO (
        Long id,
        String code,
        String forename,
        String surname,
        String constructor
){ }
