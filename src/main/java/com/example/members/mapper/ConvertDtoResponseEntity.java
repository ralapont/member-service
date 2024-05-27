package com.example.members.mapper;

import com.example.members.entity.MemberEntity;
import com.example.members.model.output.MemberResponseDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConvertDtoResponseEntity {

    private ModelMapper modelMapper;

    public MemberResponseDto entityToDto(MemberEntity memberEntity) {
        return modelMapper.map(memberEntity, MemberResponseDto.class);
    }

    public MemberEntity dtoToEntity(MemberResponseDto memberResponseDto) {
        return modelMapper.map(memberResponseDto, MemberEntity.class);
    }
}
