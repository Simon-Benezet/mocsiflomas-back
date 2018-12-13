<<<<<<< HEAD
=======

>>>>>>> d0128ea22535ce1e64e9d3ffe3bfee078b8cb85f
package dev.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Produit;
import dev.repository.ProduitRepo;
import services.Recherche;


@CrossOrigin
@RestController()
@RequestMapping()
public class ProduitController {

	@Autowired
	private ProduitRepo produitRepo;
	@Autowired
	private Recherche rech;
	
	@GetMapping("/produits")
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
	
	// Modifier Les produits 
	@PatchMapping("/{modif-produit}")
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
	
	@GetMapping("/{nomFigurine}")

	public Produit trouverProd(@PathVariable String nomFigurine) {
		Produit coco = this.produitRepo.findByNomFigurine(nomFigurine);
		return coco;
	}
	
	//filtre 
	@GetMapping("/recherche")
	public List<Produit> recherche(@RequestParam String nomSaga, @RequestParam String personnage) {
		
		//Recherche solo la saga
		
		if (!nomSaga.isEmpty() && personnage.isEmpty()) {
		return	produitRepo.findAll(Recherche.triSaga(nomSaga));
			
		}
		
		//recherche solo le personnage
		if (nomSaga.isEmpty() && !personnage.isEmpty()) {
			return produitRepo.findAll(Recherche.triPersonnage(personnage));
		}
		//recherche les deux 
		if (!nomSaga.isEmpty() && !personnage.isEmpty()) {
			List<Produit> l = new ArrayList<Produit>(); 
			l.addAll(produitRepo.findAll(Recherche.triPersonnage(personnage)));
			l.addAll(produitRepo.findAll(Recherche.triSaga(nomSaga)));
			return l;
		}
		else {
			return null;
		}
		
	}
	
	
	
}
