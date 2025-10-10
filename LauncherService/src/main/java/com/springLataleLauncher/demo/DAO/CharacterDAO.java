package com.springLataleLauncher.demo.DAO;

import java.util.List;
import java.util.Optional;

import com.springLataleLauncher.demo.entity.Characters;
import com.springLataleLauncher.demo.interfaces.CharacterRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterDAO {

    public Characters createNewCharacter(Characters characters);
    public List<Characters> getAllCharacters();
    public Optional<Characters> findCharacterById(Long id);
    public boolean existByName(String charName);
    public List<Characters> findCharacterByClass(String charClass);
    public Characters updateCharacter(Long id, String newBio);
    public Characters updateCharacterException(Long id);
    public Page<Characters> findAllWithPagingAndSorting(Pageable pageable);
    public List<Characters> getAllCharactersNativeQuery();
    // public void insertCharacter(CharacterRequest todoRequest);

    public void deleteById(Long id);
    public void bulkCreateCharacterItems(List<CharacterRequest> characterRequests);
}
