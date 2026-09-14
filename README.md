# User Management System

Bu proje, **THINX Yazılım Stajı Programı** kapsamında geliştirilen; SOLID prensiplerine tam uyumlu, katmanlı mimari (
Layered Architecture) desenini benimseyen, Spring Boot 3.2.x, Spring Data JPA, Oracle XE 21c (Docker) ve Swagger/OpenAPI
3 tabanlı kurumsal bir Kullanıcı Yönetim Sistemi projesidir.

---

## 🛠 Teknoloji Yığını (Tech Stack)

* **Dil:** Java 21 (LTS)
* **Framework:** Spring Boot 3.2.5 (Web, Validation, Data JPA)
* **Dokümantasyon & API Sözleşmesi:** Springdoc OpenAPI 3 (Swagger UI 2.5.0)
* **Girdi Doğrulama:** Jakarta Bean Validation (JSR 380)
* **ORM & Veritabanı:** Hibernate 6.4.x / JPA, Oracle Database XE 21c (Dockerized)
* **Bağlantı Havuzu:** HikariCP
* **Derleme Aracı:** Apache Maven
* **Mimari:** Domain-Driven Layered Architecture (IoC & DI, RESTful API, DTO Pattern, Global Exception Handling)
* **Sürüm Kontrolü:** Git & GitHub

---

## 📅 Staj İlerleme Günlüğü

### 🔹 Gün 1: Şirket Tanıtımı, Git ve Proje Ortamı

* `user-management-system` repository'si oluşturuldu ve GitHub bağlantısı sağlandı.
* IntelliJ IDEA üzerinde Maven tabanlı Java projesi iskeleti kuruldu.
* Standart `.gitignore` tanımlamaları yapılarak IDE bağımlılıkları yalıtıldı.

### 🔹 Gün 2: Java Temelleri, JVM Mimarisi ve İlk Domain Modeli

* **JVM Bellek Analizi:** Stack (ilkel tipler, referans adresleri) ve Heap (dinamik nesneler) bellek yönetimi incelendi.
* **Kapsülleme (Encapsulation):** `User` domain modeli oluşturularak alanlar `private` erişim belirleyicisiyle korundu.
* **Wrapper Tipler:** Veritabanı entegrasyonuna hazırlık ve `null` durum desteği için `Long` ve `Boolean` Wrapper
  tipleri kullanıldı.
* **Constructor & Metotlar:** Parametresiz (No-Args) ve parametreli (All-Args) constructor'lar tanımlandı; `this`
  anahtar kelimesiyle sınıf içi atamalar yönetildi.
* **Polymorphism / Override:** `java.lang.Object` sınıfından gelen `toString()` metodu ezilerek okunabilir JSON formatlı
  veri çıktısı sağlandı.

### 🔹 Gün 3: OOP, Kalıtım (Inheritance) ve Çok Biçimlilik (Polymorphism)

* **Kapsülleme & Erişim Belirleyiciler:** `private`, `protected`, `default` ve `public` erişim seviyeleri incelendi;
  hiyerarşi güvenliği kurgulandı.
* **Soyutlama (Abstraction):** Ortak kimlik ve iletişim alanlarını toplayan `Person` soyut üst sınıfı (abstract class)
  modellendi.
* **Kalıtım (Inheritance):** `Employee`, `Customer` ve `Admin` sınıfları `Person` sınıfından türetilerek `super` anahtar
  kelimesiyle yapıcı metot zinciri kuruldu.
* **Polimorfizm (Polymorphism):** `bilgiGoster()` ve `getRol()` metotları alt sınıflarda ezilerek (Override) tek bir üst
  tip referansı üzerinden dinamik çalışma zamanı davranışı sağlandı.

### 🔹 Gün 4: Koleksiyonlar, Hata Yönetimi ve Stream API

* **Collections Framework:** `List`, `Set` ve `Map` arayüzlerinin bellek karmaşıklıkları ($O(1)$ vs $O(n)$) ve kullanım
  senaryoları incelendi.
