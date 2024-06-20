package com.example.members.service;

import com.example.members.exceptions.MemberNotFoundException;
import com.example.members.feign.dtos.State;
import com.example.members.model.input.MemberRequestDto;
import com.example.members.model.output.MemberResponseDto;

import java.util.List;

public interface MemberService {

    MemberResponseDto getMemberById(Integer id) throws MemberNotFoundException;

    List<MemberResponseDto> getAllMembers();

    MemberResponseDto saveMember(MemberRequestDto memberRequestDto);

    MemberResponseDto updateMember(Integer integer, MemberRequestDto memberRequestDto) throws MemberNotFoundException;

    void deleteMember(Integer id);

    List<State> getStates();
}
