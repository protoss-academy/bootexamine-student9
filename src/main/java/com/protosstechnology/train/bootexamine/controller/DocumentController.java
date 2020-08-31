package com.protosstechnology.train.bootexamine.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.protosstechnology.train.bootexamine.datamodel.Document;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Controller
@RequestMapping("/document")
public class DocumentController {

	static final Logger logger = LoggerFactory.getLogger(DocumentController.class);
	private List<Document> documentList = new ArrayList<>();

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found Document",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Document.class))),
			@ApiResponse(responseCode = "404", description = "Not found Document",
					content = @Content)
	})

	@GetMapping("/{id}")
	public ResponseEntity<?> getDoc(
			@PathVariable
					Long id) {
		Document temp = new Document();
		for (int i = 0; i < documentList.size(); i++) {
			if (documentList.get(i).getId().equals(id)) {
				temp = documentList.get(i);
				return ResponseEntity.ok().body(temp);

			}
		}
		return ResponseEntity.notFound().build();

	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created Document",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Document.class)))
	})
	@PostMapping()
	public ResponseEntity<?> postDocument(
			@RequestBody
					Document body) {
		documentList.add(body);
		Document tmp = documentList.get(documentList.size() - 1);
		return ResponseEntity.ok().body(tmp);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Edited Document",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Document.class))),
			@ApiResponse(responseCode = "404", description = "Not found Document",
					content = @Content)
	})
	@PutMapping("/{id}")
	public ResponseEntity<?> putDoc(
			@PathVariable
					Long id,
			@RequestBody
					Document body) {
		Document temp = new Document();
		for (int i = 0; i < documentList.size(); i++) {
			if (documentList.get(i).getId().equals(id)) {
				temp = documentList.get(i);
				documentList.get(i).setId(body.getId());
				documentList.get(i).setDescription(body.getDescription());
				documentList.get(i).setDocumentNumber(body.getDocumentNumber());
				return ResponseEntity.ok().body(temp);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Delete Document",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = Document.class))),
			@ApiResponse(responseCode = "404", description = "Not found Document",
					content = @Content)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(
			@PathVariable
					Long id) {
		Document temp = new Document();
		for (int i = 0; i < documentList.size(); i++) {
			if (documentList.get(i).getId().equals(id)) {
				documentList.remove(i);
				return ResponseEntity.ok().build();

			}
		}
		return ResponseEntity.notFound().build();

	}

}