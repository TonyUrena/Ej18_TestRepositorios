package org.api.springf1.service;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.dto.DriverResponse;
import org.api.springf1.exception.DriverNotFoundException;
import org.api.springf1.mapper.DriverMapper;
import org.api.springf1.model.Driver;
import org.api.springf1.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverResponse getDrivers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Driver> drivers = driverRepository.findAll(pageable);

        return DriverResponse.builder()
                .content(drivers.getContent().stream().map(DriverMapper::toDriverDTO).toList())
                .pageNo(drivers.getNumber())
                .pageSize(drivers.getSize())
                .totalElements(drivers.getTotalElements())
                .totalPages(drivers.getTotalPages())
                .last(drivers.isLast())
                .build();
    }

    @Override
    public DriverDTO getDriverByCode(String code) {
        Driver driver = driverRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found"));
        return DriverMapper.toDriverDTO(driver);
    }

    @Override
    public DriverDTO saveDriver(Driver driver) {
        return DriverMapper.toDriverDTO(driverRepository.save(driver));
    }

    @Override
    public DriverDTO updateDriver(Driver driver) {
        driverRepository.findById(driver.getId())
                .orElseThrow(() -> new DriverNotFoundException("Driver not found"));
        return DriverMapper.toDriverDTO(driverRepository.save(driver));
    }

    @Override
    public void deleteDriverByCode(String code) {
        Driver driver = driverRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found"));
        driverRepository.delete(driver);
    }
}
