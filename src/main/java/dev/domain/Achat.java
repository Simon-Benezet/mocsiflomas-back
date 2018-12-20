package dev.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @ManyToOne
    private Produit achatProduits;
    private Integer quantité;
    @ManyToOne
    private Commande commande;
    
public Achat() {};
	
	public Achat(Produit achatProduits, Integer quantité, Commande commande) {
		super();
		this.achatProduits = achatProduits;
		this.quantité = quantité;
		this.commande = commande;
	}


	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Produit getAchatProduits() {
		return achatProduits;
	}
	public void setAchatProduits(Produit achatProduits) {
		this.achatProduits = achatProduits;
	}
	public Integer getQuantité() {
		return quantité;
	}
	public void setQuantité(Integer quantité) {
		this.quantité = quantité;
	}
	
}
