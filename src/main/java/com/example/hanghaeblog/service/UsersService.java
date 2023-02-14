package com.example.hanghaeblog.service;

import com.example.hanghaeblog.dto.UsersRequestDto;
import com.example.hanghaeblog.entity.Users;
import com.example.hanghaeblog.jwt.JwtUtil;
import com.example.hanghaeblog.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public String join(UsersRequestDto usersRequestDto) {
        String username = usersRequestDto.getUsername();
        try{usersRepository.findByUsername(username)
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 이름");
                });

        Users user = new Users(usersRequestDto);
        usersRepository.save(user);
        return "성공";}
        catch(IllegalArgumentException e){
            return "중복된 이름입니다.";
        }
    }

    @Transactional(readOnly = true)
    public String login(UsersRequestDto usersRequestDto, HttpServletResponse response) {
        String name = usersRequestDto.getUsername();
        String password = usersRequestDto.getPassword();
        try{
        Users user = usersRepository.findByUsername(name).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        try{
            if(!user.getPassword().equals(password)){
                throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
            response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
            return "성공";
        }catch (IllegalArgumentException e){
            return "비밀번호를 확인해 주세요";
        }
        }catch(IllegalArgumentException e){
            return "등록된 사용자가 아닙니다";
        }


    }
}
