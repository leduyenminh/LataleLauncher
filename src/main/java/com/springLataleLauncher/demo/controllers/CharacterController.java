package com.springLataleLauncher.demo.controllers;

import com.springLataleLauncher.demo.entity.Characters;
import com.springLataleLauncher.demo.interfaces.CharacterRequest;
import com.springLataleLauncher.demo.service.CachingService;
import com.springLataleLauncher.demo.DAO.CharacterServiceDAO;
import com.springLataleLauncher.demo.service.CharacterService;
import com.springLataleLauncher.demo.vo.CharacterVO;
import com.springLataleLauncher.demo.vo.Classes;

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
	private CharacterServiceDAO characterServiceDAO;

	@Autowired
	private CachingService cachingService;

	@Autowired
	private CharacterService characterService;

	private KafkaTemplate<String, Long> kafkaTemplate;

	// QUERY CREATION
	@GetMapping
	public ResponseEntity<List<Characters>> getAllCharacters() {
		return ResponseEntity.ok(characterServiceDAO.getAllCharacters());
	}

	// QUERY CREATION: CREATE NEW Character
	@PostMapping
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> addCharacter(@RequestParam Classes classes, @RequestParam String bio, @RequestBody String characterName) {
		Characters character = new Characters();
		character.setCharacterClass(classes);
		character.setBio(bio);
		character.setCharacterName(characterName);
		kafkaTemplate.send("characterTopic", character.getCharacterId());
		return ResponseEntity.ok(characterServiceDAO.createNewCharacter(character));
	}

	// QUERY CREATION: FIND BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Characters> getCharacterById(@PathVariable long id) {
		return ResponseEntity.ok(characterServiceDAO.findCharacterById(id));
	}

	// QUERY CREATION: FIND BY DESC
	@GetMapping("/desc/{desc}")
	public ResponseEntity<List<Characters>> findTaskByDescription(@PathVariable String desc) {
		return ResponseEntity.ok(characterServiceDAO.findCharacterByClass(desc));
	}

	@DeleteMapping("/{id}")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Boolean> deleteCharacterById(@PathVariable long id) {
		characterServiceDAO.deleteById(id);
		return ResponseEntity.ok(true);
	}

	@PutMapping("/{id}/{bio}")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> updateCharacterStatus(@PathVariable long id, @PathVariable String bio) {
		return ResponseEntity.ok(characterServiceDAO.updateCharacter(id, bio));
	}

	// TRANSACTIONS EX 1
	@GetMapping("/demo/transaction")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Boolean> performMultipleOperations() {
		characterServiceDAO.performMultipleOperations();
		return ResponseEntity.ok(true);
	}

	// TRANSACTIONS EX 2
	@PostMapping("/demo/transaction-rollback")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Boolean> performOperationsRollback(@RequestParam String bio, @RequestParam String status) {
		Characters character = new Characters();
		character.setBio(bio);
		characterServiceDAO.createTaskRollback(character);
		return ResponseEntity.ok(true);
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
		return characterServiceDAO.findAllWithPagingAndSorting(pageable);
	}

	@GetMapping("/all-page-two")
	public List<Characters> getAllCharactersTwo(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortBy, @RequestParam Sort.Direction direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
		Page<Characters> todoPage = characterServiceDAO.findAllWithPagingAndSorting(pageable);
		return todoPage.getContent();
	}

	// NATIVE QUERY & PROJECTION
	// 1. NATIVE QUERY & PROJECTION
	@GetMapping("/native-query-one")
	public ResponseEntity<List<Characters>> getAllCharacterNativeOne() {
		List<Characters> todos = characterServiceDAO.getAllCharactersNativeQuery();
		return ResponseEntity.ok(todos);
	}

	@GetMapping("/native-query-two")
	public ResponseEntity<List<Characters>> getAllCharacterNativeTwo(@RequestParam String classes) {
		List<Characters> todos = characterServiceDAO.findCharacterByClass(classes);
		return ResponseEntity.ok(todos);
	}

	@PostMapping("/native-query-three")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> insertCharacter(@RequestBody Characters characterRequest) {
		try {
			characterServiceDAO.createNewCharacter(characterRequest);
			return ResponseEntity.ok("Characters item created successfully.");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
		}
	}

	// 2. PROJECTION
	@GetMapping("/projection")
	public ResponseEntity<List<CharacterVO>> getAllCharacterItemsProjection() {
		List<CharacterVO> character = characterService.getAllCharactersByClass("warrior");
		return ResponseEntity.ok(character);
	}

	// BULK CREATE
	@PostMapping("/bulk-create")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> bulkCreateCharacterItems(@RequestBody List<CharacterRequest> todoRequests) {
		characterServiceDAO.bulkCreateCharacterItems(todoRequests);
		return ResponseEntity.ok("Characters items created successfully.");
	}

	// CACHING
	@GetMapping("/caching")
	public ResponseEntity<List<Characters>> getAllCharacterCaching() {
		List<Characters> todos = characterServiceDAO.getAllCharacters();
		return ResponseEntity.ok(todos);
	}

	@DeleteMapping("/caching/{id}")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> deleteCharacterCaching(@PathVariable long id) {
		characterServiceDAO.deleteById(id);
		return ResponseEntity.ok("Delete task successfully.");
	}

	@PostMapping("/caching")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> deleteCharacterCaching(@RequestBody Characters character) {
		Characters result = characterServiceDAO.updateCharacter(character.getCharacterId(), character.getBio());
		return ResponseEntity.ok(result);
	}

	@GetMapping("/clearAllCaches")
//	@RolesAllowed(Role.USER_ADMIN)
	public void clearAllCaches() {
		cachingService.evictAllCaches();
	}
}
