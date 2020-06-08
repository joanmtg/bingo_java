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

    public List<BingoParamBoard> getWinners(List<Integer> winnerNumbers){
        int boardSize = 25, boardLimit = 75;
        int indexEnd = boardSize - 1;
        int winnersLimit = 100;

        List<Integer> numbersToEvaluate = winnerNumbers.subList(0, indexEnd);
        List<BingoParamBoard> resultingBoards = bingoParamBoardRepository.getWinners(processNumbersList(numbersToEvaluate));

        while ( (resultingBoards.size() < winnersLimit) &&
                (indexEnd <= winnerNumbers.size()) &&
                (indexEnd <= boardLimit)){

            numbersToEvaluate = winnerNumbers.subList(0, indexEnd);
            resultingBoards = bingoParamBoardRepository.getWinners(processNumbersList(numbersToEvaluate));
            indexEnd++;
        }

        if (resultingBoards.size() > winnersLimit){
            resultingBoards = resultingBoards.subList(0, winnersLimit);
        }

        return resultingBoards;
    }

    static String processNumbersList(List<Integer> numbersList){
        return numbersList.toString()
                          .replaceAll("\\[", "")
                          .replaceAll("]", "");

    }
}
