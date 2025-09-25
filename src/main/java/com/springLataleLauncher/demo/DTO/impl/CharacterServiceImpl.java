package com.springLataleLauncher.demo.DTO.impl;

import com.springLataleLauncher.demo.DAO.CharacterDAO;
import com.springLataleLauncher.demo.DTO.CharacterService;
import com.springLataleLauncher.demo.vo.CharacterVO;
import com.springLataleLauncher.demo.vo.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterDAO characterDAO;


    public List<CharacterVO> getAllCharactersByClass(String characterClass){
        List<CharacterVO> characterVOS = new ArrayList<>();
//        characterDAO.getCharacterByClass();
        CharacterVO characterVO = new CharacterVO();
        characterVO.setCharacterId(1L);
        characterVO.setCharacterClass(Classes.KEMONO);
        characterVO.setCharacterName("TheFirstKemono");
        characterVOS.add(characterVO);
        return characterVOS;
    }
}
