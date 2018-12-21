package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dev.domain.Produit;

public interface ProduitRepo extends JpaRepository<Produit, Integer>, JpaSpecificationExecutor<Produit> {
	public Produit findByNomFigurine(String nomFigurine);
	public List<Produit> findByNomSaga(String nomSaga);
}
