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

    /**
     * Método getAllBoards
     *
     * @param pageNo    Número de página
     * @param pageSize  Tamaño de página
     * @return  Lista de Tablas de bingo pertenecientes a la página
     */

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

    /**
     *  Método getWinners
     *
     * @param winnerNumbers  Lista de números ganadores del bingo ordenada por "turnos"
     * @return  Lista de las primeras 100 tablas ganadoras del bingo
     */

    public List<BingoParamBoard> getWinners(List<Integer> winnerNumbers){
        int boardSize = 25, boardLimit = winnerNumbers.size(), winnersLimit = 100;
        int indexEnd = boardSize - 1;

        List<Integer> numbersToEvaluate = winnerNumbers.subList(0, indexEnd);
        List<BingoParamBoard> resultingBoards = bingoParamBoardRepository.getWinners(processNumbersList(numbersToEvaluate));

        /**
         * Ciclo que revisa a partir del turno 25 (a partir del cual pueden aparecer nuevos ganadores en cada turno)
         * y actualiza la lista de ganadores resultingBoards
         *
         * El ciclo para cuando sea:
         *
         * Se alcanzan los primeros 100 ganadores en la lista resultingBoards
         * Final de arreglo de números ganadores winnersLimit
         */

        while ( (resultingBoards.size() < winnersLimit) &&
                (indexEnd <= boardLimit) ) {

            numbersToEvaluate = winnerNumbers.subList(0, indexEnd);
            resultingBoards = bingoParamBoardRepository.getWinners(processNumbersList(numbersToEvaluate));
            indexEnd++;
        }

        if (resultingBoards.size() > winnersLimit){
            resultingBoards = resultingBoards.subList(0, winnersLimit);
        }

        return resultingBoards;
    }

    /**
     * Método processNumbersList
     * @param numbersList List = [1, 2, 3, 4, 5]
     * @return  String "1,2,3,4,5"
     */

    private static String processNumbersList(List<Integer> numbersList){
        return numbersList.toString()
                          .replaceAll("\\[", "")
                          .replaceAll("]", "");

    }
}
