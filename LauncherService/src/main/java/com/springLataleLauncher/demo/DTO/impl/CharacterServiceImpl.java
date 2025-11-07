package com.springLataleLauncher.demo.DTO.impl;

import com.springLataleLauncher.demo.DAO.CharacterDAO;
import com.springLataleLauncher.demo.DTO.CharacterService;
import com.springLataleLauncher.demo.DTO.CharacterUpdatedBioEvent;
import com.springLataleLauncher.demo.aop.exception.CharacterValidationException;
import com.springLataleLauncher.demo.entity.Characters;
import com.springLataleLauncher.demo.interfaces.CharacterRequest;
import com.springLataleLauncher.demo.interfaces.CharacterResponse;
import com.springLataleLauncher.demo.kafka.CharacterKafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final List<String> sensitiveWords = Arrays.asList("damn", "black");

    @Autowired
    private CharacterDAO characterDAO;

    @Autowired
    private CharacterKafkaProducer kafkaProducer;

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

        kafkaProducer.sendCharacterEvent(newCharacter, "CREATE_CHARACTER");

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

        kafkaProducer.sendCharacterEvent(character, "UPDATE_CHARACTER");
        return toCharacterResponse(character);
    }

    @Override
    public void deleteCharacter(Long id){
        characterDAO.deleteById(id);
        kafkaProducer.sendCharacterEvent(characterDAO.findCharacterById(id).get(), "DELETE_CHARACTER");
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

    @Override
    public Boolean bioValidation(CharacterUpdatedBioEvent event) {
        boolean containsSensitiveWords = sensitiveWords.stream()
                .anyMatch(word -> event.bio().toLowerCase().contains(word));

        if (containsSensitiveWords) {
            // Publish failure event
            CharacterUpdatedBioEvent failureEvent = new CharacterUpdatedBioEvent(event.id(),event.name(), "Contains sensitive content");
            kafkaProducer.sendCharacterEvent(characterDAO.findCharacterById(failureEvent.id()).get(), "bio.update.failed");
        } else {
            // Publish success event (this may trigger other services if necessary, 
            // but for this simple case, it confirms completion)
            CharacterUpdatedBioEvent successEvent = new CharacterUpdatedBioEvent(event.id(),event.name(), "");
            kafkaProducer.sendCharacterEvent(characterDAO.findCharacterById(successEvent.id()).get(), "bio.update.success");
        }
        return containsSensitiveWords;
    }

    @Override
    @KafkaListener(topics = "character.events", groupId = "latale-kafka-service")
    public void reverseCharacterBioUpdate(CharacterUpdatedBioEvent characterUpdatedBioEvent) {
        Characters characterUpdating = characterDAO.findCharacterById(characterUpdatedBioEvent.id()).get();
        String previousBio = characterUpdating.getBio();
        if (!bioValidation(characterUpdatedBioEvent)){
            
            
        }
    }
}
