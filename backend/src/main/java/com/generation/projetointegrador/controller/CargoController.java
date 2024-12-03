package com.generation.projetointegrador.controller;
import com.generation.projetointegrador.repository.CargoRepository;
import com.generation.projetointegrador.model.Cargo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@RestController
@RequestMapping("/cargo")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping
    public ResponseEntity<List<Cargo>> getAll() {
        return ResponseEntity.ok(cargoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getById(@PathVariable Long id) {
        return cargoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<List<Cargo>> getByCargo(@PathVariable String cargo){
        return ResponseEntity.ok(cargoRepository.findAllByCargoContainingIgnoreCase(cargo));
    }

    @PostMapping
    public ResponseEntity<Cargo> post(@Valid @RequestBody Cargo cargo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cargoRepository.save(cargo));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Cargo> postagem = cargoRepository.findById(id);

        if (postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        cargoRepository.deleteById(id);
    }

        @PutMapping
        public ResponseEntity<Cargo> put(@Valid @RequestBody Cargo cargo){
            return cargoRepository.findById(cargo.getIdCargo())
                    .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                            .body(cargoRepository.save(cargo)))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        }
    }


