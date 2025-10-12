package com.springLataleLauncher.demo.DAO.impl;

import com.springLataleLauncher.demo.DAO.CharacterDAO;
import com.springLataleLauncher.demo.interfaces.CharacterRequest;
import com.springLataleLauncher.demo.entity.Characters;
import com.springLataleLauncher.demo.repository.CharacterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional // Apply transactional behavior at the class level
public class CharacterDAOImpl implements CharacterDAO {

    @Autowired
    private CharacterRepository characterRepository;

    @PersistenceContext
    private EntityManager em;

    /**
     * Example query method
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @CachePut(value = "characters", key = "#characters.id")
    public Characters createNewCharacter(Characters characters) {
        return characterRepository.save(characters);
    }

	// CACHING
    /**
     * The method returns the character,
     only it doesn't find it the cache.
     *
     * @return list of characters
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @Cacheable(value = "characters")
    public List<Characters> getAllCharacters() {
        List<Characters> characters = characterRepository.findAll();
        return characters;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public Optional<Characters> findCharacterById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Characters> cq = cb.createQuery(Characters.class);
        Root<Characters> root = cq.from(Characters.class);
        cq.select(root).where(cb.equal(root.get("id"), id));
        return Optional.ofNullable(em.createQuery(cq).getSingleResult());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public List<Characters> findCharacterByClass(String charClass) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Characters> cq = cb.createQuery(Characters.class);
        Root<Characters> root = cq.from(Characters.class);
        cq.select(root).where(cb.equal(root.get("characterClass"), charClass));;
        return em.createQuery(cq).getResultList();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public Characters updateCharacter(Long id, String newBio) {
        Characters characters = characterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Character not found with id: " + id));
        characters.setBio(newBio);
        return characterRepository.save(characters);
    }

    @Override
    public Page<Characters> findAllWithPagingAndSorting(Pageable pageable) {
        return characterRepository.findAllWithPagingAndSorting(pageable);
    }

    @Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public List<Characters> getAllCharactersNativeQuery() {
        return characterRepository.findAllCharacters();
    }

    @Override
    public boolean existByName(String characterName) {
        return characterRepository.existsByCharacterName(characterName);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void insertCharacter(CharacterRequest characterRequest) {
        characterRepository.insertCharacter(characterRequest.getCharacterClass().toString(),
                characterRequest.getCharacterName(),
                characterRequest.getBio());
    }

    /**
     * Bulk create
     */
    @Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void bulkCreateCharacterItems(List<CharacterRequest> characterRequests) {
        List<Characters> charactersList = characterRequests.stream()
                .map(this::mapToCharacters)
                .collect(Collectors.toList());
        characterRepository.saveAll(charactersList);
    }

    /**
     * The method returns the tasks,
     but refreshes all the entries in the cache to load new ones.
     *
     * @return the character list
     */

    @CacheEvict(value = "characters", allEntries = true)
    public void deleteById(Long id) {
        characterRepository.deleteById(id);
    }


    @Override
    public Characters updateCharacterException(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCharacter'");
    }

    private List<Characters> fetchAllTodosFromRepository() {
        return (List<Characters>) characterRepository.findAll();
    }

    private Characters mapToCharacters(CharacterRequest characterRequest) {
        Characters characters = new Characters();
        characters.setBio(characterRequest.getBio());
        return characters;
    }
}
