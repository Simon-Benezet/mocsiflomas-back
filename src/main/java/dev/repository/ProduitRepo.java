package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dev.domain.Produit;

public interface ProduitRepo extends JpaRepository<Produit, Integer>, JpaSpecificationExecutor<Produit> {

	public Produit findByNomFigurine(String nomFigurine);
}
