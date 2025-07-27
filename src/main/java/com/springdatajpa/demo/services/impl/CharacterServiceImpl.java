package com.springdatajpa.demo.services.impl;

import com.springdatajpa.demo.interfaces.CharacterProjection;
import com.springdatajpa.demo.interfaces.CharacterRequest;
import com.springdatajpa.demo.entity.Characters;
import com.springdatajpa.demo.repository.CharacterRepository;

import com.springdatajpa.demo.services.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
//@Transactional // Apply transactional behavior at the class level
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

		@PersistenceContext
    private EntityManager em;

    /**
     * Example query method
     */
    @Override
    public Characters createNewCharacter(Characters characters) {
        return characterRepository.save(characters);
    }

		@Override
    public List<Characters> getAllCharacters() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Characters> cq = cb.createQuery(Characters.class);
        Root<Characters> root = cq.from(Characters.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

		@Override
    public Characters findCharacterById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Characters> cq = cb.createQuery(Characters.class);
        Root<Characters> root = cq.from(Characters.class);
        cq.select(root).where(cb.equal(root.get("id"), id));
        return em.createQuery(cq).getSingleResult();
    }

		@Override
    public List<Characters> findTaskByDescription(String desc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Characters> cq = cb.createQuery(Characters.class);
        Root<Characters> root = cq.from(Characters.class);
        cq.select(root).where(cb.equal(root.get("todoItem"), desc));;
        return em.createQuery(cq).getResultList();
    }



    public void deleteTask(Long id) {
        Characters task = characterRepository.getById(id);
        characterRepository.delete(task);
    }

    public Characters updateCharacter(Long id, String newBio) {
        Characters characters = characterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Character not found with id: " + id));

        characters.setBio(newBio);

        return characterRepository.save(characters);
    }

    /**
     * Example transaction
     */
    @Transactional
    public void performMultipleOperations() {
        // Perform multiple database operations within a transaction
        Characters todo1 = new Characters("Task 1", "No");
        Characters todo2 = new Characters("Task 2", "Yes");

        // Save todos
        characterRepository.save(todo1);
        characterRepository.save(todo2);

        // Update todo status
        todo1.setCompleted("Yes");
        characterRepository.save(todo1);

        // Delete todo
        characterRepository.delete(todo2);
    }

    @Transactional
    public void createTaskRollback(Characters characters) {
        try {
            // Thực hiện thao tác lưu người dùng vào cơ sở dữ liệu
            characterRepository.save(characters);

            // Ném một ngoại lệ để tạo ra một tình huống lỗi
            throw new RuntimeException("Error occurred");

        } catch (Exception e) {
            // Xảy ra lỗi, giao dịch sẽ được rollback
            throw new RuntimeException("Failed to create task: " + e.getMessage());
        }
    }

    public Page<Characters> findAllWithPagingAndSorting(Pageable pageable) {
        return characterRepository.findAllWithPagingAndSorting(pageable);
    }

    /**
     * Example paging
     */
    // public Page<Characters> getAllTodos(Pageable pageable) {
    //     return characterRepository.findAll(pageable);
    // }


    /**
     * Example native query & projection
     */
    public List<Characters> getAllTodosNativeQuery() {
        return characterRepository.findAllCharacters();
    }

    // public List<Characters> findTodosByCompleted(String status) {
    //     return characterRepository.findTodosByCompleted(status);
    // }

    public void insertTodo(CharacterRequest todoRequest) {
        characterRepository.insertTodo(todoRequest.getCompleted(),
                todoRequest.getBio(),
                todoRequest.getCreatedAt());
    }

    public List<CharacterProjection> getAllTodoItems() {
        return characterRepository.findAllBy();
    }

    /**
     * Bulk create
     */
    @Override
    public void bulkCreateTodoItems(List<CharacterRequest> todoRequests) {
        List<Characters> todoItems = todoRequests.stream()
                .map(this::mapToTodo)
                .collect(Collectors.toList());
        characterRepository.saveAll(todoItems);
    }

    private Characters mapToTodo(CharacterRequest todoRequest) {
        Characters characters = new Characters();
        characters.se(todoRequest.getCompleted());
        characters.setTodoItem(todoRequest.getBio());
        return characters;
    }

    // CACHING
    /**
     * The method returns the tasks,
     only it doesn't find it the cache.
     *
     * @return the todos
     */
    @Cacheable(value = "todos")
    public List<Characters> getAllTodos() {
        return fetchAllTodosFromRepository();
    }

    /**
     * The method returns the tasks,
     but refreshes all the entries in the cache to load new ones.
     *
     * @return the todos
     */
    @CacheEvict(value = "todos", allEntries = true)
    public List<Characters> getAllTodos1() {
        return fetchAllTodosFromRepository();
    }

    @CachePut(value = "todos", key = "#todo.id")
    public Characters saveTodo(Characters todo) {
        return characterRepository.save(todo);
    }

    @CacheEvict(value = "todos", allEntries = true)
    public void deleteById(Long id) {
        characterRepository.deleteById(id);
    }

    private List<Characters> fetchAllTodosFromRepository() {
        return (List<Characters>) characterRepository.findAll();
    }
}
