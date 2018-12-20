package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nomSaga;
	private String nomImage;
	private String personnage;
	private String nomFigurine;
	private float taille;
	private float prix;
	private String description;
	private int numeroFigurine;
	
////	@OneToMany
//////    @JoinColumn(name = "commande_id")
////    private Commande com_com;
//	
//	
//	
//	
//	public Commande getCom_com() {
//		return com_com;
//	}
//
//	public void setCom_com(Commande com_com) {
//		this.com_com = com_com;
//	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
