package dev.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Commande {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private LocalDate dateCommande;
    
	private Integer numero;
	
	@OneToMany(mappedBy="commande", cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "commande_id")
	List<Achat> achat;
	
	@ManyToOne
	Collegue comClient;
	
//    @ManyToMany(mappedBy = "com_com")
//    		//, cascade = CascadeType.PERSIST)
//    @ElementCollection(targetClass=Produit.class)
//    private Map<Produit, Integer> comProduit = new HashMap<Produit, Integer>();
    		
    public Commande() { }
    

	/**
	 * Constructeur
	 * @param dateCommande 
	 * @param numeroCommande
	 * @param comClient
	 * @param comProduit
	 */
	public Commande(LocalDate dateCommande, int numero, Collegue comClient) {
		super();
		this.dateCommande = dateCommande;
		this.numero = numero;
		this.comClient = comClient;
	}


    
    //VOICI LES GETTERS SETTERS

	
	public List<Achat> getAchat() {
		return achat;
	}


	public void setAchat(List<Achat> achat) {
		this.achat = achat;
	}

	
	
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
		return numero;
	}

	public void setNumeroCommande(int numero) {
		this.numero = numero;
	}

	public Collegue getComClient() {
		return comClient;
	}

	public void setComClient(Collegue comClient) {
		this.comClient = comClient;
	}


}