* **Stream API & Lambda:** `filter`, `map`, `collect` ve `forEach` operasyonlarıyla bildirimsel (declarative) veri
  işleme boru hatları kuruldu.
* **Custom Exception:** Domain bazlı hata izolasyonu için `RuntimeException` tabanlı `UserNotFoundException` sınıfı
  geliştirildi ve `try-catch` bloklarıyla kontrol altına alındı.
* **Generics:** Tip dönüşümü güvenliğini derleme zamanında sağlayan generic yardımcı metot (`CollectionUtils.getFirst`)
  uygulandı.

### 🔹 Gün 5: SOLID Prensipleri ve Tasarım Desenleri (Design Patterns)

* **SOLID İncelemesi:** SRP, OCP, LSP, ISP ve DIP prensipleri kurumsal mimari senaryoları üzerinden özetlendi; kod
  kokuları (code smells) giderildi.
* **Singleton Pattern:** Thread-safe ve lazy initialization destekleyen `AppConfig` konfigürasyon yöneticisi (Bill Pugh
  yaklaşımı) modellendi.
* **Factory Pattern:** Somut sınıf bağımlılıklarını izole ederek `Person` türevlerini (`Employee`, `Customer`, `Admin`)
  dinamik üreten `UserFactory` uygulandı.
* **Builder Pattern:** `User` modeli üzerinde telescoping constructor krizini önleyen ve okunabilir nesne inşası sunan
  `User.Builder` deseni kurgulandı.

### 🔹 Gün 6: Spring Boot Giriş, IoC Konteyneri ve Dependency Injection (DI)

* **Spring Framework & IoC:** Nesne yaşam döngüsünün ve bağımlılık yönetiminin Spring `ApplicationContext` konteynerine
  devredilmesi sağlandı.
* **Constructor Injection:** Değişmezliği (`immutability`) ve test edilebilirliği garanti altına alan `private final`
  tabanlı Constructor Injection mimarisi uygulandı.
* **Stereotype Anotasyonlar:** `@Component`, `@Service`, `@Repository` ve `@RestController` anotasyonlarının mimari
  katmanlardaki sorumlulukları ayrıştırıldı.
* **Konfigürasyon & Ortam:** `application.properties` üzerinden sunucu portu (`server.port=8085`), log seviyeleri ve
  uygulama meta verileri özelleştirilerek Tomcat üzerinde ayağa kaldırıldı.

### 🔹 Gün 7: Katmanlı Mimari (Layered Architecture), DTO Deseni ve REST Uçları

* **Katmanlı Mimari İzolasyonu:** `Controller -> Service -> Repository -> Data` akışı kurularak her katmanın sorumluluğu
  kesin çizgilerle ayrıştırıldı.
* **DTO Deseni & Güvenlik:** Over-posting saldırılarını ve veri sızıntısını önlemek için `UserRequestDto` ve
  `UserResponseDto` sınıfları modellendi; `UserEntity` dış dünyadan tamamen yalıtıldı.
* **Constructor Injection Zinciri:** Controller'dan Repository'ye kadar tüm bağımlılıklar `private final` alanlar
  üzerinden gevşek bağlı (loose coupling) bağlandı.
* **REST Endpoints:** `POST /api/users`, `GET /api/users/{id}` ve `GET /api/users` uç noktaları `ResponseEntity` ve Bean
  Validation (`@Valid`) standartlarıyla ayağa kaldırıldı.

### 🔹 Gün 8: ORM Mantığı, Hibernate/JPA ve Spring Data JPA Repository

* **ORM & Hibernate Mimarisi:** JDBC karmaşıklığını ortadan kaldıran ORM felsefesi ve JPA şartnamesi ile Hibernate
  motorunun çalışma mekanizması analiz edildi.
