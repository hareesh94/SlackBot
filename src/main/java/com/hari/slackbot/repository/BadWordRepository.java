package com.hari.slackbot.repository;

import com.hari.slackbot.models.BadWord;
import org.springframework.data.repository.CrudRepository;

public interface BadWordRepository extends CrudRepository<BadWord,String> {
    Integer countByUser(String userId);

}
