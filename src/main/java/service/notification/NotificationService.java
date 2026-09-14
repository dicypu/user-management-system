package service.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NotificationService {

    private final Map<String, NotificationStrategy> strategies;

    public NotificationService(List<NotificationStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(NotificationStrategy::getChannelName, Function.identity()));
    }

    public void dispatch(String channel, String to, String message) {
        NotificationStrategy strategy = strategies.get(channel.toUpperCase());
        if (strategy == null) {
            log.warn("Desteklenmeyen bildirim kanalı: {}", channel);
            return;
        }
        strategy.sendNotification(to, message);
    }
}