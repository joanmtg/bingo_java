package com.progracol.bingo.repositories;

import com.progracol.bingo.models.BingoParamBoard;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;

public interface BingoParamBoardRepository extends JpaRepository<BingoParamBoard, Long> {
}
