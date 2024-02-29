package org.api.springf1.controller;

import org.api.springf1.dto.ConstructorDTO;
import org.api.springf1.model.Constructor;
import org.api.springf1.service.ConstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/constructors")
public class ConstructorRestController {
    private final ConstructorService constructorService;

    @Autowired
    public ConstructorRestController(ConstructorService service) {
        this.constructorService = service;
    }

    /*
    GET http://localhost:8080/api/constructors
     */
    @GetMapping("")
    public ResponseEntity<List<ConstructorDTO>> getAll() {
        List<ConstructorDTO> constructors = constructorService.getConstructors();
        if (constructors.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(constructors);
    }

    /*
    GET http://localhost:8080/api/constructors/mclaren
     */
    @GetMapping("/{ref}")
    public ResponseEntity<ConstructorDTO> getByRef(@PathVariable String ref) {
        return ResponseEntity.ok(constructorService.getConstructorByRef(ref));
    }

    /*
    POST http://localhost:8080/api/constructors/
    */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ConstructorDTO> create(@RequestBody Constructor constructor) {
        if (constructor.getId() != null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(constructorService.saveConstructor(constructor));
    }

    /*
    PUT http://localhost:8080/api/constructors/
    */
    @PutMapping("")
    public ResponseEntity<ConstructorDTO> update(@RequestBody Constructor constructor) {
        if (constructor == null || constructor.getId() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(constructorService.updateConstructor(constructor));
    }

    /*
    DELETE http://localhost:8080/api/constructors/mclaren
    */
    @DeleteMapping("/{ref}")
    public ResponseEntity<Constructor> deleteByRef(@PathVariable String ref) {
        if (ref == null || ref.isBlank())
            return ResponseEntity.badRequest().build();
        constructorService.deleteConstructorByRef(ref);
        return ResponseEntity.noContent().build();
    }
}