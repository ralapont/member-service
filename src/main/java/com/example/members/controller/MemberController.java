package com.example.members.controller;

import com.example.members.feign.dtos.State;
import com.example.members.model.input.MemberRequestDto;
import com.example.members.model.output.MemberResponseDto;
import com.example.members.service.impl.MemberServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private MemberServiceImpl memberService;

    @GetMapping("/members/demo")
    public ResponseEntity<List<State>> demoFeign() {

        return ResponseEntity.ok(memberService.getStates());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Integer id) {

        return ResponseEntity.ok(memberService.getMemberById(id));
    }


    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAllMembres() {

        return ResponseEntity.ok(memberService.getAllMembers());
    }


    @PostMapping("/members")
    public ResponseEntity<MemberResponseDto> saveMember(@Valid @RequestBody MemberRequestDto memberRequestDto) {

        MemberResponseDto responseEntity = memberService.saveMember(memberRequestDto);
        java.net.URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseEntity.getId()).toUri();
        return ResponseEntity.created(location).body(responseEntity);

    }

    @PutMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Integer id, @Valid @RequestBody MemberRequestDto memberRequestDto) {

        return ResponseEntity.ok(memberService.updateMember(id, memberRequestDto));
    }

    @DeleteMapping("/members/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
    }
}
