package com.example.members.mapper;

import com.example.members.entity.MemberEntity;
import com.example.members.model.output.MemberResponseDto;
import com.example.members.utils.BuildTestObjects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ConvertDtoResponseEntityTest {

    @Autowired
    private ConvertDtoResponseEntity convertDtoEntity;

    @Test
    void entityToDto() {

        MemberEntity memberEntity = MemberEntity.builder()
                .id(Integer.valueOf(1))
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

        MemberResponseDto memberResponseDto = convertDtoEntity.entityToDto(memberEntity);

        assertNotNull(memberResponseDto);
        assertEquals(Integer.valueOf(1), memberResponseDto.getId());
        assertEquals("Grogan", memberResponseDto.getLastName());
        assertEquals("Vladimir", memberResponseDto.getFirstName());
        assertEquals(BuildTestObjects.stringToLocalDate("2004-10-25"), memberResponseDto.getExpiration());
        assertEquals("vladimir_grogan@earth.com", memberResponseDto.getEmail());
        assertEquals("3263 Gilbert Rd.", memberResponseDto.getStreet());
        assertEquals("Ithaca", memberResponseDto.getCity());
        assertEquals("NY", memberResponseDto.getState());
        assertEquals("99357", memberResponseDto.getZip());
        assertEquals("332-511-5038", memberResponseDto.getPhone());
        assertEquals("Great Depression,Spanish-American War", memberResponseDto.getInterests());

    }

    @Test
    void dtoToEntity() {

        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
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

        MemberEntity memberEntity = convertDtoEntity.dtoToEntity(memberResponseDto);

        assertNotNull(memberResponseDto);
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
