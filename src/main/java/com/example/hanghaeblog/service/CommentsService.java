package com.example.hanghaeblog.service;

import com.example.hanghaeblog.dto.CommentsRequestDto;
import com.example.hanghaeblog.entity.Comments;
import com.example.hanghaeblog.entity.Posts;
import com.example.hanghaeblog.jwt.JwtUtil;
import com.example.hanghaeblog.repository.CommentsRepository;
import com.example.hanghaeblog.repository.PostRepository;
import com.example.hanghaeblog.repository.UsersRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;
    private final UsersRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public String createComments(Long id, CommentsRequestDto commentsRequestDto, HttpServletRequest request) {
        String username = checkTokenGetName(request);
        if (username.equals("권한이 업내오!") || username.equals("토큰이 고장낫서오!") || username.equals("회원가입 하고 오새오!"))
            return username;
        Posts post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        Comments comments = new Comments(commentsRequestDto,post,username);
        commentsRepository.save(comments);
        return "성공";
    }

    private String checkTokenGetName(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token == null)//노 토큰 노 권한!
            return "권한이 업내오!";
        if (jwtUtil.validateToken(token)) {
            // 토큰에서 사용자 정보 가져오기
            claims = jwtUtil.getUserInfoFromToken(token);
        } else {
            return "토큰이 고장낫서오!";
        }
        try {
            userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
        } catch (IllegalArgumentException e) {
            return "회원가입 하고 오새오!";
        }
        //claims.get("role", UsersEnum.class);
        return claims.getSubject();
    }
}
