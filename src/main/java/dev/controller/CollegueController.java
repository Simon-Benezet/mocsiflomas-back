package dev.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.repository.CollegueRepo;


@RestController
@RequestMapping("/collegue")
public class CollegueController {
	
	private CollegueRepo collegueRepo;

	@PostMapping("/upload")
	public ResponseEntity<?> uplodeImage(@RequestBody byte[] val) {
		try {
			Path path = Paths.get("C:/Users/formation/git/top-back/src/main/resources/Temp/lkj.jpg");
			Files.write(path, val);
			return ResponseEntity.status(HttpStatus.OK).body("C:/Users/formation/git/top-back/src/main/resources/Temp/lkj.jpg");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad matricule");
		}
	}

}
