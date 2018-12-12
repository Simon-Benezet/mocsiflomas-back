package dev.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Collegue;
import dev.repository.CollegueRepo;

@RestController
@RequestMapping("/collegue")
public class CollegueController {

	@Autowired
	private CollegueRepo collegueRepo;

	@Autowired
	private ServletContext servletContext;

	@GetMapping("/upload/{fileName}")
	public ResponseEntity<InputStreamResource> returnImage(@PathVariable(name = "fileName") String fileName)throws IOException {
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
	public ResponseEntity<?> uplodeImage(@PathVariable(name = "fileName") String fileName,
			@RequestBody byte[] val) {
		try {	
			Path path = Paths.get("C:/Temp/images/"+fileName);
			Files.write(path, val);

			return ResponseEntity.status(HttpStatus.OK).body("ok");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad matricule");
		}
	}

	@PostMapping("/nouveau")
	public Collegue create(@RequestBody Map<String, String> form) {
		Collegue collegue = new Collegue();
		collegue.setNom(form.get("nom"));
		collegue.setPrenom(form.get("prenom"));
		collegue.setEmail(form.get("email"));
		collegue.setAdresse(form.get("adresse"));
		collegue.setDateDeNaissance(LocalDate.parse(form.get("date")));
		if (form.get("imgUrl") != null) {
			String[] listImg = { form.get("imgUrl") };
			collegue.setImgUrl(form.get("imgUrl"));
		} else {
			collegue.setImgUrl("http://myprofilart.com/bundles/myprofilart/img/profil_vide.png");
		}
		collegueRepo.save(collegue);
		return collegue;
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
