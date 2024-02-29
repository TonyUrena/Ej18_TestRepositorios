package org.api.springf1.repository;

import org.api.springf1.model.Constructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DataJpaTest
class ConstructorRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.5");

    @Autowired ConstructorRepository constructorRepository;
    Constructor constructorA, constructorB;

    @BeforeEach
    void initialize(){
        constructorA = Constructor
                .builder().ref("AAA").name("aaa").drivers(null)
                .nationality("AAaa").url("aaaa.com").build();
        constructorB = Constructor
                .builder().ref("BBB").name("bbb").drivers(null)
                .nationality("BBbb").url("bbbb.com").build();
    }

    @Test
    void shouldReturnSavedConstructorWhenSave(){
        Constructor constructorSaved = constructorRepository.save(constructorA);

        assertThat(constructorSaved).isNotNull();
        assertThat(constructorSaved.getId()).isGreaterThan(0);
    }

    @Test
    void shouldReturnMoreThanOneConstructorWhenSaveTwoConstructors(){
        constructorRepository.save(constructorA);
        constructorRepository.save(constructorB);

        assertEquals(2, constructorRepository.findAll().size());
    }

    @Test
    void shouldReturnConstructorNotNullWhenFindByCode(){
        constructorRepository.save(constructorA);

        assertNotNull(constructorRepository.findByRefIgnoreCase("AAA"));
    }

    @Test
    void shouldReturnConstructorNotNullWhenUpdateConstructor(){
        constructorRepository.save(constructorA);
        constructorA.setName("CCC");

        assertNotNull(constructorRepository.save(constructorA));
    }

    @Test
    void shouldReturnNullConstructorWhenDelete(){
        constructorRepository.save(constructorA);
        constructorRepository.delete(constructorA);

        assertEquals(0, constructorRepository.findAll().size());
    }

}
