package com.example.yin.service.impl;
import com.example.yin.dao.CommentsMapper;
import com.example.yin.domain.Comments;
import com.example.yin.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Override
    public int addComment(Comments comments) {
        return commentsMapper.addComment(comments);
    }
    @Override
    public int deleteComment(Comments comments) {
        return commentsMapper.deleteComment(comments);
    }
    @Override
    public List<Comments> getAll(String songId) {
        return commentsMapper.getAll(songId);
    }
}
