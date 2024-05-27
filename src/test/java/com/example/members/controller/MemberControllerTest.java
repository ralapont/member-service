package com.example.members.controller;

import com.example.members.model.input.MemberRequestDto;
import com.example.members.model.output.MemberResponseDto;
import com.example.members.service.impl.MemberServiceImpl;
import com.example.members.utils.BuildTestObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberServiceImpl memberService;

    @Test
    public void getMemberByIdTest() {

        when(memberService.getMemberById(Integer.valueOf(1))).thenReturn(BuildTestObjects.buildDtoResponse());

        ResponseEntity<MemberResponseDto> memberResponse = memberController.getMemberById(Integer.valueOf(1));

        assertNotNull(memberResponse);

        MemberResponseDto response = memberResponse.getBody();
        assertNotNull(response);
        assertEquals("Grogan", response.getLastName());
        assertEquals("Vladimir", response.getFirstName());
        assertEquals(BuildTestObjects.stringToLocalDate("2004-10-25"), response.getExpiration());
        assertEquals("vladimir_grogan@earth.com", response.getEmail());
        assertEquals("3263 Gilbert Rd.", response.getStreet());
        assertEquals("Ithaca", response.getCity());
        assertEquals("NY", response.getState());
        assertEquals("99357", response.getZip());
        assertEquals("332-511-5038", response.getPhone());
        assertEquals("Great Depression,Spanish-American War", response.getInterests());
    }

    @Test
    public void getAllMemberTest() {
        when(memberService.getAllMembers()).thenReturn(BuildTestObjects.buildListDtoResponse());

        ResponseEntity<List<MemberResponseDto>> members = memberController.getAllMembres();

        assertNotNull(members);
        List<MemberResponseDto> response = members.getBody();
        assertNotNull(response);
        MemberResponseDto memberResponseDto = response.get(0);

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
    public void saveMemberTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/members");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        MemberRequestDto memberRequestDto = BuildTestObjects.buildDtoRequest();
        when(memberService.saveMember(memberRequestDto)).thenReturn(BuildTestObjects.buildDtoResponse());

        ResponseEntity<MemberResponseDto> response = memberController.saveMember(memberRequestDto);

        assertEquals(201, response.getStatusCode().value());
        URI location = response.getHeaders().getLocation();
        assertNotNull(location);
        assertEquals("/api/members/1", location.getPath());

    }

    @Test
    public void updateMemberTest() {

        when(memberService.updateMember(any(Integer.class), any(MemberRequestDto.class))).thenReturn(BuildTestObjects.buildDtoModifyResponse());

        ResponseEntity<MemberResponseDto> memberResponse = memberController.updateMember(Integer.valueOf(1), BuildTestObjects.buildDtoRequest());

        assertNotNull(memberResponse);

    }

    @Test
    public void deleteMemberTest() {

        memberController.deleteMember(Integer.valueOf(1));
        verify(memberService).deleteMember(any(Integer.class));
    }


}
