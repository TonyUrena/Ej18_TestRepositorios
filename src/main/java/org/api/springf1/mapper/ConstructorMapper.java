package org.api.springf1.mapper;

import org.api.springf1.dto.ConstructorDTO;
import org.api.springf1.model.Constructor;

public class ConstructorMapper {
    public static ConstructorDTO toConstructorDTO(Constructor constructor) {
        return ConstructorDTO.builder()
                .id(constructor.getId())
                .ref(constructor.getRef())
                .name(constructor.getName())
                .build();
    }
}
