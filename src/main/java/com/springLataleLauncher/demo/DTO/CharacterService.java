package com.springLataleLauncher.demo.DTO;

import com.springLataleLauncher.demo.vo.CharacterVO;

import java.util.List;

public interface CharacterService {
    List<CharacterVO> getAllCharactersByClass(String characterClass);
}
