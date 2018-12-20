package dev.controller.vm;

import dev.domain.Achat;

public class AchatVM {
	
	private ProduitVM achatProduits;
	private Integer quantité;
	
	public AchatVM(Achat achat) {
		super();
		this.achatProduits = new ProduitVM(achat.getAchatProduits());
		this.quantité = achat.getQuantité();
	}

	public ProduitVM getAchatProduits() {
		return achatProduits;
	}

	public void setAchatProduits(ProduitVM achatProduits) {
		this.achatProduits = achatProduits;
	}

	public Integer getQuantité() {
		return quantité;
	}

	public void setQuantité(Integer quantité) {
		this.quantité = quantité;
	}
	
	
}
