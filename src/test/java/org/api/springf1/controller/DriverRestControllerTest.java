package org.api.springf1.controller;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.dto.DriverResponse;
import org.api.springf1.model.Driver;
import org.api.springf1.repository.DriverRepository;
import org.api.springf1.service.DriverServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Indicamos a JUnit que se procesen las anotaciones usando Mockito
@ExtendWith(MockitoExtension.class)
public class DriverRestControllerTest {

    @InjectMocks    // Crea una instancia de esta clase para inyectar los mocks definidos abajo
    DriverServiceImpl driverService;
    @Mock
    DriverRepository driverRepository;    // Modelo del que crearemos los Mock
    Driver driver;
    DriverDTO driverDTO;

    @BeforeEach
    public void setup(){
        driver = Driver.builder().id(2L).code("123").forename("BBB").surname("CCC").build();
        driverDTO = DriverDTO.builder().id(2L).code("123").forename("BBB").surname("CCC").build();
    }

    @Test
    public void shouldReturnDriverDTOWhenFindDriverByCode() {
        // Given configura el comportamiento del mock.
        // Qué deben devolver al llamarles con ciertos argumentos
        when(driverRepository.
                findByCodeIgnoreCase(any(String.class))).
                thenReturn(Optional.ofNullable(driver));

        // When. El método a probar
        DriverDTO driverDTO = driverService.getDriverByCode("123");

        // Then. Verifica que el resultado de la clase de servicio es el correcto
        // comparándolo con el valor esperado
        assertNotNull(driverDTO);
        assertEquals("123", driverDTO.code());

        // Verifica que el metodo del mock fue invocado de la forma esperada.
        // qué métodos y con qué argumentos
        verify(driverRepository, times(1)).findByCodeIgnoreCase("123");

    }

    @Test
    public void driverService_updateDriver_returnsDriverDTO(){
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);
        when(driverRepository.findById(any())).thenReturn(Optional.ofNullable(driver));

        Driver driverToUpdate = driver;
        driverToUpdate.setForename("cosas");
        DriverDTO driverUpdateTest = driverService.updateDriver(driverToUpdate);

        assertNotNull(driverUpdateTest);
        assertEquals("cosas", driverUpdateTest.forename());
        verify(driverRepository, times(1)).save(driverToUpdate);
    }

    @Test
    public void shouldReturnNothingWhenDeleteDriverByCode(){
        when(driverRepository.
                findByCodeIgnoreCase(any())).
                thenReturn(Optional.ofNullable(driver));

        driverService.deleteDriverByCode("123");
        verify(driverRepository, times(1)).delete(driver);
    }

    @Test
    public void shouldReturnDriverResponseWhenGetAllDrivers(){
        when(driverRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl(List.of(driver)));

        DriverResponse driverResponse = driverService.getDrivers(0, 20);

        assertEquals(List.of(driverDTO), driverResponse.content());
        verify(driverRepository, times(1)).findAll(PageRequest.of(0, 20));
    }

}
