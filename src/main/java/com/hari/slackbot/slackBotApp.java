package com.hari.slackbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot", "com.hari"})
public class slackBotApp {
    public static void main(String[] args) {
        SpringApplication.run(slackBotApp.class, args);
    }
}
