package com.example.yin.dao;

import com.example.yin.domain.Comments;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsMapper {
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
    /**
     * 删除评论
     * @param songId
     * @return
     */
    int deleteCommentById(Integer songId);

    /**
     * 获取用户的评论
     * @return
     */
    List<Comments> getComments();
}
