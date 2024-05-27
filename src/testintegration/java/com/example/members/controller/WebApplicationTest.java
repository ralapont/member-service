package com.example.members.controller;

import com.example.members.model.input.MemberRequestDto;
import com.example.members.model.output.MemberResponseDto;
import com.example.members.utils.BuildTestObjects;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql({ "classpath:insert_gets.sql" })
    void shouldReturnMemberOne() throws Exception {

        String memberResponseDtoString = this.mockMvc.perform(get("/api/members/{id}", 1)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        MemberResponseDto memberResponseDto = objectMapper.readValue(memberResponseDtoString, MemberResponseDto.class);

        assertNotNull(memberResponseDto);
        assertEquals(Integer.valueOf(1), memberResponseDto.getId());
    }

    @Test
    @Sql({ "classpath:insert_gets.sql" })
    void shouldReturnNotFoundMember() throws Exception {

        this.mockMvc.perform(get("/api/members/{id}", 7)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

    }


    @Test
    @Sql({ "classpath:insert_gets.sql" })
    void shouldReturnAllMembers() throws Exception {
        String memberResponseDtoString = this.mockMvc.perform(get("/api/members")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        TypeReference<List<MemberResponseDto>> jacksonTypeReference = new TypeReference<>() {};
        List<MemberResponseDto> memberResponseDtoList = objectMapper.readValue(memberResponseDtoString, jacksonTypeReference);

        assertNotNull(memberResponseDtoList);
        assertEquals(3, memberResponseDtoList.size());
    }

    @Test
    @Sql({ "classpath:insert_create.sql" })
    void shouldReturnNewMember() throws Exception {

        MemberRequestDto request = BuildTestObjects.buildDtoRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        String memberResponseDtoString = this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/members")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        MemberResponseDto memberResponseDto = objectMapper.readValue(memberResponseDtoString, MemberResponseDto.class);

        assertNotNull(memberResponseDto);
        assertNotNull(memberResponseDto.getId());
        assertEquals("Grogan", memberResponseDto.getLastName());
        assertEquals("Vladimir", memberResponseDto.getFirstName());
    }

    @Test
    @Sql({ "classpath:insert_update.sql" })
    void shouldReturnMemberModify() throws Exception {

        MemberRequestDto request = BuildTestObjects.buildDtoRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        this.mockMvc.perform( MockMvcRequestBuilders
                        .put("/api/members/{id}", 1)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.memberId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.memberId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Vladimir"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Grogan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("vladimir_grogan@earth.com"));
    }

    @Test
    @Sql({ "classpath:insert_update.sql" })
    void shouldReturnNotFoundMemberToModify() throws Exception {

        this.mockMvc.perform(get("/api/members/{id}", 7)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

    }

    @Test
    @Sql({ "classpath:insert_delete.sql" })
    void shouldReturnNothingMember() throws Exception {
        this.mockMvc.perform( MockMvcRequestBuilders.delete("/api/members/{id}", 1) )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isAccepted());
    }

}
