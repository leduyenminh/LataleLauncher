package com.springLataleLauncher.demo.DTO.impl;

import com.springLataleLauncher.demo.DAO.CharacterDAO;
import com.springLataleLauncher.demo.DTO.CharacterService;
import com.springLataleLauncher.demo.aop.exception.CharacterValidationException;
import com.springLataleLauncher.demo.entity.Characters;
import com.springLataleLauncher.demo.interfaces.CharacterRequest;
import com.springLataleLauncher.demo.interfaces.CharacterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterDAO characterDAO;

    @Override
    public List<CharacterResponse> getAllCharacters(){
        List<Characters> characters = characterDAO.getAllCharacters();
        List<CharacterResponse> characterResponses = characters.stream().map(c -> toCharacterResponse(c)).toList();
        return characterResponses;
    }

    @Override
    public List<CharacterResponse> getAllCharactersByClass(String characterClass){
        List<Characters> characters = characterDAO.findCharacterByClass(characterClass);
        List<CharacterResponse> characterResponses = characters.stream().map(c -> toCharacterResponse(c)).toList();
        return characterResponses;
    }

    @Override
    public CharacterResponse createCharacter(CharacterRequest characterRequest){
        Characters newCharacterRequest = toCharacterEntity(characterRequest);
        Characters newCharacter = characterDAO.createNewCharacter(newCharacterRequest);
        CharacterResponse characterVO = toCharacterResponse(newCharacter);
        return characterVO;
    }

    @Override
    public CharacterResponse updateCharacter(Long id, CharacterRequest characterRequest){
        Characters character = characterDAO.findCharacterById(id).orElseThrow(
        () -> new CharacterValidationException("Character not found with ID: " + id));

        if (characterDAO.existByName(characterRequest.getCharacterName())){
            throw new CharacterValidationException("Character name "+characterRequest.getCharacterName() + "already exist!");
        }

        if (characterRequest.getCharacterClass() != null){
            throw new CharacterValidationException("Cannot change character class!");
        }

        character.setBio(characterRequest.getBio());
        character.setCharacterName(characterRequest.getCharacterName());
        return toCharacterResponse(character);
    }

    @Override
    public void deleteCharacter(Long id){
        characterDAO.deleteById(id);
    }

    private CharacterResponse toCharacterResponse(Characters characters) {
        CharacterResponse characterResponse = new CharacterResponse();
        characterResponse.setCharacterClass(characters.getCharacterClass());
        characterResponse.setBio(characters.getBio());
        characterResponse.setCharacterName(characters.getCharacterName());
        return characterResponse;
    }

    private Characters toCharacterEntity(CharacterRequest characterRequest) {
        Characters newCharacter = new Characters();
        newCharacter.setCharacterClass(characterRequest.getCharacterClass());
        newCharacter.setBio(characterRequest.getBio());
        newCharacter.setCharacterName(characterRequest.getCharacterName());
        return newCharacter;
    }
}
