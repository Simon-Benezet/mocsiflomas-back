package dev;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Achat;
import dev.domain.Collegue;
import dev.domain.Commande;
import dev.domain.Produit;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Version;
import dev.repository.AchatRepo;
import dev.repository.CollegueRepo;
import dev.repository.CommandeRepo;
import dev.repository.ProduitRepo;
import dev.repository.VersionRepo;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

	private String appVersion;
	private VersionRepo versionRepo;
	private PasswordEncoder passwordEncoder;
	private CollegueRepo collegueRepo;
	private ProduitRepo produitRepo;
	private CommandeRepo commandeRepo;
	private AchatRepo achatRepo;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, ProduitRepo produitRepo, CommandeRepo commandeRepo,AchatRepo achatRepo) {
		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;
		this.produitRepo = produitRepo;
		this.commandeRepo = commandeRepo;
		this.achatRepo= achatRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		this.versionRepo.save(new Version(appVersion));

		// Création de deux utilisateurs
		if (this.collegueRepo.findAll().isEmpty()) {
			Collegue col1 = new Collegue();
			col1.setNom("Admin");
			col1.setPrenom("DEV");
			col1.setEmail("admin@dev.fr");
			col1.setMotDePasse(passwordEncoder.encode("superpass"));
			col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR),
					new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
			this.collegueRepo.save(col1);

			Collegue col2 = new Collegue();
			col2.setNom("User");
			col2.setPrenom("DEV");
			col2.setEmail("user@dev.fr");
			col2.setMotDePasse(passwordEncoder.encode("superpass"));
			col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
			this.collegueRepo.save(col2);
		}

		if (this.produitRepo.findAll().isEmpty()) {
			Produit prod1 = new Produit();
			prod1.setNomSaga("Harry Potter");
			prod1.setNomImage("http://localhost:8080/gestion-produit/upload/HarryPop.jpg");
			prod1.setPersonnage("Harry Potter");
			prod1.setNomFigurine("HarryPop");
			prod1.setTaille(20);
			prod1.setPrix(5);
			prod1.setDescription("aaaaaaaaaaaaaaaaaaaaaaaa");
			prod1.setNumeroFigurine(1);
			this.produitRepo.save(prod1);

			Produit prod2 = new Produit();
			prod2.setNomSaga("Harry Potter");
			prod2.setNomImage("http://localhost:8080/gestion-produit/upload/RonPop.jpg");
			prod2.setPersonnage("Ron Wisley");
			prod2.setNomFigurine("RonPop");
			prod2.setTaille(20);
			prod2.setPrix(5);
			prod2.setDescription("bbbbbbbbbbbbbbbbbbbbbbbb");
			prod2.setNumeroFigurine(2);
			this.produitRepo.save(prod2);

		
		}
	    
			Commande com1 = new Commande();
	        com1.setNumeroCommande(1);
	        com1.setDateCommande(LocalDate.parse("2001-01-01"));
	        com1.setComClient(this.collegueRepo.findByNom("User"));
	        this.commandeRepo.save(com1);
	        
	        Achat ach1=new Achat(this.produitRepo.findByNomFigurine("RonPop"), 2,com1);
	        Achat ach2=new Achat(this.produitRepo.findByNomFigurine("HarryPop"), 1, com1);
	        this.achatRepo.save(ach1);
	        this.achatRepo.save(ach2);
//			
		
		
		
		}

        
        
    
	

}
