# User Management System

Bu proje, **THINX Yazılım Stajı Programı** kapsamında geliştirilen; SOLID prensiplerine uygun, katmanlı mimari (Layered Architecture) desenini benimseyen, Spring Boot, Spring Data JPA ve H2/Oracle Database tabanlı kurumsal bir Kullanıcı Yönetim Sistemi projesidir.

---

##  Teknoloji Yığını (Tech Stack)

* **Dil:** Java 21 (LTS)
* **Framework:** Spring Boot 3.2.x (Web, Validation, Data JPA)
* **ORM & Veritabanı:** Hibernate / JPA, H2 In-Memory Database (Oracle DB Hazırlığı)
* **Derleme Aracı:** Apache Maven
* **Mimari:** Domain-Driven Layered Architecture (IoC & DI, RESTful API, DTO Pattern)
* **Sürüm Kontrolü:** Git & GitHub

---

##  Staj İlerleme Günlüğü

### 🔹 Gün 1: Şirket Tanıtımı, Git ve Proje Ortamı
* `user-management-system` repository'si oluşturuldu ve GitHub bağlantısı sağlandı.
* IntelliJ IDEA üzerinde Maven tabanlı Java projesi iskeleti kuruldu.
* Standart `.gitignore` tanımlamaları yapılarak IDE bağımlılıkları yalıtıldı.

### 🔹 Gün 2: Java Temelleri, JVM Mimarisi ve İlk Domain Modeli
* **JVM Bellek Analizi:** Stack (ilkel tipler, referans adresleri) ve Heap (dinamik nesneler) bellek yönetimi incelendi.
* **Kapsülleme (Encapsulation):** `User` domain modeli oluşturularak alanlar `private` erişim belirleyicisiyle korundu.
* **Wrapper Tipler:** Veritabanı entegrasyonuna hazırlık ve `null` durum desteği için `Long` ve `Boolean` Wrapper tipleri kullanıldı.
* **Constructor & Metotlar:** Parametresiz (No-Args) ve parametreli (All-Args) constructor'lar tanımlandı; `this` anahtar kelimesiyle sınıf içi atamalar yönetildi.
* **Polymorphism / Override:** `java.lang.Object` sınıfından gelen `toString()` metodu ezilerek okunabilir JSON formatlı veri çıktısı sağlandı.

### 🔹 Gün 3: OOP, Kalıtım (Inheritance) ve Çok Biçimlilik (Polymorphism)
* **Kapsülleme & Erişim Belirleyiciler:** `private`, `protected`, `default` ve `public` erişim seviyeleri incelendi; hiyerarşi güvenliği kurgulandı.
* **Soyutlama (Abstraction):** Ortak kimlik ve iletişim alanlarını toplayan `Person` soyut üst sınıfı (abstract class) modellendi.
* **Kalıtım (Inheritance):** `Employee`, `Customer` ve `Admin` sınıfları `Person` sınıfından türetilerek `super` anahtar kelimesiyle yapıcı metot zinciri kuruldu.
* **Polimorfizm (Polymorphism):** `bilgiGoster()` ve `getRol()` metotları alt sınıflarda ezilerek (Override) tek bir üst tip referansı üzerinden dinamik çalışma zamanı davranışı sağlandı.

### 🔹 Gün 4: Koleksiyonlar, Hata Yönetimi ve Stream API
* **Collections Framework:** `List`, `Set` ve `Map` arayüzlerinin bellek karmaşıklıkları ($O(1)$ vs $O(n)$) ve kullanım senaryoları incelendi.
* **Stream API & Lambda:** `filter`, `map`, `collect` ve `forEach` operasyonlarıyla bildirimsel (declarative) veri işleme boru hatları kuruldu.
* **Custom Exception:** Domain bazlı hata izolasyonu için `RuntimeException` tabanlı `UserNotFoundException` sınıfı geliştirildi ve `try-catch` bloklarıyla kontrol altına alındı.
* **Generics:** Tip dönüşümü güvenliğini derleme zamanında sağlayan generic yardımcı metot (`CollectionUtils.getFirst`) uygulandı.

### 🔹 Gün 5: SOLID Prensipleri ve Tasarım Desenleri (Design Patterns)
* **SOLID İncelemesi:** SRP, OCP, LSP, ISP ve DIP prensipleri kurumsal mimari senaryoları üzerinden özetlendi; kod kokuları (code smells) giderildi.
* **Singleton Pattern:** Thread-safe ve lazy initialization destekleyen `AppConfig` konfigürasyon yöneticisi (Bill Pugh yaklaşımı) modellendi.
* **Factory Pattern:** Somut sınıf bağımlılıklarını izole ederek `Person` türevlerini (`Employee`, `Customer`, `Admin`) dinamik üreten `UserFactory` uygulandı.
* **Builder Pattern:** `User` modeli üzerinde telescoping constructor krizini önleyen ve okunabilir nesne inşası sunan `User.Builder` deseni kurgulandı.

