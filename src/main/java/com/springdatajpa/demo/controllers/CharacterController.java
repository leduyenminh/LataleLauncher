package com.springdatajpa.demo.controllers;

import com.springdatajpa.demo.entity.Characters;
import com.springdatajpa.demo.interfaces.CharacterProjection;
import com.springdatajpa.demo.interfaces.CharacterRequest;
import com.springdatajpa.demo.services.CachingService;
import com.springdatajpa.demo.services.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.management.relation.Role;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterController {
	@Autowired
	private CharacterService characterService;

	@Autowired
	private CachingService cachingService;

	// QUERY CREATION
	@GetMapping
	public ResponseEntity<List<Characters>> getAllCharacters() {
		return ResponseEntity.ok(characterService.getAllCharacters());
	}

	// QUERY CREATION: CREATE NEW TODO
	@PostMapping
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> addCharacter(@RequestParam String todoItem, @RequestParam String status) {
		Characters todo = new Characters(todoItem, status);
		todo.setCharacterItem(todoItem);
		todo.setCompleted(status);
		return ResponseEntity.ok(characterService.createNewTask(todo));
	}

	// QUERY CREATION: FIND BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Characters> getCharacterById(@PathVariable long id) {
		return ResponseEntity.ok(characterService.findCharacterById(id));
	}

	// QUERY CREATION: FIND BY DESC
	@GetMapping("/desc/{desc}")
	public ResponseEntity<List<Characters>> findTaskByDescription(@PathVariable String desc) {
		return ResponseEntity.ok(characterService.findTaskByDescription(desc));
	}

	@DeleteMapping("/{id}")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Boolean> deleteCharacterById(@PathVariable long id) {
		characterService.deleteTask(id);
		return ResponseEntity.ok(true);
	}

	@PutMapping("/{id}")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> updateCharacterStatus(@PathVariable long id) {
		return ResponseEntity.ok(characterService.updateTask(id));
	}

	// TRANSACTIONS EX 1
	@GetMapping("/demo/transaction")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Boolean> performMultipleOperations() {
		characterService.performMultipleOperations();
		return ResponseEntity.ok(true);
	}

	// TRANSACTIONS EX 2
	@PostMapping("/demo/transaction-rollback")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Boolean> performOperationsRollback(@RequestParam String todoItem, @RequestParam String status) {
		Characters todo = new Characters(todoItem, status);
		todo.setCharacterItem(todoItem);
		todo.setCompleted(status);
		characterService.createTaskRollback(todo);
		return ResponseEntity.ok(true);
	}

	// PAGING & SORTING EX 1
	@GetMapping("/all")
	public Page<Characters> getAllCharacters(Pageable pageable) {
		return characterService.getAllCharacters(pageable);
	}

	@GetMapping("/all-page-one")
	public Page<Characters> getAllCharactersOne(@RequestParam Integer page, @RequestParam Integer size) {
		// Create a Pageable object with page number, page size, and sorting
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "todoItem"));
		return characterService.getAllCharacters(pageable);
	}

	@GetMapping("/all-page-two")
	public List<Characters> getAllCharactersTwo(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortBy, @RequestParam Sort.Direction direction) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
		Page<Characters> todoPage = characterService.findAllWithPagingAndSorting(pageable);
		return todoPage.getContent();
	}

	// NATIVE QUERY & PROJECTION
	// 1. NATIVE QUERY & PROJECTION
	@GetMapping("/native-query-one")
	public ResponseEntity<List<Characters>> getAllCharacterNativeOne() {
		List<Characters> todos = characterService.getAllCharactersNativeQuery();
		return ResponseEntity.ok(todos);
	}

	@GetMapping("/native-query-two")
	public ResponseEntity<List<Characters>> getAllCharacterNativeTwo(@RequestParam String status) {
		List<Characters> todos = characterService.findCharactersByCompleted(status);
		return ResponseEntity.ok(todos);
	}

	@PostMapping("/native-query-three")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> insertCharacter(@RequestBody CharacterRequest todoRequest) {
		try {
			characterService.insertCharacter(todoRequest);
			return ResponseEntity.ok("Characters item created successfully.");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
		}
	}

	// 2. PROJECTION
	@GetMapping("/projection")
	public ResponseEntity<List<CharacterProjection>> getAllCharacterItemsProjection() {
		List<CharacterProjection> todos = characterService.getAllCharacterItems();
		return ResponseEntity.ok(todos);
	}

	// BULK CREATE
	@PostMapping("/bulk-create")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> bulkCreateCharacterItems(@RequestBody List<CharacterRequest> todoRequests) {
		characterService.bulkCreateCharacterItems(todoRequests);
		return ResponseEntity.ok("Characters items created successfully.");
	}

	// CACHING
	@GetMapping("/caching")
	public ResponseEntity<List<Characters>> getAllCharacterCaching() {
		List<Characters> todos = characterService.getAllCharacters();
		return ResponseEntity.ok(todos);
	}

	@DeleteMapping("/caching/{id}")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<String> deleteCharacterCaching(@PathVariable long id) {
		characterService.deleteById(id);
		return ResponseEntity.ok("Delete task successfully.");
	}

	@PostMapping("/caching")
//	@RolesAllowed(Role.USER_ADMIN)
	public ResponseEntity<Characters> deleteCharacterCaching(@RequestBody Characters todo) {
		Characters result = characterService.saveCharacter(todo);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/clearAllCaches")
//	@RolesAllowed(Role.USER_ADMIN)
	public void clearAllCaches() {
		cachingService.evictAllCaches();
	}
}
