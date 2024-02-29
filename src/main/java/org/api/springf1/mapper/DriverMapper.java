package org.api.springf1.mapper;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.model.Driver;

public class DriverMapper {
    public static DriverDTO toDriverDTO(Driver driver) {
        return DriverDTO.builder()
                .id(driver.getId())
                .code(driver.getCode())
                .forename(driver.getForename())
                .surname(driver.getSurname())
                .constructor(driver.getConstructor() != null ? driver.getConstructor().getName() : null)
                .build();
    }
}
