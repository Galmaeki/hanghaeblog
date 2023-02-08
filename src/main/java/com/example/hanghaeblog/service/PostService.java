package com.example.hanghaeblog.service;

import com.example.hanghaeblog.dto.PostDto;
import com.example.hanghaeblog.dto.PostRequestDto;
import com.example.hanghaeblog.entity.Posts;
import com.example.hanghaeblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)//수정이 들어가지 않음
    public List getPosts(){
        List<Posts> lists =postRepository.findAllByOrderByModifiedAtDesc();
        List<PostDto> listdto = new ArrayList<>();
        for (Posts post : lists) {
            listdto.add(new PostDto(post));
        }
        return listdto;
    }

    @Transactional
    public String createPost(PostRequestDto requestDto){
        Posts post = new Posts(requestDto);
        postRepository.save(post);
        return "성공";
    }

    @Transactional
    public String update(Long id, PostRequestDto postRequestDto){
        Posts post = postRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 글이 없음")
        );
        if(!postRequestDto.getPassword().equals(post.getPassword())){
            return "실패";//포스트.겟패스워드를 통해 db에서 조회한 비밀번호와
        }//포스트리퀘스트 dto로 받은 비밀번호가 다를경우 업데이트 전에 메소드가 종료되도록함
        post.update(postRequestDto);
        return "성공";
    }

    @Transactional
    public String delete(Long id,String pw){
        Posts post = postRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 글이 없음")
        );
        if(!pw.equals(post.getPassword())){
            return "실패";//포스트.겟패스워드를 통해 db에서 조회한 비밀번호와
        }
        postRepository.deleteById(id);
        return "성공";
    }

    @Transactional(readOnly = true)
    public List getauthorone(String author){
        List<Posts> list = postRepository.findByAuthor(author);
        List<PostDto> postDto = new ArrayList<>();
        for (Posts post:list) {
            postDto.add(new PostDto(post));
        }
        return postDto;
    }

//    @Transactional(readOnly = true)//수정이 들어가지 않음
//    public List getPosts(){
//        List<Posts> lists =postRepository.findAllByOrderByModifiedAtDesc();
//        List<PostDto> listdto = new ArrayList<>();
//        for (Posts post : lists) {
//            listdto.add(new PostDto(post));
//        }
//        return listdto;
//    }

    @Transactional(readOnly = true)
    public PostDto getidone(Long id){
       Posts post = postRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 글이 없음")
        );
        PostDto postDto = new PostDto(post);
        return postDto;
    }


//    @Transactional(readOnly = true)
//    public Optional<Posts> getidone(Long id){
//        Optional<Posts> optionalExample = postRepository.findById(id);
//        //List<Posts> exampleList = optionalExample.map(Collections::singletonList).orElse(Collections.emptyList());
//        return  optionalExample;
//    }
//    @Transactional(readOnly = true)
//    public List<Posts> getone(Long id){
//       postRepository.findById(id).orElseThrow(
//               ()->new IllegalArgumentException("해당 글이 없음")
//        );
//       return postRepository.findById(id);
//
//    }


}
