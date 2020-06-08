package com.progracol.bingo;

import com.progracol.bingo.services.BingoParamBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BingoBoardControllerTest {
    @MockBean
    private BingoParamBoardService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/bingo/board with pageNo=0 , pageSize=10 - SUCCESS")
    void testGetBingoBoardsSuccess() throws Exception {
        int pageNo=0, pageSize = 10;

        mockMvc.perform(get("/api/bingo/board")
                .param("pageNo", Integer.toString(pageNo))
                .param("pageSize", Integer.toString(pageSize)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/bingo/board without pageNo, pageSize - SUCCESS")
    void testDefaultPagingBingoBoard() throws Exception {

        mockMvc.perform(get("/api/bingo/board")) // <-- no space after comma!
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/bingo/board with an invalid parameter - BAD REQUEST")
    void testGetBingoBoardInvalid() throws Exception {

        mockMvc.perform(get("/api/bingo/board")
                .param("pageNo", "F")
                .param("pageSize", "50"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("POST /api/bingo/winners with a valid winners array    size=75   SUCCESS")
    void testGetBingoWinnersValid() throws Exception {

        int[] numbers = new int[] {30, 71, 57, 38, 21, 69, 26, 6, 55, 47, 52, 3, 16, 34, 35, 20, 10, 72, 23, 60, 62, 51, 74, 54, 43, 7, 12, 66, 22, 31, 40, 49, 58, 50, 70, 64, 67, 36, 2, 75, 56, 73, 59, 41, 42, 24, 9, 18, 45, 48, 39, 65, 29, 19, 5, 63, 15, 4, 44, 46, 28, 14, 27, 8, 53, 11, 13, 33, 32, 1, 61, 17, 37, 68, 25};
        ArrayList<Integer> winners = new ArrayList<>();

        for(int i = 0; i< numbers.length; i++){
            winners.add(numbers[i]);
        }

        mockMvc.perform(post("/api/bingo/winners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(winners.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/bingo/winners with an invalid winners array    size!=75   BAD REQUEST")
    void testGetBingoWinnersInvalid() throws Exception {

        int[] numbers = new int[] {30, 35, 20, 39, 65, 29, 53, 11, 13, 33, 32, 1, 61, 17, 37, 68, 25};
        ArrayList<Integer> winners = new ArrayList<>();

        for(int i = 0; i< numbers.length; i++){
            winners.add(numbers[i]);
        }

        mockMvc.perform(post("/api/bingo/winners")
                .contentType(MediaType.APPLICATION_JSON)
                .content(winners.toString()))
                .andExpect(status().is4xxClientError());
    }

}
