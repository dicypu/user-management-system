#  User Management System

Bu proje, **THINX Yazılım Stajı Programı** kapsamında geliştirilen; SOLID prensiplerine uygun, katmanlı mimari (Layered Architecture) desenini benimseyen, Spring Boot, Oracle Database ve React tabanlı kurumsal bir Kullanıcı Yönetim Sistemi projesidir.

---

##  Teknoloji Yığını (Tech Stack)

* **Dil:** Java 21 (LTS)
* **Derleme Aracı:** Apache Maven
* **Mimari:** Domain-Driven Layered Architecture
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

---

## Domain ve Sınıf Hiyerarşisi

### 1. `User` Modeli (Builder Destekli)
| Alan (Field) | Tip | Açıklama |
| :--- | :--- | :--- |
| `id` | `Long` | Benzersiz Kayıt Kimliği (Primary Key) |
| `ad` | `String` | Kullanıcı Adı |
| `soyad` | `String` | Kullanıcı Soyadı |
| `email` | `String` | Benzersiz E-posta Adresi |
| `telefon` | `String` | İletişim Numarası |
| `durum` | `Boolean` | Kullanıcı Durumu (Aktif / Pasif) |
| `tarih` | `LocalDateTime` | Kayıt Oluşturulma Zamanı |

### 2. `Person` Kalıtım Ağacı & Tasarım Desenleri
* **`Person` (Abstract Class):** `id`, `ad`, `soyad`, `email`, `telefon`
  * ↳ **`Employee`:** `departman`, `maas`, `calis()`
  * ↳ **`Customer`:** `musteriNumarasi`, `sadakatPuani`, `siparisVer()`
  * ↳ **`Admin`:** `yetkiSeviyesi`, `kullaniciEngelle()`
* **`AppConfig` (Singleton):** Uygulama genelinde tek instance garantisi.
* **`UserFactory` (Factory):** Rol bazlı `Person` nesnesi üretim merkezi.

---
