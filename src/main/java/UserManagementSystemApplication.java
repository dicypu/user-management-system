import model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import service.UserManagerService;

import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan(basePackages = {"component", "service", "model", "config", "exception", "util"})
public class UserManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementSystemApplication.class, args);
    }

    /**
     * CommandLineRunner: Spring Boot konteyneri tamamen ayağa kalktıktan sonra
     * IoC ve Dependency Injection yapısını test etmek için otomatik çalışan metot.
     */
    @Bean
    public CommandLineRunner run(UserManagerService userManagerService) {
        return args -> {
            System.out.println("\n==================================================");
            System.out.println("=== GÜN 6: SPRING BOOT IOC & DI DOĞRULAMA ===");
            System.out.println("==================================================");

            User testUser = new User.Builder()
                    .id(1L)
                    .ad("Emirhan")
                    .soyad("Yavuz")
                    .email("emirhan.yavuz@thinx.com.tr")
                    .durum(true)
                    .tarih(LocalDateTime.now())
                    .build();

            userManagerService.registerUser(testUser);
            System.out.println("==================================================\n");
        };
    }
}