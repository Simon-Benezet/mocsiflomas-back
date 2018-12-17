package dev.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Collegue;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.exception.ClientErrorInformation;
import dev.exception.FunctionalException;
import dev.repository.CollegueRepo;

@RestController
@RequestMapping("/collegue")
public class CollegueController extends AbstractController{

	@Autowired
	private CollegueRepo collegueRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ServletContext servletContext;

	@GetMapping("/upload/{fileName}")
	public ResponseEntity<InputStreamResource> returnImage(@PathVariable(name = "fileName") String fileName)
			throws IOException {
		MediaType mediaType = getMediaTypeForFileName(this.servletContext, fileName);
		System.out.println("fileName: " + fileName);
		System.out.println("mediaType: " + mediaType);

		File file = new File("C:/Temp/images/" + fileName);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
				// Content-Disposition
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				// Content-Type
				.contentType(mediaType)
				// Contet-Length
				.contentLength(file.length()) //
				.body(resource);
	}

	@PostMapping("/upload/{fileName}")
	public ResponseEntity<?> uplodeImage(@PathVariable(name = "fileName") String fileName, @RequestBody byte[] val) {
		try {
			Path path = Paths.get("C:/Temp/images/" + fileName);
			Files.write(path, val);

			return ResponseEntity.status(HttpStatus.OK).body("http://localhost:8080/collegue/upload/" + fileName);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad matricule");
		}
	}
	
	//crée nouveau client
	@PostMapping("/nouveau")
	public void create(@RequestBody Map<String, String> form) throws FunctionalException{
		if (!collegueRepo.findByEmail(form.get("email")).isPresent()) {
			Collegue collegue = new Collegue();
			collegue.setNom(form.get("nom"));
			collegue.setPrenom(form.get("prenom"));
			collegue.setEmail(form.get("email"));
			collegue.setAdresse(form.get("adresse"));
			collegue.setTelephone(form.get("phone"));
			collegue.setMotDePasse(passwordEncoder.encode(form.get("password")));
			if(form.get("date")!=null) {
				collegue.setDateDeNaissance(LocalDate.parse(form.get("date")));
			}
			if (form.get("imgProfil") != null) {
				collegue.setImgUrl(form.get("imgProfil"));
			} else {
				collegue.setImgUrl("http://myprofilart.com/bundles/myprofilart/img/profil_vide.png");
			}
			collegue.setRoles(Arrays.asList(new RoleCollegue(collegue, Role.ROLE_UTILISATEUR)));
			collegueRepo.save(collegue);
			if (collegueRepo.findByEmail(collegue.getEmail()) != null) {
				
			} else {
				throw new FunctionalException("Echec enregistrement");
			}
		} else {
			throw new FunctionalException("Email deja existant");
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
