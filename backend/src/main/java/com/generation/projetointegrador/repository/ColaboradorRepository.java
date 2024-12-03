package com.generation.projetointegrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.projetointegrador.model.Colaborador;

public interface ColaboradorRepository  extends JpaRepository<Colaborador, Long>{

	 public List<Colaborador> findAllBynomeCompletoContainingIgnoreCase (@Param("nomeCompleto")String nomeCompleto);
}
