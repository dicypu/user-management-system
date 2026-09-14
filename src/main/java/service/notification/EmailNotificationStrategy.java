package service.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String to, String message) {
        log.info("[STRATEGY: EMAIL] {} adresine bildirim iletildi: {}", to, message);
    }

    @Override
    public String getChannelName() {
        return "EMAIL";
    }
}