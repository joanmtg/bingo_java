package com.progracol.bingo.controllers;

import com.progracol.bingo.models.BingoParamBoard;
import com.progracol.bingo.repositories.BingoParamBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/bingo/board")
public class BingoParamBoardController {
    @Autowired
    private BingoParamBoardRepository bingoParamBoardRepository;

    @GetMapping
    public List<BingoParamBoard> list(){
        return bingoParamBoardRepository.findAll();
    }
}
