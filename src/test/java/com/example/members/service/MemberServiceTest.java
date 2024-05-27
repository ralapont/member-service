package com.example.members.service;

import com.example.members.entity.MemberEntity;
import com.example.members.mapper.ConvertDtoRequestEntity;
import com.example.members.mapper.ConvertDtoResponseEntity;
import com.example.members.model.input.MemberRequestDto;
import com.example.members.model.output.MemberResponseDto;
import com.example.members.repository.MemberRepository;
import com.example.members.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.example.members.utils.BuildTestObjects;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    MemberServiceImpl memberService;

    @Mock
    MemberRepository memberRespository;

    @Mock
    ConvertDtoResponseEntity convertDtoEntity;

    @Mock
    ConvertDtoRequestEntity converDtoRequest;

    @Test
    void getMemberByIdTest() {

        when(memberRespository.findById(1)).thenReturn(Optional.of(BuildTestObjects.buildEntityMock()));
        when(convertDtoEntity.entityToDto(any(MemberEntity.class))).thenReturn(BuildTestObjects.buildDtoResponse());

        MemberResponseDto memberResponseDto = memberService.getMemberById(1);

        assertNotNull(memberResponseDto);
        assertNotNull(memberResponseDto);
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
    void getAllMember() {

        when(memberRespository.findAll()).thenReturn(BuildTestObjects.buildListEntityMock());
        when(convertDtoEntity.entityToDto(any(MemberEntity.class))).thenReturn(BuildTestObjects.buildDtoResponse());

        List<MemberResponseDto> memberResponseDtoList = memberService.getAllMembers();

        assertNotNull(memberResponseDtoList);
        assertEquals(1, memberResponseDtoList.size());
        MemberResponseDto memberResponseDto = memberResponseDtoList.get(0);
        assertNotNull(memberResponseDto);

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
    void saveMember() {
        when((converDtoRequest.dtoToEntity(any(MemberRequestDto.class)))).thenReturn(BuildTestObjects.buildEntityMock());
        when(memberRespository.save(any(MemberEntity.class))).thenReturn(BuildTestObjects.buildEntityMock());
        when(convertDtoEntity.entityToDto(any(MemberEntity.class))).thenReturn(BuildTestObjects.buildDtoResponse());

        MemberResponseDto newMemberDto = memberService.saveMember(BuildTestObjects.buildDtoRequest());

        assertNotNull(newMemberDto);

        assertEquals(Integer.valueOf(1), newMemberDto.getId());
        assertEquals("Grogan", newMemberDto.getLastName());
        assertEquals("Vladimir", newMemberDto.getFirstName());
        assertEquals(BuildTestObjects.stringToLocalDate("2004-10-25"), newMemberDto.getExpiration());
        assertEquals("vladimir_grogan@earth.com", newMemberDto.getEmail());
        assertEquals("3263 Gilbert Rd.", newMemberDto.getStreet());
        assertEquals("Ithaca", newMemberDto.getCity());
        assertEquals("NY", newMemberDto.getState());
        assertEquals("99357", newMemberDto.getZip());
        assertEquals("332-511-5038", newMemberDto.getPhone());
        assertEquals("Great Depression,Spanish-American War", newMemberDto.getInterests());

    }

    @Test
    void updateMember() {

        when(memberRespository.findById(any(Integer.class))).thenReturn(Optional.of(BuildTestObjects.buildEntityMock()));
        when(memberRespository.save(any(MemberEntity.class))).thenReturn(BuildTestObjects.buildEntityModifyMock());
        when(convertDtoEntity.entityToDto(any(MemberEntity.class))).thenReturn(BuildTestObjects.buildDtoModifyResponse());

        MemberResponseDto newMemberDto = memberService.updateMember(1, BuildTestObjects.buildDtoRequest());

        assertNotNull(newMemberDto);

        assertEquals(Integer.valueOf(1), newMemberDto.getId());
        assertEquals("Grogan", newMemberDto.getLastName());
        assertEquals("Vladimir", newMemberDto.getFirstName());
        assertEquals(BuildTestObjects.stringToLocalDate("2004-04-25"), newMemberDto.getExpiration());
        assertEquals("vladimir_grogan@server.com", newMemberDto.getEmail());
        assertEquals("3263 Gilbert Rd.", newMemberDto.getStreet());
        assertEquals("Ithaca", newMemberDto.getCity());
        assertEquals("NY", newMemberDto.getState());
        assertEquals("99358", newMemberDto.getZip());
        assertEquals("332-511-5038", newMemberDto.getPhone());
        assertEquals("Great Depression,Spanish-American War", newMemberDto.getInterests());

    }

    @Test
    void deleteMember() {

        memberService.deleteMember(1);
        verify(memberRespository).deleteById(any(Integer.class));
    }
}
