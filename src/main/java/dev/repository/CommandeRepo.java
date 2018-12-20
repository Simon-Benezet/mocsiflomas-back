package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dev.domain.Commande;

public interface CommandeRepo extends JpaRepository<Commande, Integer>, JpaSpecificationExecutor<Commande> {
	public Commande findByNumero(Integer numero);
}
