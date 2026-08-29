package service;

import component.NotificationSender;
import model.User;
import org.springframework.stereotype.Service;

/**
 * İş mantığı katmanı (@Service).
 * Constructor Injection kullanılarak NotificationSender bileşenine 'private final' üzerinden bağlanmıştır.
 */
@Service
public class UserManagerService {

    private final NotificationSender notificationSender;

    // Constructor Injection: Spring Container NotificationSender bean'ini buraya otomatik enjekte eder.
    public UserManagerService(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
        System.out.println("[UserManagerService] Constructor Injection ile NotificationSender başarıyla bağlandı.");
    }

    public void registerUser(User user) {
        System.out.println("[İŞ MANTIĞI] Kullanıcı kayıt işlemi yürütülüyor: " + user.getAd() + " " + user.getSoyad());
        // Enjekte edilen bileşenin metodunu tetikle
        notificationSender.sendWelcomeNotification(user.getEmail(), user.getAd() + " " + user.getSoyad());
    }
}