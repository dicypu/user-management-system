package dto;

import java.time.LocalDateTime;

/**
 * İstemciye (Frontend / Mobile) sunulan filtrelenmiş ve güvenli veri şablonu.
 */
public class UserResponseDto {

    private Long id;
    private String ad;
    private String soyad;
    private String email;
    private String telefon;
    private Boolean durum;
    private LocalDateTime tarih;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String ad, String soyad, String email, String telefon, Boolean durum, LocalDateTime tarih) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefon = telefon;
        this.durum = durum;
        this.tarih = tarih;
    }

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