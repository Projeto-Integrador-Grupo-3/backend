package com.generation.projetointegrador.repository;
import com.generation.projetointegrador.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
   public List<Cargo> findAllByCargoContainingIgnoreCase(@Param("cargo") String cargo);
}
