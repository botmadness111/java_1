package org.example;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @MessageMapping("/webs")
    @SendTo("/topic/messages")
    public Message echoMessage(Message message) {
        return message;
    }
}
