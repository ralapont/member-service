package com.example.members.service.impl;

import com.example.members.entity.MemberEntity;
import com.example.members.exceptions.MemberNotFoundException;
import com.example.members.mapper.ConvertDtoRequestEntity;
import com.example.members.mapper.ConvertDtoResponseEntity;
import com.example.members.model.input.MemberRequestDto;
import com.example.members.model.output.MemberResponseDto;
import com.example.members.repository.MemberRepository;
import com.example.members.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ConvertDtoResponseEntity convertDtoEntity;
    private final ConvertDtoRequestEntity convertDtoRequestEntity;

    @Override
    public MemberResponseDto getMemberById(Integer id) throws MemberNotFoundException {

        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        return memberEntity.map(convertDtoEntity::entityToDto).orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Override
    public List<MemberResponseDto> getAllMembers() {
        return (memberRepository.findAll()).stream()
                .map(convertDtoEntity::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MemberResponseDto saveMember(MemberRequestDto memberRequestDto) {
        MemberEntity oldMemberEntity = convertDtoRequestEntity.dtoToEntity(memberRequestDto);
        MemberEntity newMemberEntity = memberRepository.save(oldMemberEntity);

        return convertDtoEntity.entityToDto(newMemberEntity);
    }

    @Override
    public MemberResponseDto updateMember(Integer id, MemberRequestDto memberRequestDto) throws MemberNotFoundException {

        Optional<MemberEntity> oldEntityOpt = memberRepository.findById(id);

        MemberEntity oldEntity = oldEntityOpt.isPresent()?
                oldEntityOpt.get():
                oldEntityOpt.orElseThrow(() -> new MemberNotFoundException(id));

//        if (oldEntityOpt.isPresent()) {
//            oldEntity = oldEntityOpt.get();
//        } else {
//            throw new MemberNotFoundException(id);
//        }

        modifyEntity(oldEntity, memberRequestDto);
        MemberEntity entity = memberRepository.save(oldEntity);

        return convertDtoEntity.entityToDto(entity);
    }

    @Override
    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    private void modifyEntity(MemberEntity oldEntity, MemberRequestDto memberRequestDto) {
        oldEntity.setLastName(memberRequestDto.getLastName());
        oldEntity.setFirstName(memberRequestDto.getFirstName());
        oldEntity.setSuffix(memberRequestDto.getSuffix());
        oldEntity.setExpiration(memberRequestDto.getExpiration());
        oldEntity.setEmail(memberRequestDto.getEmail());
        oldEntity.setStreet(memberRequestDto.getStreet());
        oldEntity.setCity(memberRequestDto.getCity());
        oldEntity.setState(memberRequestDto.getState());
        oldEntity.setZip(memberRequestDto.getZip());
        oldEntity.setPhone(memberRequestDto.getPhone());
        oldEntity.setInterests(memberRequestDto.getInterests());
    }
}
