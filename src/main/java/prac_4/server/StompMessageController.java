import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StompMessageController {

    // Принимает сообщения от клиента по /app/sendMessage
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages") // Отправляет всем подписчикам на /topic/messages
    public String handleClientMessage(String message) {
        System.out.println("Сообщение от клиента: " + message);
        return "Ответ от сервера: " + message;
    }
}
