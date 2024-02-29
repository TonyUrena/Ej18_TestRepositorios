package org.api.springf1.service;

import org.api.springf1.dto.ConstructorDTO;
import org.api.springf1.model.Constructor;

import java.util.List;
public interface ConstructorService {
    List<ConstructorDTO> getConstructors();
    ConstructorDTO getConstructorByRef(String ref);
    ConstructorDTO saveConstructor(Constructor constructor);
    ConstructorDTO updateConstructor(Constructor constructor);
    void deleteConstructorByRef(String ref);
}
