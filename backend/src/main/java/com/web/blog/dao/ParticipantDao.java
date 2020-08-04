package com.web.blog.dao;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.participant.Participant;
import com.web.blog.model.post.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantDao extends JpaRepository<Participant, String> {

    Post getPostByArticleId(int articleId);

    Participant getParticipantByNo(int no);

    Optional<Participant> getParticipantByUserIdAndArticleId(int userId, int articleId);

    List<Participant> findParticipantByArticleId(int articleId);
}
