package event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import service.notification.NotificationService;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventListener {

    private final NotificationService notificationService;

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        log.info("[OBSERVER] Yeni kullanıcı olayı yakalandı: {}", event.getUser().getEmail());
        notificationService.dispatch("EMAIL", event.getUser().getEmail(), "Aramıza hoş geldiniz!");
    }
}