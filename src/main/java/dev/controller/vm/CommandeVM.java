package dev.controller.vm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.domain.Achat;
import dev.domain.Collegue;
import dev.domain.Commande;

public class CommandeVM {
private LocalDate dateCommande;
    
	private Integer numero;
	private CollegueVM comClient;
	private List<AchatVM> achat = new ArrayList<>();
	
	public CommandeVM(Commande com) {
		super();
		this.dateCommande = com.getDateCommande();
		this.numero = com.getNumeroCommande();
		this.comClient = new CollegueVM(com.getComClient());
		
		for (Achat achatVM : com.getAchat()) {
			this.achat.add(new AchatVM(achatVM));
		}
	}

	public LocalDate getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(LocalDate dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public CollegueVM getComClient() {
		return comClient;
	}

	public void setComClient(CollegueVM comClient) {
		this.comClient = comClient;
	}

	public List<AchatVM> getAchat() {
		return achat;
	}

	public void setAchat(List<AchatVM> achat) {
		this.achat = achat;
	}
	
	
	
}
