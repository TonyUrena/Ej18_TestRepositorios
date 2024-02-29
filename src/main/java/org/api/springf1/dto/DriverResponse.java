package org.api.springf1.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DriverResponse(
        List<DriverDTO> content,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
) {
}
