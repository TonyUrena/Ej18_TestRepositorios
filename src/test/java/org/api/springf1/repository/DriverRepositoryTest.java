package org.api.springf1.repository;

import org.api.springf1.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DriverRepositoryTest {

    @Autowired DriverRepository driverRepository;
    Driver driverA, driverB;

    @BeforeEach
    void initialize(){
        driverA = Driver
                .builder().code("AAA").forename("Aaaa").surname("Aa")
                .constructor(null).dob(LocalDate.of(2024, 1, 1))
                .nationality("AAAA").build();
        driverB = Driver
                .builder().code("BBB").forename("Bbbb").surname("Bb")
                .constructor(null).dob(LocalDate.of(1900, 1, 1))
                .nationality("BBBB").build();
    }

    @Test
    void shouldReturnSavedDriverWhenSave(){
        Driver driverSaved = driverRepository.save(driverA);

        assertThat(driverSaved).isNotNull();
        assertThat(driverSaved.getId()).isGreaterThan(0);
    }

    @Test
    void shouldReturnMoreThanOneDriverWhenSaveTwoDrivers(){
        driverRepository.save(driverA);
        driverRepository.save(driverB);

        assertEquals(2, driverRepository.findAll().size());
    }

    @Test
    void shouldReturnDriverNotNullWhenFindByCode(){
        driverRepository.save(driverA);

        assertNotNull(driverRepository.findByCodeIgnoreCase("AAA"));
    }

    @Test
    void shouldReturnDriverNotNullWhenUpdateDriver(){
        driverRepository.save(driverA);
        driverA.setForename("CCC");

        assertNotNull(driverRepository.save(driverA));
    }

    @Test
    void shouldReturnNullDriverWhenDelete(){
        driverRepository.save(driverA);
        driverRepository.delete(driverA);

        assertEquals(0, driverRepository.findAll().size());
    }

}
