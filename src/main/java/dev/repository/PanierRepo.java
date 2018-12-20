package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dev.domain.Panier;

public interface PanierRepo extends JpaRepository<Panier, Integer>, JpaSpecificationExecutor<Panier>{

}
