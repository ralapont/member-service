package com.example.members.mapper;

import com.example.members.entity.MemberEntity;
import com.example.members.model.input.MemberRequestDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConvertDtoRequestEntity {

    private ModelMapper modelMapper;

    public MemberRequestDto entityToDto(MemberEntity memberEntity) {
        return modelMapper.map(memberEntity, MemberRequestDto.class);
    }

    public MemberEntity dtoToEntity(MemberRequestDto memberRequestDto) {
        return modelMapper.map(memberRequestDto, MemberEntity.class);
    }
}
