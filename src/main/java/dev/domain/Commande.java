package dev.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Commande {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private LocalDate dateCommande;
    
	private int numeroCommande;
	
	@ManyToOne
	Collegue comClient;
	
    @OneToMany(mappedBy = "", cascade = CascadeType.PERSIST)
    private List<Produit> comProduit;

    
    public Commande() { }
    

	public Commande(LocalDate dateCommande, int numeroCommande, Collegue comClient, List<Produit> comProduit) {
		super();
		this.dateCommande = dateCommande;
		this.numeroCommande = numeroCommande;
		this.comClient = comClient;
		this.comProduit = comProduit;
	}


    
    //VOICI LES GETTERS SETTERS



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(LocalDate dateCommande) {
		this.dateCommande = dateCommande;
	}

	public int getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(int numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public Collegue getComClient() {
		return comClient;
	}

	public void setComClient(Collegue comClient) {
		this.comClient = comClient;
	}

	public List<Produit> getComProduit() {
		return comProduit;
	}

	public void setComProduit(List<Produit> comProduit) {
		this.comProduit = comProduit;
	}
	
	
	
	
}
