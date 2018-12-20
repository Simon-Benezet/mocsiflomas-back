package dev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CommandeVM;
import dev.domain.Commande;
import dev.repository.CommandeRepo;

@CrossOrigin
@RestController()
@RequestMapping("/commandes")
public class CommandeController {

	@Autowired CommandeRepo comRepo;
	
	/**	récupération de toutes les commandes
	 * @return la liste des commandes
	 */
	@GetMapping("/liste")
	public List<CommandeVM> allCommande() {
		List<Commande> result = this.comRepo.findAll();
		List<CommandeVM> commandeVM=new ArrayList<>();
		for (Commande commande : result) {
			 commandeVM.add(new CommandeVM(commande));
		}
		
		return commandeVM;
		
	}
	
	
	
	
	/** Méthode qui ajoute une commande en bdd
	 * @param newCom nouvelle commande
	 * @return Commande
	 */
	@PostMapping
	public Commande newCommande(@RequestBody Commande newCom) {
		return this.comRepo.save(newCom);
	}
	
	
}
