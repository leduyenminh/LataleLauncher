package com.springLataleLauncher.demo.controllers;

import com.springLataleLauncher.demo.DAO.CharacterDAO;
import com.springLataleLauncher.demo.entity.Characters;
import com.springLataleLauncher.demo.interfaces.CharacterRequest;
import com.springLataleLauncher.demo.interfaces.CharacterResponse;
import com.springLataleLauncher.demo.DTO.CachingService;
import com.springLataleLauncher.demo.DTO.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@Tag(name = "Characters", description = "API for Characters by each user")
public class CharacterController {
	@Autowired
	private CharacterDAO characterDAO;

	@Autowired
	private CachingService cachingService;

	@Autowired
	private CharacterService characterService;

//	private KafkaTemplate<String, Long> kafkaTemplate;

	// QUERY CREATION
	@GetMapping
	@Operation(summary = "Get Characters")
	public ResponseEntity<List<CharacterResponse>> getAllCharacters() {
		List<CharacterResponse> characterResponses = characterService.getAllCharacters();
		return ResponseEntity.ok().body(characterResponses);
	}

	// QUERY CREATION: CREATE NEW Character
	@PostMapping
	@Operation(summary = "Create Character")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<CharacterResponse> addCharacter(@Valid @RequestBody CharacterRequest characterRequest) {
		CharacterResponse characterResponse = characterService.createCharacter(characterRequest);
		return ResponseEntity.ok().body(characterResponse);
	}

	// QUERY CREATION: FIND BY DESC
	@GetMapping("/class/{charclass}")
	@Operation(summary = "Get Characters By Class")
	public ResponseEntity<List<CharacterResponse>> findCharacterByClass(@PathVariable String charClass) {
		List<CharacterResponse> characterResponse = characterService.getAllCharactersByClass(charClass);
		return ResponseEntity.ok().body(characterResponse);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Get Character By Id")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Boolean> deleteCharacterById(@PathVariable Long id) {
		characterService.deleteCharacter(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}/{bio}")
	@Operation(summary = "Update Character Bio")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> updateCharacterStatus(@PathVariable Long id, @PathVariable String bio) {
		return ResponseEntity.ok(characterDAO.updateCharacter(id, bio));
	}


	// PAGING & SORTING EX 1
	// @GetMapping("/all")
	// public Page<Characters> getAllCharacters(Pageable pageable) {
	// 	return characterService.getAllCharacters(pageable);
	// }

	@GetMapping("/paging")
	@Operation(summary = "Get Characters with Pagination")
	public Page<Characters> getAllCharactersOne(@RequestParam Integer page, @RequestParam Integer size) {
		// Create a Pageable object with page number, page size, and sorting
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,""));
		return characterDAO.findAllWithPagingAndSorting(pageable);
	}

	@GetMapping("/paging-two")
	@Operation(summary = "Get Characters with Pagination")
	public List<Characters> getAllCharactersTwo(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortBy, @RequestParam Sort.Direction direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
		Page<Characters> todoPage = characterDAO.findAllWithPagingAndSorting(pageable);
		return todoPage.getContent();
	}

	// NATIVE QUERY & PROJECTION
	// 1. NATIVE QUERY & PROJECTION
	@GetMapping("/native-query-one")
	@Operation(summary = "Get Characters with query")
	public ResponseEntity<List<Characters>> getAllCharacterNativeOne() {
		List<Characters> todos = characterDAO.getAllCharactersNativeQuery();
		return ResponseEntity.ok(todos);
	}

	// BULK CREATE
	@PostMapping("/bulk-create")
	@Operation(summary = "Get Characters")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> bulkCreateCharacterItems(@RequestBody List<CharacterRequest> todoRequests) {
		characterDAO.bulkCreateCharacterItems(todoRequests);
		return ResponseEntity.ok("Characters items created successfully.");
	}

	@DeleteMapping("/caching/{id}")
	@Operation(summary = "Delete Character")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> deleteCharacterCaching(@PathVariable Long id) {
		characterDAO.deleteById(id);
		return ResponseEntity.ok("Delete character successfully.");
	}

	@PostMapping("/caching")
	@Operation(summary = "Update character")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> updateCharacterCaching(@RequestBody Characters character) {
		Characters result = characterDAO.updateCharacter(character.getCharacterId(), character.getBio());
		return ResponseEntity.ok(result);
	}

	@GetMapping("/clearAllCaches")
	@Operation(summary = "Clear all cache")
//	@RolesAllowed(Role.USER_ADMIN)
	public void clearAllCaches() {
		cachingService.evictAllCaches();
	}
}