* **JPA Entity & Mapping:** `UserEntity` sınıfı `@Entity`, `@Table`, `@Id`, `@GeneratedValue` ve `@Column`
  anotasyonlarıyla ilişkisel veritabanı şemasına bağlandı.
* **Spring Data JPA:** `UserRepository` arayüzü `JpaRepository<UserEntity, Long>` ile genişletilerek saf SQL yazmadan
  CRUD operasyonları devreye alındı.
* **Derived Query Methods:** `findByEmail`, `existsByEmail` ve `findByAdContainingIgnoreCase` türetilmiş metotlarıyla
  dinamik SQL üretimi sağlandı; `/api/users/search` ucu ayağa kaldırıldı.

### 🔹 Gün 9: Kurumsal Veritabanı Entegrasyonu (Oracle XE & Docker)

* **Konteyner Mimarisi:** Oracle XE 21c (`gvenzl/oracle-xe:21-slim`) imajı Docker üzerinde `1521` portu ve `XEPDB1`
  Pluggable Database servisiyle izole edildi.
* **JPA & Sequence Stratejisi:** Oracle'ın yerel sıralayıcı mekanizması olan `GenerationType.SEQUENCE` kurgulandı;
  Oracle rezerve anahtar sözcük çakışmalarını önlemek adına tablo adı `users` olarak yapılandırıldı.
* **Uçtan Uca Doğrulama:** Spring Boot üzerinden fırlatılan REST kayıtları hem yerel konteyner CLI aracı `sqlplus` hem
  de Hibernate DDL-Auto izleme logları üzerinden fiziksel olarak doğrulandı.

### 🔹 Gün 10: Kurumsal REST Mimarisi, Bean Validation ve OpenAPI Dokümantasyonu

* **REST & HTTP Durum Kodları:** Standartlara tam uyum sağlandı; `POST` için `201 Created`, `GET/PUT` için `200 OK`,
  `DELETE` için `204 No Content`, hata durumlarında ise `400 Bad Request` ve `404 Not Found` uygulandı.
* **Bean Validation (JSR 380):** DTO seviyesinde `@NotBlank`, `@Size`, `@Email` ve regex tabanlı `@Pattern`
  doğrulamaları devreye alındı; `@Valid` ile controller katmanında tetiklendi.
* **Merkezi Hata Yönetimi:** `@RestControllerAdvice` ile `MethodArgumentNotValidException` ve `UserNotFoundException`
  yakalanarak anlamlı JSON hata yanıtları üretildi.
* **Swagger/OpenAPI 3 Entegrasyonu:** `springdoc-openapi-starter-webmvc-ui` kütüphanesi ile API kontratı belgelendi;
  `/swagger-ui/index.html` üzerinden interaktif dokümantasyon sağlandı.

### 🔹 Gün 11: Frontend Mimarisine Giriş (React 18, Vite, Bileşenler ve State Yönetimi)

* **Vite & Modern SPA İskeleti:** Hızlı derleme ve Native ESM desteği sunan Vite altyapısıyla React 18 projesi ayağa
  kaldırıldı; monorepo mimarisi dahilinde `/frontend` dizinine konumlandırıldı.
* **Fonksiyonel Bileşen (Function Component) Mimarisi:** Tek sorumluluk prensibine (SRP) sadık kalınarak `UserCard`
  bileşeni geliştirildi; arayüz mantığı modüler parçalara ayrıştırıldı.
* **Props ile Tek Yönlü Veri Akışı (Unidirectional Data Flow):** Üst bileşenden (`App.jsx`) alt bileşene (
  `UserCard.jsx`) veri aktarımı salt okunur (read-only) `props` (`ad`, `soyad`, `email`, `telefon`, `durum`) üzerinden
  sağlandı.
* **Reaktif Durum Yönetimi (`useState`):** Değişmezlik (immutability) kurallarına sadık kalınarak kullanıcı listesi
  dinamik state'e bağlandı; durum tersine çevirme (toggle) ve form üzerinden yeni kayıt ekleme fonksiyonları Virtual DOM
  üzerinde sıfır konsol hatasıyla işletildi.

