package model;

import java.time.LocalDateTime;

/**
 * User sınıfı, sistemdeki bir kullanıcının özelliklerini (state) ve
 * bu özelliklere erişim yöntemlerini (behavior) belirleyen şablondur (Class).
 */
public class User {

    // ==========================================
    // 1. ALANLAR (FIELDS / ATTRIBUTES)
    // ==========================================
    // Encapsulation kuralı gereği tüm alanlar 'private' yapılır.
    // Doğrudan dış erişim engellenir; veri güvenliği Getter/Setter ile sağlanır.

    // Veritabanında henüz oluşmamış kaydın ID'si null olabilmelidir.
    // Bu yüzden ilkel 'long' yerine Wrapper olan 'Long' nesnesi kullanılır.
    // Wrapper: Temel veri tiplerini nesneye dönüştüren özel sınıflardır.
    private Long id;

    private String ad;
    private String soyad;

    // E-posta benzersiz kimlik doğrulayıcıdır (Unique).
    private String email;

    private String telefon;

    // Durum alanı null desteği ve veritabanı esnekliği için 'Boolean' seçildi.
    // True: Aktif, False: Pasif anlamına gelir.
    private Boolean durum;

    // Kullanıcının sisteme kayıt anını tutan zaman damgası.
    private LocalDateTime tarih;


    // ==========================================
    // 2. YAPICI METOTLAR (CONSTRUCTORS)
    // ==========================================

    /**
     * Parametresiz Yapıcı Metot (No-Args Constructor)
     * Nesne 'new User()' şeklinde hiçbir değer verilmeden üretildiğinde çalışır.
     * Framework'ler (Hibernate, Spring Boot) reflection ile nesneyi belleğe çıkarırken bu metoda ihtiyaç duyar.
     */
    public User() {
        // Yeni bir kullanıcı oluşturulduğunda varsayılan olarak AKTİF kabul ediyoruz.
        this.durum = true;
        // Kayıt tarihini nesnenin bellekte üretildiği anın zamanı olarak atıyoruz.
        this.tarih = LocalDateTime.now();
    }

    /**
     * Parametreli Yapıcı Metot (All-Args Constructor)
     * Nesne oluşturulurken tüm alanların tek seferde doldurulmasını sağlar.
     *
     * 'this' anahtar kelimesi:
     * Metoda gelen parametre adı (ör: ad) ile sınıfın kendi alanı (ad) çakıştığında,
     * 'this.ad' diyerek "bu sınıfın nesne alanını" işaret ederiz.
     */
    public User(Long id, String ad, String soyad, String email, String telefon, Boolean durum, LocalDateTime tarih) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefon = telefon;
        this.durum = durum;
        this.tarih = tarih;
    }

    /**
     * Builder Üzerinden Nesne Üretimini Sağlayan Private Constructor
     * Dış dünyadan doğrudan çağrılamaz; yalnızca User.Builder sınıfının build() metodu tarafından tetiklenir.
     * Builder nesnesindeki tüm değerleri User nesnesine aktarır ve varsayılan fallback atamalarını yapar.
     */
    private User(Builder builder) {
        this.id = builder.id;
        this.ad = builder.ad;
        this.soyad = builder.soyad;
        this.email = builder.email;
        this.telefon = builder.telefon;
        this.durum = builder.durum != null ? builder.durum : true;
        this.tarih = builder.tarih != null ? builder.tarih : LocalDateTime.now();
    }


    // ==========================================
    // 3. BUILDER DESENİ (STATİK İÇ SINIF)
    // ==========================================

    /**
     * Builder Pattern: Karmaşık parametreli nesnelerin adım adım, okunabilir ve
     * parametre sırası karmaşasına (telescoping constructor) girmeden üretilmesini sağlar.
     * Sınıfın 'static' tanımlanması, dışarıdan User nesnesi üretmeden 'new User.Builder()' çağrısı yapabilmeyi sağlar.
     */
    public static class Builder {
        private Long id;
        private String ad;
        private String soyad;
        private String email;
        private String telefon;
        private Boolean durum;
        private LocalDateTime tarih;

        // 'return this;' zincirleme metot çağrısını (Method Chaining / Fluent API) mümkün kılar.
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder ad(String ad) {
            this.ad = ad;
            return this;
        }

        public Builder soyad(String soyad) {
            this.soyad = soyad;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefon(String telefon) {
            this.telefon = telefon;
            return this;
        }

        public Builder durum(Boolean durum) {
            this.durum = durum;
            return this;
        }

        public Builder tarih(LocalDateTime tarih) {
            this.tarih = tarih;
            return this;
        }

        /**
         * Toplanan tüm alanlarla asıl User nesnesini inşa eden sonlandırıcı metot.
         */
        public User build() {
            return new User(this);
        }
    }


    // ==========================================
    // 4. ERİŞİM VE ATAMA METOTLARI (GETTERS & SETTERS)
    // ==========================================

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return this.ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return this.soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Boolean getDurum() {
        return this.durum;
    }

    public void setDurum(Boolean durum) {
        this.durum = durum;
    }

    public LocalDateTime getTarih() {
        return this.tarih;
    }

    public void setTarih(LocalDateTime tarih) {
        this.tarih = tarih;
    }


    // ==========================================
    // 5. TOSTRING METODU
    // ==========================================

    /**
     * java.lang.Object sınıfından gelen varsayılan toString() metodunu ezeriz (@Override).
     * Nesne System.out.println(user) ile yazdırıldığında anlamsız bellek adresi yerine
     * okunabilir JSON benzeri bir metin çıktısı üretir.
     */
    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", durum=" + (durum != null && durum ? "Aktif" : "Pasif") +
                ", tarih=" + tarih +
                '}';
    }
}