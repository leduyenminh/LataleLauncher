package com.springdatajpa.demo.services;

import java.util.List;

import com.springdatajpa.demo.entity.Characters;
import com.springdatajpa.demo.interfaces.CharacterProjection;
import com.springdatajpa.demo.interfaces.CharacterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterService {

    public Characters createNewCharacter(Characters characters);
    public List<Characters> getAllCharacters();
    public Characters findCharacterById(Long id);
    public List<Characters> findTaskByDescription(String desc);
    // public List<Characters> findAllCompletedTask();
    // public List<Characters> findAllInCompleteTask();
    public void deleteTask(Long id);
    public Characters updateCharacter(Long id);
    public void performMultipleOperations();
    public void createTaskRollback(Characters task);
    public Page<Characters> findAllWithPagingAndSorting(Pageable pageable);
    // public Page<Characters> getAllTodos(Pageable pageable);
    public List<Characters> getAllTodosNativeQuery();
    // public List<Characters> findTodosByCompleted(String status);
    public void insertTodo(CharacterRequest todoRequest);
    public List<CharacterProjection> getAllTodoItems();
    public void bulkCreateTodoItems(List<CharacterRequest> todoRequests);
    public List<Characters> getAllTodos();
    public List<Characters> getAllTodos1();
    public Characters saveTodo(Characters todo);
    public void deleteById(Long id) ;
}
