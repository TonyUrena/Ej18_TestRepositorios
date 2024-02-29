package org.api.springf1.controller;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.dto.DriverResponse;
import org.api.springf1.model.Driver;
import org.api.springf1.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
public class DriverRestController {
    private final DriverService driverService;

    @Autowired
    public DriverRestController(DriverService service) {
        this.driverService = service;
    }

    /*
    GET http://localhost:8080/api/drivers
    */
    @GetMapping("")
    public ResponseEntity<DriverResponse> getDrivers(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        DriverResponse drivers = driverService.getDrivers(pageNo, pageSize);
        if (drivers.content().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(drivers);
    }

    /*
    GET http://localhost:8080/api/drivers/alo
     */
    @GetMapping("/{code}")
    public ResponseEntity<DriverDTO> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(driverService.getDriverByCode(code));
    }

    /*
    POST http://localhost:8080/api/drivers/
    */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DriverDTO> create(@RequestBody Driver driver) {
        if (driver.getId() != null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(driverService.saveDriver(driver));
    }

    /*
    PUT http://localhost:8080/api/drivers/
    */
    @PutMapping("")
    public ResponseEntity<DriverDTO> update(@RequestBody Driver driver) {
        if (driver == null || driver.getId() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(driverService.updateDriver(driver));
    }

    /*
    DELETE http://localhost:8080/api/drivers/alo
    */
    @DeleteMapping("/{code}")
    public ResponseEntity<Driver> deleteByCode(@PathVariable String code) {
        if (code == null || code.isBlank())
            return ResponseEntity.badRequest().build();
        driverService.deleteDriverByCode(code);
        return ResponseEntity.noContent().build();
    }

}
