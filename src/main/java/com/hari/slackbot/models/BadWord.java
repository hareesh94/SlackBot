package com.hari.slackbot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

    @Document(collection = "badwords")
    public class BadWord {
        @Id
        String id;
        String user;
        String word;
        Date update = new Date();

        public BadWord(){}

        public BadWord(String user, String word) {
            this.user = user;
            this.word = word;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public Date getUpdate() {
            return update;
        }

        public void setUpdate(Date update) {
            this.update = update;
        }
    }

