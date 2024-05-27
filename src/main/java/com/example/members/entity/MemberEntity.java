package com.example.members.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "member")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "member_id")
    private Integer id;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(name = "suffix")
    private String suffix;

    @Column(name = "expiration")
    private LocalDate expiration;

    @Column(name = "email")
    private String email;

    @Column(nullable = false, name = "street")
    private String street;

    @Column(nullable = false, name = "city")
    private String city;

    @Column(nullable = false, name = "state")
    private String state;

    @Column(nullable = false, name = "zip")
    private String zip;

    @Column(name = "phone")
    private String phone;

    @Column(nullable = false, name = "interests")
    private String interests;
}