### 🔹 Gün 12: Full-Stack Entegrasyon (Axios, Controlled Components, CORS ve REST Köprüsü)

* **CORS (Cross-Origin Resource Sharing) Protokolü:** Tarayıcıların Same-Origin Policy (SOP) güvenlik kalkanı, Spring
  Boot katmanında `@CrossOrigin(origins = "http://localhost:5173")` anotasyonu ile Controller seviyesinde
  yapılandırıldı; `5173` (React/Vite) ve `8085` (Tomcat/Spring) portları arasındaki HTTP Preflight (`OPTIONS`) ve çapraz
  kaynak veri transferi güvence altına alındı.
* **Axios ile Asenkron İstemci Mimarisi:** Fetch API yerine otomatik JSON serileştirme/ters-serileştirme (
  serialization/deserialization) ve 2xx dışı HTTP durum kodlarını doğrudan `catch` bloklarına yönlendiren Axios HTTP
  istemcisi entegre edildi; Oracle XE veritabanına bağlı REST uçlarına `GET` ve `POST` istekleri bağlandı.
* **Controlled Component Form Disiplini:** Tek Yönlü Veri Akışı (Unidirectional Data Flow) standardına sadık kalınarak
  form alanları (`ad`, `soyad`, `email`, `telefon`) React `useState` kancasıyla kontrollü bileşenler haline getirildi;
  JavaScript *Computed Property Names* mantığıyla tek bir dinamik `handleInputChange` metodu üzerinden durum
  senkronizasyonu sağlandı.
* **Reaktif Veri Çekimi ve Yaşam Döngüsü (`useEffect`):** Bileşenin ilk yüklenme (Mount) anında `useEffect` kancası
  üzerinden veritabanı kayıtları tek seferlik asenkron çağrıyla çekilerek dinamik HTML tablosuna bağlandı; ağ
  gecikmeleri ve olası servis kesintileri için reaktif `loading` ve `error` durum mekanizmaları kurgulandı.
* **Koyu Tema (Dark Mode) ve Arayüz Optimizasyonu:** Kullanıcı deneyimini kurumsal seviyeye taşımak adına WCAG kontrast
  standartlarına uygun koyu tema mimarisi oluşturuldu; tarayıcı zorunlu kontrast (forced colors) çakışmaları
  `:root { color-scheme: dark; }` direktifiyle izole edildi.

### 🔹 Gün 13: Modüler CRUD Mimarisi (Bileşen Ayrıştırma, State ile Edit Modu ve Yaşam Döngüsü)

* **Bileşen Tabanlı Mimari (Component Decomposition):** Tek sorumluluk prensibine (SRP) uygun olarak `App.jsx`
  içerisindeki monolitik yapı parçalandı; form mantığı `UserForm.jsx`, tablo ve eylem butonları `UserList.jsx`, veri ve
  durum orkestrasyonu ise container bileşen olarak `App.jsx` üzerine devredildi.
* **Seçili Durum (Edit Mode) Yönetimi:** Düzenleme akışı `editingUser` state'i üzerinden merkezi olarak kurgulandı;
  `UserList` bileşeninden tetiklenen düzenleme eylemi formu doldurarak `POST` modundan dinamik `PUT` moduna geçiş
  sağladı ve iptal mekanizmasıyla form temizleme izolasyonu korundu.
* **Onay Mekanizmalı Silme Akışı (DELETE):** İstem dışı veri kayıplarını engellemek amacıyla `window.confirm` kalkanı
  ile iki aşamalı doğrulama kuruldu; onaylanan kayıtlar REST API üzerinden `DELETE /api/users/{id}` ucu ile Oracle XE
  veritabanından kalıcı olarak temizlendi.
