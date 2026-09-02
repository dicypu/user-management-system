package entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @SequenceGenerator(
            name = "users_seq_gen",
            sequenceName = "users_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "ad", nullable = false, length = 50)
    private String ad;

    @Column(name = "soyad", nullable = false, length = 50)
    private String soyad;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "telefon", length = 20)
    private String telefon;

    @Column(name = "durum", nullable = false)
    private Boolean durum = true;

    @Column(name = "kayit_tarihi", nullable = false, updatable = false)
    private LocalDateTime tarih;

    public UserEntity() {
    }

    public UserEntity(Long id, String ad, String soyad, String email, String telefon, Boolean durum, LocalDateTime tarih) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefon = telefon;
        this.durum = durum;
        this.tarih = tarih;
    }

    @PrePersist
    protected void onCreate() {
        if (this.tarih == null) {
            this.tarih = LocalDateTime.now();
        }
        if (this.durum == null) {
            this.durum = true;
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    public String getSoyad() { return soyad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public Boolean getDurum() { return durum; }
    public void setDurum(Boolean durum) { this.durum = durum; }
    public LocalDateTime getTarih() { return tarih; }
    public void setTarih(LocalDateTime tarih) { this.tarih = tarih; }
}