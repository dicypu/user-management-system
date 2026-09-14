package service.notification;

public interface NotificationStrategy {
    void sendNotification(String to, String message);
    String getChannelName(); // "EMAIL", "SMS"
}