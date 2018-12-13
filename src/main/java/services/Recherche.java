package services;

import org.springframework.data.jpa.domain.Specification;

import dev.domain.Produit;

public interface Recherche {

	public static Specification<Produit> triSaga(String nomSaga) {
	    return (Produit, cq, cb) -> cb.equal(Produit.get("nomSaga"), nomSaga);
	}
	 
	public static Specification<Produit> triPersonnage(String personnage) {
	    return (Produit, cq, cb) -> cb.like(Produit.get("personnage"), "%" + personnage + "%");
	}

}