* **Reaktif Liste Senkronizasyonu:** `POST`, `PUT` ve `DELETE` operasyonlarının ardından arayüzün veritabanı ile tam
  tutarlılıkta kalması adına asenkron `fetchUsers()` tetikleyicisi işletilerek tablo DOM üzerinde kesintisiz güncel
  tutuldu.

### 🔹 Gün 14: Dağıtık Sistem Temelleri ve Konteynerizasyon (Docker & Multi-Stage Build)

* **Monolith vs. Microservice Analizi:** Monolitik mimarinin getirdiği tek nokta arızası (SPOF), yatay ölçekleme
  darboğazları ve sıkı bağlılık (tight-coupling) riskleri analiz edildi; API Gateway (Spring Cloud Gateway), Service
  Discovery (Eureka) ve merkezi Config Server katmanlarının dağıtık topolojideki rolleri belirlendi.
* **Çok Aşamalı Docker Derlemesi (Multi-Stage Build):** Java 21 LTS mimarisine uygun olarak derleme (
  `maven:3.9.6-eclipse-temurin-21`) ve çalışma (`eclipse-temurin:21-jre-jammy`) ortamları birbirinden izole edildi;
  nihai imaj boyutu optimize edilerek saldırı yüzeyi daraltıldı.
* **Konteyner Güvenliği ve Yetki İzolasyonu:** Çalışma aşamasında `root` kullanıcı yerine kısıtlı sistem kullanıcısı (
  `appuser`) tanımlanarak konteyner kaçış (container breakout) güvenlik riskleri minimize edildi.
* **Konteynerler Arası Ağ İletişimi (Docker Bridge Network):** `thinx-network` özel köprü ağı kurularak
  `thinx-backend-app` ile `thinx-oracle-xe` arasındaki haberleşme yerel DNS isim çözümlemesi üzerinden bağlandı;
  veritabanı bağlantısı `SPRING_DATASOURCE_URL` çevre değişkeni ile çalışma anında (runtime) dinamik olarak
  yapılandırıldı.
* **Servis Ayrıştırma Mimarisi:** Mevcut monolitik kullanıcı yönetim sisteminin kurumsal ölçekte Auth/IAM, User Core,
  Notification ve Audit servislerine bölünme şeması tasarlandı.

### 🔹 Gün 15: Kod Tabanı Refactoring, Merkezi İstisna Yönetimi ve Telemetri (SLF4J & Lombok)

* **Davranış Korumalı Refactoring (Clean Code):** Mevcut REST API sözleşmesi ve doğrulama mekanizmaları bozulmadan;
  servis ve denetleyici katmanındaki tekrar eden kod blokları temizlendi, mimari borç (Technical Debt) asgariye
  indirildi.
* **Lombok ile Boilerplate Eliminasyonu:** JPA Entity katmanında `@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`,
  `@AllArgsConstructor` kullanılarak veri bütünlüğü ve Thread-safety korundu (JPA bellek tutarsızlığı ve döngüsel
  bağımlılık riski nedeniyle Entity sınıflarında `@Data` kullanımından kaçınıldı). DTO sınıfları ise saf veri taşıyıcı (
  POJO) formunda derlendi.
* **Jakarta Bean Validation Entegrasyonu:** `UserRequestDto` üzerindeki `@NotBlank`, `@Size`, `@Email` ve `@Pattern`
  kuralları Controller metoduna `@Valid` enjeksiyonu yapılarak güvenceye alındı.
* **Merkezi Hata Yönetimi (`@RestControllerAdvice`):** Dağınık `try-catch` blokları kaldırılarak
  `GlobalExceptionHandler` sınıfı inşa edildi. `UserNotFoundException` (404), `MethodArgumentNotValidException` (400) ve
  genel istisnalar (500) standart `ErrorResponseDto` formatına dönüştürüldü.
* **Endüstri Standardı Loglama (SLF4J/Logback):** Performans kaybına yol açan `System.out.println` çağrıları kaldırıldı;
  parametrik `{}` placeholder yapısıyla çalışan SLF4J loglama altyapısı (INFO, WARN, DEBUG, ERROR) servis ve denetleyici
  katmanlarına entegre edildi.

