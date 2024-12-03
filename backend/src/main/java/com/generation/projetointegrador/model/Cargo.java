package com.generation.projetointegrador.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_cargo")
public class Cargo {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idCargo;

   @NotBlank
   @Size(max = 100)
   private String cargo;

   @NotBlank
   @Size(max = 100)
   private String descricao;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "cargo", cascade = CascadeType.REMOVE)
   @JsonIgnoreProperties("colaborador")
   private List<Colaborador> colaborador;
  
   public Long getIdCargo() {
       return idCargo;
   }

   public void setIdCargo(Long idCargo) {
       this.idCargo = idCargo;
   }

   public String getCargo() {
       return cargo;
   }

   public void setCargo(String cargo) {
       this.cargo = cargo;
   }

   public String getDescricao() {
       return descricao;
   }

   public void setDescricao(String descricao) {
       this.descricao = descricao;
   }

	public List<Colaborador> getColaborador() {
		return colaborador;
	}

	public void setColaborador(List<Colaborador> colaborador) {
		this.colaborador = colaborador;
	}
  
}
