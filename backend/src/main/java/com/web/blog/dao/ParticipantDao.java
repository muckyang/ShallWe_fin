package com.web.blog.dao;

import java.util.List;

import com.web.blog.model.participant.Participant;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ParticipantDao extends JpaRepository<Participant, String> {

   
    Participant getParticipantByNo(int no);

    Participant getParticipantByUserIdAndArticleId(int userId, int articleId);
    @Transactional(readOnly = true) 
    List<Participant> findParticipantByArticleId(int articleId);
    List<Participant> getParticipantByArticleId(int articleId);
}
