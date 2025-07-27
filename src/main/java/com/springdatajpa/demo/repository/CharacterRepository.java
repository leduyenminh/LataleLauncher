package com.springdatajpa.demo.repository;

import com.springdatajpa.demo.entity.Characters;
import com.springdatajpa.demo.interfaces.CharacterProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<Characters, Long> {
    public Characters findByTodoItem(String task);

    public Characters getById(Long id);

    public Characters getCharacterById(Long id);

    public List<Characters> findCharactersById(Long id);

    public List<Characters> findCharacterByClass(String desc);

    // @Query(value = "SELECT * FROM characters ORDER BY created_at DESC", countQuery = "SELECT COUNT(*) FROM todo", nativeQuery = true)
    Page<Characters> findAllWithPagingAndSorting(Pageable pageable);

    @Query(value = "SELECT * FROM characters", nativeQuery = true)
    List<Characters> findAllCharacters();

    @Modifying
    @Query(value = "INSERT INTO characters (completed, todo_item, created_at) VALUES (:completed, :todoItem, :createdAt)", nativeQuery = true)
    void insertTodo(@Param("completed") String completed, @Param("todoItem") String todoItem, @Param("createdAt") LocalDateTime createdAt);

    List<CharacterProjection> findAllBy();
}