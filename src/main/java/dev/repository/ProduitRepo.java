package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Produit;

public interface ProduitRepo extends JpaRepository<Produit, Integer> {

	public Produit findByNomFigurine(String nomFigurine);

}
