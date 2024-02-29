package org.api.springf1.service;

import org.api.springf1.dto.ConstructorDTO;
import org.api.springf1.exception.ConstructorNotFoundException;
import org.api.springf1.mapper.ConstructorMapper;
import org.api.springf1.model.Constructor;
import org.api.springf1.repository.ConstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstructorServiceImpl implements ConstructorService {
    private final ConstructorRepository constructorRepository;

    @Autowired
    public ConstructorServiceImpl(ConstructorRepository constructorRepository) {
        this.constructorRepository = constructorRepository;
    }

    @Override
    public List<ConstructorDTO> getConstructors() {
        return constructorRepository.findAll()
                .stream()
                .map(ConstructorMapper::toConstructorDTO)
                .toList();
    }

    @Override
    public ConstructorDTO getConstructorByRef(String ref) {
        Constructor constructor = constructorRepository.findByRefIgnoreCase(ref)
                .orElseThrow(() -> new ConstructorNotFoundException("Constructor not found"));
        return ConstructorMapper.toConstructorDTO(constructor);
    }

    @Override
    public ConstructorDTO saveConstructor(Constructor constructor) {
        return ConstructorMapper.toConstructorDTO(constructorRepository.save(constructor));
    }

    @Override
    public ConstructorDTO updateConstructor(Constructor constructor) {
        constructorRepository.findByRefIgnoreCase(constructor.getRef())
                .orElseThrow(() -> new ConstructorNotFoundException("Constructor not found"));
        return ConstructorMapper.toConstructorDTO(constructorRepository.save(constructor));
    }

    @Override
    public void deleteConstructorByRef(String ref) {
        Constructor constructor = constructorRepository.findByRefIgnoreCase(ref)
                .orElseThrow(() -> new ConstructorNotFoundException("Constructor not found"));
        constructorRepository.delete(constructor);
    }
}
