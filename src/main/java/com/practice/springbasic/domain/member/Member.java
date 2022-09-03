package com.practice.springbasic.domain.member;

import com.practice.springbasic.domain.base.BaseEntity;
import com.practice.springbasic.domain.member.dto.MemberDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @NotNull @NotEmpty @Length(min=4, max=20)
    private String  nickname;
    @NotNull @NotEmpty @Email
    private String  email;
    @NotNull @NotEmpty @Length(min=10, max=20)
    private String  password;

    @Builder
    public Member(String nickname, String email, String password) throws IllegalStateException{
        if(nickname == null || email == null || password == null) {
            throw new IllegalStateException("필수 파라미터가 누락되었습니다!");
        }
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public void memberUpdate(MemberDto memberUpdateDto) {
        this.nickname = memberUpdateDto.getNickname();
        this.email = memberUpdateDto.getEmail();
        this.password = memberUpdateDto.getPassword();
    }

}