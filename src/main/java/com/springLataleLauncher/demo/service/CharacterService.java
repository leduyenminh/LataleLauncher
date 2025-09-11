package com.springLataleLauncher.demo.service;

import com.springLataleLauncher.demo.vo.CharacterVO;

import java.util.List;

public interface CharacterService {
    List<CharacterVO> getAllCharactersByClass(String characterClass);
}
