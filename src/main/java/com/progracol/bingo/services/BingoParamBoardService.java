package com.progracol.bingo.services;

import com.progracol.bingo.models.BingoParamBoard;
import com.progracol.bingo.repositories.BingoParamBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BingoParamBoardService {

    @Autowired
    private BingoParamBoardRepository bingoParamBoardRepository;


    public List<BingoParamBoard> getAllBoards(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<BingoParamBoard> pagedResult = bingoParamBoardRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<BingoParamBoard>();
        }
    }

    public List<BingoParamBoard> getCustomBoard(Integer boardId){
        return bingoParamBoardRepository.getCustomBoard(boardId);
    }

    public List<BingoParamBoard> getWinners(List<Integer> winnerNumbers){

        /*Collection<Integer> collection = new ArrayList<Integer>(winnerNumbers);
        return bingoParamBoardRepository.getWinners(collection);*/

        return bingoParamBoardRepository.getWinners(winnerNumbers.toString()
                .replace('[', ' ').
                        replace(']', ' ').trim());
    }
}
