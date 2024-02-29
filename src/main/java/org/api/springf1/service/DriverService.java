package org.api.springf1.service;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.dto.DriverResponse;
import org.api.springf1.model.Driver;

public interface DriverService {
    DriverResponse getDrivers(int pageNo, int pageSize);
    DriverDTO getDriverByCode(String code);
    DriverDTO saveDriver(Driver driver);
    DriverDTO updateDriver(Driver driver);
    void deleteDriverByCode(String code);
}
