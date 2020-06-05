package com.progracol.bingo.controllers;

import com.progracol.bingo.models.BingoParamBoard;
import com.progracol.bingo.services.BingoParamBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/bingo/board")
public class BingoParamBoardController {
    @Autowired
    private BingoParamBoardService bingoParamBoardService;

    @GetMapping
    public ResponseEntity<List<BingoParamBoard>> getAllBoards(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "50") Integer pageSize)
    {
        List<BingoParamBoard> list = bingoParamBoardService.getAllBoards(pageNo, pageSize);

        return new ResponseEntity<List<BingoParamBoard>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
