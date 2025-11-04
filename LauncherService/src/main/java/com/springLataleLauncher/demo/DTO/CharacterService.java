package com.springLataleLauncher.demo.DTO;

import com.springLataleLauncher.demo.interfaces.CharacterRequest;
import com.springLataleLauncher.demo.interfaces.CharacterResponse;

import java.util.List;

public interface CharacterService {
    List<CharacterResponse> getAllCharacters();
    List<CharacterResponse> getAllCharactersByClass(String characterClass);
    CharacterResponse createCharacter(CharacterRequest characterRequest);
    CharacterResponse updateCharacter(Long id, CharacterRequest characterRequest);
    void deleteCharacter(Long id);
    Boolean bioValidation();
    void reverseCharacterBioUpdate(CharacterUpdatedBioEvent characterUpdatedBioEvent);
}
