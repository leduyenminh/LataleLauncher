package com.springLataleLauncher.demo.repository;

import com.springLataleLauncher.demo.entity.Characters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Characters, Long> {

    @Query(value = "SELECT * FROM characters ORDER BY created_at DESC", countQuery = "SELECT COUNT(*) FROM characters", nativeQuery = true)
    Page<Characters> findAllWithPagingAndSorting(Pageable pageable);

    @Query(value = "SELECT * FROM characters", nativeQuery = true)
    List<Characters> findAllCharacters();

    @Modifying
    @Query(value = "INSERT INTO characters (characterClass, characterName, bio, createdAt) VALUES (:characterClass, :characterName, :bio, :createdAt)", nativeQuery = true)
    void insertCharacter(@Param("characterClass") String characterClass, @Param("characterName") String characterName, @Param("bio") String bio, @Param("createdAt") LocalDateTime createdAt);

}