### 🔹 Gün 6: Spring Boot Giriş, IoC Konteyneri ve Dependency Injection (DI)
* **Spring Framework & IoC:** Nesne yaşam döngüsünün ve bağımlılık yönetiminin Spring `ApplicationContext` konteynerine devredilmesi sağlandı.
* **Constructor Injection:** Değişmezliği (`immutability`) ve test edilebilirliği garanti altına alan `private final` tabanlı Constructor Injection mimarisi uygulandı.
* **Stereotype Anotasyonlar:** `@Component`, `@Service`, `@Repository` ve `@RestController` anotasyonlarının mimari katmanlardaki sorumlulukları ayrıştırıldı.
* **Konfigürasyon & Ortam:** `application.properties` üzerinden sunucu portu (`server.port=8085`), log seviyeleri ve uygulama meta verileri özelleştirilerek Tomcat üzerinde ayağa kaldırıldı.

### 🔹 Gün 7: Katmanlı Mimari (Layered Architecture), DTO Deseni ve REST Uçları
* **Katmanlı Mimari İzolasyonu:** `Controller -> Service -> Repository -> Data` akışı kurularak her katmanın sorumluluğu kesin çizgilerle ayrıştırıldı.
* **DTO Deseni & Güvenlik:** Over-posting saldırılarını ve veri sızıntısını önlemek için `UserRequestDto` ve `UserResponseDto` sınıfları modellendi; `UserEntity` dış dünyadan tamamen yalıtıldı.
* **Constructor Injection Zinciri:** Controller'dan Repository'ye kadar tüm bağımlılıklar `private final` alanlar üzerinden gevşek bağlı (loose coupling) bağlandı.
* **REST Endpoints:** `POST /api/users`, `GET /api/users/{id}` ve `GET /api/users` uç noktaları `ResponseEntity` ve Bean Validation (`@Valid`) standartlarıyla ayağa kaldırıldı.

### 🔹 Gün 8: ORM Mantığı, Hibernate/JPA ve Spring Data JPA Repository
* **ORM & Hibernate Mimarisi:** JDBC karmaşıklığını ortadan kaldıran ORM felsefesi ve JPA şartnamesi ile Hibernate motorunun çalışma mekanizması analiz edildi.
* **JPA Entity & Mapping:** `UserEntity` sınıfı `@Entity`, `@Table`, `@Id`, `@GeneratedValue` ve `@Column` anotasyonlarıyla ilişkisel veritabanı şemasına bağlandı.
* **Spring Data JPA:** `UserRepository` arayüzü `JpaRepository<UserEntity, Long>` ile genişletilerek saf SQL yazmadan CRUD operasyonları devreye alındı.
* **Derived Query Methods:** `findByEmail`, `existsByEmail` ve `findByAdContainingIgnoreCase` türetilmiş metotlarıyla dinamik SQL üretimi sağlandı; `/api/users/search` ucu ayağa kaldırıldı.

---

##  Mimari Katmanlar ve Sınıf Hiyerarşisi

### 1. DTO & Entity Veri Modeli
| Katman / Sınıf | Sorumluluk Alanı | Validasyon / Güvenlik Kriteri |
| :--- | :--- | :--- |
| **`UserRequestDto`** | API İstek Şablonu | `@NotBlank`, `@Email`, `@Size` ile istemci girdi denetimi |
| **`UserResponseDto`** | API Yanıt Şablonu | Hassas/teknik verilerden arındırılmış filtrelenmiş çıktı |
| **`UserEntity`** | Veritabanı Yansıması | JPA `@Entity`, `@Table(name = "users")` ile Persistence eşlemesi |

### 2. Katmanlı Mimari Bileşenleri
* **`UserController` (`@RestController`):** `/api/users` taban rotasında HTTP isteklerini karşılar, validasyonları denetler ve HTTP durum kodlarını yönetir.
* **`UserService` (`@Service`):** `@Transactional` yönetimini üstlenir, iş kurallarını işletir, Entity-DTO dönüşümlerini sağlar.
* **`UserRepository` (`@Repository`):** `JpaRepository` üzerinden H2/Oracle veritabanına otomatik SQL sorguları üreterek veri erişimini yönetir.
* **`NotificationSender` (`@Component`):** Sistem içi e-posta ve bildirim operasyonlarını yürüten bağımsız bileşen.

---