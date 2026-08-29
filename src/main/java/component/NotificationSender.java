package component;

import org.springframework.stereotype.Component;

/**
 * Genel amaçlı bildirim bileşeni (@Component).
 * Spring IoC konteyneri bu sınıfı tarayarak bellekte tekil (Singleton) bir Bean olarak başlatır.
 */
@Component
public class NotificationSender {

    public void sendWelcomeNotification(String email, String fullName) {
        System.out.println("[BİLDİRİM BİLEŞENİ] Hoş geldiniz mesajı iletildi -> Alıcı: " + fullName + " <" + email + ">");
    }
}