### 🔹 Gün 16: Clean Code Refactoring, Kod Hijyeni ve Mimari Standardizasyon
* **Ölü Kodların Tasfiyesi (Dead Code Elimination):** Projenin ilk aşamalarından kalan, hiçbir Spring Bean'ine enjekte edilmeyen ve çağrılmayan atıl sınıflar (`Main.java`, `UserFactory.java`, `CollectionUtils.java`) teknik borç oluşturmaması adına projeden tamamen temizlendi.
* **Java Adlandırma Konvansiyonu (Naming Conventions):** Java ve Spring Boot standartlarına aykırı olan büyük harfli paket isimlendirmesi (`package Controller;`) Git seviyesinde iki aşamalı olarak küçük harfe (`package controller;`) çekildi; Windows dosya sisteminin case-insensitive yapısından kaynaklanabilecek çapraz platform derleme riskleri bertaraf edildi.
* **Tek Sorumluluk Prensibi (SRP & SLAP):** `UserService` sınıfı monolitik metot yapısından arındırıldı. Yüksek seviyeli iş akışları ile düşük seviyeli implementasyon detayları (veritabanı varlık doğrulaması, DTO mapleme, alan güncellemeleri) private yardımcı metotlara (`findUserByIdOrThrow`, `validateUserExists`, `updateUserFields`, `buildNewUserEntity`, `mapToDto`) bölünerek Single Level of Abstraction Principle (SLAP) uygulandı.
* **Statik Kod Hijyeni ve Format:** Kullanılmayan import satırları (Unused Imports) temizlendi, girintiler ve kod blokları standart Java formatına hizalanarak bilişsel karmaşıklık (Cognitive Complexity) asgariye indirildi.
---

## 🏗 Mimari Katmanlar ve Sınıf Hiyerarşisi

### 1. DTO & Entity Veri Modeli

| Katman / Sınıf        | Sorumluluk Alanı     | Validasyon / Güvenlik Kriteri                                         |
|:----------------------|:---------------------|:----------------------------------------------------------------------|
| **`UserRequestDto`**  | API İstek Modeli     | `@NotBlank`, `@Size(min=2, max=50)`, `@Email`, `@Pattern(regexp=...)` |
| **`UserResponseDto`** | API Yanıt Modeli     | Hassas/teknik verilerden arındırılmış filtrelenmiş çıktı              |
| **`UserEntity`**      | Veritabanı Yansıması | JPA `@Entity`, `@Table(name = "users")`, `GenerationType.SEQUENCE`    |

### 2. Katmanlı Mimari Bileşenleri

* **`UserController` (`@RestController`):** `/api/users` taban rotasında HTTP isteklerini karşılar, validasyonları (
  `@Valid`) denetler, Swagger anotasyonlarını (`@Tag`, `@Operation`, `@ApiResponse`) barındırır ve HTTP durum kodlarını
  yönetir.
* **`UserService` (`@Service`):** `@Transactional` sınırlarını yönetir, iş kurallarını işletir, Entity-DTO dönüşümlerini
  sağlar.
* **`UserRepository` (`@Repository`):** `JpaRepository` üzerinden Oracle XE veritabanına otomatik SQL sorguları üreterek
  veri erişimini yönetir.
* **`GlobalExceptionHandler` (`@RestControllerAdvice`):** `MethodArgumentNotValidException`, `UserNotFoundException` ve
  genel hataları merkezi olarak yakalayıp standart JSON hata şablonuna dönüştürür.
* **`NotificationSender` (`@Component`):** Sistem içi e-posta ve bildirim operasyonlarını yürüten bağımsız bileşen.

---

## 📋 REST API Uç Nokta Sözleşmesi

