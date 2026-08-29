import config.AppConfig;
import exception.UserNotFoundException;
import factory.UserFactory;
import model.Admin;
import model.Customer;
import model.Employee;
import model.Person;
import model.User;
import util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // 2. Gün Senaryoları (Domain Model & Kapsülleme)
        testGun2UserModel();

        System.out.println("\n==================================================\n");

        // 3. Gün Senaryoları (Kalıtım, Soyutlama ve Polimorfizm)
        testGun3KalitimVePolimorfizm();

        System.out.println("\n==================================================\n");

        // 4. Gün Senaryoları (Collections, Stream API, Generics ve Custom Exception)
        testGun4KoleksiyonlarVeStream();

        System.out.println("\n==================================================\n");

        // 5. Gün Senaryoları (SOLID & Tasarım Desenleri)
        testGun5SolidAndDesignPatterns();
    }

    private static void testGun2UserModel() {
        System.out.println("=== GÜN 2: USER DOMAIN MODELİ VE KAPSÜLLEME TESTİ ===");

        User user1 = new User();
        user1.setId(1L);
        user1.setAd("Emirhan");
        user1.setSoyad("Yavuz");
        user1.setEmail("emirhan.yavuz@gmail.com.tr");
        user1.setTelefon("+90 555 123 45 67");

        User user2 = new User(
                2L,
                "Fatih",
                "Yılmaz",
                "fatih.yilmaz@gmail.com.tr",
                "+90 555 999 88 77",
                false,
                LocalDateTime.now().minusDays(5)
        );

        System.out.println(user1);
        System.out.println(user2);

        user1.setDurum(false);
        System.out.println("1. Kullanıcı Güncel Durumu: " + (user1.getDurum() ? "Aktif" : "Pasif"));
    }

    private static void testGun3KalitimVePolimorfizm() {
        System.out.println("=== GÜN 3: KALITIM VE POLİMORFİZM TESTİ ===");

        Person[] kullanicilar = new Person[3];
        kullanicilar[0] = new Employee(1L, "Fatih", "Yılmaz", "fatih@gmail.com", "+90 555 999 88 77", "Yazılım", 45000.0);
        kullanicilar[1] = new Customer(2L, "Mehmet", "Demir", "mehmet@gmail.com", "+90 555 444 55 66", "CUST-1002", 250);
        kullanicilar[2] = new Admin(3L, "Emirhan", "Yavuz", "emirhan@gmail.com", "+90 555 123 45 67", 5);

        for (Person p : kullanicilar) {
            p.bilgiGoster();
            System.out.println("--------------------------------------------------");
        }

        if (kullanicilar[0] instanceof Employee emp) {
            emp.calis();
        }

        if (kullanicilar[2] instanceof Admin adm) {
            adm.kullaniciEngelle(kullanicilar[1]);
        }
    }

    private static void testGun4KoleksiyonlarVeStream() {
        System.out.println("=== GÜN 4: KOLEKSİYONLAR, STREAM API VE HATA YÖNETİMİ ===");

        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Emirhan", "Yavuz", "emirhan@gmail.com", "+90 555 123 45 67", true, LocalDateTime.now()));
        userList.add(new User(2L, "Fatih", "Yılmaz", "fatih@gmail.com", "+90 555 999 88 77", false, LocalDateTime.now().minusDays(10)));
        userList.add(new User(3L, "Mehmet", "Demir", "mehmet@gmail.com", "+90 555 444 55 66", true, LocalDateTime.now().minusDays(2)));
        userList.add(new User(4L, "Ayşe", "Kaya", "ayse@gmail.com", "+90 555 777 88 88", false, LocalDateTime.now().minusDays(1)));

        System.out.println("\n--- Aktif Kullanıcı İsimleri (Stream API Filtreleme) ---");
        List<String> aktifKullanicilar = userList.stream()
                .filter(u -> Boolean.TRUE.equals(u.getDurum()))
                .map(User::getAd)
                .collect(Collectors.toList());

        aktifKullanicilar.forEach(ad -> System.out.println("Aktif Kullanıcı Adı: " + ad));

        System.out.println("\n--- Generic Metot Testi ---");
        User ilkKullanici = CollectionUtils.getFirst(userList);
        System.out.println("Generic Metot İle Alınan İlk Kullanıcı: " + (ilkKullanici != null ? ilkKullanici.getAd() : "Liste Boş"));

        System.out.println("\n--- Custom Exception (UserNotFoundException) Simülasyonu ---");
        Long arananId = 99L;
        try {
            User bulunanUser = findUserById(userList, arananId);
            System.out.println("Bulunan Kullanıcı: " + bulunanUser);
        } catch (UserNotFoundException ex) {
            System.err.println("[HATA YAKALANDI] " + ex.getMessage());
        }
    }

    private static void testGun5SolidAndDesignPatterns() {
        System.out.println("=== GÜN 5: SOLID VE TASARIM DESENLERİ TESTİ ===");

        // 1. Singleton Testi
        System.out.println("\n--- Singleton Pattern Testi ---");
        AppConfig config1 = AppConfig.getInstance();
        AppConfig config2 = AppConfig.getInstance();
        System.out.println("Uygulama Adı: " + config1.getApplicationName());
        System.out.println("config1 ve config2 aynı bellek adresini mi işaret ediyor? " + (config1 == config2));

        // 2. Factory Testi
        System.out.println("\n--- Factory Pattern Testi ---");
        Person calisan = UserFactory.createPerson(
                UserFactory.UserRole.EMPLOYEE,
                10L, "Fatih", "Yılmaz", "fatih@gmail.com", "+90 555 999 88 77"
        );
        Person musteri = UserFactory.createPerson(
                UserFactory.UserRole.CUSTOMER,
                11L, "Mehmet", "Demir", "mehmet@gmail.com", "+90 555 444 55 66"
        );
        calisan.bilgiGoster();
        musteri.bilgiGoster();

        // 3. Builder Testi
        System.out.println("\n--- Builder Pattern Testi ---");
        User builderUser = new User.Builder()
                .id(100L)
                .ad("Emirhan")
                .soyad("Yavuz")
                .email("emirhan.builder@gmail.com")
                .durum(true)
                .build();
        System.out.println("Builder ile üretilen User: " + builderUser);
    }

    private static User findUserById(List<User> users, Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("ID'si " + id + " olan kullanıcı sistemde bulunamadı!"));
    }
}