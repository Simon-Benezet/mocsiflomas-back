package dev.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Produit;
import dev.repository.ProduitRepo;
import dev.utils.StringUtils;
import services.Recherche;

@CrossOrigin
@RestController()
@RequestMapping("/gestion-produit")
public class ProduitController {

	@Autowired
	private ProduitRepo produitRepo;

	@Autowired
	private ServletContext servletContext;

	// Récupération de tous les produits
	@GetMapping("/liste-produits")
	public List<Produit> findAll() {
		return this.produitRepo.findAll();
	}

	
	/** Envoi d'un nouveau produit en BDD
	 * @param ajoutProd : un nouveau produit
	 * @return le produit est ajouté dans la base de données
	 */
	@Secured(value = { "ROLE_ADMINISTRATEUR" })
	@PostMapping("/creer")
	public Produit createProduit(@RequestBody Produit ajoutProd) {
		for (Produit p : this.produitRepo.findAll()) {
			if (p.getNomFigurine().equals(ajoutProd.getNomFigurine())) {
				return null;
			}
		}
		this.produitRepo.save(ajoutProd);
		return ajoutProd;
	}

	// Modifier un produit en BDD
	@Secured(value = { "ROLE_ADMINISTRATEUR" })
	@PatchMapping("/modif-produit/{nomFigurine}")
	public Produit modif(@PathVariable String nomFigurine, @RequestBody Produit prod) {
		Produit prodBase = this.produitRepo.findByNomFigurine(nomFigurine);
		prodBase.setNomFigurine(StringUtils.choisirValeurString(prodBase.getNomFigurine(), prod.getNomFigurine()));
		prodBase.setNomSaga(StringUtils.choisirValeurString(prodBase.getNomSaga(), prod.getNomSaga()));
		prodBase.setDescription(StringUtils.choisirValeurString(prodBase.getDescription(), prod.getDescription()));
		prodBase.setNomImage(StringUtils.choisirValeurString(prodBase.getNomImage(), prod.getNomImage()));
		prodBase.setNumeroFigurine(
				StringUtils.choisirValeurInt(prodBase.getNumeroFigurine(), prod.getNumeroFigurine()));
		prodBase.setPersonnage(StringUtils.choisirValeurString(prodBase.getPersonnage(), prod.getPersonnage()));
		prodBase.setTaille(StringUtils.choisirValeurFloat(prodBase.getTaille(), prod.getTaille()));
		this.produitRepo.save(prodBase);
		return prod;
	}

	// Trouver un produit en fonction de son nom de figurine
	@GetMapping("/{nomFigurine}")
	public Produit trouverProd(@PathVariable String nomFigurine) {
		Produit coco = this.produitRepo.findByNomFigurine(nomFigurine);
		return coco;
	}

	@DeleteMapping(path = "/supprimer/{nomFigurine}")
	public void deleteProduit(@PathVariable String nomFigurine) {
		produitRepo.delete(this.produitRepo.findByNomFigurine(nomFigurine));
	}

	// filtre
	@GetMapping("/recherche")
	public List<Produit> recherche(@RequestParam String nomSaga, @RequestParam String personnage) {

		// Recherche solo la saga

		if (!nomSaga.isEmpty() && personnage.isEmpty()) {
			return produitRepo.findAll(Recherche.triSaga(nomSaga));
		}

		// recherche solo le personnage
		if (nomSaga.isEmpty() && !personnage.isEmpty()) {
			return produitRepo.findAll(Recherche.triPersonnage(personnage));
		}
		// recherche les deux
		if (!nomSaga.isEmpty() && !personnage.isEmpty()) {
			List<Produit> l = new ArrayList<Produit>();
			l.addAll(produitRepo.findAll(Recherche.triPersonnage(personnage)));
			l.addAll(produitRepo.findAll(Recherche.triSaga(nomSaga)));
			return l;
		} else {
			return null;
		}
	}
	


	@GetMapping("/upload/{fileName}")
	public ResponseEntity<InputStreamResource> returnImage(@PathVariable(name = "fileName") String fileName)
			throws IOException {
		MediaType mediaType = getMediaTypeForFileName(this.servletContext, fileName);
		System.out.println("fileName: " + fileName);
		System.out.println("mediaType: " + mediaType);

		File file = new File("C:/Temp/images/" + fileName);
		InputStreamResource ressource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentType(mediaType).contentLength(file.length()).body(ressource);
	}

	@PostMapping("/upload/{fileName}")
	public ResponseEntity<?> uploadImage(@PathVariable(name = "fileName") String fileName, @RequestBody byte[] val) {
		try {
			Path path = Paths.get("C:/Temp/images/" + fileName);
			Files.write(path, val);

			return ResponseEntity.status(HttpStatus.OK)
					.body("http://localhost:8080/gestion-produit/upload/" + fileName);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("coucou");
		}
	}

	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
		// application/pdf
		// application/xml
		// image/gif, ...
		String mineType = servletContext.getMimeType(fileName);
		try {
			MediaType mediaType = MediaType.parseMediaType(mineType);
			return mediaType;
		} catch (Exception e) {
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
	
	
}