| HTTP Metodu | Endpoint          | Başarılı Durum Kodu | Hata Durum Kodları                 | Açıklama                                   |
|-------------|-------------------|---------------------|------------------------------------|--------------------------------------------|
| `POST`      | `/api/users`      | `201 Created`       | `400 Bad Request`                  | Yeni kullanıcı oluşturur                   |
| `GET`       | `/api/users`      | `200 OK`            | `500 Internal Server Error`        | Tüm kullanıcıları listeler                 |
| `GET`       | `/api/users/{id}` | `200 OK`            | `404 Not Found`                    | Belirtilen ID'ye sahip kullanıcıyı getirir |
| `PUT`       | `/api/users/{id}` | `200 OK`            | `400 Bad Request`, `404 Not Found` | Belirtilen kullanıcıyı günceller           |
| `DELETE`    | `/api/users/{id}` | `204 No Content`    | `404 Not Found`                    | Belirtilen kullanıcıyı siler               |

---

## 🚀 Baştan Sona Kurulum ve Çalıştırma Rehberi

Projeyi sıfırdan ayağa kaldırmak için aşağıdaki adımları sırasıyla işletin.

### 1. Adım: Ön Gereksinimler

* **Java:** OpenJDK 21 (LTS)
* **Konteyner Motoru:** Docker Desktop (WSL2 backend veya macOS Hypervisor açık olmalıdır)
* **Derleme Aracı:** Apache Maven 3.9+ (veya proje kök dizinindeki yerleşik `./mvnw`)

---

### 2. Adım: Oracle XE 21c Docker Konteynerini Ayağa Kaldırma

Oracle XE 21c imajını 1521 portunu dışarıya açarak başlatın:

```bash
docker run -d --name thinx-oracle-xe -p 1521:1521 -e ORACLE_PASSWORD=oracle gvenzl/oracle-xe:21-slim
```

Konteynerin başlatılmasını ve veritabanının kullanıma hazır hale gelişini canlı takip edin:

```bash
docker logs -f thinx-oracle-xe
```

> **Önemli Kontrol:** Konsolda **`DATABASE IS READY TO USE!`** çıktısını görene kadar bekleyin. Bu çıktıyı gördükten
> sonra `Ctrl + C` ile log akışını durdurun.

---

### 3. Adım: Veritabanı Konfigürasyonunu Doğrulama

`src/main/resources/application.properties` dosyasının Oracle XE parametrelerini içerdiğinden emin olun:

```properties
server.port=8085
spring.application.name=user-management-system
# Oracle XE Datasource Ayarları (Pluggable DB: XEPDB1)
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=system
spring.datasource.password=oracle
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
# Hibernate & DDL Senkronizasyonu
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

### 4. Adım: Spring Boot Uygulamasını Başlatma

Terminal üzerinden proje kök dizininde şu komutlardan birini çalıştırın:

```bash
# macOS / Linux (Maven Wrapper)
./mvnw spring-boot:run

# Windows PowerShell (Maven Wrapper)
.\mvnw spring-boot:run

# Sistem Maven'ı ile çalıştırma (Tüm platformlar)
mvn spring-boot:run
```

*(Veya IntelliJ IDEA üzerinden `UserManagementSystemApplication.java` sınıfındaki yeşil **Run** butonuna basarak
başlatın).*

Konsolda `Tomcat started on port 8085 (http)` ve `Started UserManagementSystemApplication` satırları çıktığında uygulama
hazırdır.

---

### 5. Adım: Frontend (React / Vite) Arayüzünü Başlatma

Ayrı bir terminal penceresinde `frontend` dizinine geçip geliştirme sunucusunu başlatın:

```powershell
# Frontend dizinine gir
cd frontend

# Bağımlılıkları kur (İlk kurulumda)
npm install

