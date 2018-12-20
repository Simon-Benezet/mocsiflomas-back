package dev.controller.vm;

import dev.domain.Produit;

public class ProduitVM {
	
	private String nomSaga;
	private String nomImage;
	private String personnage;
	private String nomFigurine;
	private float taille;
	private float prix;
	private String description;
	private int numeroFigurine;
	
	public ProduitVM(Produit prod) {
		super();
		this.nomSaga = prod.getNomSaga();
		this.nomImage = prod.getNomImage();
		this.personnage = prod.getPersonnage();
		this.nomFigurine = prod.getNomFigurine();
		this.taille = prod.getTaille();
		this.prix = prod.getPrix();
		this.description = prod.getDescription();
		this.numeroFigurine = prod.getNumeroFigurine();
	}

	public String getNomSaga() {
		return nomSaga;
	}

	public void setNomSaga(String nomSaga) {
		this.nomSaga = nomSaga;
	}

	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public String getPersonnage() {
		return personnage;
	}

	public void setPersonnage(String personnage) {
		this.personnage = personnage;
	}

	public String getNomFigurine() {
		return nomFigurine;
	}

	public void setNomFigurine(String nomFigurine) {
		this.nomFigurine = nomFigurine;
	}

	public float getTaille() {
		return taille;
	}

	public void setTaille(float taille) {
		this.taille = taille;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumeroFigurine() {
		return numeroFigurine;
	}

	public void setNumeroFigurine(int numeroFigurine) {
		this.numeroFigurine = numeroFigurine;
	}
	
	

}
