package com.generation.projetointegradorfit.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import com.generation.projetointegradorfit.model.Treino;


public interface TreinoRepository extends JpaRepository<Treino, Long>{


    public List<Treino> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);


}
