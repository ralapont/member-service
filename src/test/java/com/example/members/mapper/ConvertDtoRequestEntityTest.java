package com.example.members.mapper;

import com.example.members.entity.MemberEntity;
import com.example.members.model.input.MemberRequestDto;
import com.example.members.utils.BuildTestObjects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ConvertDtoRequestEntityTest {

    @Autowired
    private ConvertDtoRequestEntity convertDtoEntity;

    @Test
    void entityToDto() {

        MemberEntity memberEntity = MemberEntity.builder()
                .id(1)
                .lastName("Grogan")
                .firstName("Vladimir")
                .expiration(BuildTestObjects.stringToLocalDate("2004-10-25"))
                .email("vladimir_grogan@earth.com")
                .street("3263 Gilbert Rd.")
                .city("Ithaca")
                .state("NY")
                .zip("99357")
                .phone("332-511-5038")
                .interests("Great Depression,Spanish-American War")
                .build();

        MemberRequestDto memberRequestDto = convertDtoEntity.entityToDto(memberEntity);

        assertNotNull(memberRequestDto);
        assertEquals("Grogan", memberRequestDto.getLastName());
        assertEquals("Vladimir", memberRequestDto.getFirstName());
        assertEquals(BuildTestObjects.stringToLocalDate("2004-10-25"), memberRequestDto.getExpiration());
        assertEquals("vladimir_grogan@earth.com", memberRequestDto.getEmail());
        assertEquals("3263 Gilbert Rd.", memberRequestDto.getStreet());
        assertEquals("Ithaca", memberRequestDto.getCity());
        assertEquals("NY", memberRequestDto.getState());
        assertEquals("99357", memberRequestDto.getZip());
        assertEquals("332-511-5038", memberRequestDto.getPhone());
        assertEquals("Great Depression,Spanish-American War", memberRequestDto.getInterests());

    }

    @Test
    void dtoToEntity() {

        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .lastName("Grogan")
                .firstName("Vladimir")
                .expiration(BuildTestObjects.stringToLocalDate("2004-10-25"))
                .email("vladimir_grogan@earth.com")
                .street("3263 Gilbert Rd.")
                .city("Ithaca")
                .state("NY")
                .zip("99357")
                .phone("332-511-5038")
                .interests("Great Depression,Spanish-American War")
                .build();

        MemberEntity memberEntity = convertDtoEntity.dtoToEntity(memberRequestDto);

        assertNotNull(memberEntity);
        assertEquals("Grogan", memberEntity.getLastName());
        assertEquals("Vladimir", memberEntity.getFirstName());
        assertEquals(BuildTestObjects.stringToLocalDate("2004-10-25"), memberEntity.getExpiration());
        assertEquals("vladimir_grogan@earth.com", memberEntity.getEmail());
        assertEquals("3263 Gilbert Rd.", memberEntity.getStreet());
        assertEquals("Ithaca", memberEntity.getCity());
        assertEquals("NY", memberEntity.getState());
        assertEquals("99357", memberEntity.getZip());
        assertEquals("332-511-5038", memberEntity.getPhone());
        assertEquals("Great Depression,Spanish-American War", memberEntity.getInterests());
    }


}
