package com.example.springbootjpa.controller;

import model.dto.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UserRestController {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("입력한 Id로 조회가 잘 되는지")
    void findById() throws Exception {

        given(userService.getUser(1l)).willReturn(new UserResponse(1l,"suyeon","회원 등록 성공"));
        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect((jsonPath("$.id").value(1)))
                .andExpect((jsonPath("$.message").value("회원 등록 성공")))
                .andDo(print());
    }

    @Test
    @DisplayName("입력한 Id로 조회 실패 했을 때 message가 잘 나오는지")
    void findByIdFail() throws Exception{given(userService.getUser(1l)).willReturn(new UserResponse(null,"","해당 id의 유저가 없습니다"));
        mockMvc.perform(get("/api/v1/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isEmpty())
                .andExpect(jsonPath("$.message").value("해당 id의 유저가 없습니다"))
                .andDo(print());
    }
}

