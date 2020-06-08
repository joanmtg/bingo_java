package com.progracol.bingo.repositories;

import com.progracol.bingo.models.BingoParamBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BingoParamBoardRepository extends JpaRepository<BingoParamBoard, Long> {
    @Query("FROM #{#entityName} WHERE boardId = :boardId")
    List<BingoParamBoard> getCustomBoard(@Param("boardId") Integer boardId);

    @Query(value= "SELECT * FROM #{#entityName} " +
                  "WHERE array_remove(#{#entityName}.board_numbers, NULL) <@ " +
                  "CAST(string_to_array(:winnerNumbers, ',') as int[]) "
           , nativeQuery = true)
    List<BingoParamBoard> getWinners(@Param("winnerNumbers") String winnerNumbers);
}
