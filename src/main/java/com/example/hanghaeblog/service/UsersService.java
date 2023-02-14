package com.example.hanghaeblog.service;

import com.example.hanghaeblog.dto.UsersRequestDto;
import com.example.hanghaeblog.entity.Users;
import com.example.hanghaeblog.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    private void jungbok(String username) {

    }

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
}
