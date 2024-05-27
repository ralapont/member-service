package com.example.members.utils;

import com.example.members.entity.MemberEntity;
import com.example.members.model.input.MemberRequestDto;
import com.example.members.model.output.MemberResponseDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuildTestObjects {


    public static List<MemberEntity> buildListEntityMock() {

        List<MemberEntity> members = new ArrayList<>();
        members.add(buildEntityMock().get());
        return members;
    }

    public static Optional<MemberEntity> buildEntityMock() {

        MemberEntity memberEntity = MemberEntity.builder()
                .id(Integer.valueOf(1))
                .lastName("Grogan")
                .firstName("Vladimir")
                .expiration(stringToLocalDate("2004-10-25"))
                .email("vladimir_grogan@earth.com")
                .street("3263 Gilbert Rd.")
                .city("Ithaca")
                .state("NY")
                .zip("99357")
                .phone("332-511-5038")
                .interests("Great Depression,Spanish-American War")
                .build();

        return  Optional.of(memberEntity) ;
    }

    public static Optional<MemberEntity> buildEntityModifyMock() {

        MemberEntity memberEntity = MemberEntity.builder()
                .id(Integer.valueOf(1))
                .lastName("Grogan")
                .firstName("Vladimir")
                .expiration(stringToLocalDate("2004-04-25"))
                .email("vladimir_grogan@server.com")
                .street("3263 Gilbert Rd.")
                .city("Ithaca")
                .state("NY")
                .zip("99358")
                .phone("332-511-5038")
                .interests("Great Depression,Spanish-American War")
                .build();

        return  Optional.of(memberEntity) ;
    }

    public static List<MemberResponseDto> buildListDtoResponse() {
        List<MemberResponseDto> members = new ArrayList<>();
        members.add(buildDtoResponse());
        return members;
    }

    public static MemberResponseDto buildDtoResponse() {
        return  MemberResponseDto.builder()
                .id(Integer.valueOf(1))
                .lastName("Grogan")
                .firstName("Vladimir")
                .expiration(stringToLocalDate("2004-10-25"))
                .email("vladimir_grogan@earth.com")
                .street("3263 Gilbert Rd.")
                .city("Ithaca")
                .state("NY")
                .zip("99357")
                .phone("332-511-5038")
                .interests("Great Depression,Spanish-American War")
                .build();
    }

    public static MemberResponseDto buildDtoModifyResponse() {
        return  MemberResponseDto.builder()
                .id(Integer.valueOf(1))
                .lastName("Grogan")
                .firstName("Vladimir")
                .expiration(stringToLocalDate("2004-04-25"))
                .email("vladimir_grogan@server.com")
                .street("3263 Gilbert Rd.")
                .city("Ithaca")
                .state("NY")
                .zip("99358")
                .phone("332-511-5038")
                .interests("Great Depression,Spanish-American War")
                .build();
    }

    public static MemberRequestDto buildDtoRequest() {
        return  MemberRequestDto.builder()
                .lastName("Grogan")
                .firstName("Vladimir")
                .expiration(stringToLocalDate("2004-10-25"))
                .email("vladimir_grogan@earth.com")
                .street("3263 Gilbert Rd.")
                .city("Ithaca")
                .state("NY")
                .zip("99357")
                .phone("332-511-5038")
                .interests("Great Depression,Spanish-American War")
                .build();
    }



    public static LocalDate stringToLocalDate(String fecha) {

        DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
        return LocalDate.parse(fecha, f);
    }
}
