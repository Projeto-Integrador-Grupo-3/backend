package com.generation.projetointegrador.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.generation.projetointegrador.model.Colaborador;
import com.generation.projetointegrador.repository.ColaboradorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/colaborador")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ColaboradorController {
   @Autowired
   private ColaboradorRepository colaboradorRepository;
   @GetMapping
   public ResponseEntity<List<Colaborador>> getAll() {
		return ResponseEntity.ok(colaboradorRepository.findAll());
	}

   @GetMapping("/{id}")
   public ResponseEntity <Colaborador> getById(@PathVariable Long id) {
       Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
       if (colaborador.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
       return colaboradorRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
   }

   @GetMapping("/nomeCompleto/{nomeCompleto}")
   public ResponseEntity<List<Colaborador>> getBynomeCompleto (@PathVariable String nomeCompleto){
       return ResponseEntity.ok(colaboradorRepository.findAllBynomeCompletoContainingIgnoreCase(nomeCompleto));
   }

   @PostMapping
   public ResponseEntity<Colaborador> post(@Valid @RequestBody Colaborador colaborador) {
       try {
           colaboradorRepository.save(colaborador);
       } catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar colaborador", e);
       }
       return ResponseEntity.status(HttpStatus.CREATED)
       		.body(colaboradorRepository.save(colaborador));
   }

   @PutMapping("/{id}")
   public ResponseEntity<Colaborador> put(@Valid @RequestBody Colaborador colaborador) {
       return colaboradorRepository.findById(colaborador.getIdColaborador())
               .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                       .body(colaboradorRepository.save(colaborador)))
               .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
   }
   
   @DeleteMapping("/{id}")
   public void delete(@PathVariable Long id) {
       Optional<Colaborador> colaboradorExistente = colaboradorRepository.findById(id);
       if (colaboradorExistente.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
       colaboradorRepository.deleteById(id);
   }
}
