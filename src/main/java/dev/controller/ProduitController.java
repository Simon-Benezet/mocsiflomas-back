package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Produit;
import dev.repository.ProduitRepo;


@CrossOrigin
@RestController()
@RequestMapping()
public class ProduitController {

	@Autowired
	private ProduitRepo produitRepo;
	
	@GetMapping
	public List<Produit> findAll() {
		return this.produitRepo.findAll();
	}
	
	@Secured(value = { "ROLE_ADMINISTRATEUR" })
	@PostMapping
	public Produit createProduit(@RequestBody Produit ajoutProd) {
		Produit ajPro = new Produit();
		ajPro.setNomSaga(ajoutProd.getNomSaga());
		ajPro.setNomImage(ajoutProd.getNomImage());
		ajPro.setPersonnage(ajoutProd.getPersonnage());
		ajPro.setNomFigurine(ajoutProd.getNomFigurine());
		ajPro.setTaille(ajoutProd.getTaille());
		ajPro.setDescription(ajoutProd.getDescription());
		ajPro.setNumeroFigurine(ajoutProd.getNumeroFigurine());
		this.produitRepo.save(ajPro);
		return ajPro;
	}
	
	/*
	@Secured(value = { "ROLE_ADMINISTRATEUR" })
	@PatchMapping
	public Produit patchProduit(@PathVariable String nomFigurine) {
		Produit prod = this.produitRepo.findByNomFigurine(nomFigurine);
		
		
	}
	*/
	
	// Modifier Les produits 
	@PatchMapping("/{produit}")
    public Produit modif(@PathVariable String nomFigurine, @RequestBody Produit prod) {

		Produit produit = this.produitRepo.findByNomFigurine(nomFigurine);

		produit.setNomFigurine(prod.getNomFigurine());
		produit.setNomImage(prod.getNomImage());
		produit.setNomSaga(prod.getNomSaga());
		produit.setNumeroFigurine(prod.getNumeroFigurine());
		produit.setPersonnage(prod.getPersonnage());
		produit.setPrix(prod.getPrix());
		produit.setTaille(prod.getTaille());
		produit.setDescription(prod.getDescription());
		
		this.produitRepo.save(produit);    	
		return produit;
	}
	
}
