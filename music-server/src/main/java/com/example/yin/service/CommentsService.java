package com.example.yin.service;

import com.example.yin.domain.Comments;

import java.util.List;

public interface CommentsService {
    /**
     * 添加评论
     * @param comments
     * @return
     */
    int addComment(Comments comments);
    /**
     * 删除评论
     * @param comments
     * @return
     */
    int deleteComment(Comments comments);
    /**
     * 获取用户的评论
     * @return
     */
    List<Comments> getAll(String songId);


}
