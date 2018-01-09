package com.hari.slackbot.bot;

import com.hari.slackbot.models.BadWord;
import com.hari.slackbot.repository.BadWordRepository;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.regex.Matcher;

@Component
public class slackbot extends Bot {
    @Autowired
    BadWordRepository badWordRepository;

    private static final Logger logger = LoggerFactory.getLogger(slackbot.class);
    @Value("{$slackBotToken}")
    private String slackToken;

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    public Bot getSlackBot() {
        return null;
    }

    public void setSlackToken(String slackToken) {
        this.slackToken = slackToken;
    }

    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE})
    public void onReceiveDM(WebSocketSession session, Event event) {
        reply(session, event, new Message("Hi, I am " + slackService.getCurrentUser().getName()));
    }

    @Controller(events = EventType.MESSAGE, pattern = "lazyfellow|stupid|rascal")
    public void onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) {
            if(!matcher.group(0).isEmpty()) {
                BadWord badword = new BadWord(event.getUserId(), matcher.group(0));
                badWordRepository.save(badword);
                Integer countBadWords = badWordRepository.countByUser(event.getUserId());
                if (countBadWords >= 10) {
                    reply(session, event, new Message("Using so many bad words"));
                } else {
                    reply(session, event, new Message("You have used badwords " + countBadWords + " times"));
                }
            }

    }
}


