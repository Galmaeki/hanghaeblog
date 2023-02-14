package com.example.hanghaeblog.entity;

import com.example.hanghaeblog.dto.UsersRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Entity
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "UserId")
    private Long id;

    //@Size(min = 4, max = 10,message = "id길이를 확인해주세요")
    @Pattern(regexp = "[a-z0-9]{4,10}",message = "소문자와 숫자만 가능합니다")
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Pattern(regexp = "[A-Za-z0-9]{8,15}",message = "대소문자와 숫자만 가능합니다")
    private String password;

    public Users(UsersRequestDto usersRequestDto) {
        this.username = usersRequestDto.getUsername();
        this.password = usersRequestDto.getPassword();
    }
}
