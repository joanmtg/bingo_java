package com.progracol.bingo.repositories;

import com.progracol.bingo.models.BingoParamBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;
//import org.springframework.data.repository.PagingAndSortingRepository;

public interface BingoParamBoardRepository extends JpaRepository<BingoParamBoard, Long> {
    @Query("FROM #{#entityName} WHERE boardId = :boardId")
    List<BingoParamBoard> getCustomBoard(@Param("boardId") Integer boardId);

    /*@Query(value= "SELECT * FROM #{#entityName} WHERE #{#entityName}.board_numbers @> :winnerNumbers", nativeQuery = true)
    List<BingoParamBoard> getWinners(@Param("winnerNumbers") Collection<Integer> winnerNumbers);*/

    @Query(value= "SELECT * FROM #{#entityName} WHERE #{#entityName}.board_numbers <@ CAST(string_to_array(:winnerNumbers, ',') as int[]) ", nativeQuery = true)
    List<BingoParamBoard> getWinners(@Param("winnerNumbers") String winnerNumbers);
}
