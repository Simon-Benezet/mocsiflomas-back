package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produit {
	@Id
	@GeneratedValue
	Integer id;
	String nomSaga;
	String nomImage;
	String personnage;
	String nomFigurine;
	float taille;
	float prix;
	String description;
	int numeroFigurine;
	
	@ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande com_com;
	
	public Produit() {
		
	}
	
	public Produit(String nomSaga, String nomImage, String personnage, String nomFigurine, float taille, float prix,
			String description, int numeroFigurine) {
		super();
		this.nomSaga = nomSaga;
		this.nomImage = nomImage;
		this.personnage = personnage;
		this.nomFigurine = nomFigurine;
		this.taille = taille;
		this.prix = prix;
		this.description = description;
		this.numeroFigurine = numeroFigurine;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