# Geliştirme sunucusunu çalıştır
npm run dev
```

Konsolda `VITE ready in ... ms` ve yerel adres çıktısı görüldüğünde ön yüz hazırdır.

---

### 6. Adım: Servis Arayüzlerine Erişim ve Canlı Dokümantasyon

Tüm servisler ayaktayken tarayıcınızdan şu panellere doğrudan erişebilirsiniz:

* **Kullanıcı Paneli (React UI):** [http://localhost:5173/](http://localhost:5173/)
* **Swagger UI Test Paneli:** [http://localhost:8085/swagger-ui/index.html](http://localhost:8085/swagger-ui/index.html)
* **Ham OpenAPI Şeması (JSON):** [http://localhost:8085/v3/api-docs](http://localhost:8085/v3/api-docs)

---

## 🧪 Uçtan Uca API Doğrulama ve Test Komutları (cURL)

Backend REST servisinin HTTP durum kodlarını ve Bean Validation kalkanlarını doğrulamak için terminalden şu testleri
koşturun:

### 1. Başarılı Kullanıcı Ekleme (`201 Created`):

```bash
curl -X POST http://localhost:8085/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "ad": "Emirhan",
    "soyad": "Yavuz",
    "email": "emirhan@thinx.com",
    "telefon": "+905551234567"
  }'
```

### 2. Bean Validation Hata Kalkanı Testi (`400 Bad Request`):

```bash
curl -X POST http://localhost:8085/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "ad": "",
    "soyad": "A",
    "email": "hatali-email-formati",
    "telefon": "123"
  }'
```

### 3. Kullanıcıları Listeleme (`200 OK`):

```bash
curl -X GET http://localhost:8085/api/users
```

### 4. Olmayan Kullanıcı Sorgulama (`404 Not Found`):

```bash
curl -X GET http://localhost:8085/api/users/99999
```

### 5. Kullanıcı Güncelleme (`200 OK`):

```bash
curl -X PUT http://localhost:8085/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "ad": "Emirhan",
    "soyad": "Yavuz",
    "email": "emirhan.guncel@thinx.com",
    "telefon": "+905559876543"
  }'
```

### 6. Kullanıcı Silme (`204 No Content`):

```bash
curl -X DELETE http://localhost:8085/api/users/1
```

---

### 7. Adım: Veritabanı Doğrulama (Docker `sqlplus`)

Uygulamanın Oracle XE üzerinde fiziksel tablo ve kayıt oluşturduğunu doğrulamak için konteyner CLI aracını çalıştırın:

```bash
docker exec -it thinx-oracle-xe sqlplus system/oracle@XEPDB1
```

SQL satırında doğrulama sorgularını işletin:

```sql
-- Tablonun fiziksel yapısını denetle
DESC users;

-- Eklenen kullanıcıları listele
SELECT id, ad, soyad, email, durum
FROM users;

-- Çıkış
EXIT;
```

---

### 8. Adım: Konteyneri Durdurma ve Temizleme (Opsiyonel)

Çalışma tamamlandığında sistem kaynaklarını serbest bırakmak için:

```bash
# Konteyneri durdurma
docker stop thinx-oracle-xe

# Konteyneri tamamen kaldırma (Gerektiğinde)
docker rm thinx-oracle-xe
```

---

### 6. Adım: Veritabanı Doğrulama (Docker `sqlplus`)

Uygulamanın Oracle üzerinde fiziksel tablo ve veri oluşturduğunu konteyner CLI aracıyla doğrulamak için:

```bash
docker exec -it thinx-oracle-xe sqlplus system/oracle@XEPDB1
```

SQL satırında sorguları çalıştırın:

```sql
-- Tablonun varlığını ve yapısını kontrol et
DESC users;

-- Eklenen kayıtları listele
SELECT id, ad, soyad, email, durum
FROM users;

-- Çıkış
EXIT;
```

---

### 7. Adım: Konteyneri Durdurma ve Temizleme (Opsiyonel)

Çalışmanız bittiğinde Docker kaynaklarını serbest bırakmak için:

```bash
# Konteyneri durdurma
docker stop thinx-oracle-xe

# Konteyneri tamamen kaldırma (Gerektiğinde)
docker rm thinx-oracle-xe
```