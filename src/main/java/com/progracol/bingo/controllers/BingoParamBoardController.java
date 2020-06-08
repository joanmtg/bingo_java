package com.progracol.bingo.controllers;

import com.progracol.bingo.models.BingoParamBoard;
import com.progracol.bingo.services.BingoParamBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bingo")
public class BingoParamBoardController {
    @Autowired
    private BingoParamBoardService bingoParamBoardService;

    @GetMapping(path="/board")
    public ResponseEntity<List<BingoParamBoard>> getAllBoards(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "50") Integer pageSize)
    {
        List<BingoParamBoard> list = bingoParamBoardService.getAllBoards(pageNo, pageSize);
        return new ResponseEntity<List<BingoParamBoard>>(list, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping(path="/winners")
    public ResponseEntity<List<BingoParamBoard>> getWinners(@RequestBody final List<Integer> winnerNumbers)
    {
        List<BingoParamBoard> list = bingoParamBoardService.getWinners(winnerNumbers);

        if(winnerNumbers.size() == 75){
            return new ResponseEntity<List<BingoParamBoard>>(list, new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

}
