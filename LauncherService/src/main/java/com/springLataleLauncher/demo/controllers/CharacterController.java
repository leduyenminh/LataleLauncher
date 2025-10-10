package com.springLataleLauncher.demo.controllers;

import com.springLataleLauncher.demo.DAO.CharacterDAO;
import com.springLataleLauncher.demo.entity.Characters;
import com.springLataleLauncher.demo.interfaces.CharacterRequest;
import com.springLataleLauncher.demo.interfaces.CharacterResponse;
import com.springLataleLauncher.demo.DTO.CachingService;
import com.springLataleLauncher.demo.DTO.CharacterService;
import com.springLataleLauncher.demo.vo.CharacterVO;
import com.springLataleLauncher.demo.vo.Classes;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterController {
	@Autowired
	private CharacterDAO characterDAO;

	@Autowired
	private CachingService cachingService;

	@Autowired
	private CharacterService characterService;

	private KafkaTemplate<String, Long> kafkaTemplate;

	// QUERY CREATION
	@GetMapping
	public ResponseEntity<List<CharacterResponse>> getAllCharacters() {
		List<CharacterResponse> characterResponses = characterService.getAllCharacters();
		return ResponseEntity.ok().body(characterResponses);
	}

	// QUERY CREATION: CREATE NEW Character
	@PostMapping
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<CharacterResponse> addCharacter(@Valid @RequestBody CharacterRequest characterRequest) {
		CharacterResponse characterResponse = characterService.createCharacter(characterRequest);
		return ResponseEntity.ok().body(characterResponse);
	}

	// QUERY CREATION: FIND BY DESC
	@GetMapping("/class/{charclass}")
	public ResponseEntity<List<CharacterResponse>> findCharacterByClass(@PathVariable String charClass) {
		List<CharacterResponse> characterResponse = characterService.getAllCharactersByClass(charClass);
		return ResponseEntity.ok().body(characterResponse);
	}

	@DeleteMapping("/{id}")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Boolean> deleteCharacterById(@PathVariable Long id) {
		characterService.deleteCharacter(id);
		return ResponseEntity.ok(true);
	}

	@PutMapping("/{id}/{bio}")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> updateCharacterStatus(@PathVariable Long id, @PathVariable String bio) {
		return ResponseEntity.ok(characterDAO.updateCharacter(id, bio));
	}


	// PAGING & SORTING EX 1
	// @GetMapping("/all")
	// public Page<Characters> getAllCharacters(Pageable pageable) {
	// 	return characterService.getAllCharacters(pageable);
	// }

	@GetMapping("/all-page-one")
	public Page<Characters> getAllCharactersOne(@RequestParam Integer page, @RequestParam Integer size) {
		// Create a Pageable object with page number, page size, and sorting
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "todoItem"));
		return characterDAO.findAllWithPagingAndSorting(pageable);
	}

	@GetMapping("/all-page-two")
	public List<Characters> getAllCharactersTwo(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortBy, @RequestParam Sort.Direction direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
		Page<Characters> todoPage = characterDAO.findAllWithPagingAndSorting(pageable);
		return todoPage.getContent();
	}

	// NATIVE QUERY & PROJECTION
	// 1. NATIVE QUERY & PROJECTION
	@GetMapping("/native-query-one")
	public ResponseEntity<List<Characters>> getAllCharacterNativeOne() {
		List<Characters> todos = characterDAO.getAllCharactersNativeQuery();
		return ResponseEntity.ok(todos);
	}

	@GetMapping("/native-query-two")
	public ResponseEntity<List<Characters>> getAllCharacterNativeTwo(@RequestParam String classes) {
		List<Characters> todos = characterDAO.findCharacterByClass(classes);
		return ResponseEntity.ok(todos);
	}

	@PostMapping("/native-query-three")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> insertCharacter(@RequestBody Characters characterRequest) {
		try {
			characterDAO.createNewCharacter(characterRequest);
			return ResponseEntity.ok("Characters item created successfully.");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
		}
	}

	// BULK CREATE
	@PostMapping("/bulk-create")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> bulkCreateCharacterItems(@RequestBody List<CharacterRequest> todoRequests) {
		characterDAO.bulkCreateCharacterItems(todoRequests);
		return ResponseEntity.ok("Characters items created successfully.");
	}

	// CACHING
	@GetMapping("/caching")
	public ResponseEntity<List<Characters>> getAllCharacterCaching() {
		List<Characters> todos = characterDAO.getAllCharacters();
		return ResponseEntity.ok(todos);
	}

	@DeleteMapping("/caching/{id}")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> deleteCharacterCaching(@PathVariable Long id) {
		characterDAO.deleteById(id);
		return ResponseEntity.ok("Delete character successfully.");
	}

	@PostMapping("/caching")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> deleteCharacterCaching(@RequestBody Characters character) {
		Characters result = characterDAO.updateCharacter(character.getCharacterId(), character.getBio());
		return ResponseEntity.ok(result);
	}

	@GetMapping("/clearAllCaches")
//	@RolesAllowed(Role.USER_ADMIN)
	public void clearAllCaches() {
		cachingService.evictAllCaches();
	}
}
