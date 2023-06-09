package com.example.yin.controller;
import com.example.yin.dao.CommentsMapper;
import com.example.yin.domain.Comments;
import com.example.yin.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CommentsMapper commentsMapper;
    @PostMapping("/comment")
    public String addComment(Comments comment){
        int flag = commentsService.addComment(comment);
        if(flag==1)
            return "success";
        return "error";
    }
    @PostMapping("/comment/del")
    public String deleteComment(Comments comments){
        int flag = commentsService.deleteComment(comments);
        if(flag==1)
            return "success";
        return "error";
    }
    @GetMapping("/comment")
    public List<Comments> getAll(@RequestParam("songId")String songId){
        return commentsService.getAll(songId);
    }

    @GetMapping("/comment/alls")
    public List<Comments> getComments(){
        return commentsMapper.getComments();
    }
}
